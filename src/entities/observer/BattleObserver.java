package entities.observer;

import entities.ally.Ally;

public interface BattleObserver {
    void onAllyInvoked(Ally ally);
    void onAddObserver(BattleObserver observer);
    void onNotifyObserver(Ally ally);
    void onTrapActivated(Trap trap);
    void onAllyAttack(Ally ally, BattleObserver targetObserver);
    void onAllySupport(Ally ally, BattleObserver targetObserver);
}
