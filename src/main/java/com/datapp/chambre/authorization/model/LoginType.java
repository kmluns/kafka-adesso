package com.datapp.chambre.authorization.model;

/**
 * created by kmluns 30.05.2019
 **/
public enum LoginType {

    APP("APP"),
    LDAP("LDAP"),
    FACEBOOK("FACEBOOK"),
    GOOGLE("GOOGLE");

    private final String text;

    /**
     * @param text
     */
    LoginType(final String text) {
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
