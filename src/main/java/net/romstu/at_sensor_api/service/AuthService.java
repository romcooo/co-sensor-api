package net.romstu.at_sensor_api.service;

import lombok.AllArgsConstructor;
import net.romstu.at_sensor_api.persistence.repositories.CityRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private CityRepository cityRepository;

    public static String encodePassword(String password) {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password);
    }

    public boolean checkAuthentication(String name, String password) {
        var city = cityRepository.findByName(name);
        if (city == null) return false;
        return PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(password, city.getPasswordHash());
    }

}
