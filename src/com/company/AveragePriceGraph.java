package com.company;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class AveragePriceGraph {

   public static void makeGraph(TreeMap<Double, String> hmProdNameAndAveragePrice, String title) {
        JFrame frame = new JFrame(title);
        frame.add(createPanel(hmProdNameAndAveragePrice));
        frame.setSize(new Dimension(900, 900));
        frame.setVisible(true);
    }

    private static JPanel createPanel(TreeMap<Double, String> hmProdNameAndAveragePrice) {
        JFreeChart chart = createChart(createDataset(hmProdNameAndAveragePrice));
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setPreferredSize(new Dimension(600, 300));
        return panel;
    }

    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart("График средней цены разных видов товаров", "Виды товаров", "Средняя цена", dataset);
        chart.addSubtitle(new TextTitle("С учетом их количества на складе и их стоимости"));
        chart.setBackgroundPaint(Color.white);
        return chart;
    }

    private static CategoryDataset createDataset(TreeMap<Double, String> hmProdNameAndAveragePrice) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<Double, String> hm : hmProdNameAndAveragePrice.entrySet()){
            String prodName = hm.getValue();
            double averagePrice = hm.getKey();
            dataset.addValue(averagePrice, prodName, "");
        }
        return dataset;
    }
}
