package com.bi11cypher.quickbuild.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;
import com.bi11cypher.quickbuild.QuickBuild;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StructureSpawner {

    /**
     * Calculate a position 10 blocks in front of the player
     * at their current Y level.
     */
    public static BlockPos getSpawnPos(PlayerEntity player) {
        Vec3d loc = player.getPos();
        Vec3d dir = player.getRotationVector();

        // Normalize horizontal (XZ) part only
        double hLen = Math.sqrt(dir.x * dir.x + dir.z * dir.z);
        double nx = hLen > 0.001 ? dir.x / hLen : 0;
        double nz = hLen > 0.001 ? dir.z / hLen : 1;

        int x = (int) Math.floor(loc.x + nx * 10);
        int y = (int) Math.floor(loc.y);
        int z = (int) Math.floor(loc.z + nz * 10);

        return new BlockPos(x, y, z);
    }

    /**
     * Spawn a structure at a given position
     */
    public static void spawnStructure(PlayerEntity player, String structureName) {
        World world = player.getWorld();
        if (!(world instanceof ServerWorld serverWorld)) {
            QuickBuild.LOGGER.warn("Cannot spawn structure: player is not on a server world");
            return;
        }

        BlockPos pos = getSpawnPos(player);

        try {
            // Load the structure template
            StructureTemplate template = loadStructureTemplate(serverWorld, structureName);
            if (template == null) {
                player.sendMessage(
                    net.minecraft.text.Text.literal("§c[QuickBuild] Failed to load structure: " + structureName),
                    false
                );
                return;
            }

            // Place the structure
            StructurePlacementData placementData = new StructurePlacementData();
            template.place(serverWorld, pos, pos, placementData, serverWorld.getRandom(), 2);

            // Play success sound and show message
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 0.5;
            double z = pos.getZ() + 0.5;

            serverWorld.playSound(
                null,
                x, y, z,
                net.minecraft.sound.SoundEvents.BLOCK_NOTE_BLOCK_CHIME,
                net.minecraft.sound.SoundCategory.BLOCKS,
                1.0f, 1.0f
            );

            player.sendMessage(
                net.minecraft.text.Text.literal("§a✔ Spawned " + structureName + "!"),
                true
            );

        } catch (Exception e) {
            QuickBuild.LOGGER.error("Error spawning structure: " + structureName, e);
            player.sendMessage(
                net.minecraft.text.Text.literal("§c[QuickBuild] Failed to spawn \"" + structureName + "\". Error: " + e.getMessage()),
                false
            );
        }
    }

    /**
     * Load a structure template from NBT file
     */
    private static StructureTemplate loadStructureTemplate(ServerWorld world, String structureName) {
        try {
            // First try to get from structure manager
            StructureTemplate template = world.getStructureTemplateManager().getTemplate(
                new Identifier("quickbuild", structureName.toLowerCase())
            ).orElse(null);

            if (template != null) {
                return template;
            }

            // Fallback: Try to load from world structures folder
            Path structurePath = world.getDirectory().resolve("structures").resolve(structureName + ".nbt");
            if (Files.exists(structurePath)) {
                NbtCompound nbt = NbtIo.readCompressed(structurePath.toFile());
                template = new StructureTemplate();
                template.readNbtData(nbt);
                return template;
            }

            return null;
        } catch (Exception e) {
            QuickBuild.LOGGER.error("Error loading structure template: " + structureName, e);
            return null;
        }
    }
}
