package com.toyota.carservice.interfaces;

import java.util.List;

import com.toyota.carservice.model.Car;

public interface CarDao {

    List<Car> findAll();

    List<Car> delete(Car car);

    Car save(Car car);
}
