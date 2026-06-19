# QuickBuild Fabric Mod - 1.21.x
## Java Port - Ready to Compile

This is a complete Fabric mod for Minecraft 1.21.x - a port of the original Bedrock QuickBuild addon.

**Current Status**: ✅ **COMPLETE & READY TO BUILD**

---

## 📦 What's Included

```
QuickBuild-Fabric/
├── src/main/
│   ├── java/                          # Java source code
│   │   └── com/bi11cypher/quickbuild/
│   │       ├── QuickBuild.java              # Main mod
│   │       ├── client/                      # Client-side code
│   │       │   ├── QuickBuildClient.java
│   │       │   └── gui/                     # Screen/Menu GUIs
│   │       ├── data/                        # Structure registry
│   │       │   ├── StructureCategory.java
│   │       │   └── StructureRegistry.java
│   │       ├── mixin/                       # Compass right-click handler
│   │       │   └── ItemStackMixin.java
│   │       └── util/                        # Utilities
│   │           ├── StructureSpawner.java
│   │           └── PlacedStructure.java
│   │
│   └── resources/
│       ├── fabric.mod.json            # Mod metadata
│       ├── quickbuild.mixins.json     # Mixin config
│       ├── data/quickbuild/           # Structure NBT files (19 structures)
│       │   └── structures/
│       │       ├── Charizard.nbt
│       │       ├── House.nbt
│       │       ├── UFO.nbt
│       │       └── ... (19 total)
│       └── assets/quickbuild/         # Language/textures
│           └── lang/en_us.json
│
├── build.gradle                       # Gradle build config
├── gradle.properties                  # Gradle properties
├── settings.gradle                    # Gradle settings
│
├── README.md                          # Feature overview
├── BUILD_GUIDE.md                     # Build instructions
├── INSTALLATION.md                    # Installation guide
├── STRUCTURE_GUIDE.md                 # How to add structures
├── LICENSE                            # MIT License
└── .gitignore                        # Git ignore rules
```

---

## 🚀 Quick Build

### 1. Prerequisites
- **Java 21 or later**
- **Gradle** (included - use `./gradlew` or `gradlew.bat`)

### 2. Build Command
```bash
# Linux/macOS
./gradlew build

# Windows
gradlew.bat build
```

### 3. Output
The compiled JAR will be created at:
```
build/libs/quickbuild-1.3.0.jar
```

### 4. Install & Test
1. Place JAR in your Fabric `mods` folder
2. Launch Minecraft 1.21.x (with Fabric installed)
3. Get a compass: `/give @s compass`
4. Right-click it to open the menu!

---

## 📋 Requirements to Build

1. **Java 21 or later**
   - Check version: `java -version`
   - Download: https://adoptium.net/

2. **Fabric Loader** (NOT needed to build, only to run)
   - https://fabricmc.net/use/installer/

3. **Fabric API** (NOT needed to build, only to run)
   - https://www.curseforge.com/minecraft/mods/fabric-api

---

## 🎮 Features Implemented

✅ **Right-click Compass** to open menu  
✅ **4 Structure Categories** (Pokemon, Buildings, Vehicles, Extras)  
✅ **19 Pre-built Structures** (Charizard, House, UFO, etc.)  
✅ **10-Block Forward Spawn** (spawns ahead of player)  
✅ **Menu Navigation** (Back buttons, category selection)  
✅ **Sound & Message Feedback** (success indicators)  
✅ **Structure Tracking** (for future removal system)  

---

## 🔧 Troubleshooting Build Issues

### "Could not determine java version"
```bash
# Check Java
java -version

# If not Java 21, download it from:
# https://adoptium.net/
```

### Gradle Download Error
```bash
# Clear cache and retry
./gradlew clean
./gradlew build
```

### Out of Memory Error
```bash
# If gradle runs out of memory, increase heap:
export GRADLE_OPTS="-Xmx2G"  # Linux/macOS
set GRADLE_OPTS=-Xmx2G       # Windows
./gradlew build
```

---

## 📚 Documentation Files

Each markdown file contains detailed information:

| File | Content |
|------|---------|
| **README.md** | Feature overview, mod info |
| **BUILD_GUIDE.md** | Detailed build instructions, IDE setup |
| **INSTALLATION.md** | Installation & troubleshooting |
| **STRUCTURE_GUIDE.md** | How to add/modify structures |

---

## 🎯 What Each Component Does

### Core Files
- **QuickBuild.java** - Main mod entry point
- **QuickBuildClient.java** - Client initialization
- **ItemStackMixin.java** - Detects compass right-click

### GUI/Screens
- **QuickBuildScreen.java** - Base screen class
- **QuickBuildMainScreen.java** - Main category menu
- **QuickBuildCategoryScreen.java** - Structure selection

### Data Management
- **StructureRegistry.java** - Lists all structures by category
- **StructureCategory.java** - Structure data model
- **StructureSpawner.java** - Spawns structures in world
- **PlacedStructure.java** - Tracks placed structures

### Resources
- **fabric.mod.json** - Mod metadata
- **quickbuild.mixins.json** - Mixin configuration
- **en_us.json** - Language strings

---

## 📦 Structure Files Included

19 pre-made structures in NBT format:

**Pokemon (8):** Charizard, Charmander, Haunter, Scorbunny, Vaporeon, Mudkip, Flareon, Giratina

**Buildings (4):** House, Cherry House, Furnace, Floating Island

**Vehicles (3):** Boat, UFO, Balloon

**Extras (4):** Sword, Bee, COMBO, Grogu

---

## 🛠️ Customization

### Add New Structures
1. Convert to NBT format
2. Place in `src/main/resources/data/quickbuild/structures/`
3. Register in `StructureRegistry.java`
4. Rebuild

See `STRUCTURE_GUIDE.md` for details.

### Change Spawn Distance
Edit `StructureSpawner.java`:
```java
// Change from 10 to 20 blocks:
int distance = 20;  // Was: 10
```

---

## 📝 File Sizes

Approximate sizes of mod components:
- Java source: ~50 KB
- NBT structures: ~257 KB
- Total zip: ~272 KB
- Compiled JAR: ~350 KB

---

## ✅ Pre-Build Checklist

Before building:
- [ ] Java 21+ installed
- [ ] Gradle available (bundled - just use `./gradlew`)
- [ ] 1 GB free disk space
- [ ] Internet connection (for dependency downloads)

---

## 🎉 After Building

1. **JAR Location**: `build/libs/quickbuild-1.3.0.jar`
2. **Install**: Copy to `mods` folder
3. **Launch**: Use Fabric profile in Minecraft
4. **Test**: Right-click compass to verify

---

## 🆘 Support

If you encounter issues:
1. Check the relevant guide (BUILD_GUIDE.md, INSTALLATION.md)
2. Review the included javadoc comments in source files
3. Check Minecraft's `logs/latest.log` for errors
4. Verify Java 21+ is being used

---

## 📋 Version Info

- **Mod Version**: 1.3.0
- **Minecraft**: 1.21.x
- **Loader**: Fabric 0.16.0+
- **Java**: 21+
- **Dependencies**: Fabric API 0.100.0+

---

## 📄 License

MIT License - See LICENSE file for details

---

## 🎨 Original Credit

- **Original Bedrock Mod**: Bi11_Cypher
- **Java/Fabric Port**: ChatGPT & Contributors
- **Structures**: Converted from Bedrock Edition

---

## 🚀 Next Steps

1. **Extract** this zip file
2. **Read** BUILD_GUIDE.md for detailed build instructions
3. **Run** `./gradlew build` (or `gradlew.bat build` on Windows)
4. **Install** the compiled JAR to your mods folder
5. **Launch** Minecraft and enjoy!

---

**Happy Building! 🏗️**

For detailed instructions, see the individual markdown files included.
