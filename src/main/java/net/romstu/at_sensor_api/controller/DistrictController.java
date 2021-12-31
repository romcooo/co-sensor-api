package net.romstu.at_sensor_api.controller;

import lombok.AllArgsConstructor;
import net.romstu.at_sensor_api.controller.exceptions.ForbiddenException;
import net.romstu.at_sensor_api.controller.exceptions.ResourceNotFoundException;
import net.romstu.at_sensor_api.controller.model_assembler.DistrictModelAssembler;
import net.romstu.at_sensor_api.controller.model_assembler.SensorModelAssembler;
import net.romstu.at_sensor_api.domain.District;
import net.romstu.at_sensor_api.domain.Sensor;
import net.romstu.at_sensor_api.service.DistrictService;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class DistrictController {
    private final DistrictService districtService;
    private final DistrictModelAssembler districtModelAssembler;
    private final SensorModelAssembler sensorModelAssembler;
    private static final String ENDPOINT_ALL = "/districts";
    private static final String ENDPOINT_ONE = ENDPOINT_ALL + "/{id}";
    private static final String ENDPOINT_ONE_SENSORS = ENDPOINT_ONE + "/sensors";

    @GetMapping(ENDPOINT_ALL)
    public List<EntityModel<District>> allByCity() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var districts = districtService.getByCityName(authentication.getName());
        // could be CollectionModel instead of list
        return districts.stream()
                        .map(districtModelAssembler::toModel)
                        .collect(Collectors.toList());
    }

    @PostMapping(ENDPOINT_ALL)
    public EntityModel<District> addDistrict(@RequestBody District district) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var createdDistrict = districtService.addDistrictToCity(authentication.getName(), district);
        return districtModelAssembler.toModel(createdDistrict);
    }

    @GetMapping(ENDPOINT_ONE)
    public EntityModel<District> one(@PathVariable Long id) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var district = districtService.getByID(id);
        if (district != null) {
            checkAccess(authentication, district);
            return districtModelAssembler.toModel(district);
        } else throw new ResourceNotFoundException();
    }

    @GetMapping(ENDPOINT_ONE_SENSORS)
    public List<EntityModel<Sensor>> allByDistrictID(@PathVariable Long id) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var district = districtService.getByID(id);
        if (district != null) {
            checkAccess(authentication, district);
            var sensors = district.getSensors();
            return sensors.stream().map(sensorModelAssembler::toModel).collect(Collectors.toList());
        } throw new ResourceNotFoundException();
    }

    @PostMapping(ENDPOINT_ONE_SENSORS)
    public EntityModel<Sensor> addSensorToDistrict(@PathVariable Long id,
                                                   @RequestBody Sensor sensor) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var district = districtService.getByID(id);
        if (district != null) {
            checkAccess(authentication, district);
            var createdSensor = districtService.addSensorToDistrict(district, sensor);
            return sensorModelAssembler.toModel(createdSensor);
        } else throw new ResourceNotFoundException();
    }

    private void checkAccess(Authentication authentication, District district) throws ForbiddenException {
        if (!district.getCityName().equals(authentication.getName())) throw new ForbiddenException();
    }
}
