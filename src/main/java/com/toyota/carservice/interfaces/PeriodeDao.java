package com.toyota.carservice.interfaces;

import java.util.List;

import com.toyota.carservice.model.Car;
import com.toyota.carservice.model.Periode;
import com.toyota.carservice.model.Service;

public interface PeriodeDao {

    List<Service> findByCarAndPeriod(Car car, Integer periode);

    List<Service> delete(Periode periode);

    List<Service> save(Periode periode);
}
