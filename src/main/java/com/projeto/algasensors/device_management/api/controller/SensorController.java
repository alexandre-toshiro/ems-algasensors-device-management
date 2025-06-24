package com.projeto.algasensors.device_management.api.controller;

import com.projeto.algasensors.device_management.api.model.SensorInput;
import com.projeto.algasensors.device_management.common.IdGenerator;
import com.projeto.algasensors.device_management.domain.model.Sensor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sensor create(@RequestBody SensorInput input) {
        return Sensor.builder().
                ID(IdGenerator.generateTSID()).
                name(input.getName()).
                ip(input.getIp()).
                location(input.getLocation()).
                protocol(input.getProtocol()).
                model(input.getModel()).
                enable(true).
                build();
    }
}
