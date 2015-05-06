package org.x4121.sokocraft.reference;

import java.util.ArrayList;
import java.util.List;

public enum Colors {
    WHITE("Blank", 0),
    BLUE("Blue", 11),
    GREEN("Green", 13),
    RED("Red", 14);

    private final String name;
    private final int code;
    public static final int MAX = 16;

    Colors(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static List<Integer> getCodes() {
        List<Integer> codes = new ArrayList<>();
        for (Colors color: Colors.values()) {
            codes.add(color.getCode());
        }
        return codes;
    }

    public static Colors getColor(int code) {
        for (Colors color: Colors.values()) {
            if (color.getCode() == code) {
                return color;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getLCName() {
        return name.toLowerCase();
    }
}
