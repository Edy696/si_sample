package com.toyota.carservice.implement;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toyota.carservice.interfaces.PartDao;
import com.toyota.carservice.model.Car;
import com.toyota.carservice.model.Part;

@Repository
public class PartDaoImpl implements PartDao {

    @Autowired
    configSessionFactory csf;

    @Override
    public List<Part> findByCar(Car car) {
        String hql = "SELECT * FROM part WHERE CAR_ID = :CAR_ID";
        Query query = csf.getSf().openSession().createSQLQuery(hql);
        query.setParameter("CAR_ID", car.getCarId());
        return query.list();
    }

    @Override
    public List<Part> findByKeyword() {
        return null;
    }

    @Override
    public List<Part> delete(Part part) {
        csf.delete(part);
        return findByCar(part.getCar());
    }

    @Override
    public Part save(Part part) {
        if (part.getPartId() == null) {
            part.setPartId((Integer) csf.getSf().openSession().createSQLQuery("values ( next value for part_sequence )").uniqueResult());
            csf.create(part);
        } else {
            csf.update(part);
        }
        return part;
    }

}
