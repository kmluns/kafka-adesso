package com.datapp.chambre.model.utils;

import com.datapp.chambre.authorization.model.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.UUID;

/**
 * created by kmluns
 **/
@Accessors(chain = true)
@EnableMongoAuditing(auditorAwareRef = "auditorProvider")
public abstract class Base {

    @Id
    @Getter
    @Setter
    private String id = UUID.randomUUID().toString();

    @CreatedDate
    @Getter
    @Setter
    private Date created;

    @LastModifiedDate
    @Getter
    @Setter
    private Date updated;

    @Version
    @Getter
    @Setter
    private Long version;


    @Getter
    @Setter
    private Boolean delete = Boolean.FALSE;

    @LastModifiedBy
    @DBRef
    @Getter
    @Setter
    private Account modifiedBy;

    @CreatedBy
    @DBRef
    @Getter
    @Setter
    private Account createdBy;

}
