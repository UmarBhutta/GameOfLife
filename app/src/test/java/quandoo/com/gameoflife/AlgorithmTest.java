package quandoo.com.gameoflife;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import quandoo.com.gameoflife.enums.LifeState;
import quandoo.com.gameoflife.gamelogic.GameLife;
import quandoo.com.gameoflife.ui.customviews.CellView;

/**
 * Unit Test for the Algorithm.
 */

@RunWith(MockitoJUnitRunner.class)
public class AlgorithmTest {
    @Mock
    Context mMockContext;

    @Test
    public void algoTest() throws InterruptedException{
        int gridDimension = 20 ; // 20X20 dimension grid
        GameLife gameLife = GameLife.getInstance(gridDimension,gridDimension);
        for(int j = 0; j<gridDimension;j++){
            for(int l = 0; l<gridDimension;l++){
                CellView cellView = new CellView(mMockContext, j, l,true);
                gameLife.addCell(cellView);
            }
        }
        int row = new Random().nextInt(gridDimension - 2) + 2;
        int column = new Random().nextInt(gridDimension - 2);

        gameLife.borned(row,column);
        gameLife.borned(row,column+1);
        gameLife.borned(row,column+2);
        StringBuilder sb = new StringBuilder();
        gameLife.start();
        Thread.sleep(2000);
        gameLife.stop();
        for (int i = 0; i < gridDimension; i++) {
            sb.append("\n");
            for (int k = 0; k < gridDimension; k++) {
                sb.append(i == row && k == column ? "|" : "").append(gameLife.getCells()[i][k].getLifeState() == LifeState.ALIVE).append(i == row && k == column ? "| " : " ");
            }
        }
        boolean check = (gameLife.getCells()[row][column].getLifeState() == LifeState.ALIVE && gameLife.getCells()[row][column+1].getLifeState() == LifeState.ALIVE && gameLife.getCells()[row][column+2].getLifeState() == LifeState.ALIVE) ||
                (gameLife.getCells()[row][column + 1].getLifeState() == LifeState.ALIVE && gameLife.getCells()[row-1][column+1].getLifeState() == LifeState.ALIVE && gameLife.getCells()[row+1][column+1].getLifeState() == LifeState.ALIVE);
        if (!check)
            System.out.println(sb);
    }

}