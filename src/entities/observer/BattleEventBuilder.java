package entities.observer;

public class BattleEventBuilder {
    private String eventName;
    private Object subject;
    private String source;
    private double damage;
    private double heal;

    public BattleEventBuilder() {}

    public BattleEventBuilder builder() {
        return new BattleEventBuilder();
    }

    public BattleEventBuilder eventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public BattleEventBuilder subject(Object subject) {
        this.subject = subject;
        return this;
    }

    public BattleEventBuilder source(String source) {
        this.source = source;
        return this;
    }

    public BattleEventBuilder damage(double damage) {
        this.damage = damage;
        return this;
    }

    public BattleEventBuilder heal(double heal) {
        this.heal = heal;
        return this;
    }

    public BattleEvent build() {
        return new BattleEvent(eventName, subject, source, damage, heal);
    }
}
