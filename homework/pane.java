package homework;



import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class pane extends JPanel{
    static int time = 0;
    static float spot[][] = new float [1000][3];
    static float weight[] = new float [3];
    static int exp[] = new int [1000];
    static int detexp[] = new int [2];
    pane(float data[][], float w[], int line,int e[], int det[]){
        time = line;
        for(int i = 0;i<line;i++){
            for(int j=0;j<3;j++){
                spot[i][j] = data[i][j];
            }
        }
        for(int i=0;i<3;i++){
            weight[i] = w[i];
            System.out.println("w:"+weight[i]);
        }
        for(int i=0;i<time;i++){
            exp[i] = e[i];
        }

        for(int i=0;i<2;i++){
            detexp[i] = det[1];
        }
    }


    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        int i = 0;
        g2.scale(2,2);
        while(i<time){
            int  j= 1;
            if(exp[i] == detexp[1]){
                g2.setColor(Color.blue);
            }
            else{
                g2.setColor(Color.black);
            }
            Ellipse2D ellipse = new Ellipse2D.Double((spot[i][j]*5+100),(spot[i][j+1]*5+100),0.05,0.05);
            g2.draw(ellipse);
            i++;

        }
        float x1 = (weight[0]/weight[1])*5+100;
        float x2 = (weight[0]/weight[2])*5+100;
        float x11 = x1+1000;
        float x12 = 100-1000*((weight[0]/weight[2])/(weight[0]/weight[1]));
        float x13 = x1-1000;
        float x14 = 100+1000*((weight[0]/weight[2])/(weight[0]/weight[1]));



        Line2D line = new Line2D.Double(x1,0+100,0+100,x2);
        g2.draw(line);

        Line2D line2 = new Line2D.Double(x1,0+100,x11,x12);
        g2.draw(line2);

        Line2D line3 = new Line2D.Double(x1,0+100,x13,x14);
        g2.draw(line3);


        }



    }





