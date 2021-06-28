package com.datapp.chambre.model.setting;

import com.datapp.chambre.model.utils.Base;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Created by kmluns on 13.03.2020
 */
@Document
@Accessors(chain = true)
public class Setting<T> extends Base {

    @Getter
    @Setter
    @Indexed(unique = true)
    @NotNull
    private String key;

    @Getter
    @Setter
    private T value;

}
