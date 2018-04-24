package com.toyota.carservice.interfaces;

import java.util.List;

import com.toyota.carservice.model.Car;
import com.toyota.carservice.model.Part;

public interface PartDao {
	List<Part> findByCar(Car car);
	List<Part> findByKeyword();
	List<Part> delete(Part part);
	Part save(Part part);
}
