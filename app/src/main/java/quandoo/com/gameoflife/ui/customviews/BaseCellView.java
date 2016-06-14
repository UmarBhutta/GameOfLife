package quandoo.com.gameoflife.ui.customviews;

import quandoo.com.gameoflife.enums.Evolution;
import quandoo.com.gameoflife.enums.LifeState;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public interface BaseCellView {
    int getRow();

    int getColumn();

    LifeState getLifeState();

    void setNexLifeStep(Evolution willBeDead);

    Evolution getNewLifeState();

    void willIsNow();

    void setLifeStep(LifeState born);

    void setBornColor(int color);
}
