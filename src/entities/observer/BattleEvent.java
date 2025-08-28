package entities.observer;

public class BattleEvent {
    private String eventName;
    private Object subject;
    private String source;
    private double damage;
    private double heal;

    public BattleEvent(String eventName, Object subject, String source, double damage, double heal) {
        this.eventName = eventName;
        this.subject = subject;
        this.source = source;
        this.damage = damage;
        this.heal = heal;
    }
}
