package quandoo.com.gameoflife.gamelogic;

import java.util.ArrayList;

import quandoo.com.gameoflife.enums.Evolution;
import quandoo.com.gameoflife.ui.customviews.BaseCellView;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public class NextGenerationRule extends AbstractRule {

    @Override
    public void applyRule(BaseCellView cell, ArrayList<BaseCellView> neighbours) {
        int newBornNeighbourCount = 0;
        switch (cell.getLifeState()) {
            case ALIVE:
                for (BaseCellView nCell : neighbours) {
                    switch (nCell.getLifeState()) {
                        case ALIVE:
                            newBornNeighbourCount++;
                            break;
                    }
                }

                if (newBornNeighbourCount >= 2 && newBornNeighbourCount < 4) {
                    cell.setNexLifeStep(Evolution.REMAIN_UNCHANGED);
                }
                break;
        }

        super.applyRule(cell, neighbours);

    }
}
