package dev.romahn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlertType {

    LEIPZIG_RESIDENCE_PERMIT_PICKUP("Leipzig residence permit pickup"),
    LEIPZIG_DRIVERS_LICENCE_PICKUP("Leipzig residence permit pickup");

    private final String description;
}
