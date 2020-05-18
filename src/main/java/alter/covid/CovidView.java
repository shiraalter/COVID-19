package alter.covid;

import javafx.scene.chart.LineChart;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CovidView extends JComponent {
    private ArrayList<CovidUpdateFeed.Result> covid;
    public ArrayList<Integer> deathArray = new ArrayList<>();       //new array list to hold values of the deaths


    public void setCovid(ArrayList<CovidUpdateFeed.Result> covid) {
        this.covid = covid;

        for (int i = 0; i < covid.size(); i++) {
            deathArray.add(covid.get(i).deaths);        // add deaths from each obj into new array list
        }
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (covid == null) {
            return;
        }

        //CREATE AXIS
        int maxYValue = 5000;                   //max cases
        int xLeftBoundary = 50;                 //left cushion
        int xRightBoundary = getWidth() - 50;   //set x axis right boundary/cushion
        int yTopBoundary = 10;                  //top cushion
        int yBottomBoundary = getHeight() - 50; //set y axis lower boundary

        //draw axis
        g.setColor(Color.BLACK);
        g.drawLine(xLeftBoundary, yTopBoundary, xLeftBoundary, yBottomBoundary);
        g.drawLine(xLeftBoundary, yBottomBoundary, xRightBoundary, yBottomBoundary);


        //calculate total pixels on x/y axis
        int totalXPixels = xRightBoundary - xLeftBoundary + 1;
        int totalYPixels = yBottomBoundary - yTopBoundary + 1;

        //calculate the increments
        int xIncrement = totalXPixels/covid.size();        // split by how many objects
        int yIncrement = totalYPixels/maxYValue;           //split y axis by max value (5000)

        //draw data for deaths
        drawDeathData(g, totalXPixels, totalYPixels,maxYValue, xLeftBoundary, yBottomBoundary, xIncrement, yIncrement);

    }

    private void drawDeathData(Graphics g, int totalX, int totalY, int maxYValue, int xLeft, int yBottom, int xIncrement, int yIncrement) {
        int x1, x2, y1, y2;

        //set origin coordinates
        x1 = xLeft;
        y1 = deathArray.get(0)* totalY/maxYValue;  //y coordinate of first death in array

        //compute and plot points for death data
        for (int i = 0; i < deathArray.size(); i++) {
            x2 = xLeft + xIncrement * i;
            y2 = deathArray.get(i) * totalY/maxYValue;

            g.setColor(Color.RED);                  //dots between data points
            g.fillOval(x2,y2,5,5);

            g.setColor(Color.BLUE);
            g.drawLine(x1,y1,x2,y2);

            //set coordinates for next data points
            x1 = x2;
            y1 = y2;

        }
    }
}
