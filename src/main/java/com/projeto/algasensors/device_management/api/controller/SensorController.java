package com.projeto.algasensors.device_management.api.controller;

import com.projeto.algasensors.device_management.api.model.SensorInput;
import com.projeto.algasensors.device_management.api.model.SensorOutput;
import com.projeto.algasensors.device_management.common.IdGenerator;
import com.projeto.algasensors.device_management.domain.model.Sensor;
import com.projeto.algasensors.device_management.domain.model.SensorId;
import com.projeto.algasensors.device_management.domain.repository.SensorRepository;
import io.hypersistence.tsid.TSID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorRepository repository;

    public SensorController(SensorRepository repository) {
        this.repository = repository;
    }

   @GetMapping
   public Page<SensorOutput> search(@PageableDefault Pageable pageable) {
        Page<Sensor> sensors = repository.findAll(pageable);

        return sensors.map(this::convertToModel);
   }

    @GetMapping("{sensorId}")
    public SensorOutput getById(@PathVariable TSID sensorId) {
        Sensor sensor = repository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return convertToModel(sensor);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SensorOutput create(@RequestBody SensorInput input) {
        Sensor sensor = Sensor.builder().
                id(new SensorId(IdGenerator.generateTSID())).
                name(input.getName()).
                ip(input.getIp()).
                location(input.getLocation()).
                protocol(input.getProtocol()).
                model(input.getModel()).
                enable(false).
                build();

        sensor = repository.saveAndFlush(sensor);

        return convertToModel(sensor);
    }

    private SensorOutput convertToModel(Sensor sensor) {
        return SensorOutput.builder().
                id(sensor.getId().getValue()).
                name(sensor.getName()).
                ip(sensor.getIp()).
                location(sensor.getLocation()).
                protocol(sensor.getProtocol()).
                model(sensor.getModel()).
                enable(sensor.getEnable()).
                build();
    }
}
