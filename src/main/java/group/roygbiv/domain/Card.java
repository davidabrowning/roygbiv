package group.roygbiv.domain;

import javafx.scene.paint.Color;

public class Card {

    private final int value;

    public Card(int value) {
        this.value = value;
    }

    // ----------------------------------------------------------------------------------------------------------------

    public int getValue() { return value; }

    @Override
    public String toString() {
        return "#" + value;
    }
    // ----------------------------------------------------------------------------------------------------------------

    public Color getColor() {

        /*
        0 RED = 255, 0, 0
        12 ORANGE = 255, 165, 0
        24 YELLOW = 255, 255, 0
        36 GREEN = 0, 255, 0
        48 BLUE = 0, 0, 255
        59 PURPLE = 160, 32, 240
        */

        int red = 0;
        int green = 0;
        int blue = 0;

        // Red
        if (value == 0) {
            red = 255;
            green = 0;
            blue = 0;
            return Color.rgb(red, green, blue);
        }

        // Red -> Orange
        if (value < 12) {
            red = 255;
            green = 0 + (value - 0) * (165 - 0) / 12;
            blue = 0;
            return Color.rgb(red, green, blue);
        }

        // Orange
        if (value == 12) {
            red = 255;
            green = 165;
            blue = 0;
            return Color.rgb(red, green, blue);
        }

        // Orange -> Yellow
        if (value < 24) {
            red = 255;
            green = 165 + (value - 12) * (255 - 165) / 12;
            blue = 0;
            return Color.rgb(red, green, blue);
        }

        // Yellow
        if (value == 24) {
            red = 255;
            green = 255;
            blue = 0;
            return Color.rgb(red, green, blue);
        }

        // Yellow -> Green
        if (value < 36) {
            red = 255 + (value - 24) * (0 - 255) / 12;
            green = 255;
            blue = 0;
            return Color.rgb(red, green, blue);
        }

        // Green
        if (value == 36) {
            red = 0;
            green = 255;
            blue = 0;
            return Color.rgb(red, green, blue);
        }

        // Green -> Blue
        if (value < 48) {
            red = 0;
            green = 255 + (value - 36) * (0 - 255) / 12;
            blue = 0 + (value - 36) * (255 - 0) / 12;
            return Color.rgb(red, green, blue);
        }

        // Blue
        if (value == 48) {
            red = 0;
            green = 0;
            blue = 255;
            return Color.rgb(red, green, blue);
        }

        // Blue -> Purple
        if (value < 59) {
            red = 0 + (value - 48) * (160 - 0) / 12;
            green = 0 + (value - 48) * (32 - 0) / 12;
            blue = 255 + (value - 48) * (240 - 255) / 12;
            return Color.rgb(red, green, blue);
        }

        //Purple
        if (value == 59) {
            red = 160;
            green = 32;
            blue = 240;
            return Color.rgb(red, green, blue);
        }

        return Color.rgb(red, green, blue);
    }

    public Color getTextColor() {
        if (value < 5) {
            return Color.WHITE;
        }
        if (value < 40) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

}
