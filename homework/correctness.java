package homework;

import javax.swing.*;

public class correctness extends JPanel{
    float[][] traindata = new float[1000][3];
    int[] trainexp = new int [1000];
    int traintime = 0;
    float[][] testdata = new float[1000][3];
    int[] testexp =  new int [1000];
    int testtime = 0;
    float []weight = new float[3];
    int []det = new int [2];

    float trainrate = 0;
    float testrate = 0;



    correctness(int trainc, int testc){
        setLayout(null);
        trainrate = trainc;
        testrate = testc;

        gui();
    }


    private int returnexp(float v){
        if(v>=0){
            return det[0];
        }
        else
            return det[1];

    }
    private void gui(){
        JLabel trainc = new JLabel("traning data acc:");
        JLabel testc = new JLabel("testing data acc:");
        JLabel tra = new JLabel(trainrate+"");
        JLabel tes = new JLabel(testrate+"");
        JLabel pa1 = new JLabel("%");
        JLabel pa2 = new JLabel("%");
        trainc.setBounds(0,0,100,40);
        add(trainc);

        tra.setBounds(100,0,40,40);
        add(tra);
        pa1.setBounds(140,0,30,40);
        add(pa1);
        testc.setBounds(0,40,100,40);
        add(testc);
        tes.setBounds(100,40,40,40);
        add(tes);
        pa2.setBounds(140,40,30,40);
        add(pa2);


    }


}

