package entities.state;

@Deprecated
public class PoisonedState {


//    public double calculateDamage(entities.character.Character actionPlayer, entities.character.Character passivePlayer, int activeSKillPowerAttack) {
//        System.out.println("O jogador " + passivePlayer.getName() + " está envenenado! Dano aumentado.");
//        return (actionPlayer.getMainAttribute() * actionPlayer.weaponFactor()) + (activeSKillPowerAttack - passivePlayer.poisonedDefenseValue());
//
//    }
//
//    public double calculateDefense() {
//        return 0;
//    }
//
//    public State withLife(double life) {
//        this.life = life;
//        return this;
//    }
//
//    public void stateCountDown(Character actionPlayer, State state) {
//
//    }
//
//    public void receiveDamage(Character actionPlayer, Character passivePlayer, double value, Skill skill) {
//        System.out.println("O jogador " + passivePlayer.getName() + " está envenenado! Recebendo dano aumentado.");
//        this.life -= value * 1.1; // Aumenta o dano recebido por envenenamento
//        skill.skillAction(actionPlayer, passivePlayer);
//    }
//
//
//    public void receiveEffect(String name) {
//        this.life = this.life * 0.95; // Reduz a vida em 5% por envenenamento
//        System.out.println("O jogador " + name + " está envenenado! Vida reduzida em 5%." + " Vida atual: " + String.format("%.2f",  this.life ));
//    }
//
//    public void onDeath(Character character) {
//        character.changeState(DeadState.of(new Character("* DEAD " + character.getName(), OriginalState.ofDeath())));
//    }
//
//    public boolean isAlive() {
//        return this.life > 0;
//    }
}
