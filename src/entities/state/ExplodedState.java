package entities.state;

public class ExplodedState extends TrapState {
    public ExplodedState() {
        super("Estado Explosivo");
    }

    public static ExplodedState ofTrap() {
        return new ExplodedState();
    }

    @Override
    public boolean hasBeenExploded() {
        return true;
    }
}
