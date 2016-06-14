package quandoo.com.gameoflife.observer;

import quandoo.com.gameoflife.enums.Action;

/**
 * Created by malikumarbhutta on 6/12/16.
 *
 * Implementer for the ActionObserver
 *
 */
public interface ActionObserverImp {


    void register(ActionObserver obj);
    void unregister(ActionObserver obj);


    void notifyObserver(Action action);

    Object getUpdate(ActionObserver obj);
}
