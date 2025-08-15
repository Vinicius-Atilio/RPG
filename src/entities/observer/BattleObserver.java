package entities.observer;

import entities.ally.Ally;
import entities.character.Character;
import entities.skill.Skill;
import entities.skill.attack.Trap;

public interface BattleObserver {
    void onTurnStart();
    void onAllyInvoked(Ally ally);
    void onAllyAttack(Ally ally);
    void onAddObserver(BattleObserver observer);
    void onNotifyAllyAction(Ally ally, Skill skill);
    void onTrapActivated(Trap trap);
    void onReceiveAllyAttack(Ally ally, Skill skill);
    void onAllySupport(Ally ally);
    void onAllyUpdateState(Ally ally);
    Character getObserver();
}
