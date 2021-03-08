package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class playergui {
    public static void main(String[] args){
        JFrame jf = new JFrame();
        JButton b1 = new JButton("Player1");
        JButton b2 = new JButton("Player2");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller c = new Controller();
                try {
                    c.test1();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playergui p = new playergui();
                p.gamestarter();
            }
        });
        b1.setBounds(65,50,100, 40);
        b2.setBounds(65,100,100, 40);
        jf.add(b1);
        jf.add(b2);
        jf.setSize(200,200);
        jf.setLayout(null);
        jf.setVisible(true);

    }
    public void gamestarter(){
        JFrame jf = new JFrame();
        JButton b1 = new JButton("CandyCrush");
        JButton b2 = new JButton("Tetris");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//               Controller C = new Controller();
//               C.run();
            }
        });
        b1.setBounds(65,50,100, 40);
        b2.setBounds(65,100,100, 40);
        jf.add(b1);
        jf.add(b2);
        jf.setSize(200,200);
        jf.setLayout(null);
        jf.setVisible(true);
    }

}
