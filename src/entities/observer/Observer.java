package entities.observer;

import entities.ally.Ally;
import entities.character.Player;
import entities.skill.Skill;
import entities.skill.attack.Trap;

public interface Observer {
    void onContract(Player activePlayer, Trap trap);
    void onContract(Player activePlayer, Ally ally);
    void onTrapDamage(Player passivePlayer, Trap trap);
    void onReceiveEnemyAllyAttack(Player passivePlayer, Ally ally, double damage);
    void onDefendAgainstAllyAttack(Player passivePlayer, Ally ally);
    void onAllySupport(Player passivePlayer, Ally ally, double heal);
    void onDefendEnemyAttack(Player activePlayer, Player passivePlayer);
    void onReceiveEnemyAttack(Player activePlayer, Player passivePlayer, double damage);
    void onReceiveSpecialDamage(Player activePlayer, Player passivePlayer, Ally ally);
    void onPlayerDied(Player activePlayer, Player passivePlayer, Skill skill);
    void onUpdateLifeStatus(Player passivePlayer);
    void onAllyInvoked(Player activePLayer, Ally ally);
}
