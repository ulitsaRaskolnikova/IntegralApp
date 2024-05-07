import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
//import org.jfree.chart.ui.RefineryUtilities;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("[a,b]\na = ");
        double a = scanner.nextInt();
        System.out.print("b = ");
        double b = scanner.nextInt();
        DefaultCategoryDataset datasetMAE = new DefaultCategoryDataset();
        DefaultCategoryDataset datasetMSE = new DefaultCategoryDataset();
        for (SquareCalcMethodType methodType : SquareCalcMethodType.values()){
            for (int n = 15; n <= 100; n+=5){
                List<Double> partition = getUniformPartition(a, b, n);
                double sum = summarize(partition, methodType.getMethod());
                datasetMAE.addValue(Math.abs(sum - 0.5), methodType.name(), Integer.valueOf(n));
                datasetMSE.addValue(sum*sum, methodType.name(), Integer.valueOf(n));
            }
        }
        LineChartMeanError chartMAE = new LineChartMeanError(
                "MAE_chart",
                "MAE chart. Square calculation methods vs number of partitions on [" + a + ", " + b + "].",
                datasetMAE
        );
        chartMAE.pack();
        chartMAE.setVisible(true);
        LineChartMeanError chartMSE = new LineChartMeanError(
                "MSE_chart",
                "MSE chart. Square calculation methods vs number of partitions on [" + a + ", " + b + "].",
                datasetMAE
        );
        chartMSE.pack();
        chartMSE.setVisible(true);
        ChartUtils.saveChartAsJPEG(new File("MAE_chart.jpeg"), chartMAE.getChart(), 1080, 720);
        ChartUtils.saveChartAsJPEG(new File("MSE_chart.jpeg"), chartMSE.getChart(), 1080, 720);
    }
    public static List<Double> getUniformPartition(double left, double right, int n){
        List<Double> partition = new ArrayList<>();
        for (int i = 0; i <= n; i++){
            partition.add(left + (right - left) * i/n);
        }
        return partition;
    }
    public static List<Double> getPartition(double left, double right, double rang){
        Stream
        List<Double> partition = new ArrayList<>();
        double x = left;
        while (x < right){
            partition.add(x);
            x = x + rang * Math.random();
        }
        partition.add(right);
        return partition;
    }
    public static double summarize(List<Double> partition, SquareCalcMethod method){
        double sum = 0;
        for (int i = 1; i < partition.size(); i++){
            sum += method.calculateSquare(partition.get(i - 1), partition.get(i));
        }
        return sum;
    }
}