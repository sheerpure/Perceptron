package homework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class input extends JFrame implements KeyListener, ActionListener{
    JPanel pane = new JPanel();
    JLabel text1 = new JLabel("learn rate:");
    JLabel text2 = new JLabel("epoch:");
    JLabel text3 = new JLabel("times");
    JTextField text11 = new JTextField();
    JTextField text22 = new JTextField();
    JButton okbtn = new JButton("ok");
    public input(){
        setTitle("Perceptron");
        setLayout(null);
        setBounds(0,0,250,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        gui();



    }

    private void gui(){

        pane = new JPanel();
        pane.setBounds(0,0,250,250);
        pane.setLayout(null);
        add(pane);


        text1.setBounds(10,10,60,50);
        pane.add(text1);


        text11.setBounds(70,10,50,50);
        pane.add(text11);


        text2.setBounds(10,60,60,50);
        pane.add(text2);


        text22.setBounds(70,60,50,50);
        pane.add(text22);

        text3.setBounds(120,70,100,40);
        pane.add(text3);


        okbtn.setBounds(30,120,50,40);
        okbtn.addActionListener(this);
        pane.add(okbtn);

    }



    public void keyPressed(KeyEvent event) {

    }
    public void keyReleased(KeyEvent event){

    }
    public void keyTyped(KeyEvent event) {


    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == okbtn){
            setVisible(false);
            train trainn = new train(Float.parseFloat(text11.getText()),Integer.valueOf(text22.getText()));
        }


    }

}
