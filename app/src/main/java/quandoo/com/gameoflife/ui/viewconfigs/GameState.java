package quandoo.com.gameoflife.ui.viewconfigs;

import android.os.Bundle;

import quandoo.com.gameoflife.constants.PreferenceKeys;
import quandoo.com.gameoflife.enums.Action;

/**
 * Created by malikumarbhutta on 6/12/16.
 */
public class GameState {

    private static GameState gameStateInstance = null;

    public GameState() {
    }

    public static GameState getStateGameInstance(){

        if(gameStateInstance == null){
            gameStateInstance = new GameState();
        }
        return gameStateInstance;
    }
    public Bundle writeGameState(Bundle bundle, Action action) {
        if (bundle == null || action == null) {
            return bundle;
        }
        bundle.putInt(PreferenceKeys.GAME_STATE, action.getActionId());
        return bundle;
    }

    public Bundle writeCellState(Bundle bundle, String cellStatesJson) {
        bundle.putString(PreferenceKeys.CELL_STATE, cellStatesJson);
        return bundle;
    }

    public String readCellsState(Bundle bundle) {
        return bundle.containsKey(PreferenceKeys.GAME_STATE) ?
                bundle.getString(PreferenceKeys.CELL_STATE) :
                "";
    }
}
