package GUI;

import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class playergui {
    public static void main(String[] args){
        JFrame jf = new JFrame();
        JButton b1 = new JButton("Player1");
        JButton b2 = new JButton("Player2");
        Player p1 = new Player();
        Player p2 = new Player();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playergui p = new playergui();
                p.gamestarter1(p1,p2);

            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playergui p = new playergui();
                p.gamestarter1(p2,p1);
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
    public void gamestarter1(Player p1,Player p2){
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JButton b1 = new JButton("CandyCrush 1");
        JButton b3 = new JButton("CandyCrush 2");
        JButton b2 = new JButton("Tetris");
        JLabel l1,l2;
        l1=new JLabel("CandyCrush Score:" + p1.score1);
        l1.setBounds(5,5, 200,15);
        l2=new JLabel("Tetris Score:"+p1.score2);
        l2.setBounds(5,20, 200,15);
        jf.add(l1);
        jf.add(l2);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller c = new Controller();
                try {
                    c.test1(p1);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });
        b1.setBounds(20,50,120, 40);
        b2.setBounds(20,100,100, 40);
        b3.setBounds(150,50,120,40);
        jf.add(b1);
        jf.add(b2);
        jf.add(b3);
        jf.setSize(200,200);
        jf.setLayout(null);
        jf.setVisible(true);
    }
    public void gamestarter2(Player p1,Player p2){
        JFrame jf = new JFrame();
        JButton b1 = new JButton("CandyCrush");
        JButton b2 = new JButton("Tetris");

        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller c = new Controller();
                try {
                    c.test1(p1);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
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