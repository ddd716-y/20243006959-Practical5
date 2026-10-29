package com.example.practicalfive;

/**
 * Validates and normalises user input for the Chinese Municipalities app.
 * This is a plain Java class: it does not extend Activity and has no layout.
 */
public class TextProcessor {

    /**
     * Validates a municipality name and returns the correctly capitalised
     * name, or {@code null} if the input is invalid.
     *
     * @param municipality the raw user input, possibly null, empty or mixed case
     * @return the capitalised name (Beijing, Shanghai, Tianjin, Chongqing)
     *         or {@code null} if the input is not a supported municipality
     */
    public static String processMunicipality(String municipality) {
        if (municipality == null) {
            return null;
        }
        String trimmed = municipality.trim();
        if (trimmed.isEmpty()) {
            return null;
        }
        if (trimmed.equalsIgnoreCase("Beijing")) {
            return "Beijing";
        }
        if (trimmed.equalsIgnoreCase("Shanghai")) {
            return "Shanghai";
        }
        if (trimmed.equalsIgnoreCase("Tianjin")) {
            return "Tianjin";
        }
        if (trimmed.equalsIgnoreCase("Chongqing")) {
            return "Chongqing";
        }
        return null;
    }
}
