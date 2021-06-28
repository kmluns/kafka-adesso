package com.datapp.chambre.repository.setting;

import com.datapp.chambre.model.setting.Setting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by kmluns on 13.03.2020
 */
public interface SettingRepository extends MongoRepository<Setting, String> {

    Optional<Setting> findByKey(String key);


}
