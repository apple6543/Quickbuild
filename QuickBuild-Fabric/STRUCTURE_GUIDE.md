# Structure Configuration Guide

## Adding Custom Structures

This guide explains how to add your own structures to QuickBuild.

### Step 1: Prepare Your Structure File

1. Create your structure in Minecraft using any building tool
2. Export it to an NBT file using a structure exporter
3. Name the file exactly as you want it referenced (e.g., `MyStructure.nbt`)

### Step 2: Place Structure File

1. Copy your `.nbt` file to one of these locations:
   - **Mod Package**: `src/main/resources/data/quickbuild/structures/`
   - **World Folder**: `world/structures/` (loaded at runtime)

### Step 3: Register in Mod

1. Open `src/main/java/com/bi11cypher/quickbuild/data/StructureRegistry.java`
2. Find the category you want to add to (or create a new one)
3. Add your structure:

```java
// Example: Add to existing category
StructureCategory buildings = new StructureCategory("§a§l🏠 Buildings");
buildings.addStructure("§fMy Building", "MyStructure");  // Name must match NBT file (without .nbt)
```

### Step 4: Rebuild the Mod

```bash
./gradlew build
```

### Structure Naming Convention

**Important**: The structure name in the registry must match the NBT filename exactly (case-sensitive):
- ✓ `addStructure("...", "MyStructure")` + `MyStructure.nbt`
- ✗ `addStructure("...", "mystructure")` + `MyStructure.nbt` (won't work)

## Creating New Categories

To add a new category (e.g., 🏰 Castles):

```java
StructureCategory castles = new StructureCategory("§c§l🏰 Castles");
castles.addStructure("§fCastle 1", "Castle1");
castles.addStructure("§fCastle 2", "Castle2");
castles.addStructure("§fCastle 3", "Castle3");
CATEGORIES.add(castles);
```

## Text Formatting Codes

QuickBuild uses Minecraft's text formatting:
- `§0` - Black
- `§1` - Dark Blue
- `§2` - Dark Green
- `§3` - Dark Cyan
- `§4` - Dark Red
- `§5` - Dark Purple
- `§6` - Gold
- `§7` - Gray
- `§8` - Dark Gray
- `§9` - Blue
- `§a` - Green
- `§b` - Cyan
- `§c` - Red
- `§d` - Magenta
- `§e` - Yellow
- `§f` - White

**Styles:**
- `§l` - Bold
- `§o` - Italic
- `§n` - Underline
- `§m` - Strikethrough
- `§k` - Obfuscated
- `§r` - Reset

Example: `"§6§lGold Bold"` = Gold Bold text

## Emoji Support

You can include Unicode emojis in category labels:
```java
new StructureCategory("🏰 Castles")
new StructureCategory("⚔️ Weapons")
new StructureCategory("🌳 Nature")
```

## Structure Sizes (Optional)

If you want undo/removal functionality, you can define structure sizes:

```java
// In StructureSpawner.java, add to STRUCTURE_SIZES map:
STRUCTURE_SIZES.put("MyStructure", new Structure(20, 15, 20));
```

Format: `(width, height, depth)` in blocks

## Converting Bedrock Structures

If you have Bedrock edition structures:

1. Use an NBT converter tool (e.g., Bedrock Porting Toolkit, MCctructure)
2. Export to Java format NBT files
3. Place in structures folder
4. Register in StructureRegistry.java

## Troubleshooting

**Structure doesn't appear in menu after rebuild**
- Check spelling: registry name must match NBT filename (case-sensitive)
- Verify the category was added to CATEGORIES
- Rebuild with: `./gradlew clean build`

**Structure spawns but looks wrong**
- NBT file might be corrupted
- Try re-exporting from Minecraft
- Check that blocks are supported in 1.21.x

**Structure file not found error**
- Verify file extension is `.nbt`
- Check file is in correct location
- Ensure filename has no spaces (use underscores instead)

## Example: Complete Addition

To add a 🏰 Castles category with structures:

1. **Prepare files:**
   - `Castle1.nbt` → Copy to `src/main/resources/data/quickbuild/structures/`
   - `Castle2.nbt` → Copy to `src/main/resources/data/quickbuild/structures/`

2. **Edit StructureRegistry.java:**
```java
// In init() method, before CATEGORIES.clear():
StructureCategory castles = new StructureCategory("§c§l🏰 Castles");
castles.addStructure("§fCastle 1", "Castle1");
castles.addStructure("§fCastle 2", "Castle2");
CATEGORIES.add(castles);
```

3. **Rebuild:**
```bash
./gradlew build
```

4. **Install:** Copy new JAR to mods folder

5. **Test:** Launch game and right-click compass!

## Advanced: Custom Spawn Behavior

To customize how structures spawn (distance, height, etc.), edit `StructureSpawner.java`:

```java
// Change spawn distance from 10 to 15 blocks
int distance = 15;  // Was: 10
```

Then rebuild!
