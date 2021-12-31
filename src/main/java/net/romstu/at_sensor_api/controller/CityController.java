package net.romstu.at_sensor_api.controller;

import lombok.AllArgsConstructor;
import net.romstu.at_sensor_api.controller.exceptions.ForbiddenException;
import net.romstu.at_sensor_api.domain.City;
import net.romstu.at_sensor_api.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
public class CityController {

    private final CityService cityService;
    private static final String ENDPOINT = "/cities";

    @PostMapping(ENDPOINT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void newCity(@RequestBody City city) {
        // can't be [ResponseStatusException] because Forbidden doesn't show message by default (not sure why)
        if (!cityService.addCity(city)) throw new ForbiddenException("City with that name already exists, please choose a different one.");
    }

    @PatchMapping(ENDPOINT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody City city) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var cityName = authentication.getName();
        if (cityName != null
                && city != null
                && city.getPassword() != null
                && !city.getPassword().isEmpty()) {
            cityService.update(cityName, city.getPassword());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide a non-null password to update to.");
        }
    }
}
