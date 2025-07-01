package builders;

import entities.character.Character;
import entities.Inventory;
import enums.Race;
import enums.Specialization;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CharacterBuilder {
    private static final Random random = new Random();
    private static final List<String> NAMES = List.of(
            "Tharion", "Eryndor", "Kaelric", "Darius", "Gorim",
            "Alaric", "Fenris", "Draven", "Eldric", "Ragnar",
            "Malrik", "Varian", "Torvak", "Lucien", "Kharos",
            "Lyra", "Selene", "Arwyn", "Elira", "Freya",
            "Isolde", "Nyssa", "Kaela", "Seraphina", "Liora",
            "Vaelira", "Miriel", "Astra", "Sylvara", "Calista",
            "Ashen", "Rowan", "Veyra", "Kairon", "Zephyr",
            "Neris", "Solen", "Vale", "Riven", "Elen",
            "Soren", "Maelis", "Kyris", "Renwyn", "Aeris",
            "Graktar", "Urgash", "Moktar", "Kharok", "Zargra");

    private String name;
    private Race race = Race.Human;
    private Specialization specialization = Specialization.Warrior;

    public CharacterBuilder() {}

    public CharacterBuilder builder() {
        return new CharacterBuilder();
    }

    public CharacterBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CharacterBuilder withRace(Race race) {
        this.race = race;
        return this;
    }

    public CharacterBuilder withSpecialization(Specialization specialization) {
        this.specialization = specialization;
        return this;
    }

    public Character build() {
        return new Character(getString(), this.race, this.specialization, this.specialization.attribute(),
                this.specialization.type(), this.specialization.skills(), this.specialization.weapon(),
                new Inventory(new ArrayList<>()));
    }

    private String getString() {
        if (this.name != null) {
            return this.name;
        }

        return String.valueOf(NAMES.get(random.nextInt(50)));
    }
}
