package net.romstu.at_sensor_api.test_data;

import lombok.AllArgsConstructor;
import net.romstu.at_sensor_api.domain.City;
import net.romstu.at_sensor_api.domain.District;
import net.romstu.at_sensor_api.domain.Measurement;
import net.romstu.at_sensor_api.domain.Sensor;
import net.romstu.at_sensor_api.service.CityService;
import net.romstu.at_sensor_api.service.DistrictService;
import net.romstu.at_sensor_api.service.SensorService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@AllArgsConstructor
public class TestDataLoader {

    private final CityService cityService;
    private final DistrictService districtService;
    private final SensorService sensorService;

    @EventListener
    public void startup(ApplicationReadyEvent e) {
        var wien = new City();
        wien.setName("Wien");
        wien.setPassword("testpass");
        cityService.addCity(wien);


        var wahring = new District();
        wahring.setName("Wahring");
        var wahringWithID = districtService.addDistrictToCity(wien.getName(), wahring);


        var wahringSensor1 = new Sensor();
        wahringSensor1.setName("wahring_1");
        var wahringS1WithID = districtService.addSensorToDistrict(wahringWithID, wahringSensor1);

        var wahringSensor2 = new Sensor();
        wahringSensor2.setName("wahring_2");
        districtService.addSensorToDistrict(wahringWithID, wahringSensor2);


        var wahringSensor1Measurement1 = new Measurement();
        wahringSensor1Measurement1.setTimestamp(new Timestamp(System.currentTimeMillis()));
        wahringSensor1Measurement1.setLevel(2.13);
        sensorService.addMeasurementToSensor(wahringS1WithID, wahringSensor1Measurement1);

        var wahringSensor1Measurement2 = new Measurement();
        wahringSensor1Measurement2.setTimestamp(new Timestamp(System.currentTimeMillis()));
        wahringSensor1Measurement2.setLevel(2.14);
        sensorService.addMeasurementToSensor(wahringS1WithID, wahringSensor1Measurement1);
    }
}
