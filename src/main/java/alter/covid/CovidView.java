package alter.covid;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CovidView extends JComponent {
    private ArrayList<CovidUpdateFeed.Result> covid;
    public ArrayList<Integer> deathArray = new ArrayList<>();
    public ArrayList<Integer> recoveredArray = new ArrayList<>();
    public ArrayList<Integer> confirmedArray = new ArrayList<>();



    public void setCovid(ArrayList<CovidUpdateFeed.Result> covid) {
        this.covid = covid;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (covid == null) {
            return;
        }

        // populate arrays from the covid list
        for (int i = 0; i < covid.size(); i++) {
            deathArray.add(covid.get(i).deaths);
            recoveredArray.add(covid.get(i).recovered);
            confirmedArray.add(covid.get(i).confirmed);
        }

        //CREATE AXIS
        int maxYValue = 300000;                   //max cases
        int xLeftBoundary = 70;                 //left cushion
        int xRightBoundary = getWidth() - 70;   //set x axis right boundary/cushion
        int yTopBoundary = 10;                  //top cushion
        int yBottomBoundary = getHeight() - 70; //set y axis lower boundary


        //draw axis
        g.setColor(Color.BLACK);
        g.drawLine(xLeftBoundary, yBottomBoundary, xLeftBoundary, yTopBoundary);
        g.drawLine(xLeftBoundary, yBottomBoundary, xRightBoundary, yBottomBoundary);

        labelAxis(g, xLeftBoundary, yBottomBoundary);


        //calculate total pixels on x/y axis
        int totalXPixels = xRightBoundary - xLeftBoundary + 1;
        int totalYPixels = yBottomBoundary - yTopBoundary + 1;

        //calculate the increments
        int xIncrement = totalXPixels/covid.size();        // split by how many objects
        int yIncrement = totalYPixels/maxYValue;           //split y axis by max value (5000)

        //draw graphs
        drawDeathData(g, totalXPixels, totalYPixels,maxYValue, xLeftBoundary, xIncrement, yIncrement);
        drawRecoveredData(g, totalXPixels, totalYPixels,maxYValue, xLeftBoundary, xIncrement, yIncrement);
        drawConfirmedData(g, totalXPixels, totalYPixels,maxYValue, xLeftBoundary, xIncrement, yIncrement);

    }

    private void labelAxis(Graphics g, int xLeftBoundary, int yBottomBoundary) {
        //label axis
        g.setColor(Color.black);
        g.setFont(new Font("SansSerif", Font.BOLD, 16));
        g.drawString("Date", xLeftBoundary + 300, yBottomBoundary + 20);

        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 16));
        g.drawString("Cases", xLeftBoundary-60, yBottomBoundary-350);
    }

    public void clearDataInArrays() {
        deathArray.clear();
        recoveredArray.clear();
        confirmedArray.clear();
    }

    private void drawConfirmedData(Graphics g, int totalXPixels, int totalYPixels, int maxYValue, int xLeftBoundary, int xIncrement, int yIncrement) {
        int x1, x2, y1, y2;

        //set origin coordinates
        x1 = xLeftBoundary;
        y1 = confirmedArray.get(0)* yIncrement;  //y coordinate of first confirmed in array

        //compute and plot points for recovered data
        for (int i = 0; i < confirmedArray.size(); i++) {
            x2 = xLeftBoundary + xIncrement * i;
            y2 = confirmedArray.get(i) * yIncrement;

            g.setColor(new Color(134, 94, 0, 195));
            g.fillOval(x2, y2, 5, 5);

            g.setColor(Color.ORANGE);
            g.drawLine(x1, y1, x2, y2);

            //set coordinates for next data points
            x1 = x2;
            y1 = y2;

        }
    }

    private void drawRecoveredData(Graphics g, int totalXPixels, int totalYPixels, int maxYValue, int xLeftBoundary, int xIncrement, int yIncrement) {
        int x1, x2, y1, y2;

        //set origin coordinates
        x1 = xLeftBoundary;
        y1 = recoveredArray.get(0)* totalYPixels/maxYValue;  //y coordinate of first recovered in array

        //compute and plot points for recovered data
        for (int i = 0; i < recoveredArray.size(); i++) {
            x2 = xLeftBoundary + xIncrement * i;
            y2 = recoveredArray.get(i) * totalYPixels/maxYValue;

            g.setColor(Color.BLACK);                  //dots between data points
            g.fillOval(x2,y2,5,5);

            g.setColor(Color.GREEN);
            g.drawLine(x1,y1,x2,y2);

            //set coordinates for next data points
            x1 = x2;
            y1 = y2;

        }
    }


    private void drawDeathData(Graphics g, int totalXPixels, int totalYPixels, int maxYValue, int xLeftBoundary, int xIncrement, int yIncrement) {
        int x1, x2, y1, y2;

        //set origin coordinates
        x1 = xLeftBoundary;
        y1 = deathArray.get(0)* totalYPixels/maxYValue;  //y coordinate of first death in array

        //compute and plot points for death data
        for (int i = 0; i < deathArray.size(); i++) {
            x2 = xLeftBoundary + xIncrement * i;
            y2 = deathArray.get(i) * totalYPixels/maxYValue;

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
