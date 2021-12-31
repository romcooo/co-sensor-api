package net.romstu.at_sensor_api.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.romstu.at_sensor_api.persistence.entities.SensorEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Sensor {
    private Long id;
    private String name;
    private List<Measurement> measurements;
    private String districtName;
    private String cityName;

    public Sensor(SensorEntity sensorEntity) {
        this.id = sensorEntity.getId();
        this.name = sensorEntity.getName();
        this.measurements = sensorEntity.getMeasurementEntities().stream().map(Measurement::new).collect(
                Collectors.toList());
        this.districtName = sensorEntity.getDistrictEntity().getName();
        this.cityName = sensorEntity.getDistrictEntity().getCityEntity().getName();
    }
}
