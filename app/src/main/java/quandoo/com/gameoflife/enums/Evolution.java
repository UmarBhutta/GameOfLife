package quandoo.com.gameoflife.enums;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public enum  Evolution {
    WILL_BORN_NEW(1),
    WILL_DIE(0),
    REMAIN_UNCHANGED(-1);

    private int stateId;

    Evolution(int stateId) {
        this.stateId = stateId;
    }

    public int getStateId() {
        return stateId;
    }
}
