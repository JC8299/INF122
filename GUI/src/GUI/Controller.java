package GUI;
import CandyCrush.CandyCrush;
import GameTemplate.*;
import Player.Player;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {
    int exit = 0;
    int turncount = 30;

    boolean selected = false;
    private boolean running = true;
    GameTemplate g;
    int player_count = 1;

    Player current_player;
    Player[] player_pool;
    int next_player = 1;
    JFrame jf1 = new JFrame();
    GamePanel gp;


    long initialTime = System.nanoTime();
    long deltaTime = 0;
    final long TARGET_FPS = 60;
    final long TIME_PER_FRAME = 1000000000 / TARGET_FPS;
    long timer = System.currentTimeMillis();

    public Controller(GameTemplate g,Player p1){
        this.g = g;
        this.g.createNewBoard();
        do { g.matching();} while(g.returnScore()!=0);
        current_player = p1;
        jf1.setVisible(true);
        jf1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf1.setSize(g.board.tileMap[0].length*105, g.board.tileMap.length*110);
        jf1.setTitle("Score:"+current_player.score1 + " Turn Left:" + turncount);
    }
    public Controller(GameTemplate g,Player p1, Player p2){
        this.g = g;
        this.g.createNewBoard();
        do { g.matching();} while(g.returnScore()!=0);
        current_player = p1;
        player_pool = new Player[]{p1,p2};
        player_count = 2;
        jf1.setVisible(true);
        jf1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf1.setSize(g.board.tileMap[0].length*105, g.board.tileMap.length*110);
        jf1.setTitle("Player1 Score:"+current_player.score1 +" Player2 Score:"+player_pool[1].score1+ " Turn Left:" + turncount);

    }
    public void Keyboard_control_1(){
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
                        g.temp2[0] = g.temp1[0];
                        g.temp2[1]= g.temp1[1];
                    }
                    else{
                        selected = false;
                        if(g.move(g.temp1[0],g.temp1[1],g.temp2[0],g.temp2[1])){
                            g.temp2[0] = -2;
                            g.temp2[1] = -2;
                            do{g.matching();
                            current_player.score1+=g.returnScore();}while(g.returnScore()!=0);

//                            while(g.returnScore()!=0){
//                                jf1.getContentPane().add(gp);
//                                jf1.setVisible(true);
//                                long currentTime = System.nanoTime();
//                                deltaTime = (currentTime - initialTime) / TIME_PER_FRAME;
//                                if (deltaTime >= 100) {
//                                    initialTime = currentTime;
//                                    g.matching();
//                                    gp = new GamePanel(g,1);
//
//                                    current_player.score1+=g.returnScore();
//                                }
//                            }
                            turncount--;

                        }
                        else{
                            selected = false;
                            g.temp2[0] = -2;
                            g.temp2[1] = -2;
                        }
                    }
//
                }
                else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    exit = 1;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
    public void Keyboard_control_2(){
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
                        g.temp2[0] = g.temp1[0];
                        g.temp2[1]= g.temp1[1];
                    }
                    else{
                        selected = false;

                        if(g.move(g.temp1[0],g.temp1[1],g.temp2[0],g.temp2[1])){
                            g.temp2[0] = -2;
                            g.temp2[1] = -2;
                            do{
                                g.matching();
                                current_player.score1+=g.returnScore();
                                gp = new GamePanel(g,next_player);
                                jf1.getContentPane().add(gp);
                                jf1.setVisible(true);

                            }while(g.returnScore()!=0);
                            turncount--;
                        }
                        else{

                            selected = false;
                            g.temp2[0] = -2;
                            g.temp2[1] = -2;
                        }

                        if(player_count == 2){
//                            Player p1 = current_player;
                            Player p2 = player_pool[next_player];
                            current_player = p2;
                            next_player = (next_player==1)?0:1;
                        }
                    }
                }
                else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    exit = 1;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
    public void Gameloop() throws InterruptedException {
        final SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {

                Keyboard_control_1();

                int updates = 0;

                while(running){

                    long currentTime = System.nanoTime();

                    //used to calculate time between loops
                    deltaTime = (currentTime - initialTime) / TIME_PER_FRAME;

                    //will only be called when the time from last loop is greater than one second divided by TARGET_FPS
                    //anything called outside of this if will be dependent on machine
                    jf1.setTitle("Score:"+current_player.score1 + " Turn Left:" + turncount);

                    gp = new GamePanel(g,1);
                    jf1.getContentPane().add(gp);
                    jf1.setVisible(true);

                    if (deltaTime >= 100) {
                        initialTime = currentTime;
//                        System.out.println(System.currentTimeMillis());
//                        System.out.println(timer);

                        updates++;
                    }

                    //calculate a second for if we need anything with a timer
                    //it may call an update before a second has passed
                    //running into a problem where it has one too many updates after 16 secs, but is 1/60 of a sec so should be fine
                    if (System.currentTimeMillis() - timer > 1000)
                    {
                        timer = System.currentTimeMillis();
//                        System.out.println((updates/TARGET_FPS) + " seconds have passed, Updates: " + updates);

                        //if loop is running for 30 or more seconds, stop running
                        if ((updates/TARGET_FPS) >= 30)
                        {
                            running = false;
                        }
                    }
                    if(exit == 1 || g.isGameOver()){
                        jf1.dispose();
                        return null;
                    }
                }

                return null;
            }
        };
        worker.execute();

    }
    public void Gameloop1() throws InterruptedException {
        final SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {

                Keyboard_control_2();

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
                    jf1.setTitle("Player1 Score:"+player_pool[0].score1 +" Player2 Score:"+player_pool[1].score1+ " Turn Left:" + turncount);

                    gp = new GamePanel(g,next_player);
                    jf1.getContentPane().add(gp);
                    jf1.setVisible(true);

                    if (deltaTime >= 100) {
                        initialTime = currentTime;
//                        System.out.println(System.currentTimeMillis());
//                        System.out.println(timer);

                        updates++;
                    }

                    //calculate a second for if we need anything with a timer
                    //it may call an update before a second has passed
                    //running into a problem where it has one too many updates after 16 secs, but is 1/60 of a sec so should be fine
                    if (System.currentTimeMillis() - timer > 1000)
                    {
                        timer = System.currentTimeMillis();
//                        System.out.println((updates/TARGET_FPS) + " seconds have passed, Updates: " + updates);

                        //if loop is running for 30 or more seconds, stop running
                        if ((updates/TARGET_FPS) >= 30)
                        {
                            running = false;
                        }
                    }
                    if(exit == 1 || g.isGameOver()){
                        jf1.dispose();
                        return null;
                    }
                }

                return null;
            }
        };
        worker.execute();

    }
//    public void run(){
//        //put anything that need to be initialized before the loop
//        long initialTime = System.nanoTime();
//        long deltaTime = 0;
//        final long TARGET_FPS = 60;
//        final long TIME_PER_FRAME = 1000000000 / TARGET_FPS;
//        long timer = System.currentTimeMillis();
//        int updates = 0;
//        JFrame jf1 = new JFrame("game");
//
//        jf1.setVisible(true);
//        GameTemplate g = new CandyCrush(10,10);
//        g.createNewBoard();
//        jf1.setSize(g.board.tileMap[0].length*100,g.board.tileMap.length*100);
//        jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        GamePanel gg = new GamePanel(g.board.tileMap,g,1);
//        jf1.getContentPane().add(gg);
//
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
//                    System.out.println("as");
//                }
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
//
//
////        GameTemplate g1 = new CandyCrush(10,10);
////        g1.createNewBoard();aa
//        //Gameloop
//        //it will be attempting to run at TARGET_FPS
//        while(running) {
//            long currentTime = System.nanoTime();
//
//            //used to calculate time between loops
//            deltaTime = (currentTime - initialTime) / TIME_PER_FRAME;
//
//            //will only be called when the time from last loop is greater than one second divided by TARGET_FPS
//            //anything called outside of this if will be dependent on machine
////            gg.render(g.board.tileMap,jf);
//            gg = new GamePanel(g.board.tileMap,g,1);
//
//
//
//            if (deltaTime >= 100) {
//                initialTime = currentTime;
////                handle_input();
////                gg.control(g);
//
////                g.createNewBoard();
//                updates++;
//            }
//            jf1.getContentPane().add(gg);
//            jf1.setVisible(true);
//            //calculate a second for if we need anything with a timer
//            //it may call an update before a second has passed
//            //running into a problem where it has one too many updates after 16 secs, but is 1/60 of a sec so should be fine
//            if (System.currentTimeMillis() - timer > 1000)
//            {
//                timer = System.currentTimeMillis();
//                System.out.println((updates/TARGET_FPS) + " seconds have passed, Updates: " + updates);
//
//                //if loop is running for 30 or more seconds, stop running
//                if ((updates/TARGET_FPS) >= 30)
//                {
//                    running = false;
//                }
//            }
//        }
//    }
}
