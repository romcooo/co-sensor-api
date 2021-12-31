package net.romstu.at_sensor_api.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.romstu.at_sensor_api.persistence.entities.DistrictEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class District {
    private Long id;
    private String name;
    private String cityName;
    private List<Sensor> sensors;

    public District(DistrictEntity districtEntity) {
        this.id = districtEntity.getId();
        this.name = districtEntity.getName();
        this.sensors = districtEntity.getSensorEntities().stream().map(Sensor::new).collect(Collectors.toList());
        this.cityName = districtEntity.getCityEntity().getName();
    }
}
