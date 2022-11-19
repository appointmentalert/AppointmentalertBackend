package dev.romahn.rest;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.security.Principal;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/appointmentalert")
public class AppointmentalertController {

    @Get(produces = MediaType.TEXT_PLAIN)
    public String get(Principal principal) {
        return principal.getName();
    }
}