package quandoo.com.gameoflife.observer;

import quandoo.com.gameoflife.enums.LifeState;

/**
 * Created by malikumarbhutta on 6/12/16.
 *
 * Implementer for CellViewObserver
 *
 */
public interface CellViewObserverImp {
    LifeState getLifeState();

    void setState(LifeState lifeState);

    void attach(CellViewObserver observer);

    void notifyAllObservers(LifeState lifeState);
}
