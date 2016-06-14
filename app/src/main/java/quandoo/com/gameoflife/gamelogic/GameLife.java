package quandoo.com.gameoflife.gamelogic;


import android.support.v4.util.ArrayMap;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

import quandoo.com.gameoflife.enums.Action;
import quandoo.com.gameoflife.enums.LifeState;
import quandoo.com.gameoflife.ui.customviews.BaseCellView;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public class GameLife {

    public static int count;

    private static GameLife instance = null;
    private final int cellColumns;
    private final int cellRows;


    AbstractRule ruleOne = new UnderPopulation();
    AbstractRule ruleTwo = new NextGenerationRule();
    AbstractRule ruleThree = new OverCrowdedRule();
    AbstractRule ruleFour = new Reproduction();

    ArrayMap<BaseCellView ,ArrayList<BaseCellView>> neighbours;

    private BaseCellView[][] cells;
    private ArrayMap<BaseCellView,BaseCellView> cellsMap;
    private Action actionState;

    ScheduledExecutorService scheduledExecutorServiceStart = Executors.newSingleThreadScheduledExecutor();
    Future futureStart;

    Runnable runnable_start = new Runnable() {
        public void run() {

            while (actionState == Action.RUNNING) {

                count++;
                for (Map.Entry<BaseCellView, BaseCellView> entry : getCellMap().entrySet()) {
                    BaseCellView cellViewIter = entry.getKey();

                    final int col = cellViewIter.getColumn();
                    final int row = cellViewIter.getRow();

                    // Add Neighbours
                    if (getNeighbours().get(cellViewIter) == null) {
                        ArrayList<BaseCellView> tCellList = new ArrayList<>();

                        BaseCellView n1 = getNeighbour(col - 1, row - 1);
                        if (n1 != null) {
                            tCellList.add(n1);
                        }

                        BaseCellView n2 = getNeighbour(col, row - 1);
                        if (n2 != null) {
                            tCellList.add(n2);
                        }

                        BaseCellView n3 = getNeighbour(col + 1, row - 1);
                        if (n3 != null) {
                            tCellList.add(n3);
                        }

                        BaseCellView n4 = getNeighbour(col - 1, row);
                        if (n4 != null) {
                            tCellList.add(n4);
                        }

                        BaseCellView n5 = getNeighbour(col + 1, row);
                        if (n5 != null) {
                            tCellList.add(n5);
                        }

                        BaseCellView n6 = getNeighbour(col - 1, row + 1);
                        if (n6 != null) {
                            tCellList.add(n6);
                        }

                        BaseCellView n7 = getNeighbour(col, row + 1);
                        if (n7 != null) {
                            tCellList.add(n7);
                        }

                        BaseCellView n8 = getNeighbour(col + 1, row + 1);
                        if (n8 != null) {
                            tCellList.add(n8);
                        }
                        getNeighbours().put(cellViewIter, tCellList);
                    }

                    ruleOne.applyRule(cellViewIter, getNeighbours().get(cellViewIter));

                }



                for (Map.Entry<BaseCellView, BaseCellView> entry : cellsMap.entrySet()) {

                    entry.getKey().willIsNow();
                }

            }
        }
    };

    private GameLife(int cellColumns, int cellRows) {
        this.cellColumns = cellColumns;
        this.cellRows = cellRows;
        cells = new BaseCellView[cellColumns][cellRows];
        cellsMap = new ArrayMap<>(cellColumns*cellRows);
        neighbours = new ArrayMap<>();

        ruleOne.setNextRule(ruleTwo);
        ruleTwo.setNextRule(ruleThree);
        ruleThree.setNextRule(ruleFour);
    }
    
    public static GameLife getInstance(int cellColumns,int cellRows){
        if (instance == null){
            instance = new GameLife(cellColumns,cellRows);
        }
        return instance;
    }

    public void addCell(BaseCellView cellView) {
        getCellMap().put(cellView, cellView);
        getCells()[cellView.getColumn()][cellView.getRow()] = cellView;
    }

    public void update(BaseCellView cellView) {

        if (cellView == null) return;
        if (cellsMap == null) return;

        BaseCellView keyCell = getCellMap().get(cellView);


    }

    public synchronized void start() {

        actionState = Action.RUNNING;

        if (futureStart != null) {
            if (!futureStart.isCancelled()) {
                futureStart.cancel(true);
            }
        }

        futureStart = scheduledExecutorServiceStart.submit(runnable_start);

    }

    private BaseCellView getNeighbour(int c, int r) {
        try {
            return getCells()[c][r];
        } catch (ArrayIndexOutOfBoundsException e) {
            // donothing
        }
        return null;
    }

    public synchronized void stop() {
       
        actionState = Action.STOP;
        if (!futureStart.isCancelled() && !futureStart.isDone()) {
            futureStart.cancel(true);
        }

    }

    public BaseCellView[][] getCells() {

        if (cells == null) {
            cells = new BaseCellView[cellColumns][cellRows];
        }
        return cells;
    }

    public void borned(int i, int j) {
        getCells()[i][j].setLifeStep(LifeState.ALIVE);
    }

    public void pause() {
        if (futureStart != null && !futureStart.isCancelled() && !futureStart.isDone()) {
            futureStart.cancel(true);
        }

        neighbours = null;
        cellsMap = null;
        cells = null;
    }

    public ArrayMap<BaseCellView, ArrayList<BaseCellView>> getNeighbours() {
        if (neighbours == null) {
            neighbours = new ArrayMap<>();
        }
        return neighbours;
    }

    public ArrayMap<BaseCellView, BaseCellView> getCellMap() {
        if (cellsMap == null) {
            cellsMap = new ArrayMap<>(cellColumns * cellRows);
        }
        return cellsMap;
    }
    
    
}
