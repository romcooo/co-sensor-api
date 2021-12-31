package net.romstu.at_sensor_api.persistence.repositories;

import net.romstu.at_sensor_api.persistence.entities.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
}
