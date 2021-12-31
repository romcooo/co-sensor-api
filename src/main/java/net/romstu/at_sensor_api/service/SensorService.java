package net.romstu.at_sensor_api.service;

import lombok.AllArgsConstructor;
import net.romstu.at_sensor_api.domain.Measurement;
import net.romstu.at_sensor_api.domain.Sensor;
import net.romstu.at_sensor_api.persistence.entities.MeasurementEntity;
import net.romstu.at_sensor_api.persistence.repositories.DistrictRepository;
import net.romstu.at_sensor_api.persistence.repositories.MeasurementRepository;
import net.romstu.at_sensor_api.persistence.repositories.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SensorService {

    private final DistrictRepository districtRepository;
    private final SensorRepository sensorRepository;
    private final MeasurementRepository measurementRepository;

    public List<Sensor> getAllByDistrictID(Long districtID) {
        var district = districtRepository.findById(districtID);
        return district.map(districtEntity -> districtEntity.getSensorEntities()
                                                            .stream()
                                                            .map(Sensor::new)
                                                            .collect(Collectors.toList()))
                       .orElse(Collections.emptyList());
    }

    public Sensor getById(Long id) {
        return sensorRepository.findById(id).map(Sensor::new).orElse(null);
    }

    public Measurement addMeasurementToSensor(Sensor sensor, Measurement measurement) {
        var optionalSensorEntity = sensorRepository.findById(sensor.getId());
        if (optionalSensorEntity.isPresent()) {
            var sensorEntity = optionalSensorEntity.get();
            var measurementEntity = new MeasurementEntity(measurement, sensorEntity);
            measurementRepository.save(measurementEntity);
            return new Measurement(measurementEntity);
        } else return null;
    }
}
