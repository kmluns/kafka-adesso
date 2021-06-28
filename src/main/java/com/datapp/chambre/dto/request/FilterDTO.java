package com.datapp.chambre.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Accessors(chain = true)
public class FilterDTO {

    @Getter
    @Setter
    @NotNull
    private int currentPage;

    @Getter
    @Setter
    @NotNull
    private int pageSize;

    @Getter
    @Setter
    @NotNull
    private String sortingColumn;

    @Getter
    @Setter
    private boolean asc;

    @Getter
    @Setter
    @NotNull
    private String filterText;

    @Getter
    @Setter
    private String[] filter;
}
