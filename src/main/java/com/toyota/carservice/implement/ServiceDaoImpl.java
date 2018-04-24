package com.toyota.carservice.implement;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toyota.carservice.interfaces.ServiceDao;
import com.toyota.carservice.model.Car;
import com.toyota.carservice.model.Service;

@Repository
public class ServiceDaoImpl implements ServiceDao {

    @Autowired
    configSessionFactory csf;

    @Override
    public List<Service> findByCar(Car car) {
        String hql = "SELECT * FROM SERVICE WHERE CAR_ID = :CAR_ID";
        Query query = csf.getSf().openSession().createSQLQuery(hql);
        query.setParameter("CAR_ID", car.getCarId());
        return query.list();
    }

    @Override
    public List<Service> delete(Service service) {
        csf.delete(service);
        return findByCar(service.getCar());
    }

    @Override
    public List<Service> save(Service service) {
        if (service.getServiceId() == null) {
            service.setServiceId((Integer) csf.getSf().openSession().createSQLQuery("values ( next value for service_sequence )").uniqueResult());
            csf.create(service);
        } else {
            csf.update(service);
        }
        return findByCar(service.getCar());
    }

}
