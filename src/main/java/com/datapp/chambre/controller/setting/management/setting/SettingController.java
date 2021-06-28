package com.datapp.chambre.controller.setting.management.setting;

import com.datapp.chambre.dto.response.GenericResponse;
import com.datapp.chambre.service.setting.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kmluns on 13.03.2020
 */
@RestController
@RequestMapping("setting")
public class SettingController {

    @Autowired
    SettingService settingService;

    @PostMapping("default-password/change")
    public GenericResponse changeDefaultPassword(@RequestBody String defaultPassword) {
        return settingService.changeDefaultPassword(defaultPassword);
    }

}
