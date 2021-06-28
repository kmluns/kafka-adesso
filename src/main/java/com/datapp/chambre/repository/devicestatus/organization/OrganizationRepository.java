package com.datapp.chambre.repository.devicestatus.organization;

import com.datapp.chambre.model.devicestatus.organization.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by kmluns on 25.06.2021
 */
public interface OrganizationRepository extends MongoRepository<Organization, String> {
}
