package com.datapp.chambre.authorization.dto;

import com.datapp.chambre.authorization.model.AccountDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * created by kmluns
 **/
@Accessors(chain = true)
public class AccountDTO {


    @Getter
    @Setter
    private String id;

    //    @Email
    @Getter
    @Setter
    @NotNull
    @Size(min = 5, max = 100)
    private String username;

    @Getter
    @Setter
    @NotNull
    @Size(min = 8, max = 50)
    private String password;

    @Getter
    @Setter
    @Valid
    private AccountDetail accountDetail;
}
