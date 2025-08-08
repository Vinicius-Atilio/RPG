package entities.observer;

import entities.ally.Ally;

import java.util.ArrayList;
import java.util.List;

public class BattleInvoker implements BattleObserver {
    private final List<BattleObserver> observerList = new ArrayList<>();

    @Override
    public void onAllyInvoked(Ally ally) {
        for (BattleObserver observer : observerList) {
            observer.onNotifyObserver(ally);
        }
    }

    @Override
    public void onAddObserver(BattleObserver observer) {
        this.observerList.add(observer);
    }

    @Override
    public void onNotifyObserver(Ally ally) {
        System.out.println("🔔 Notificando observadores sobre a invocação do aliado: " + ally.getName());
    }
}
