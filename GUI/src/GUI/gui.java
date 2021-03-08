package GUI;
import javax.swing.*;
import java.awt.*;
import GameTemplate.*;
import java.awt.geom.Line2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class gui extends JPanel{
    private final Color[] tColors = {
            Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green, Color.pink, Color.red
    };
    int[][] gameboard;
    GameTemplate game;
    JFrame jf;
    public gui(int[][] gameboard,GameTemplate game){
        this.gameboard = gameboard;
        this.game=game;

    }

//    public void control(GameTemplate g){
//        jf.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if(e.getKeyCode() == KeyEvent.VK_A){
//                    g.createNewBoard();
//                    System.out.println("as");
//                }
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
//    }
    public void render(int[][] gameboard,JFrame jfam){
//        JFrame jf = new JFrame("Game");
//        gameboard = new int[10][10];
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                gameboard[i][j] = 1;
//            }
//        }
//        jf.setSize(gameboard[0].length*100,gameboard.length*100);


//        gui g = new gui(jfam);
//        g.gameboard=gameboard;
//
//        jf.add(g);
//        jf.setVisible(true);

//        new Thread() {
//            @Override public void run() {
////                while (true) {
////                    try {
////                        System.out.println("1");
////                        Thread.sleep(1000);
////                    } catch ( InterruptedException e ) {}
////                }
//            }
//        }.start();
    }
    public void paintComponent(Graphics g){
//        System.out.println("dd");
//        super.paintComponent(g);
        g.fillRect(0,0,100*gameboard[0].length,100*gameboard.length);

        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[0].length; j++) {
                g.setColor(tColors[gameboard[i][j]]);
                g.fillRect(100*i, 100*j, 99, 99);
//                repaint();
            }
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
//        g2.draw(new Line2D.Float(30, 20, 80, 90));
        g2.setColor(Color.black);
        g2.drawRect(game.temp1[0]*100,game.temp1[1]*100,100,100);
        g2.drawRect(game.temp2[0]*100,game.temp2[1]*100,100,100);

    }
    public static void main(String[] args){
//        int[][] gameboard;
//        gameboard =new int[10][10];
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                gameboard[i][j]=1;
//            }
//        }
//        JFrame jf = new JFrame("Game");
//        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jf.setSize(gameboard[0].length*100,gameboard.length*100);
//        jf.setVisible(true);
//        gui g = new gui(gameboard);
//        jf.add(g);

        new Thread() {
            @Override public void run() {
//                while (true) {
//                    try {
//                        System.out.println("1");
//                        Thread.sleep(1000);
//                    } catch ( InterruptedException e ) {}
//                }
            }
        }.start();
    }

}
