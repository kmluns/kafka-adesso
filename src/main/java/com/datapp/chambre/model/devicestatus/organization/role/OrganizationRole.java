package com.datapp.chambre.model.devicestatus.organization.role;

import lombok.experimental.Accessors;

/**
 * Created by kmluns on 25.06.2021
 */
@Accessors (chain = true)
public enum OrganizationRole {
	
	FOUNDER("FOUNDER"),
	FIELD_OFFICER("FIELD_OFFICER"),
	OBSERVER("OBSERVER");

	private final String text;

	OrganizationRole(final String text) {
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return text;
	}
}
