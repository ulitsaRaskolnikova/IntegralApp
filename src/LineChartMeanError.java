import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChartMeanError extends ApplicationFrame{
    private final JFreeChart chart;
    public LineChartMeanError(String applicationTitle, String chartTitle, DefaultCategoryDataset dataset){
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Number of partitions", "Mean error",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );
        this.chart = lineChart;
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }
    public JFreeChart getChart(){
        return chart;
    }
}
