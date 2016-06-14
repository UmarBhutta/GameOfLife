package quandoo.com.gameoflife.ui.presenter;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import quandoo.com.gameoflife.constants.PreferenceKeys;
import quandoo.com.gameoflife.dependency.SingleActivity;
import quandoo.com.gameoflife.enums.Action;
import quandoo.com.gameoflife.observer.ActionObserver;
import quandoo.com.gameoflife.observer.ActionObserverImp;
import quandoo.com.gameoflife.ui.viewconfigs.GameState;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
@SingleActivity
public class GameActivityPresenterImp implements GameActivityPresenter,ActionObserverImp {


    private final Object MultiThread = new Object();
    private List<ActionObserver> observers;
    private String message;
    private boolean changed;
    private Action gameState = Action.STOP;

    @Inject
    public GameActivityPresenterImp() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void changed(boolean b) {
        changed = true;
    }

    @Override
    public void register(ActionObserver presenter) {
        if (presenter == null) throw new NullPointerException("Null Observer");
        synchronized (MultiThread) {
            if (!observers.contains(presenter)) observers.add(presenter);
        }
    }

    @Override
    public void unregister(ActionObserver obj) {
        synchronized (MultiThread) {
            observers.remove(obj);
        }
    }

    @Override
    public void notifyObserver(Action action) {
        this.gameState = action;
        List<ActionObserver> observersLocal = null;

        synchronized (MultiThread) {
            if (!changed)
                return;
            observersLocal = new ArrayList<>(this.observers);
            this.changed = false;
        }
        for (ActionObserver obj : observersLocal) {
            message = action.name();
            obj.update(action);
        }
    }

    @Override
    public Object getUpdate(ActionObserver obj) {
        return message;
    }

    @Override
    public Action getGameState() {
        return gameState;
    }

    @Override
    public void setGameState(Action start) {
        this.gameState = start;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {
        notifyObserver(Action.STOP);
    }

    @Override
    public void destroy() {
        notifyObserver(Action.STOP);
    }

    @Override
    public void detach() {
        notifyObserver(Action.STOP);
    }

    @Override
    public Bundle onSaveInstanceState(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }

        if (gameState != null) {

            bundle = GameState.getStateGameInstance()
                    .writeGameState(bundle, gameState);
            switch (gameState) {
                case RUNNING:
                    notifyObserver(Action.STOP);
                    break;
            }
        }

        return bundle;
    }

    @Override
    public void onViewStateRestored(Bundle bundle) {

    }

    @Override
    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle.containsKey(PreferenceKeys.GAME_STATE)) {

            int t_actionID = bundle.getInt(PreferenceKeys.GAME_STATE);

            if (t_actionID == Action.STOP.getActionId()) {
                setGameState(Action.STOP);
            } else if (t_actionID == Action.RUNNING.getActionId()) {
                setGameState(Action.STOP);
            } else {

            }
        }
    }
}
