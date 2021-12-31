package net.romstu.at_sensor_api.persistence.repositories;

import net.romstu.at_sensor_api.persistence.entities.SensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<SensorEntity, Long> {
}
