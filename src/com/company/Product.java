package com.company;

public class Product {
    private String nameOfProduct;
    private String article;
    private double retailPrice;
    private int stockBalance;

    public Product(String nameOfProduct,
                   String article,
                   double retailPrice,
                   int stockBalance)
    {
        this.nameOfProduct = nameOfProduct;
        this.article = article;
        this.retailPrice= retailPrice;
        this.stockBalance = stockBalance;
    }

    public int getStockBalance(){
        return stockBalance;
    }

    public String getNameOfProduct(){
        return nameOfProduct;
    }

    public String getArticle(){
        return article;
    }

    public double getRetailPrice(){
        return retailPrice;
    }

    @Override
    public String toString(){
        return String.format("%s - %s - %.2f - %d", nameOfProduct, article, retailPrice, stockBalance);
    }
}
