package net.romstu.at_sensor_api.controller.model_assembler;

import net.romstu.at_sensor_api.controller.SensorController;
import net.romstu.at_sensor_api.domain.Sensor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SensorModelAssembler implements RepresentationModelAssembler<Sensor, EntityModel<Sensor>> {
    @Override
    public EntityModel<Sensor> toModel(Sensor sensor) {
        return EntityModel.of(sensor,
                              linkTo(methodOn(SensorController.class).one(sensor.getId())).withSelfRel());
    }
}
