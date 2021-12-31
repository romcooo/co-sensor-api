package net.romstu.at_sensor_api.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.romstu.at_sensor_api.domain.Measurement;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity(name = "measurement")
@Table(name = "measurement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementEntity extends WithID {
    private Timestamp timestamp;
    private Double level;

    @ManyToOne
    @JoinColumn(name="sensor_id", nullable = false)
    private SensorEntity sensorEntity;

    public MeasurementEntity(Measurement measurement, SensorEntity sensorEntity) {
        this.timestamp = measurement.getTimestamp();
        this.level = measurement.getLevel();
        this.sensorEntity = sensorEntity;
    }
}
