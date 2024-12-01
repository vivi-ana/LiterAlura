package com.aluraChallenge.literAlura.model;

import java.util.Optional;

public enum Languages {
    ES("es"),
    EN("en"),
    FR("fr"),
    PT("pt");

    private final String languageApi;

    Languages (String languageApi) {
        this.languageApi = languageApi;
    }

    /**
     * Converts a string to the corresponding Languages enum value.
     * Returns an Optional containing the Languages enum value if found, or an empty Optional if not found.
     *
     * @param text the language name as a String
     * @return an Optional containing the corresponding Languages enum value, or an empty Optional if not found
     */
    public static Optional<Languages> fromString(String text) {
        for (Languages languages : Languages.values()) {
            if (languages.languageApi.equalsIgnoreCase(text)) {
                return Optional.of(languages);
            }
        }
        return Optional.empty();
    }
}