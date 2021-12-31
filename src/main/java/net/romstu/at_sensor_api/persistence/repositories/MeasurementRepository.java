package net.romstu.at_sensor_api.persistence.repositories;

import net.romstu.at_sensor_api.persistence.entities.MeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<MeasurementEntity, Long> {
}
