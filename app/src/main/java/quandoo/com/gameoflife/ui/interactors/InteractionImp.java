package quandoo.com.gameoflife.ui.interactors;

import quandoo.com.gameoflife.enums.Action;
import quandoo.com.gameoflife.observer.ActionObserver;
import quandoo.com.gameoflife.observer.ActionObserverImp;
import quandoo.com.gameoflife.observer.CellViewObserver;
import quandoo.com.gameoflife.ui.customviews.BaseCellView;

/**
 * Created by malikumarbhutta on 6/12/16.
 */
public interface InteractionImp extends CellViewObserver,ActionObserver {
    void addCellView(BaseCellView cellView);

    @Override
    void update(BaseCellView cellView);

    @Override
    void update(Action action);

    @Override
    void setImp(ActionObserverImp imp);

    BaseCellView[][] getCells();

    void newBorn(int i, int j);

    void pause();

    void clear();
}
