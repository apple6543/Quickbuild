package com.bi11cypher.quickbuild.data;

import java.util.ArrayList;
import java.util.List;

public class StructureCategory {
    public final String label;
    public final List<Structure> structures;

    public StructureCategory(String label) {
        this.label = label;
        this.structures = new ArrayList<>();
    }

    public void addStructure(String label, String name) {
        structures.add(new Structure(label, name));
    }

    public static class Structure {
        public final String label;
        public final String name;

        public Structure(String label, String name) {
            this.label = label;
            this.name = name;
        }
    }
}
