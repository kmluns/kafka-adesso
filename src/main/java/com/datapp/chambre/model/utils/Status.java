package com.datapp.chambre.model.utils;

import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * created by kmluns 27.05.2019
 **/
@Accessors(chain = true)
@Document
public enum Status {

    PENDING("PENDING"),
    SENT("SENT"),
    FAILED("FAILED"),
    WAIT_ACTION("WAIT_ACTION");

    private final String text;

    Status(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
