package com.datapp.chambre.model.utils;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * created by kmluns
 **/
@Accessors(chain = true)
@Document
public enum Priority {
    HIGLY_IMPORTANT("HIGLY_IMPORTANT", 10),
    IMPORTANT("IMPORTANT", 8),
    MIDDLE("MIDDLE", 5),
    LOW("LOW", 3);

    private final String text;

    @Getter
    private final int value;

    Priority(final String text, final int value) {
        this.text = text;
        this.value = value;
    }

    @Override
    public String toString() {
        return text;
    }
}