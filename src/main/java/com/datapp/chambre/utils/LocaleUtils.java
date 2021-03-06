package com.datapp.chambre.utils;

import com.datapp.chambre.model.localization.Locale;
import lombok.Getter;
import lombok.Setter;


/**
 * created by kmluns
 **/
public class LocaleUtils {


    private static LocaleUtils instance;

    @Getter
    @Setter
    private Locale locale = Locale.en;

    private LocaleUtils() {

    }

    public static LocaleUtils getInstance() {
        if (instance == null) {
            instance = new LocaleUtils();
        }
        return instance;
    }
}
