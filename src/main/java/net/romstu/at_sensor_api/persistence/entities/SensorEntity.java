package net.romstu.at_sensor_api.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.romstu.at_sensor_api.domain.Sensor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "sensor")
@Table(name = "sensor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorEntity extends WithID {
    @Column(unique=true)
    private String name;

    @ManyToOne
    @JoinColumn(name="district_id", nullable = false)
    private DistrictEntity districtEntity;

    @OneToMany(mappedBy = "sensorEntity")
    private Set<MeasurementEntity> measurementEntities = new LinkedHashSet<>();

    public SensorEntity(Sensor sensor, DistrictEntity districtEntity) {
        this.name = sensor.getName();
        this.districtEntity = districtEntity;
    }
}
