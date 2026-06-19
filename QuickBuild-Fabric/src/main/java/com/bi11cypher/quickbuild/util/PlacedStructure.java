package com.bi11cypher.quickbuild.util;

import java.util.ArrayList;
import java.util.List;

public class PlacedStructure {
    public String category;
    public String structure;
    public int x;
    public int y;
    public int z;
    public int sizeX;
    public int sizeY;
    public int sizeZ;

    public PlacedStructure(String category, String structure, int x, int y, int z, 
                           int sizeX, int sizeY, int sizeZ) {
        this.category = category;
        this.structure = structure;
        this.x = x;
        this.y = y;
        this.z = z;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
    }

    @Override
    public String toString() {
        return String.format("%s @ %d, %d, %d (size: %dx%dx%d)", 
            structure, x, y, z, sizeX, sizeY, sizeZ);
    }
}
