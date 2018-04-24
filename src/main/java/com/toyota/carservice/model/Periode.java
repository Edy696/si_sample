package com.toyota.carservice.model;

public class Periode implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer periodeId;
    private Integer periode;
    private Service service;

    public Periode() {

    }

    public Periode(Integer periodeId, Integer periode,
            Service service) {
        this.setPeriodeId(periodeId);
        this.periode = periode;
        this.setService(service);
    }

    public Integer getPeriodeId() {
        return periodeId;
    }

    public void setPeriodeId(Integer periodeId) {
        this.periodeId = periodeId;
    }

    public Integer getPeriode() {
        return periode;
    }

    public void setPeriode(Integer periode) {
        this.periode = periode;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
