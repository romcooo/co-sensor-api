package net.romstu.at_sensor_api.test_dto;

import lombok.Data;
import net.romstu.at_sensor_api.domain.City;

// Needed because of write-only password on the original DTO class [City]
@Data
public class TestRequestCity {
    private String name;
    private String password;

    public TestRequestCity(String password) {
        this.password = password;
    }

    public TestRequestCity(City city) {
        this.name = city.getName();
        this.password = city.getPassword();
    }
}
