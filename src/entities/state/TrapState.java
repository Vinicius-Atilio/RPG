package entities.state;

public abstract class TrapState {
    protected String stateName;

    public TrapState(String stateName) {
        this.stateName = stateName;
    }

    public abstract boolean hasBeenExploded();
}
