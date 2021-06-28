package com.datapp.chambre.exception.runtime;

import com.datapp.chambre.constant.ErrorMessageConstant;
import com.datapp.chambre.utils.app_static.StaticUtils;

/**
 * created by oguzhan 19.07.2019
 **/
public class SerialNumberExistsException extends ApplicationRuntimeException {
    public SerialNumberExistsException() {
        super(StaticUtils.getErrorMessageService().getErrorMessageText(ErrorMessageConstant.ASSET_EXIST), ErrorMessageConstant.ASSET_EXIST);
    }
}
