use ecommerce_test;

TRUNCATE table products;

INSERT INTO products(name,stocknumber,description,rating,reviewcount,listprice,discount,actualprice,quantity,isrestrictive)VALUES
('JBL','THW123','Speakers',4,100,180,10,150,3,1);
