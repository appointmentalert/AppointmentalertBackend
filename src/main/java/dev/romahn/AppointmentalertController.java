package dev.romahn;

import io.micronaut.http.annotation.*;

@Controller("/appointmentalert")
public class AppointmentalertController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}