# QuickBuild Fabric Mod - Build & Installation Guide

## Building the Mod

### Prerequisites
- **Java 21 or later** (required for Minecraft 1.21)
- **Gradle** (included as gradlew in this project)

### Step-by-Step Build Instructions

#### On Linux/macOS:
```bash
cd QuickBuild-Fabric
./gradlew build
```

#### On Windows:
```cmd
cd QuickBuild-Fabric
gradlew.bat build
```

### Build Output
After a successful build, the mod JAR will be located at:
```
build/libs/quickbuild-1.3.0.jar
```

The JAR can be directly installed into your mods folder.

## Installation

### Step 1: Prepare Your Minecraft Installation
1. Install **Fabric Loader** (0.16.0 or later)
   - Download from: https://fabricmc.net/use/installer/
   - Select Minecraft version 1.21.x
   - Install for the game directory you want to use

2. Install **Fabric API**
   - Download from: https://www.curseforge.com/minecraft/mods/fabric-api
   - Select version 1.21.x
   - Place the JAR in your `mods` folder

### Step 2: Install QuickBuild
1. Build the mod using the instructions above
2. Copy `build/libs/quickbuild-1.3.0.jar` to your Fabric `mods` folder
   - Linux/macOS: `~/.minecraft/mods/`
   - Windows: `%APPDATA%\.minecraft\mods\`

### Step 3: Launch Minecraft
1. Open Minecraft Launcher
2. Select the Fabric profile
3. Launch Minecraft 1.21.x

### Step 4: Verify Installation
1. Create a new world or load an existing one
2. Run the following command in chat:
   ```
   /give @s compass
   ```
3. Right-click the compass in your inventory
4. The QuickBuild menu should appear

## Troubleshooting

### Build Errors

**Error: "Could not determine java version"**
- Ensure Java 21 or later is installed
- Check your JAVA_HOME environment variable:
  ```bash
  echo $JAVA_HOME  # Linux/macOS
  echo %JAVA_HOME%  # Windows
  ```

**Error: "Gradle sync failed"**
- Clear Gradle cache:
  ```bash
  ./gradlew clean
  ```
- Try building again:
  ```bash
  ./gradlew build
  ```

### Runtime Issues

**Menu doesn't open when right-clicking compass**
- Verify Fabric Loader is properly installed
- Check that Fabric API is installed
- Make sure the mod JAR is in the correct mods folder
- Restart Minecraft completely

**Structures don't spawn**
- Structures must be in NBT format
- Verify structure files exist in the world's `structures` folder
- Check the Minecraft log for errors
- Ensure you're in Creative or have proper permissions

**Game crashes on startup**
- Check logs: `logs/latest.log` in your Minecraft directory
- Ensure all dependencies (Fabric API) are installed
- Try with a fresh Minecraft instance

## Development Setup

If you want to modify the mod source code:

### IDE Setup (IntelliJ IDEA)

1. Open the project folder in IntelliJ
2. IntelliJ should detect it as a Gradle project
3. Wait for Gradle to sync
4. Go to File → Project Structure → Project
5. Set Language Level to 21
6. Run → Edit Configurations → Create new "Gradle" configuration:
   - Tasks: `runClient` (for running the dev client)
   - VM options: `-Xmx2G`

### IDE Setup (VS Code)

1. Install "Gradle for Java" extension
2. Open the project folder
3. The extension will auto-detect the Gradle project
4. Build: Ctrl+Shift+B

### Common Dev Tasks

- **Run Dev Client**: `./gradlew runClient`
- **Run Dedicated Server**: `./gradlew runServer`
- **Generate IDE Configuration**: `./gradlew idea` (for IntelliJ)
- **Clean Build**: `./gradlew clean build`

## File Structure After Build

```
QuickBuild-Fabric/
├── build/
│   ├── libs/
│   │   └── quickbuild-1.3.0.jar  ← Install this JAR
│   ├── intermediates/             (build artifacts)
│   └── ...
├── src/                          (source code)
├── build.gradle                  (build config)
├── gradle.properties             (gradle settings)
└── ...
```

## Version Info

- **Mod Version**: 1.3.0
- **Minecraft**: 1.21.x
- **Loader**: Fabric Loader 0.16.0+
- **Java**: 21+

## Need Help?

- **Fabric Documentation**: https://fabricmc.net/wiki/setup
- **Fabric API**: https://github.com/FabricMC/fabric
- **Minecraft Mods**: https://modrinth.com/mods?q=fabric
