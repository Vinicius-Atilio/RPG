package entities.skill.hunter.ally;

import entities.BattleGround;
import entities.ally.Ally;
import entities.character.Player;
import entities.observer.BattleObserver;
import entities.skill.Skill;
import entities.state.DeadState;
import entities.state.OriginalState;
import entities.state.State;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Beast extends Ally {
    private static final List<String> actionList = Arrays.asList(
            " surge ferozmente na batalha pronto para atacar!",
            " corre rapidamente para ajudar seu mestre!!",
            " salta alto e ataca com garras afiadas!",
            " ruge ferozmente antes de atacar!"
    );

    public Beast(String name, String description, String skillAction, int cooldown, State state, List<Skill> skills, double invokerPower, double skillMultiplier) {
        super(name, description, skillAction, cooldown, state, skills, invokerPower, skillMultiplier);
    }

    @Override
    public void contract(BattleObserver invokerObserver, BattleObserver enemyObserver) {
        this.invokerObserver = invokerObserver;
        this.enemyObserver = enemyObserver;
        this.state = OriginalState.ofBeast();
    }

    @Override
    public Skill allySelectSkill() {
        Skill selectedSkill = this.skills.get(ThreadLocalRandom.current().nextInt(this.skills.size()));
        if (selectedSkill.getCurrentCooldown() == 0) {
            return selectedSkill;
        }

        return allySelectSkill();
    }

    @Override
    public void doAction(BattleObserver battleGroundObserver) {
        System.out.println("🐾 " + this.getName() + " está pronto para usar sua habilidade!");
        battleGroundObserver.onNotifyAllyAction(this, this);
        Skill selectedSkill = this.allySelectSkill();
        System.out.println("⚔️ " + this.getName() + " escolheu a habilidade: " + selectedSkill.getName());
        selectedSkill.prepareSkillToExecute(this, this.invokerObserver.getObserver(), this.enemyObserver.getObserver(), battleGroundObserver);
        System.out.println("🐾 " + this.getName() + " executou a habilidade: " + selectedSkill.getName());
    }

    @Override
    public void executeSelectedSkill(Player activePlayer, Player passivePlayer, BattleGround battleGround) {
        printSkillBox("🐺 PREPARANDO FERA COMPANHEIRA");
        System.out.println(activePlayer.getName() + " chama sua fera companheira para a batalha!");
        System.out.println(activePlayer.getName() + " assobia e a fera aparece rapidamente ao seu lado!");
        System.out.println("⚔️ " + activePlayer.getName() + " se prepara para posicionar sua fera companheira no campo de batalha!");
        System.out.println();
    }

    @Override
    public void skillTypeAction(Player activePlayer, Player passivePlayer, BattleGround battleGround) {
        printSkillBox("🐾 FERA COMPANHEIRA ATIVADA");
        System.out.println("🤝 " + activePlayer.getName() + " invocou o aliado " + this.name + " para lutar ao seu lado!");
        System.out.println("🐾 O aliado de " + activePlayer.getName() + this.getAction(actionList));
        System.out.println("💪 A fera companheira está pronta para atacar e ajudar na batalha!");
        System.out.println();
        battleGround.onAllyInvoked(this);
    }

    public static Beast ofHunterAlly() {
        return new Beast("Fera Companheira",
                "Um aliado animal que traz força e agilidade para a batalha",
                "🐺 Fera Companheira está lutando por seu mestre na batalha!.",
                1,
                DeadState.of("Fera Companheira"),
                ofBeast(),
                1.5,
                1.5);
    }

    @Override
    public String getIcon() {
        return " 🐺 ";
    }

    @Override
    public double getAllyPower() {
        return this.state.getStrength();
    }

    @Override
    public double getAllyHeal() {
        return this.state.getAgility();
    }
}
