package com.allstate.services;

import com.allstate.entities.Product;
import com.allstate.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    IProductRepository repository;

    public Product create(Product product){
        return this.repository.save(product);
    }

    public Product findById(int id){
        return this.repository.findOne(id);
    }

    public Iterable<Product> findAll(){
        return this.repository.findAll();
    }


    public void deleteByID(int id){
        this.repository.delete(id);
    }

    public void updateByID(int id, Product updatedProduct){
        Product p1 = this.repository.findOne(id);
        p1.setName(updatedProduct.getName());
        p1.setDiscount(updatedProduct.getDiscount());
        p1.setListprice(updatedProduct.getListprice());
        p1.setActualprice();
        p1.setDescription(updatedProduct.getDescription());
        p1.setQuantity(updatedProduct.getQuantity());
        p1.setRating(updatedProduct.getRating());
        p1.setIsrestrictive(updatedProduct.isIsrestrictive());
        p1.setReviewcount(updatedProduct.getReviewcount());
        this.repository.save(p1);
    }

    public int getAvgRating(){
        return 0; //Write the logic here
    }
}
