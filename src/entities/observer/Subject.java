package entities.observer;

import entities.ally.Ally;
import entities.character.Player;
import entities.skill.attack.Trap;
import entities.state.State;

public interface Subject {
    void notifyObservers(State state);
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void onAllyInvoked(Ally ally);
    void onTrapActivated(Trap trap);
    void onAllySupport(Ally ally);
    void onAllyUpdateState(Ally ally);
    void onAllyContract(Subject enemyObserver);
    Player getObserver();
}
