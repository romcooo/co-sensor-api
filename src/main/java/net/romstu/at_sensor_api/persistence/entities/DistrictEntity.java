package net.romstu.at_sensor_api.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.romstu.at_sensor_api.domain.District;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "district")
@Table(name = "district")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistrictEntity extends WithID {
    @Column(unique=true)
    private String name;

    @ManyToOne
    @JoinColumn(name="city_id", nullable=false)
    private CityEntity cityEntity;

    @OneToMany(mappedBy = "districtEntity")
    private Set<SensorEntity> sensorEntities = new LinkedHashSet<>();

    public DistrictEntity(District district, CityEntity cityEntity) {
        this.name = district.getName();
        this.cityEntity = cityEntity;
    }
}
