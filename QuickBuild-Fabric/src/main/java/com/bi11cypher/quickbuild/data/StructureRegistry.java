package com.bi11cypher.quickbuild.data;

import java.util.ArrayList;
import java.util.List;

public class StructureRegistry {
    private static final List<StructureCategory> CATEGORIES = new ArrayList<>();

    public static void init() {
        CATEGORIES.clear();

        // Pokemon Category
        StructureCategory pokemon = new StructureCategory("§6§l🐉 Pokemon");
        pokemon.addStructure("§fCharizard", "Charizard");
        pokemon.addStructure("§fCharmander", "Charmander");
        pokemon.addStructure("§fHaunter", "Haunter");
        pokemon.addStructure("§fScorbunny", "Scorbunny");
        pokemon.addStructure("§fVaporeon", "Vaporeon");
        pokemon.addStructure("§fMudkip", "Mudkip");
        pokemon.addStructure("§fFlareon", "Flareon");
        pokemon.addStructure("§fGiratina", "Giratina");
        CATEGORIES.add(pokemon);

        // Buildings Category
        StructureCategory buildings = new StructureCategory("§a§l🏠 Buildings");
        buildings.addStructure("§fHouse", "House");
        buildings.addStructure("§fCherry House", "Cherry house");
        buildings.addStructure("§fFurnace", "Furnace");
        buildings.addStructure("§fFloating Island", "Floating Island");
        CATEGORIES.add(buildings);

        // Vehicles Category
        StructureCategory vehicles = new StructureCategory("§b§l🚗 Vehicles");
        vehicles.addStructure("§fBoat", "Boat");
        vehicles.addStructure("§fUFO", "UFO");
        vehicles.addStructure("§fBalloon", "Balloon");
        CATEGORIES.add(vehicles);

        // Extras Category
        StructureCategory extras = new StructureCategory("§e§l⚔ Extras");
        extras.addStructure("§fSword", "Sword");
        extras.addStructure("§fBee", "Bee");
        extras.addStructure("§fCOMBO", "COMBO");
        extras.addStructure("§fGrogu", "Grogu");
        CATEGORIES.add(extras);
    }

    public static List<StructureCategory> getCategories() {
        if (CATEGORIES.isEmpty()) {
            init();
        }
        return CATEGORIES;
    }
}
