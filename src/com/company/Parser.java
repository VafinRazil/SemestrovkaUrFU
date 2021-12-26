package com.company;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class Parser {

    public static ArrayList<Product> getListCatalogs(String pathToFile){
        final Path file = Paths.get(pathToFile);
        ArrayList<Product> listCatalogs = new ArrayList<>();
        try { listCatalogs = Files.lines(file, Charset.forName("Windows-1251")).
                    skip(1).
                    map(line -> line.split(";")).
                    map(line -> new Product(line[0],
                            line[1],
                            Double.parseDouble(line[2]),
                            Integer.parseInt(line[3]))).
                    collect(Collectors.toCollection(ArrayList<Product>::new));

        }
        catch (Exception exception){ exception.printStackTrace(); }
        return listCatalogs;
    }

    public static TreeMap<Double, String> getHMWithTypesAndAveragePrice(ArrayList<Product> products){
        HashMap<String, ProductParams> hmProdNameAndParams = fillHMValues(products);
        TreeMap<Double, String> hmProdNameAndAveragePrice = new TreeMap<>();
        for (var productParams : hmProdNameAndParams.entrySet()){
            String prodName = productParams.getKey();
            ProductParams prodParams = productParams.getValue();
            double averagePrice = prodParams.getProductRetailPrice() / prodParams.getCountProduct();
            if (averagePrice > 1000 && averagePrice < 2000 && hmProdNameAndAveragePrice.size() < 10) {
                hmProdNameAndAveragePrice.put(averagePrice, prodName);
            }
        }
        return hmProdNameAndAveragePrice;
    }

    private static HashMap<String, ProductParams> fillHMValues(ArrayList<Product> products) {
        HashMap<String, ProductParams> hmProdNameAndParams = new HashMap<>();
        for(Product product : products){
            String productName = product.getNameOfProduct().split(" ")[0];
            double productPrice = product.getRetailPrice();
            int countProduct = product.getStockBalance();
            if (incorrectEnding(productName)) continue;
            if(!hmProdNameAndParams.containsKey(productName)){
                hmProdNameAndParams.put(productName, new ProductParams(productPrice, countProduct));
            }
            else {
                hmProdNameAndParams.get(productName).setProductParams(productPrice, countProduct);
            }
        }
        return hmProdNameAndParams;
    }

    private static boolean incorrectEnding(String productName) {
        HashSet<String> endings = new HashSet<>(Arrays.asList("ая","ое","ие","ые","ый","ий","ой","ся"));
        String end = productName.toLowerCase().substring(productName.length() - 2);
        return endings.contains(end);
    }

    public static double getAveragePrice(ArrayList<Product> products){
        double totalSum = 0;
        int countLamps = 0;
        for (Product lamp : products){
            totalSum += lamp.getRetailPrice() * lamp.getStockBalance();
            countLamps += lamp.getStockBalance();
        }
        return  totalSum / countLamps;
    }
}
