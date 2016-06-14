package quandoo.com.gameoflife.observer;

import quandoo.com.gameoflife.ui.customviews.BaseCellView;

/**
 * Created by malikumarbhutta on 6/12/16.
 */
public interface CellViewObserver {
    void update(BaseCellView cellView);
}
