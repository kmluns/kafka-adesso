package com.datapp.chambre.exception.runtime;

import com.datapp.chambre.constant.ErrorMessageConstant;
import com.datapp.chambre.utils.app_static.StaticUtils;

/**
 * created by kmluns 21.01.2019
 **/
public class ExistAssetTypeInAssetException extends ApplicationRuntimeException {
    public ExistAssetTypeInAssetException() {
        super(StaticUtils.getErrorMessageService().getErrorMessageText(ErrorMessageConstant.ASSET_TYPE_EXIST_IN_ASSET), ErrorMessageConstant.ASSET_TYPE_EXIST_IN_ASSET);
    }
}
