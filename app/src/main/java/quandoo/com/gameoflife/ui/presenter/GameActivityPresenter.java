package quandoo.com.gameoflife.ui.presenter;

import quandoo.com.gameoflife.enums.Action;
import quandoo.com.gameoflife.observer.ActionObserver;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public interface GameActivityPresenter extends BasePresenter {
    void changed(boolean b);

    void notifyObserver(Action action);

    void register(ActionObserver presenter);

    Action getGameState();

    void setGameState(Action start);
}
