package com.allstate.services;

import com.allstate.entities.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;




@SpringBootTest
@RunWith(SpringRunner.class)
@Sql(value = {"/sql/seed.sql"})
public class ProductServiceTest {

    @Autowired
    private ProductService service;


    public Product createProduct(){
        Product p1 = new Product();
        p1.setName("HP_Laptop");
        p1.setListprice(100);
        p1.setDiscount(10);
        p1.setStocknumber("THNF34234");
        p1.setActualprice();

        return p1;
    }

    @Test
    public void create_addsAProduct() throws Exception {
        Product CreatedProduct = this.service.create(createProduct());

        assertEquals(CreatedProduct.getName(),"HP_Laptop");
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void create_doesntAddAProductwithsameNameOrStockNumber() throws Exception {
        Product p1 = new Product();
        p1.setName("JBL");
        p1.setListprice(100);
        p1.setDiscount(10);
        p1.setStocknumber("THW123");
        p1.setActualprice();
        p1.setIsrestrictive(true);
        Product CreatedProduct = this.service.create(p1);

        assertNull(CreatedProduct);
    }

    @Test
    public void getProductById_returnsRecordByID() throws Exception {
        Product result = this.service.findById(1);
        assertEquals("JBL", result.getName());

    }


    @Test
    public void getProductById_throwsExceptionWhenRecordNotFound() throws Exception {
        Product result = this.service.findById(10);
        assertNull(result);

    }

    @Test
    public void findAll_returnsAllProducts() throws Exception {
        Iterable<Product> result = this.service.findAll();
        List<Product> resultList = (List<Product>) result;
        assertEquals(1, resultList.size());

    }

    @Test
    @Sql(value = {"/sql/truncate.sql"})
    public void findAll_returnsNoRecordWhenNoProductFound() throws Exception {
        Iterable<Product> result = this.service.findAll();
        List<Product> resultList = (List<Product>) result;
        assertEquals(0, resultList.size());

    }

    @Test // Refactor - Add Negative TC
    public void deleteById_deletesAProduct() throws Exception {
        this.service.deleteByID(1);
        assertNull(this.service.findById(1));

    }

    @Test
    public void updateById_updatesAProduct() throws Exception {
        this.service.updateByID(1,createProduct());
        assertEquals("HP_Laptop",this.service.findById(1).getName());

    }


    @Test
    public void getAvgRating_returnsRatingAvg() throws Exception {

        assertEquals(0,this.service.getAvgRating());

    }


}
