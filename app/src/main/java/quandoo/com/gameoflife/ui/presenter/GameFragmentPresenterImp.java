package quandoo.com.gameoflife.ui.presenter;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import quandoo.com.gameoflife.enums.Action;
import quandoo.com.gameoflife.observer.ActionObserver;
import quandoo.com.gameoflife.observer.ActionObserverImp;
import quandoo.com.gameoflife.ui.interactors.InteractionImp;
import quandoo.com.gameoflife.ui.interactors.UIInteractor;
import quandoo.com.gameoflife.ui.model.GridModel;
import quandoo.com.gameoflife.ui.viewconfigs.GameState;
import quandoo.com.gameoflife.ui.viewconfigs.GridConfig;
import quandoo.com.gameoflife.utils.ScreenUtils;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public class GameFragmentPresenterImp implements
        GameFragmentPresenter,
        ActionObserver {

    private String name;
    private ActionObserverImp actionObserverImp;
    private ScreenUtils screenUtils;
    private GridModel gridConfig;
    private InteractionImp interactionImp;

    @Inject
    public GameFragmentPresenterImp() {

    }

    @Override
    public void update(Action action) {
        String msg = (String) actionObserverImp.getUpdate(this);
        if (msg == null) {
            Log.i("%s :: No new message", msg);
        } else {
            Log.i("Consuming message:: %s", msg);
        }
    }

    @Override
    public void setImp(ActionObserverImp imp) {
        this.actionObserverImp = imp;
    }

    @Override
    public void setName(String s) {

    }

    @Override
    public void setActionObserverImp(ActionObserverImp actionObserverImp) {
        this.actionObserverImp = actionObserverImp;
    }

    @Override
    public ActionObserverImp getActionObserverImp() {
        return actionObserverImp;
    }

    @Override
    public void setScreenUtils(ScreenUtils screenUtils) {
        this.screenUtils = screenUtils;

        int w, h;

        if (screenUtils.getOrientation() == Configuration.ORIENTATION_PORTRAIT) {
            Log.i("Orientation(): %s", "Configuration.ORIENTATION_PORTRAIT");
        } else {
            Log.i("Orientation(): %s", "Configuration.ORIENTATION_LANDSCAPE");
        }

        w = screenUtils.getDeviceWidth();
        h = screenUtils.getDeviceHeight();
        

        if (w < h) {
            h = w;
        } else if (h < w) {
            w = h;
        }

        int cellCount = 20;

        if (w % cellCount > 0) {
            w = w - (w % cellCount);
        }

        gridConfig = new GridModel();
        gridConfig.setWidth(w);
        gridConfig.setHeight(h);
        gridConfig.setBackgroundColor("#3F51B5");
        gridConfig.setColumnCellCount(cellCount);
        gridConfig.setRowCellCount(cellCount);
        gridConfig.setCellCount(gridConfig.getColumnCellCount() * gridConfig.getRowCellCount());
        gridConfig.setDefaultCellColor("#303F9F");
        gridConfig.setNewBornCellColor("#FF4081");
        gridConfig.setDefaultLineColor("#FFFFFF");
        gridConfig.setCellWidth(w / cellCount);
        gridConfig.setCellHeight(w / cellCount);


        interactionImp = new UIInteractor(cellCount, cellCount);
    }

    @Override
    public GridConfig getGridConfig() {
        return gridConfig;
    }

    @Override
    public InteractionImp getInteractionImp() {
        return interactionImp;
    }

    @Override
    public String getCellStatesJson() {
        return "";
    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        interactionImp.pause();
    }

    @Override
    public void detach() {
        if (actionObserverImp != null) {
            actionObserverImp.unregister(this);
            actionObserverImp.unregister(interactionImp);
        }
    }

    @Override
    public Bundle onSaveInstanceState(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle = GameState.getStateGameInstance().writeCellState(bundle, getCellStatesJson());

        interactionImp.clear();

        return bundle;
    }

    @Override
    public void onViewStateRestored(Bundle bundle) {
        //TODO
    }

    @Override
    public void onRestoreInstanceState(Bundle bundle) {
        //TODO
    }
}
