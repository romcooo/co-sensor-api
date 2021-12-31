package net.romstu.at_sensor_api.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.romstu.at_sensor_api.domain.City;
import net.romstu.at_sensor_api.persistence.entities.CityEntity;
import net.romstu.at_sensor_api.persistence.repositories.CityRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public boolean addCity(City city) {
        var existing = cityRepository.findByName(city.getName());
        if (existing == null) {
            cityRepository.save(new CityEntity(city));
            return true;
        } else return false;
    }

    public void update(String cityName, String newPassword) {
        var city = cityRepository.findByName(cityName);
        city.updatePassword(newPassword);
        cityRepository.save(city);
    }

}
