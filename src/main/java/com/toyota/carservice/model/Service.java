package com.toyota.carservice.model;

public class Service implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer serviceId;
    private String name;
    private Car car;
    private Part part;
    private String periode;

    public Service() {

    }

    public Service(Integer serviceId, String name, Car car,
            Part part) {
        this.serviceId = serviceId;
        this.name = name;
        this.car = car;
        this.part = part;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

}
