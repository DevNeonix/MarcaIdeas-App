package com.jolver.nestor.marcaideas.Models;

/**
 * Created by andre on 16/03/2018.
 */

public class Event {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String image_url;
    private String fecha_inicio;
    private String fecha_fin;
    private Double lat;
    private Double lon;
    private String ubicacion;
    private String created_at;
    private String updated_at;
    private String deleted_at;

    public Event(Integer id, String nombre, String descripcion, String image_url, String fecha_inicio, String fecha_fin, Double lat, Double lon, String ubicacion, String created_at, String updated_at, String deleted_at) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.image_url = image_url;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.lat = lat;
        this.lon = lon;
        this.ubicacion = ubicacion;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }
}
