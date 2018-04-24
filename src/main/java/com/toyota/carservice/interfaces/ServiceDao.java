package com.toyota.carservice.interfaces;

import java.util.List;

import com.toyota.carservice.model.Car;
import com.toyota.carservice.model.Service;

public interface ServiceDao {

    List<Service> findByCar(Car car);

    List<Service> delete(Service service);

    List<Service> save(Service service);
}
