package dev.romahn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlertType {

    LEIPZIG_CAR_REGISTRATION_PICKUP("Leipzig car registration pickup"),
    LEIPZIG_TRAVEL_INDUSTRY_PICKUP("Leipzig travel industry pickup"),
    LEIPZIG_LOST_AND_FOUND_OFFICE("Leipzig Lost and found office"),
    LEIPZIG_EXCHANGE_DRIVING_LICENSE("leipzig exchange drivers license"),
    LEIPZIG_DRIVER_LICENSE_MATTERS("Leipzig drivers licence matters"),
    LEIPZIG_TRADE_AUTHORITY("Leipzig trade authority"),
    LEIPZIG_TRADE_PERMISSION_PICKUP("Leipzig trade permission pickup"),
    LEIPZIG_RESIDENCE_PERMIT_PICKUP("Leipzig ABH residence permit pickup");

    private final String description;
}
