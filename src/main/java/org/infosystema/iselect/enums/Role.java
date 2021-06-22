package org.infosystema.iselect.enums;

public enum Role {
    ADMIN,
    DIRECTOR,
    ACCOUNTANT,
	INSPECTOR;

    public static Role from(org.infosystema.iselect.model.Role role) {
        if (role == null) return null;
        if (role.getId() == null) return null;

        for (Role value : Role.values()) {
            if (value.ordinal() == role.getId()) return value;
        }

        return null;
    }
}
