# QuickBuild - Complete Installation & Usage Guide

## 🎮 Quick Start

1. **Download**: Get the `quickbuild-1.3.0.jar` from `build/libs/`
2. **Install**: Place JAR in your Fabric `mods` folder
3. **Launch**: Start Minecraft 1.21.x with Fabric
4. **Use**: Run `/give @s compass` and right-click it!

---

## 📋 Requirements

Before installing QuickBuild, ensure you have:

- **Minecraft 1.21.x** installed
- **Fabric Loader 0.16.0+** ([download](https://fabricmc.net/use/installer/))
- **Fabric API 0.100.0+** ([download](https://www.curseforge.com/minecraft/mods/fabric-api))
- **Java 21+** (if you're building from source)

---

## 🔧 Installation Steps

### Method 1: Using Pre-built JAR (Recommended)

#### Step 1: Prepare Minecraft
1. Install **Fabric Loader** for Minecraft 1.21.x
2. Launch Minecraft once with Fabric to create the mods folder
3. Install **Fabric API** by placing the JAR in your mods folder

#### Step 2: Install QuickBuild
1. Copy `quickbuild-1.3.0.jar` to your mods folder:
   - **Linux/macOS**: `~/.minecraft/mods/`
   - **Windows**: `%APPDATA%\.minecraft\mods\`

#### Step 3: Verify Installation
1. Launch Minecraft
2. Check that QuickBuild appears in the mods list
3. Run `/give @s compass` in a world
4. Right-click the compass - the menu should appear!

### Method 2: Building from Source

#### Prerequisites
- Java 21 or later
- Gradle (included in project)

#### Build Steps
```bash
cd QuickBuild-Fabric
./gradlew build          # Linux/macOS
# or
gradlew.bat build        # Windows
```

The compiled JAR will be at: `build/libs/quickbuild-1.3.0.jar`

Then follow **Method 1: Step 2** to install.

---

## 🎯 How to Use

### Getting the Compass
```
/give @s compass
```

### Opening the Menu
1. Have the compass in your hand
2. **Right-click** the compass
3. The QuickBuild main menu opens

### Spawning Structures
1. Choose a **Category** (Pokemon, Buildings, Vehicles, or Extras)
2. Select a **Structure** from the category
3. The structure spawns **10 blocks in front of you**
4. You'll see a success message and hear a sound

### Menu Navigation
- **Main Menu**: Browse categories
- **Category Menu**: Select a structure
- **← Back button**: Return to main menu
- **ESC key**: Close menu

---

## 📦 Available Structures

### 🐉 Pokemon (8 structures)
- Charizard
- Charmander
- Haunter
- Scorbunny
- Vaporeon
- Mudkip
- Flareon
- Giratina

### 🏠 Buildings (4 structures)
- House
- Cherry House
- Furnace
- Floating Island

### 🚗 Vehicles (3 structures)
- Boat
- UFO
- Balloon

### ⚔ Extras (4 structures)
- Sword
- Bee
- COMBO
- Grogu

**Total: 23 pre-built structures!**

---

## 🛠️ Troubleshooting

### Mod doesn't appear in mods list
- ✓ Verify Fabric Loader is installed
- ✓ Check JAR is in correct `mods` folder
- ✓ Ensure you're using Fabric profile in launcher
- ✓ Try removing and re-adding the JAR

### Menu doesn't open when right-clicking compass
- ✓ Verify compass is in your main hand (not offhand)
- ✓ Make sure you're in a loaded world (not in main menu)
- ✓ Check that Fabric API is installed
- ✓ Look for errors in game log: `logs/latest.log`

### Structures spawn but look corrupted
- ✓ The NBT files might not have converted properly
- ✓ Try spawning a different structure
- ✓ Check if the structure works in a Bedrock world

### Game crashes on startup
- ✓ Check `logs/latest.log` for error messages
- ✓ Ensure Java 21+ is being used
- ✓ Try with a fresh Minecraft instance
- ✓ Verify all dependencies are installed

### Can't find the compass after `/give`
- ✓ Check your inventory
- ✓ You might need to enable command feedback: `/gamerule commandBlockOutput true`
- ✓ Try in Creative mode first

---

## 🎨 Customization

### Adding More Structures

1. **Convert your structure to NBT format** using a conversion tool
2. **Place the file** in: `src/main/resources/data/quickbuild/structures/`
3. **Edit** `src/main/java/com/bi11cypher/quickbuild/data/StructureRegistry.java`
4. **Add your structure**:
   ```java
   pokemon.addStructure("§fMyStructure", "MyStructure");
   ```
5. **Rebuild**: `./gradlew build`

See `STRUCTURE_GUIDE.md` for detailed instructions.

### Changing Spawn Distance

Edit `StructureSpawner.java`:
```java
// Change this line:
int distance = 10;  // Default is 10 blocks

// To your preferred distance:
int distance = 20;  // Example: 20 blocks
```

---

## 📚 Additional Resources

### Included Documentation
- `README.md` - Overview and features
- `BUILD_GUIDE.md` - Detailed build instructions
- `STRUCTURE_GUIDE.md` - How to add custom structures
- `INSTALLATION.md` - This file

### External Resources
- **Fabric**: https://fabricmc.net/
- **Fabric API**: https://www.curseforge.com/minecraft/mods/fabric-api
- **Modrinth**: https://modrinth.com/ (alternative mod source)

---

## 🐛 Reporting Issues

If you encounter problems:
1. Check `logs/latest.log` in your `.minecraft` folder
2. Verify all requirements are met
3. Try a fresh world (structures might be pre-cached)
4. Review this guide's troubleshooting section

---

## 📝 Version Information

- **Mod Version**: 1.3.0
- **Minecraft Version**: 1.21.x
- **Loader**: Fabric Loader 0.16.0+
- **Dependencies**: Fabric API 0.100.0+
- **Java**: 21+

---

## ✅ Mod Folder Checklist

After installation, your mods folder should contain:
```
mods/
├── fabric-api-0.100.0+build.xxx.jar
└── quickbuild-1.3.0.jar          ← Make sure this is here!
```

If this is set up correctly, you're ready to play!

---

## 🎉 Success!

You should now be able to:
- ✅ See QuickBuild in your mods list
- ✅ Right-click a compass to open the menu
- ✅ Spawn structures with a single click
- ✅ Enjoy instant building!

**Happy building! 🏗️**
