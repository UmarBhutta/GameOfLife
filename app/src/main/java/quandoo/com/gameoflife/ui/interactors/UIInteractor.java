package quandoo.com.gameoflife.ui.interactors;

import quandoo.com.gameoflife.enums.Action;
import quandoo.com.gameoflife.gamelogic.GameLife;
import quandoo.com.gameoflife.observer.ActionObserverImp;
import quandoo.com.gameoflife.ui.customviews.BaseCellView;

/**
 * Created by malikumarbhutta on 6/12/16.
 */
public class UIInteractor implements InteractionImp {


    private GameLife gameLife;
    private ActionObserverImp actionObserverImp;

    public UIInteractor(int cols,int row) {
        this.gameLife = GameLife.getInstance(cols,row);
    }

    @Override
    public void addCellView(BaseCellView cellView) {
        gameLife.addCell(cellView);
    }

    @Override
    public void update(BaseCellView cellView) {
        gameLife.update(cellView);
    }

    @Override
    public void update(Action action) {
        switch (action) {
            case RUNNING:
                gameLife.start();
                break;
            case STOP:
                gameLife.stop();
                break;
        }
    }

    @Override
    public void setImp(ActionObserverImp imp) {
        this.actionObserverImp = imp;
    }

    @Override
    public BaseCellView[][] getCells() {
        return gameLife.getCells();
    }

    @Override
    public void newBorn(int i, int j) {
        gameLife.borned(i,j);
    }

    @Override
    public void pause() {
        gameLife.pause();
    }

    @Override
    public void clear() {
        gameLife.pause();
    }
}
