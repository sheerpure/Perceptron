package homework;

import javax.swing.*;

public class pic extends JFrame{

    pic(float data[][], float weight[], int line, int exp[], int det[],int trainc, int testc){

        pane panee = new pane(data,weight,line,exp,det);

        panee.setBounds(25,25,500,500);
        add(panee);



        correctness cor = new correctness(trainc,testc);
        cor.setBounds(500,100,400,100);
        add(cor);


        setTitle("JFrame");
        setLayout(null);
        setBounds(50,50,700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);



    }


}
