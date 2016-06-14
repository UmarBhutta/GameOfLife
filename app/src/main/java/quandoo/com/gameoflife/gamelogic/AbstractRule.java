package quandoo.com.gameoflife.gamelogic;

import java.util.ArrayList;

import quandoo.com.gameoflife.ui.customviews.BaseCellView;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public abstract class AbstractRule {

    protected AbstractRule nextRule;

    public void setNextRule(AbstractRule nextRule) {
        this.nextRule = nextRule;
    }

    public void applyRule(BaseCellView cell, ArrayList<BaseCellView> neighbours) {

        if (nextRule != null) {
            nextRule.applyRule(cell, neighbours);
        }
    }
}
