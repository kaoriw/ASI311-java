package com.excilys.formation.battleships;

public class ColorUtil {

    private ColorUtil() {
    }

    ;

    enum Color {
        COLOR_RESET("\u001B[0m"),
        COLOR_BLACK("\u001B[30m"),
        COLOR_RED("\u001B[31m"),
        COLOR_GREEN("\u001B[32m"),
        COLOR_YELLOW("\u001B[33m"),
        COLOR_BLUE("\u001B[34m"),
        COLOR_PURPLE("\u001B[35m"),
        COLOR_CYAN("\u001B[36m"),
        COLOR_WHITE("\u001B[37m");


        private final String value;

        Color(String value) {
            this.value = value;
        }
    }

    public static String colorize(String text, Color color) {
        return String.format("%s%s%s", color.value, text, Color.COLOR_RESET.value);
    }

    public static String colorize(Character text, Color color) {
        return colorize("" + text, color);
    }
}
