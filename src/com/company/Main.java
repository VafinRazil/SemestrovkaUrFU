package com.company;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws SQLException {
        String pathToFile = "/home/razil/Semestrovka/Каталог.csv";
        ArrayList<Product> catalogs = Parser.getListCatalogs(pathToFile);
        DbHandler dbHandler = DbHandler.getInstance();
        for (int i = 0; i < catalogs.size(); i++) {
            dbHandler.addProduct(catalogs.get(i), i);
        }
        System.out.println("TASK 2");
        ArrayList<Product> lamps = dbHandler.getCatalogsByCondition("WHERE productName LIKE 'Лампа инфракрасная%'");
        System.out.printf("Средняя стоимость инфракрасной лампы : %f", Parser.getAveragePrice(lamps));

        ArrayList<Product> topProducts = dbHandler.getCatalogsByCondition("WHERE number > 10 " +
                                                                          "ORDER BY retailPrice DESC " +
                                                                          "LIMIT 5");
        System.out.println("\n\nTASK 3");
        System.out.println("5 самых дорогих товаров, которых на складе больше 10:");
        for (Product product : topProducts){
            System.out.println(product.toString());
        }

        ArrayList<Product> allProducts = dbHandler.getCatalogsByCondition("");
        TreeMap<Double, String> hmProdNameAndAveragePrice = Parser.getHMWithTypesAndAveragePrice(allProducts);
        AveragePriceGraph.makeGraph(hmProdNameAndAveragePrice, "");
    }
}
