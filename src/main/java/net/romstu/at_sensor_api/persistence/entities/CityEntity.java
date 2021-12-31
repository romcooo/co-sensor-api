package net.romstu.at_sensor_api.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.romstu.at_sensor_api.domain.City;
import net.romstu.at_sensor_api.service.AuthService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "city")
@Table(name = "city")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityEntity extends WithID {
    @Column(unique=true)
    private String name;
    private String passwordHash;

    @OneToMany(mappedBy = "cityEntity")
    private Set<DistrictEntity> districtEntities = new LinkedHashSet<>();

    public CityEntity(City city) {
        this.name = city.getName();
        this.passwordHash = AuthService.encodePassword(city.getPassword());
    }

    public void updatePassword(String password) {
        this.passwordHash = AuthService.encodePassword(password);
    }

}
