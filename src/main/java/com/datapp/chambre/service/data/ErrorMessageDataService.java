package com.datapp.chambre.service.data;

import com.datapp.chambre.constant.ErrorMessageConstant;
import com.datapp.chambre.model.localization.ErrorMessage;
import com.datapp.chambre.model.localization.Locale;
import com.datapp.chambre.repository.localization.ErrorMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;

/**
 * created by kmluns
 **/

@Service
public class ErrorMessageDataService {

    @Autowired
    ErrorMessageRepository errorMessageRepository;

    @PostConstruct
    public void createErrorMessages() {

        LinkedList<ErrorMessage> errorMessages = new LinkedList<>();

        errorMessages.add(new ErrorMessage(ErrorMessageConstant.NO_ERROR, "No error", Locale.en));
        errorMessages.add(new ErrorMessage(ErrorMessageConstant.NO_ERROR, "Hata yok", Locale.tr));

        errorMessages.add(new ErrorMessage(ErrorMessageConstant.AUTHENTICATION_ERROR, "Username or password wrong!", Locale.en));
        errorMessages.add(new ErrorMessage(ErrorMessageConstant.AUTHENTICATION_ERROR, "Kullanıcı adı ve ya şifre hatalı!", Locale.tr));

        errorMessages.add(new ErrorMessage(ErrorMessageConstant.PARSE_ERROR, "Something wrong in your information!", Locale.en));
        errorMessages.add(new ErrorMessage(ErrorMessageConstant.PARSE_ERROR, "Bilgilerinizde hata var!", Locale.tr));

        errorMessages.add(new ErrorMessage(ErrorMessageConstant.PROCESS_ERROR, "Something happened while process. Please try again", Locale.en));
        errorMessages.add(new ErrorMessage(ErrorMessageConstant.PROCESS_ERROR, "İşleminizi gerçekleştirirken bir problem oluştu. Lütfen yeniden deneyiniz.", Locale.tr));

        errorMessages.add(new ErrorMessage(ErrorMessageConstant.JWT_EXPIRED_ERROR, "Your session has been expiredö, please login again.", Locale.en));
        errorMessages.add(new ErrorMessage(ErrorMessageConstant.JWT_EXPIRED_ERROR, "Oturum suresiniz sona ermiştir, lütfen yeniden giris yapınız.", Locale.tr));

        errorMessages.add(new ErrorMessage(ErrorMessageConstant.BAD_CREDENTIALS_ERROR, "You have to login!", Locale.en));
        errorMessages.add(new ErrorMessage(ErrorMessageConstant.BAD_CREDENTIALS_ERROR, "Giriş Yapmalısınız!", Locale.tr));

        errorMessages.add(new ErrorMessage(ErrorMessageConstant.USERNAME_EXIST, "The username already using!", Locale.en));
        errorMessages.add(new ErrorMessage(ErrorMessageConstant.USERNAME_EXIST, "Kullanici ismi zaten kullanılmaktadır!", Locale.tr));

        errorMessages.add(new ErrorMessage(ErrorMessageConstant.UNCOMPLETED_ERROR, "This service will be provided soon. Thank you for your curiosity.", Locale.en));
        errorMessages.add(new ErrorMessage(ErrorMessageConstant.UNCOMPLETED_ERROR, "Bu hizmet yakinda verilecektir. Merakiniz icin tesekkur ederiz.", Locale.tr));

        errorMessages.add(new ErrorMessage(ErrorMessageConstant.NAME_EXIST, "The name already using!", Locale.en));
        errorMessages.add(new ErrorMessage(ErrorMessageConstant.NAME_EXIST, "Bu isim zaten kullanılmaktadır!", Locale.tr));

        errorMessages.add(new ErrorMessage(ErrorMessageConstant.NOT_FOUND, "Nothing found, something wrong!", Locale.en));
        errorMessages.add(new ErrorMessage(ErrorMessageConstant.NOT_FOUND, "Bulunamadı, Birşeyler yanlış!", Locale.tr));

        errorMessages.add(new ErrorMessage(ErrorMessageConstant.AUTHORIZATION_ERROR, "You are not authorized!", Locale.en));
        errorMessages.add(new ErrorMessage(ErrorMessageConstant.AUTHORIZATION_ERROR, "Yetkiniz bulunmamaktadır!", Locale.tr));

        errorMessages.add(new ErrorMessage(ErrorMessageConstant.ASSET_EXIST, "This asset_management already added!!", Locale.en));
        errorMessages.add(new ErrorMessage(ErrorMessageConstant.ASSET_EXIST, "Bu nesne daha önce eklenmiştir!!", Locale.tr));

        errorMessages.add(new ErrorMessage(ErrorMessageConstant.ASSET_TYPE_EXIST_IN_ASSET, "This asset type exist in the asset!!", Locale.en));
        errorMessages.add(new ErrorMessage(ErrorMessageConstant.ASSET_TYPE_EXIST_IN_ASSET, "Bu varlık tipi, bir varlığın içinde hala kullanılmaktadır!!", Locale.tr));

        errorMessages.add(new ErrorMessage(ErrorMessageConstant.DEVICE_NOT_FOUND, "The device can not found!!", Locale.en));
        errorMessages.add(new ErrorMessage(ErrorMessageConstant.DEVICE_NOT_FOUND, "Cihaz bulunamadı!!", Locale.tr));

        errorMessages.add(new ErrorMessage(ErrorMessageConstant.ORGANIZATION_NOT_FOUND, "The organization can not found!!", Locale.en));
        errorMessages.add(new ErrorMessage(ErrorMessageConstant.ORGANIZATION_NOT_FOUND, "Organizasyon bulunamadı!!", Locale.tr));

        errorMessages.add(new ErrorMessage(ErrorMessageConstant.USER_NOT_FOUND, "The user can not found!!", Locale.en));
        errorMessages.add(new ErrorMessage(ErrorMessageConstant.USER_NOT_FOUND, "Kullanıcı bulunamadı!!", Locale.tr));

        errorMessages.add(new ErrorMessage(ErrorMessageConstant.UNKNOWN_ERROR, "Unknown Error!", Locale.en));
        errorMessages.add(new ErrorMessage(ErrorMessageConstant.UNKNOWN_ERROR, "Bilinmeyen Hata!", Locale.tr));

        if (errorMessageRepository.count() < errorMessages.size()) {
            errorMessageRepository.deleteAll();
            errorMessageRepository.saveAll(errorMessages);
        }
    }

}
