package entities.skill.hunter;

import entities.BattleGround;
import entities.character.Player;
import entities.observer.Subject;
import entities.skill.attack.Trap;
import entities.state.ArmedState;
import entities.state.DisarmedState;
import entities.state.TrapState;

public class ExplosiveTrap extends Trap {
    protected ExplosiveTrap(String name, String description, String skillAction, TrapState state, int cooldown, int damage) {
        super(name, description, skillAction, state, cooldown, damage);
    }

    @Override
    public void contract(Subject invokerSubject, Subject enemySubject) {
        this.invokerSUbject = invokerSubject;
        this.enemySubject = enemySubject;
        this.state = ArmedState.ofTrap();
    }

    @Override
    public void executeSelectedSkill(Player activePlayer, Player passivePlayer, BattleGround battleGround) {
        printSkillBox("💣 PREPARAÇÃO: ARMADILHA EXPLOSIVA BANUK");
        System.out.println("👣 O caçador ajoelha silenciosamente no solo congelado...");
        System.out.println("🧰 Ele retira uma carga instável da bolsa e um detonador artesanal.");
        System.out.println("🛠️ Posiciona a carga entre pedras e folhas, conectando os fios com precisão...");
        System.out.println("🔥 Um pequeno LED vermelho pisca no núcleo da bomba... armada com sucesso.");
        // JOGADOR IMPLEMENTAR ESSAS ACOES
//        this.markTargetObserver(passivePlayer);
//        this.markOwnerObserver(activePlayer);
    }

    @Override
    public void skillTypeAction(Player activePlayer, Player passivePlayer, BattleGround battleGround) {
        printSkillBox("💣 ARMADILHA EXPLOSIVA ATIVADA!");
        System.out.println("💥 " + activePlayer.getName() + " instala a armadilha explosiva com maestria!");
        System.out.println("Alvo selecionado: " + passivePlayer.getName() + ".");
        System.out.println("⚠️ " + passivePlayer.getName() + " não percebe o perigo iminente...");
    }

    public static ExplosiveTrap ofExplosiveTrap() {
        return new ExplosiveTrap("Armadilha Explosiva",
                "Instala armadilha que explode ao ser acionada.",
                "💣 Uma armadilha mortal é colocada no campo de batalha!",
                DisarmedState.ofTrap(),
                1,
                30);
    }
}
