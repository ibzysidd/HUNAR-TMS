package com.hunar.api.repository;

import com.hunar.api.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findAllByIdTypeMeasure(int idTypeMeasure);
}
