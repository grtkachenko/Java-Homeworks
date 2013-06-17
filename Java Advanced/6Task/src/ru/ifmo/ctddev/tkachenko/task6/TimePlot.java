package ru.ifmo.ctddev.tkachenko.task6;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 14.05.13
 * Time: 0:24
 */

/**
 * This class is visualizing the dependence of number of threads and time
 *
 * @see XYPlot
 */
public class TimePlot extends JFrame {
    /**
     * plot window width
     */
    private final int WIDTH = 600;
    /**
     * plot windows height
     */
    private final int HEIGHT = 600;
    /**
     * max value in RGB color model
     */
    private final int MAX_RGB_VALUE = 255;
    /**
     * plot in XY axis
     */
    private XYPlot plot = new XYPlot();
    /**
     * random generator that helps us to generate the colors of the lines
     *
     * @see TimePlot#addDataSource(de.erichseifert.gral.data.DataTable)
     */
    private Random random = new Random();

    /**
     * Default constructor. It sets the window size and add plot to the pane
     */
    public TimePlot() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        getContentPane().add(new InteractivePanel(plot));
    }

    /**
     * Adds some data to out plot(e.g. when we gonna draw a new dependence line)
     *
     * @param dataTable data that draws in plot
     */
    public void addDataSource(DataTable dataTable) {
        plot.add(dataTable);
        plot.setLineRenderer(dataTable, new DefaultLineRenderer2D());
        Color color = new Color(random.nextInt(MAX_RGB_VALUE), random.nextInt(MAX_RGB_VALUE), random.nextInt(MAX_RGB_VALUE));
        plot.getPointRenderer(dataTable).setSetting(PointRenderer.COLOR, color);
        plot.getLineRenderer(dataTable).setSetting(LineRenderer.COLOR, color);
    }
}
