package quandoo.com.gameoflife.ui.presenter;

import quandoo.com.gameoflife.observer.ActionObserverImp;
import quandoo.com.gameoflife.ui.interactors.InteractionImp;
import quandoo.com.gameoflife.ui.viewconfigs.GridConfig;
import quandoo.com.gameoflife.utils.ScreenUtils;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public interface GameFragmentPresenter extends BasePresenter {

    void setName(String s);

    void setActionObserverImp(ActionObserverImp actionObserverImp);

    ActionObserverImp getActionObserverImp();

    void setScreenUtils(ScreenUtils screenUtils);

    GridConfig getGridConfig();

    InteractionImp getInteractionImp();

    String getCellStatesJson();

}
