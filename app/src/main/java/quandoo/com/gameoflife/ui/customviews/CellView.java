package quandoo.com.gameoflife.ui.customviews;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import quandoo.com.gameoflife.enums.Evolution;
import quandoo.com.gameoflife.enums.LifeState;
import quandoo.com.gameoflife.observer.CellViewObserver;
import quandoo.com.gameoflife.observer.CellViewObserverImp;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public class CellView extends View implements
        BaseCellView,
        CellViewObserverImp,
        View.OnClickListener{

    private final int hash = 1000;
    public final int row;
    public final int column;
    private boolean isTest;

    private byte neighbour;
    List<CellViewObserver> observers = new ArrayList<>();
    LifeState lifeState = LifeState.DEAD;
    private int cellColor ;
    private int newBornCellColor ;
    private Evolution nextLifeState = Evolution.WILL_DIE;


    public CellView(Context context,int row,int column) {
        super(context);
        this.row = row;
        this.column = column;
        this.neighbour = 0;   //starting no neighbours
        this.setOnClickListener(this);
        lifeState = LifeState.DEAD;
        isTest = false;
    }
    public CellView(Context context,int row,int column,boolean test) {
        super(context);
        this.row = row;
        this.column = column;
        this.neighbour = 0;   //starting no neighbours
        lifeState = LifeState.DEAD;
        isTest = test;
    }
    public boolean equals(Object o) {
        if (!(o instanceof CellView))
            return false;
        return column == ((CellView) o).column && row == ((CellView) o).row;
    }


    public int hashCode() {
        return hash * row + column;
    }


    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public LifeState getLifeState() {
        return lifeState;
    }

    @Override
    public void setNexLifeStep(Evolution willBeDead) {
        this.nextLifeState = willBeDead;
    }

    @Override
    public Evolution getNewLifeState() {
        return nextLifeState;
    }

    @Override
    public void willIsNow() {
        if (getNewLifeState() != null) {
            switch (getNewLifeState()) {
                case WILL_DIE:
                    lifeState = LifeState.DEAD;
                    if(!isTest)
                    post(new Runnable() {
                        @Override
                        public void run() {
                            setBackgroundColor(cellColor);
                        }
                    });
                    break;
                case WILL_BORN_NEW:
                    lifeState = LifeState.ALIVE;
                    if(!isTest)
                    post(new Runnable() {
                        @Override
                        public void run() {
                            setBackgroundColor(newBornCellColor);
                        }
                    });
                    break;
                case REMAIN_UNCHANGED:
                    break;
            }
        }

    }


    @Override
    public void setLifeStep(LifeState born) {
        this.lifeState = born;
        switch (born) {
            case ALIVE:
                if(!isTest)
                setBackgroundColor(newBornCellColor);
                break;
            case DEAD:
                if(!isTest)
                setBackgroundColor(cellColor);
                break;
        }
    }

    @Override
    public void setBornColor(int color) {
        newBornCellColor = color;
    }

    @Override
    public void setState(LifeState lifeState) {
        this.lifeState = lifeState;
        notifyAllObservers(lifeState);
    }

    @Override
    public void attach(CellViewObserver observer) {

        observers.add(observer);

    }

    @Override
    public void notifyAllObservers(LifeState lifeState) {
            for(CellViewObserver observer:observers){
                observer.update(this);
            }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
            switch (lifeState){
                case ALIVE:
                    lifeState = LifeState.DEAD;
                    setBackgroundColor(cellColor);
                    break;
                case DEAD:
                    lifeState = LifeState.ALIVE;
                    setBackgroundColor(newBornCellColor);
                    break;
            }
        setState(lifeState);
    }

    public void setColor(int cellColor) {
        this.cellColor = cellColor;
        setBackgroundColor(cellColor);
    }
}
