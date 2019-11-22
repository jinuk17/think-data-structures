package io.jayden.thinkdast.profiler;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;

public class Profiler extends ApplicationFrame {

    private static final long serialVersionUID = 1L;

    public interface Timeable {

        public void setup(int n);

        public void timeMe(int n);
    }

    private Timeable timeable;

    /**
     * Constructs a new application frame.
     *
     * @param title the frame title.
     */
    public Profiler(String title, Timeable timeable) {
        super(title);
        this.timeable = timeable;
    }

    public XYSeries timingLoop(int startN, int endMills) {
        final XYSeries series = new XYSeries("Time (ms)");

        int n = startN;
        for (int i = 0; i < 20; i++) {

            timeIt(n);

            long total = 0;

            for(int j = 0; j < 10; j++) {
                total += timeIt(n);
            }
            System.out.println(n + " , " + total);

            if(total > 4) series.add(n, total);
            if(total > endMills) break;

            n *= 2;
        }
        return series;
    }

    private long timeIt(int n) {
        timeable.setup(n);
        final long startTime = System.currentTimeMillis();
        timeable.timeMe(n);
        final long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public void plotResults(XYSeries series) {
        double slope = estimateSlope(series);
        System.out.println("Estimated slop = " + slope);
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        final JFreeChart chart = ChartFactory.createXYLineChart("", "", "", dataset, PlotOrientation.VERTICAL, false, true, false);
        final XYPlot plot = chart.getXYPlot();
        final NumberAxis domainAxis = new LogarithmicAxis("Problem size (n)");
        final NumberAxis rangeAxis = new LogarithmicAxis("Runtime (ms)");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(rangeAxis);
        chart.setBackgroundPaint(Color.white);
        plot.setOutlinePaint(Color.black);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1000, 600));
        setContentPane(chartPanel);
        pack();
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }

    private double estimateSlope(XYSeries series) {
        SimpleRegression regression = new SimpleRegression();

        for (Object item: series.getItems()) {
            XYDataItem xy = (XYDataItem) item;
            regression.addData(Math.log(xy.getXValue()), Math.log(xy.getYValue()));
        }
        return regression.getSlope();
    }
}
