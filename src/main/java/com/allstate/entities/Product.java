package com.allstate.entities;



import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name="products")
@Data
public class Product {

    private int id;
    private int version;
    private String name;
    private String stocknumber;
    private String description;
    private int rating;
    private int reviewcount;
    private int discount;
    private float listprice;
    private float actualprice;
    private int quantity;
    private boolean isrestrictive;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Version
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    @Column(nullable = false)
    public float getActualprice() {
        return actualprice;
    }
    public void setActualprice() {
        this.actualprice = getListprice() - (getDiscount()*getListprice()/100);
    }

}
