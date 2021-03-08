package GUI;
import CandyCrush.CandyCrush;
import GameTemplate.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {
    //public Player player1;
    //public Player player2;
    //private List<GameTemplate> gameList;
    //public GUI gui;
    boolean selected = false;
    private boolean running = true;
    JFrame jf1;
    GameTemplate g;

    public Controller(){
    }

    public static void main(String[] args){
        Controller c = new Controller();
        c.run();
    }
    public void movetemp(JFrame jf1){
        jf1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_A){
                    if (!selected){
                        g.temp1[0]--;
                        if(g.temp1[0]<0){
                            g.temp1[0] = 0;
                        }
                    }
                    else{
                        g.temp2[0]--;
                        if(g.temp2[0]<0){
                            g.temp2[0] = 0;
                        }
                    }
                }
                else if (e.getKeyCode()==KeyEvent.VK_D){
                    if (!selected){
                        g.temp1[0]++;
                        if(g.temp1[0]>g.board.tileMap[0].length-1){
                            g.temp1[0] = g.board.tileMap[0].length-1;
                        }
                    }
                    else{
                        g.temp2[0]++;
                        if(g.temp2[0]>g.board.tileMap[0].length-1){
                            g.temp2[0] = g.board.tileMap[0].length-1;
                        }
                    }
                }else if (e.getKeyCode()==KeyEvent.VK_W){
                    if (!selected){
                        g.temp1[1]--;
                        if(g.temp1[1]<0){
                            g.temp1[1] =0;
                        }
                    }
                    else{
                        g.temp2[1]--;
                        if(g.temp2[1]<0){
                            g.temp2[1] =0;
                        }
                    }
                }else if (e.getKeyCode()==KeyEvent.VK_S){
                    if (!selected){
                        g.temp1[1]++;
                        if(g.temp1[1]>g.board.tileMap.length-1){
                            g.temp1[1] = g.board.tileMap.length-1;
                        }
                    }
                    else{
                        g.temp2[1]++;
                        if(g.temp2[1]>g.board.tileMap.length-1){
                            g.temp2[1] = g.board.tileMap.length-1;
                        }
                    }
                }
                else if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    if(!selected){
                        selected = true;
//                        int[] qw = g.temp1;
                        g.temp2[0] = g.temp1[0];
                        g.temp2[1]= g.temp1[1];
                        System.out.println("s");
                    }
                    else{
                        System.out.println("a");

                        selected = false;
                        int i = g.board.tileMap[g.temp1[0]][g.temp1[1]];
                        int j = g.board.tileMap[g.temp2[0]][g.temp2[1]];
                        g.board.tileMap[g.temp1[0]][g.temp1[1]] = j;
                        g.board.tileMap[g.temp2[0]][g.temp2[1]] = i;
                        g.temp2[0] = -2;
                        g.temp2[1] = -2;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void test1() throws InterruptedException {

        JFrame jf1 = new JFrame();
        jf1.setVisible(true);
        jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g = new CandyCrush();
        g.createNewBoard();
        jf1.setSize(g.board.tileMap[0].length*100, g.board.tileMap.length*100);
        movetemp(jf1);

        gui gg = new gui(g.board.tileMap,g);
//        jf1.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if(e.getKeyCode() == KeyEvent.VK_A){
//                    g.createNewBoard();
//                }
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
        jf1.getContentPane().add(gg);
        final SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                long initialTime = System.nanoTime();
                long deltaTime = 0;
                final long TARGET_FPS = 60;
                final long TIME_PER_FRAME = 1000000000 / TARGET_FPS;
                long timer = System.currentTimeMillis();
                int updates = 0;
                while(running){

                    long currentTime = System.nanoTime();

                    //used to calculate time between loops
                    deltaTime = (currentTime - initialTime) / TIME_PER_FRAME;

                    //will only be called when the time from last loop is greater than one second divided by TARGET_FPS
                    //anything called outside of this if will be dependent on machine
//            gg.render(g.board.tileMap,jf);

                    gui gg = new gui(g.board.tileMap,g);

                    jf1.getContentPane().add(gg);
                    jf1.setVisible(true);

//                    if (deltaTime >= 100) {
//                        initialTime = currentTime;
////                handle_input();
////                gg.control(g);
//
////                g.createNewBoard();
//                        updates++;
//                    }

                    //calculate a second for if we need anything with a timer
                    //it may call an update before a second has passed
                    //running into a problem where it has one too many updates after 16 secs, but is 1/60 of a sec so should be fine
                    if (System.currentTimeMillis() - timer > 1000)
                    {
                        timer = System.currentTimeMillis();
                        System.out.println((updates/TARGET_FPS) + " seconds have passed, Updates: " + updates);

                        //if loop is running for 30 or more seconds, stop running
                        if ((updates/TARGET_FPS) >= 30)
                        {
                            running = false;
                        }
                    }
                }

                return null;
            }
        };
        worker.execute();

    }
    public void run(){
        //put anything that need to be initialized before the loop
        long initialTime = System.nanoTime();
        long deltaTime = 0;
        final long TARGET_FPS = 60;
        final long TIME_PER_FRAME = 1000000000 / TARGET_FPS;
        long timer = System.currentTimeMillis();
        int updates = 0;
        JFrame jf1 = new JFrame("game");

        jf1.setVisible(true);
        GameTemplate g = new CandyCrush();
        g.createNewBoard();
        jf1.setSize(g.board.tileMap[0].length*100,g.board.tileMap.length*100);
        jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui gg = new gui(g.board.tileMap,g);
        jf1.getContentPane().add(gg);

        jf1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_A){
                    g.createNewBoard();
                    System.out.println("as");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


//        GameTemplate g1 = new CandyCrush(10,10);
//        g1.createNewBoard();aa
        //Gameloop
        //it will be attempting to run at TARGET_FPS
        while(running) {
            long currentTime = System.nanoTime();

            //used to calculate time between loops
            deltaTime = (currentTime - initialTime) / TIME_PER_FRAME;

            //will only be called when the time from last loop is greater than one second divided by TARGET_FPS
            //anything called outside of this if will be dependent on machine
//            gg.render(g.board.tileMap,jf);
            gg = new gui(g.board.tileMap,g);



            if (deltaTime >= 100) {
                initialTime = currentTime;
//                handle_input();
//                gg.control(g);

//                g.createNewBoard();
                updates++;
            }
            jf1.getContentPane().add(gg);
            jf1.setVisible(true);
            //calculate a second for if we need anything with a timer
            //it may call an update before a second has passed
            //running into a problem where it has one too many updates after 16 secs, but is 1/60 of a sec so should be fine
            if (System.currentTimeMillis() - timer > 1000)
            {
                timer = System.currentTimeMillis();
                System.out.println((updates/TARGET_FPS) + " seconds have passed, Updates: " + updates);

                //if loop is running for 30 or more seconds, stop running
                if ((updates/TARGET_FPS) >= 30)
                {
                    running = false;
                }
            }
        }
    }
}
