# QuickBuild - Fabric Java Mod

A Fabric mod for Minecraft 1.21.x that allows players to instantly spawn pre-built structures with a user-friendly menu system.

## Features

- **Easy Structure Spawning**: Right-click a compass to open the QuickBuild menu
- **Categorized Structures**: Pokemon, Buildings, Vehicles, and Extras
- **Pre-built Structures**: Includes 23+ pre-made structures (Charizard, House, UFO, and more)
- **Simple Menu Interface**: Intuitive GUI for selecting and spawning structures
- **Sound Feedback**: Audio confirmation when structures are spawned

## Structure Categories

### 🐉 Pokemon
- Charizard
- Charmander
- Haunter
- Scorbunny
- Vaporeon
- Mudkip
- Jolteon
- Flareon
- Giratina

### 🏠 Buildings
- House
- Cherry House
- Furnace
- Floating Island

### 🚗 Vehicles
- Boat
- UFO
- Balloon

### ⚔ Extras
- Sword
- Bee
- COMBO
- Grogu

## Installation

### Prerequisites
- Java 21 or later
- Gradle (or use the included gradlew)
- Fabric Loader 0.16+
- Minecraft 1.21.x

### Build Instructions

1. Clone or extract this project
2. Run: `./gradlew build` (or `gradlew.bat build` on Windows)
3. The built JAR will be in `build/libs/`
4. Place the JAR in your `mods` folder

### Adding Structures

1. Convert your Bedrock structures to NBT format
2. Place NBT files in `world/structures/` folder
3. Add structure definitions to `StructureRegistry.java` in the appropriate category
4. Rebuild the mod

## Project Structure

```
QuickBuild-Fabric/
├── src/main/
│   ├── java/com/bi11cypher/quickbuild/
│   │   ├── QuickBuild.java              # Main mod entry point
│   │   ├── client/
│   │   │   ├── QuickBuildClient.java    # Client initialization
│   │   │   └── gui/
│   │   │       ├── QuickBuildScreen.java        # Base screen
│   │   │       ├── QuickBuildMainScreen.java    # Main menu
│   │   │       └── QuickBuildCategoryScreen.java # Category selection
│   │   ├── data/
│   │   │   ├── StructureCategory.java   # Structure category model
│   │   │   └── StructureRegistry.java    # Structure definitions
│   │   ├── mixin/
│   │   │   └── ItemStackMixin.java      # Compass right-click handler
│   │   └── util/
│   │       └── StructureSpawner.java    # Structure placement logic
│   └── resources/
│       ├── fabric.mod.json              # Mod metadata
│       ├── quickbuild.mixins.json       # Mixin configuration
│       └── assets/quickbuild/
│           └── lang/en_us.json          # Language file
├── build.gradle                          # Build configuration
├── gradle.properties                     # Gradle properties
└── settings.gradle                       # Gradle settings
```

## How to Use

1. **Get the Compass**: Right-click a compass in your inventory
2. **Open Menu**: The QuickBuild main menu will open
3. **Select Category**: Choose from Pokemon, Buildings, Vehicles, or Extras
4. **Select Structure**: Pick the structure you want to spawn
5. **Spawn**: The structure will appear 10 blocks in front of you

## Technical Details

### Compass Right-Click Detection
Uses Mixin to intercept the right-click action on compass items. When detected, opens the main menu screen.

### Structure Loading
Structures are loaded from NBT files. The mod supports structures from the world's `structures` folder or from the mod's resource pack.

### Position Calculation
The spawn position is calculated 10 blocks in front of the player at their current Y level, accounting for horizontal view direction only (not vertical looking angle).

## Customization

### Adding New Structures

1. Edit `src/main/java/com/bi11cypher/quickbuild/data/StructureRegistry.java`
2. Add structure to appropriate category:
```java
StructureCategory pokemon = new StructureCategory("§6§l🐉 Pokemon");
pokemon.addStructure("§fMyStructure", "MyStructure");
CATEGORIES.add(pokemon);
```
3. Place corresponding NBT file in world structures folder
4. Rebuild: `./gradlew build`

### Creating New Categories

```java
StructureCategory myCategory = new StructureCategory("§c§l🔥 My Structures");
myCategory.addStructure("§fStructure1", "Structure1");
myCategory.addStructure("§fStructure2", "Structure2");
CATEGORIES.add(myCategory);
```

## Debugging

### Enable Debug Logging
Check the game's debug log file for QuickBuild messages starting with `[QuickBuild]`

### Common Issues

- **Structures not loading**: Ensure NBT files are in the correct location and named correctly
- **Menu not appearing**: Check that the compass is in your hand when right-clicking
- **Structures spawning in wrong location**: Verify spawn position calculation in `StructureSpawner.java`

## Version

- **Mod Version**: 1.3.0
- **Minecraft**: 1.21.x
- **Loader**: Fabric Loader 0.16+
- **Java**: 21+

## Authors

- **Original Bedrock Version**: Bi11_Cypher
- **Java/Fabric Port**: ChatGPT & Community

## License

MIT License - Feel free to modify and distribute

## Credits

- Original concept by Bi11_Cypher
- Structures converted from Bedrock Edition
- Built with Fabric Loader and Fabric API
