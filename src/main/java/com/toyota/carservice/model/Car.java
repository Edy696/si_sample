package com.toyota.carservice.model;

import java.util.HashSet;
import java.util.Set;

public class Car implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer carId;
    private String name;
    private String photo;
    private Set<Part> part = new HashSet<Part>(0);
    private Service service;

    public Car() {
    }

    public Car(Integer carId, String name, String photo) {
        super();
        this.carId = carId;
        this.name = name;
        this.photo = photo;
        this.part = part;
        this.service = service;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Set<Part> getPart() {
        return part;
    }

    public void setPart(Set<Part> part) {
        this.part = part;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

//	public Car(Integer carId, String name, String photo) {		
//		this.carId.set(carId);
//		this.name.set(name);
//		this.photo.set(photo);
//	}
//	
//	public Integer getCarId() {
//		return carId.get();
//	}
//	public void setCarId(Integer carId) {
//		this.carId.set(carId);
//	}
//	public IntegerProperty carIdProperty(){
//		return carId;
//	}	
//	public String getName() {
//		return name.get();
//	}
//	public void setName(String name) {
//		this.name.set(name);
//	}
//	public StringProperty nameProperty(){
//		return name;
//	}	
//	public String getPhoto() {
//		return photo.get();
//	}
//	public void setPhoto(String photo) {
//		this.photo.set(photo);
//	}
//	public StringProperty photoProperty(){
//		return photo;
//	}
//	public Set<Part> getPart() {
//		return part;
//	}
//
//	public void setPart(Set<Part> setPart) {
//		this.part = setPart;
//	}
//
//	public Service getService() {
//		return service;
//	}
//
//	public void setService(Service service) {
//		this.service = service;
//	}
}
