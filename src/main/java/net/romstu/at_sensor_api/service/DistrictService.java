package net.romstu.at_sensor_api.service;

import lombok.AllArgsConstructor;
import net.romstu.at_sensor_api.domain.District;
import net.romstu.at_sensor_api.domain.Sensor;
import net.romstu.at_sensor_api.persistence.entities.DistrictEntity;
import net.romstu.at_sensor_api.persistence.entities.SensorEntity;
import net.romstu.at_sensor_api.persistence.repositories.CityRepository;
import net.romstu.at_sensor_api.persistence.repositories.DistrictRepository;
import net.romstu.at_sensor_api.persistence.repositories.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DistrictService {
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final SensorRepository sensorRepository;

    public List<District> getByCityName(String cityName) {
        var city = cityRepository.findByName(cityName);
        if (city != null) {
            return city.getDistrictEntities().stream().map(District::new).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public District getByID(Long id) {
        return districtRepository.findById(id).map(District::new)
                                 .orElse(null);
    }

    public District addDistrictToCity(String cityName, District district) {
        var cityEntity = cityRepository.findByName(cityName);
        if (cityEntity != null) {
            var districtEntity = new DistrictEntity(district, cityEntity);
            districtRepository.save(districtEntity);
            return new District(districtEntity);
        } else {
            return null;
        }
    }

    public Sensor addSensorToDistrict(District district, Sensor sensor) {
        var optionalDistrictEntity = districtRepository.findById(district.getId());
        if (optionalDistrictEntity.isPresent()) {
            var districtEntity = optionalDistrictEntity.get();
            var sensorEntity = new SensorEntity(sensor, districtEntity);
            sensorRepository.save(sensorEntity);
            return new Sensor(sensorEntity);
        } else {
            return null;
        }
    }
}
