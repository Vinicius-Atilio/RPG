package entities.state;

public class ArmedState extends TrapState {
    public ArmedState() {
        super("Estado Armado");
    }

    public static ArmedState ofTrap() {
        return new ArmedState();
    }

    @Override
    public boolean hasBeenExploded() {
        return false;
    }
}
