package net.romstu.at_sensor_api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import net.romstu.at_sensor_api.persistence.entities.CityEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public City(CityEntity cityEntity) {
        this.name = cityEntity.getName();
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", password=[protected]" +
                '}';
    }
}
