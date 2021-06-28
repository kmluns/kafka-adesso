package com.datapp.chambre.utils.export;

import lombok.experimental.Accessors;

/**
 * Created by kmluns on 30.07.2020
 */
@Accessors(chain = true)
public enum ExportType {

    WORD("word"),
    CSV("csv"),
    XLS("xls"),
    XLSX("xlsx");

    private final String text;

    /**
     * @param text
     */
    ExportType(final String text) {
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
