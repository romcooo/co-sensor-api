package net.romstu.at_sensor_api.controller.model_assembler;

import net.romstu.at_sensor_api.controller.DistrictController;
import net.romstu.at_sensor_api.domain.District;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DistrictModelAssembler implements RepresentationModelAssembler<District, EntityModel<District>> {
    @Override
    public EntityModel<District> toModel(District district) {
        return EntityModel.of(district,
                              linkTo(methodOn(DistrictController.class).one(district.getId())).withSelfRel(),
                              linkTo(methodOn(DistrictController.class).allByCity()).withRel(
                                      "districts"),
                              linkTo(methodOn(DistrictController.class).allByDistrictID(district.getId())).withRel(
                                      "sensors"));
    }
}
