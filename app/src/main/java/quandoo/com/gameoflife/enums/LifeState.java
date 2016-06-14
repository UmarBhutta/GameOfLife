package quandoo.com.gameoflife.enums;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public enum LifeState {
    ALIVE(1),
    DEAD(0);

    private int stateId;

    LifeState(int stateId) {
        this.stateId = stateId;
    }

    public int getStateId() {
        return stateId;
    }
}
