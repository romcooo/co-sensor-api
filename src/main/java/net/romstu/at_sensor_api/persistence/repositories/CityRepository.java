package net.romstu.at_sensor_api.persistence.repositories;

import net.romstu.at_sensor_api.persistence.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

    @Query("SELECT c FROM city c WHERE c.name = :name")
    CityEntity findByName(@Param("name") String name);

}
