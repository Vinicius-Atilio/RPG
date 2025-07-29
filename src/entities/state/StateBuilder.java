package entities.state;

public class StateBuilder {
    private double life;

    public StateBuilder life(double life) {
        this.life = life;
        return this;
    }
}
