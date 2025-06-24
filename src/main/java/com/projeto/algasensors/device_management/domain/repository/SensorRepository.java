package com.projeto.algasensors.device_management.domain.repository;

import com.projeto.algasensors.device_management.domain.model.Sensor;
import com.projeto.algasensors.device_management.domain.model.SensorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, SensorId> {


}
