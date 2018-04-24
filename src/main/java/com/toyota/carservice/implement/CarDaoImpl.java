package com.toyota.carservice.implement;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toyota.carservice.interfaces.CarDao;
import com.toyota.carservice.model.Car;

@Repository
public class CarDaoImpl implements CarDao {

    @Autowired
    configSessionFactory csf;

    @Override
    public List<Car> findAll() {
        Session session = csf.getSf().openSession();
        List<Car> result = session.createCriteria(Car.class).list();
        session.close();
        return result;
    }

    @Override
    public List<Car> delete(Car car) {
        csf.delete(car);
        return findAll();
    }

    @Override
    public Car save(Car car) {
        if (car.getCarId() == null || car.getCarId().equals("")) {
            car.setCarId((Integer) csf.getSf().openSession().createSQLQuery("values ( next value for car_sequence )").uniqueResult());
            csf.create(car);
        } else {
            csf.update(car);
        }
        return car;
    }

}
