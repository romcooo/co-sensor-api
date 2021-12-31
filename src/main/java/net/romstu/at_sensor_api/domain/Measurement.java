package net.romstu.at_sensor_api.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.romstu.at_sensor_api.persistence.entities.MeasurementEntity;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Measurement {
    private Timestamp timestamp;
    private Double level;

    public Measurement(MeasurementEntity measurementEntity) {
        this.timestamp = measurementEntity.getTimestamp();
        this.level = measurementEntity.getLevel();
    }
}
