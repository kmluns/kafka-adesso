package com.datapp.chambre.service.setting;

import com.datapp.chambre.authorization.AdminAuthorization;
import com.datapp.chambre.dto.response.GenericResponse;
import com.datapp.chambre.exception.runtime.ParseException;
import com.datapp.chambre.model.setting.Setting;
import com.datapp.chambre.model.setting.SettingKey;
import com.datapp.chambre.repository.setting.SettingRepository;
import com.datapp.chambre.service.GenericResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * Created by kmluns on 13.03.2020
 */
@Service
public class SettingService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    SettingRepository settingRepository;

    @Autowired
    GenericResponseService genericResponseService;


    public <T> T getValue(SettingKey settingKey) {
        Optional<Setting> optionalSetting = settingRepository.findByKey(settingKey.toString());
        if (!optionalSetting.isPresent()) {
            throw new ParseException();
        }
        Setting<T> setting = optionalSetting.get();
        return setting.getValue();
    }

    public String getDefaultPassword() {
        Optional<Setting> optionalSetting = settingRepository.findByKey(SettingKey.DEFAULT_PASSWORD.toString());
        if (!optionalSetting.isPresent()) {
            throw new ParseException();
        }
        Setting<String> settingDefaultPassword = optionalSetting.get();
        return settingDefaultPassword.getValue();
    }

    @AdminAuthorization
    public GenericResponse changeDefaultPassword(String password) {
        Optional<Setting> optionalSetting = settingRepository.findByKey(SettingKey.DEFAULT_PASSWORD.toString());
        Setting<String> settingDefaultPassword;
        if (optionalSetting.isPresent()) {
            settingDefaultPassword = optionalSetting.get();
            String hashedPassword = bCryptPasswordEncoder.encode(password);
            settingDefaultPassword.setValue(hashedPassword);
            settingRepository.save(settingDefaultPassword);
        } else {
            throw new RuntimeException();
        }
        return genericResponseService.createResponseNoError(settingDefaultPassword);
    }


    private <T> Setting<T> createSetting(String key, T value) {
        Setting setting = new Setting();
        setting.setKey(key)
                .setValue(value);
        return settingRepository.save(setting);
    }


    @PostConstruct
    private void createDefaultSetting() {
        Optional<Setting> setting;
        String key;


        key = SettingKey.DEFAULT_PASSWORD.toString();
        setting = settingRepository.findByKey(key);
        if (!setting.isPresent()) {
            String defaultPassword = bCryptPasswordEncoder.encode("Q1w2E3");
            createSetting(SettingKey.DEFAULT_PASSWORD.toString(), defaultPassword);
        }
    }

}
