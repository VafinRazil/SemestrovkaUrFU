package com.company;

public class ProductParams {

    private double productRetailPrice;
    private int countProduct;

    public ProductParams(double productRetailPrice, int productCount){
        this.productRetailPrice = productRetailPrice;
        this.countProduct = productCount;
    }

    public void setProductParams(double productRetailPrice, int countProduct){
        this.productRetailPrice += productRetailPrice;
        this.countProduct += countProduct;
    }

    public double getProductRetailPrice(){return productRetailPrice;}

    public int getCountProduct(){return countProduct;}
}
