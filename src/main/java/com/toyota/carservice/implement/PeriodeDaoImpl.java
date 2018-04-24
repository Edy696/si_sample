package com.toyota.carservice.implement;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toyota.carservice.interfaces.PeriodeDao;
import com.toyota.carservice.model.Car;
import com.toyota.carservice.model.Periode;
import com.toyota.carservice.model.Service;

@Repository
public class PeriodeDaoImpl implements PeriodeDao {

    @Autowired
    configSessionFactory csf;

    @Override
    public List<Service> findByCarAndPeriod(Car car, Integer periode) {
        String hql = "SELECT ALL FROM PERIODE WHERE CAR_ID=:carId AND PERIODE:=periode";
        Query query = csf.getSf().openSession().createQuery(hql);
        query.setParameter(":carId", car.getCarId());
        query.setParameter(":periode", periode);
        return query.list();
    }

    @Override
    public List<Service> delete(Periode periode) {
        csf.delete(periode);
        return null;
    }

    @Override
    public List<Service> save(Periode periode) {
        if (periode.getPeriodeId() == null) {
            csf.create(periode);
        } else {
            csf.update(periode);
        }
        return null;
    }

}
