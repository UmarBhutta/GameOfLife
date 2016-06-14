package quandoo.com.gameoflife.enums;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public enum  Action {
    RUNNING(1),
    STOP(0);

    private int actionId;

    Action(int actionId) {
        this.actionId = actionId;
    }

    public int getActionId() {
        return actionId;
    }
}
