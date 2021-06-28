package com.datapp.chambre.constant;

/**
 * created by kmluns
 **/
public class ErrorMessageConstant {

    private ErrorMessageConstant(){}


    // "Hata yok"
    public static final int NO_ERROR = -1;

    // "Kullanıcı adı ve ya şifre hatalı!"
    public static final int AUTHENTICATION_ERROR = 1;

    // "Bilgilerinizde hata var!"
    public static final int PARSE_ERROR = 2;

    // "İşleminizi gerçekleştirirken bir problem oluştu. Lütfen yeniden deneyiniz."
    public static final int PROCESS_ERROR = 3;

    // "Oturum suresiniz sona ermiştir, lütfen yeniden giris yapınız."
    public static final int JWT_EXPIRED_ERROR = 4;

    //  "Giris Yapmalisiniz!!"
    public static final int BAD_CREDENTIALS_ERROR = 5;

    // "Kullanici ismi bulunmaktadir"
    public static final int USERNAME_EXIST = 6;

    // "Bu hizmet yakinda verilecektir. Merakiniz icin tesekkur ederiz."
    public static final int UNCOMPLETED_ERROR = 7;

    // "Bu isim zaten kullanılmaktadır!"
    public static final int NAME_EXIST = 8;

    // "Bu nesne daha önce eklenmiştir"
    public static final int ASSET_EXIST = 9;

    // "Bu varlık tipi, bir varlığın içinde mevcut"
    public static final int ASSET_TYPE_EXIST_IN_ASSET = 10;

    // "Oda bulunamadi"
    public static final int DEVICE_NOT_FOUND = 11;

    // "Oda ismi zaten var"
    public static final int ORGANIZATION_NOT_FOUND = 12;

    // "Kullanici bulunamadi"
    public static final int USER_NOT_FOUND = 13;



    // "Bulunamadı!"
    public static final int NOT_FOUND = 404;

    // "Authorization ERROR"
    public static final int AUTHORIZATION_ERROR = 401;


    // "Bilinmeyen Hata"
    public static final int UNKNOWN_ERROR = 999;


}
