package entities.effect;

import entities.character.Player;

import java.util.function.Consumer;

public class StatusEffect {
    private Integer id;
    private String name;
    private int turnDuration;
    private Consumer<Player> characterConsumer;
    private boolean active;
    private Effect effect = new BuffEffect();

    public StatusEffect() {
    }

    public StatusEffect(Integer id, String name, int turnDuration, Consumer<Player> characterConsumer) {
        this.id = id;
        this.name = name;
        this.turnDuration = turnDuration;
        this.characterConsumer = characterConsumer;
    }

    public StatusEffect(Integer id, String name, int turnDuration, Consumer<Player> characterConsumer, Effect effect) {
        this.id = id;
        this.name = name;
        this.turnDuration = turnDuration;
        this.characterConsumer = characterConsumer;
        this.effect = effect;
    }

    public StatusEffect(Integer id, String name, int turnDuration) {
        this.id = id;
        this.name = name;
        this.turnDuration = turnDuration;
    }
//
//    public static StatusEffect ofEvasion() {
//        return new StatusEffect(1 ,"Evasão", 2, Character::makeEvasion);
//    }

    public StatusEffect ofPoisonArrow() {
        return new StatusEffect(
                2,
                "Flecha Envenenada",
                2,
                character-> {
                    System.out.println("☠️ A flecha envenenada causa dano ao longo do tempo!");
                    character.addEffect(this);
                },
                new PoisonedEffect(0.05) // 5% de dano contínuo
                );
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "StatusEffect{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", turnDuration=" + turnDuration +
                ", characterConsumer=" + characterConsumer +
                ", active=" + active +
                ", effect=" + effect +
                '}';
    }

    public void accept(Player player) {
        characterConsumer.accept(player);
    }

    public void updateTurnDuration(Player player, String effectName) {
        if (this.turnDuration > 0) {
            player.receiveEffect(this.effect.damage(player), effectName);

            if (!player.isAlive()) {
                player.makeDeath();
                System.out.println(this.name + " morreu devido ao efeito " + this.name + ".");
                return;
            }

            System.out.println(this.name + " está ativo. Turnos restantes: " + this.turnDuration);
            this.turnDuration--;
            return;
        }

        System.out.println(this.name + " não tem mais turnos restantes.");
        player.removeEffect(this);
    }

    public boolean isActive() {
        return active;
    }

    public void makeActive() {
        this.active = true;
    }

    public Integer getId() {
        return id;
    }
}
