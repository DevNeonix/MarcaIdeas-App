package com.jolver.nestor.marcaideas.Models;

/**
 * Created by Andre on 2/4/2018.
 */

public class Imagenes {
    int id;
    int lugar_id;
    String img;

    public Imagenes() {
    }

    public Imagenes(int id, int lugar_id, String img) {
        this.id = id;
        this.lugar_id = lugar_id;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLugar_id() {
        return lugar_id;
    }

    public void setLugar_id(int lugar_id) {
        this.lugar_id = lugar_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
