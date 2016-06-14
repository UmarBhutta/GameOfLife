package quandoo.com.gameoflife.observer;

import quandoo.com.gameoflife.enums.Action;

/**
 * Created by malikumarbhutta on 6/13/16.
 *
 * to observe the actions
 *
 */
public interface ActionObserver {

    void update(Action action);


    void setImp(ActionObserverImp imp);
}
