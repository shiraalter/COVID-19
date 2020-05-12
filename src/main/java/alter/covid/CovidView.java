package alter.covid;

import javax.swing.*;
import java.awt.*;

public class CovidView extends JComponent {
    private CovidUpdateFeed.Result covid;


    int[] deathArray = new int[3];

/*    int[] confirmedArray = new int[3];
      int[] recoveredArray = new int [3];*/


    public void setCovid(CovidUpdateFeed.Result covid){
        this.covid = covid;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        if(covid == null) {
            return;
        }

        //center everything
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        g.translate(centerX, centerY);

        // boundaries for graph
        g.setColor(Color.BLACK);
        g.drawLine(0,0,0,200);
        g.drawLine(0,0,100,0);



        // deaths rectangle
        g.setColor(Color.RED);
    /*    int xVal =
        g.fillRect(-50,100,200,100);
*/





        }


    }
