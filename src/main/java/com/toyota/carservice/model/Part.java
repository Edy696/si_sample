package com.toyota.carservice.model;

public class Part implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer partId;
    private Car car;
    private String partNumber;
    private String partName;
    private Service service;
    private Integer price;

    public Part() {

    }

    public Part(Integer partId, Car car, String partNumber, String partName) {
        super();
        this.partId = partId;
        this.car = car;
        this.partNumber = partNumber;
        this.partName = partName;
        this.service = service;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

//	public Part(Integer partId, Car car, String partNumber,
//			String partName) {		
//		this.partId.set(partId);
//		this.car = car;
//		this.partNumber.set(partNumber);
//		this.partName.set(partName);
//	}
//	
//	public Integer getPartId() {
//		return partId.get();
//	}
//	
//	public void setPartId(Integer partId) {
//		this.partId.set(partId);
//	}
//	
//	public String getPartNumber() {
//		return partNumber.get();
//	}
//	
//	public void setPartNumber(Integer partId) {
//		this.partId.set(partId);
//	}
//	
//	public String getPartName() {
//		return partNumber.get();
//	}
//	
//	public void setPartName(String partName) {
//		this.partName.set(partName);
//	}
//	
//	public Integer getPrice(){ 
//		return this.price.get();
//	}
//	
//	public void setPrice(Integer price) {
//		this.price.set(price);
//	}
//	
//	public Car getCar() {
//		return car;
//	}
//
//	public void setCar(Car car) {
//		this.car = car;
//	}
//	
//	public Service getService() {
//		return service;
//	}
//
//	public void setService(Service service) {
//		this.service = service;
//	}
//
//	public IntegerProperty partIdProperty(){
//		return partId;
//	}
//	
//	public StringProperty partNumberProperty() {
//		return partNumber;
//	}
//
//	public StringProperty partNameProperty() {
//		return partName;
//	}
//
//	public IntegerProperty priceProperty() {
//		return price;
//	}
}
