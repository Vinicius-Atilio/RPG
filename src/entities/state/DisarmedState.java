package entities.state;

public class DisarmedState extends TrapState {
    public DisarmedState() {
        super("Estado Desarmado");
    }

    public static DisarmedState ofTrap() {
        return new DisarmedState();
    }

    @Override
    public boolean hasBeenExploded() {
        return false;
    }
}
