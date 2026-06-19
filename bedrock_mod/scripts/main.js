import { world, system } from "@minecraft/server";
import { ActionFormData } from "@minecraft/server-ui";

// ─────────────────────────────────────────────
// Structure library — grouped by category.
// "name" must exactly match the .mcstructure
// filename (without the extension).
// ─────────────────────────────────────────────
const CATEGORIES = [
    {
        label: "§6§l🐉 Pokemon",
        structures: [
            { label: "§fCharizard",  name: "Charizard"  },
            { label: "§fCharmander", name: "Charmander" },
            { label: "§fHaunter",    name: "Haunter"    },
            { label: "§fScorbunny", name: "Scorbunny"  },
            { label: "§fVaporeon",  name: "Vaporeon"   },
            { label: "§fMudkip",    name: "Mudkip"     },
            { label: "§fJolteon",   name: "Jolteon"    },
            { label: "§fFlareon",   name: "Flareon"    },
            { label: "§fGiratina",  name: "Giratina"   }
        ]
    },
    {
        label: "§a§l🏠 Buildings",
        structures: [
            { label: "§fHouse",        name: "House"        },
            { label: "§fCherry House", name: "Cherry house" },
            { label: "§fFurnace",      name: "Furnace"      },
            { label: "§fFloating Island", name: "Floating Island" }
        ]
    },
    {
        label: "§b§l🚗 Vehicles",
        structures: [
            { label: "§fBoat",    name: "Boat"    },
            { label: "§fUFO",     name: "UFO"     },
            { label: "§fBalloon", name: "Balloon" }
        ]
    },
    {
        label: "§e§l⚔ Extras",
        structures: [
            { label: "§fSword", name: "Sword" },
            { label: "§fBee",   name: "Bee"   },
            { label: "§fCOMBO", name: "COMBO" },
            { label: "§fGrogu", name: "Grogu" }
        ]
    }
];

// ─────────────────────────────────────────────
// Calculate a position 10 blocks in front of
// the player at their current Y.
// getViewDirection() returns a full 3D vector,
// so we normalize only the horizontal (XZ) part
// — otherwise looking up/down shrinks the distance.
// ─────────────────────────────────────────────
function getSpawnPos(player) {
    const loc = player.location;
    const dir = player.getViewDirection();
    const hLen = Math.sqrt(dir.x * dir.x + dir.z * dir.z);
    // If the player is looking straight up or down hLen is ~0 — fall back to +Z
    const nx = hLen > 0.001 ? dir.x / hLen : 0;
    const nz = hLen > 0.001 ? dir.z / hLen : 1;
    return {
        x: Math.floor(loc.x + nx * 10),
        y: Math.floor(loc.y),
        z: Math.floor(loc.z + nz * 10)
    };
}

// ─────────────────────────────────────────────
// Spawn a structure with sound + actionbar
// feedback so the player knows it worked.
// ─────────────────────────────────────────────
async function spawnStructure(player, structureName) {
    const pos = getSpawnPos(player);
    try {
        await player.runCommandAsync(
            'structure load "' + structureName + '" ' + pos.x + ' ' + pos.y + ' ' + pos.z
        );
        player.runCommandAsync("title @s actionbar §a✔ Spawned " + structureName + "!");
        player.runCommandAsync("playsound random.levelup @s");
    } catch (err) {
        player.sendMessage("§c[QuickBuild] Failed to spawn \"" + structureName + "\". Error: " + err);
    }
}

// ─────────────────────────────────────────────
// Category sub-menu — lists every structure in
// the chosen category, plus a Back button.
// ─────────────────────────────────────────────
async function showCategoryMenu(player, category) {
    const form = new ActionFormData()
        .title(category.label)
        .body("§7Pick a structure to spawn 10 blocks ahead:");

    for (const s of category.structures) {
        form.button(s.label);
    }
    form.button("§7← Back to categories");

    const result = await form.show(player);
    if (result.canceled) return;

    // Last button index is always the Back button
    if (result.selection === category.structures.length) {
        system.run(() => showMainMenu(player).catch(e => console.error("[QuickBuild] Main menu error:", e)));
        return;
    }

    await spawnStructure(player, category.structures[result.selection].name);
}

// ─────────────────────────────────────────────
// Main menu — one button per category.
// ─────────────────────────────────────────────
async function showMainMenu(player) {
    const form = new ActionFormData()
        .title("§l§2QuickBuild")
        .body("§7Choose a category:");

    for (const cat of CATEGORIES) {
        form.button(cat.label);
    }

    const result = await form.show(player);
    if (result.canceled) return;

    system.run(() => showCategoryMenu(player, CATEGORIES[result.selection]).catch(e => console.error("[QuickBuild] Category menu error:", e)));
}

// ─────────────────────────────────────────────
// Startup — fires on the first game tick.
// Logs to the content log so you can confirm
// the script loaded successfully.
// ─────────────────────────────────────────────
system.run(() => {
    console.log("[QuickBuild] ✔ Script is running. Version 1.3.0 by Bi11_Cypher.");
});

// ─────────────────────────────────────────────
// Welcome message — shown to each player the
// first time they spawn in this session.
// Tells them what the addon is and how to use it.
// ─────────────────────────────────────────────
world.afterEvents.playerSpawn.subscribe((event) => {
    if (!event.initialSpawn) return;

    const player = event.player;

    // Small delay so the message appears after the
    // world finishes loading in for the player.
    system.runTimeout(() => {
        player.sendMessage("§2§l━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        player.sendMessage("§a§l       QuickBuild §r§7v1.3.0");
        player.sendMessage("§2§l━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        player.sendMessage("§7QuickBuild lets you instantly spawn");
        player.sendMessage("§7pre-built structures anywhere in your");
        player.sendMessage("§7world — no commands to memorize.");
        player.sendMessage("§r");
        player.sendMessage("§e§lHow to use:");
        player.sendMessage("§71. Run §f/function quickbuild §7in chat");
        player.sendMessage("§72. You'll receive a §fCompass");
        player.sendMessage("§73. §fRight-click §7the Compass to open the menu");
        player.sendMessage("§74. Pick a category, then pick a structure");
        player.sendMessage("§75. It spawns §f10 blocks §7in front of you");
        player.sendMessage("§r");
        player.sendMessage("§e§lStructures available:");
        player.sendMessage("§6  🐉 Pokemon  §7— Charizard, Charmander, Haunter,");
        player.sendMessage("§6             §7  Scorbunny, Vaporeon, Mudkip, Jolteon, Flareon, Giratina");
        player.sendMessage("§a  🏠 Buildings §7— House, Cherry House, Furnace");
        player.sendMessage("§b  🚗 Vehicles  §7— Boat, UFO, Balloon");
        player.sendMessage("§e  ⚔ Extras    §7— Sword, Bee, COMBO, Grogu");
        player.sendMessage("§r");
        player.sendMessage("§7Made by §fBi11_Cypher");
        player.sendMessage("§2§l━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }, 60); // 60 ticks = 3 seconds
});

// ─────────────────────────────────────────────
// Trigger: right-clicking a compass opens the
// main menu. Players get the compass by running:
//   /function quickbuild
// ─────────────────────────────────────────────
world.afterEvents.itemUse.subscribe((event) => {
    const { source: player, itemStack } = event;
    if (itemStack?.typeId === "minecraft:compass") {
        system.run(() => showMainMenu(player).catch(e => console.error("[QuickBuild] Menu error:", e)));
    }
});



// ============================================================
// QUICKBUILD STRUCTURE REMOVAL SYSTEM
// Added by ChatGPT
// ============================================================

const placedStructures = [];

/*
Stored format:
{
    category: "Buildings",
    structure: "House",
    x: 120,
    y: 64,
    z: -30,
    sizeX: 20,
    sizeY: 15,
    sizeZ: 20
}
*/

// Example structure sizes
// You should customize these later
const STRUCTURE_SIZES = {
    "House": { x: 20, y: 15, z: 20 },
    "Boat": { x: 12, y: 8, z: 18 },
    "Charizard": { x: 18, y: 22, z: 18 },
    "Grogu": { x: 10, y: 14, z: 10 }
};

// ============================================================
// SAVE STRUCTURE DATA
// ============================================================

function logPlacedStructure(category, structureName, x, y, z) {

    const size = STRUCTURE_SIZES[structureName] || {
        x: 10,
        y: 10,
        z: 10
    };

    placedStructures.push({
        category: category,
        structure: structureName,
        x: x,
        y: y,
        z: z,
        sizeX: size.x,
        sizeY: size.y,
        sizeZ: size.z
    });
}

// ============================================================
// REMOVE STRUCTURE
// ============================================================

function removeStructure(player, data) {

    const x2 = data.x + data.sizeX;
    const y2 = data.y + data.sizeY;
    const z2 = data.z + data.sizeZ;

    player.runCommandAsync(
        `fill ${data.x} ${data.y} ${data.z} ${x2} ${y2} ${z2} air`
    );

    const index = placedStructures.indexOf(data);

    if (index !== -1) {
        placedStructures.splice(index, 1);
    }

    player.sendMessage(
        `§aRemoved structure: §f${data.structure}`
    );
}

// ============================================================
// REMOVE UI PLACEHOLDER
// ============================================================

async function openRemoveMenu(player) {

    player.sendMessage("§6=== Remove Structure ===");

    if (placedStructures.length === 0) {
        player.sendMessage("§cNo structures logged.");
        return;
    }

    for (const entry of placedStructures) {

        player.sendMessage(
            `§e${entry.structure} §7@ ${entry.x}, ${entry.y}, ${entry.z}`
        );
    }

    player.sendMessage(
        "§7Next step: connect this to ActionFormData menus."
    );
}
