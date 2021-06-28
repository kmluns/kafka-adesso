package com.datapp.chambre.model.setting;

import lombok.experimental.Accessors;

/**
 * Created by kmluns on 13.03.2020
 */
@Accessors(chain = true)
public enum SettingKey {

    DEFAULT_PASSWORD("DEFAULT_PASSWORD");

    private final String text;

    SettingKey(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
