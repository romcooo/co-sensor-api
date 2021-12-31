package net.romstu.at_sensor_api.controller;

import lombok.AllArgsConstructor;
import net.romstu.at_sensor_api.controller.exceptions.ForbiddenException;
import net.romstu.at_sensor_api.controller.exceptions.ResourceNotFoundException;
import net.romstu.at_sensor_api.controller.model_assembler.SensorModelAssembler;
import net.romstu.at_sensor_api.domain.Measurement;
import net.romstu.at_sensor_api.domain.Sensor;
import net.romstu.at_sensor_api.service.SensorService;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SensorController {
    private final SensorService sensorService;
    private final SensorModelAssembler sensorModelAssembler;
    private static final String ENDPOINT = "/sensors";
    private static final String ENDPOINT_WITH_ID = ENDPOINT + "/{id}";

    @GetMapping(ENDPOINT_WITH_ID)
    public EntityModel<Sensor> one(@PathVariable Long id) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var sensor = sensorService.getById(id);
        if (sensor != null) {
            checkAccess(authentication, sensor);
            return sensorModelAssembler.toModel(sensor);
        } else throw new ResourceNotFoundException();
    }

    @PostMapping(ENDPOINT_WITH_ID + "/measurements")
    public EntityModel<Measurement> addMeasurementToSensor(@PathVariable Long id,
                                                           @RequestBody Measurement measurement) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var sensor = sensorService.getById(id);
        if (sensor != null) {
            checkAccess(authentication, sensor);
            var createdMeasurement = sensorService.addMeasurementToSensor(sensor, measurement);
            // Could have its own assembler, but it's pointless since there's no direct resource exposed to link to
            return EntityModel.of(createdMeasurement);
        } else throw new ResourceNotFoundException();
    }

    private void checkAccess(Authentication authentication, Sensor sensor) throws ForbiddenException {
        if (!sensor.getCityName().equals(authentication.getName())) throw new ForbiddenException();
    }
}
