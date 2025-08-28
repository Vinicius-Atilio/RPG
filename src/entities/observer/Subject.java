package entities.observer;

import entities.ally.Ally;
import entities.character.Player;
import entities.skill.Skill;
import entities.skill.attack.Trap;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Ally ally);
    void notifyObservers(Trap trap);
    void onAllyInvoked(Ally ally);
    void onAllyAttack(Ally ally);
    void onAddObserver(Observer observer);
    void onNotifyAllyAction(Ally ally, Skill skill);
    void onTrapActivated(Trap trap);
    void onReceiveAllyAttack(Ally ally, Skill skill);
    void onAllySupport(Ally ally);
    void onAllyUpdateState(Ally ally);
    void onAllyContract(Subject enemyObserver);
    Player getObserver();
}
