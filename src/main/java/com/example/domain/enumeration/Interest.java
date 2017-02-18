package com.example.domain.enumeration;

/**
 * The Interest enumeration.
 */
public enum Interest {
    SEEN_IT("Seen It"),
    WANT_TO_SEE("Wana See It");

    private final String name;

    Interest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
