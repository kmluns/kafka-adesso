package com.datapp.chambre.authorization.model;

import lombok.experimental.Accessors;

/**
 * created by kmluns 15.05.2019
 **/
@Accessors(chain = true)
public enum Role {

    ADMIN("ADMIN"),
    IT("IT"),
    USER("USER");

    private final String text;

    /**
     * @param text
     */
    Role(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
