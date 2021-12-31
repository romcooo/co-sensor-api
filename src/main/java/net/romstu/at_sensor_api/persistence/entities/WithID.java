package net.romstu.at_sensor_api.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class WithID {
    @Id @GeneratedValue
    protected Long id;
}
