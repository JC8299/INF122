package Tetris_GUI;

import GUI.GameControlTemplate;
import GameTemplate.GameTemplate;
import Player.Player;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameControl_Tetris extends GameControlTemplate {
    int matching_indicator = 0;
    public GameControl_Tetris(GameTemplate g, Player p1){
        super(g,p1);
        jf1.setVisible(true);
        jf1.setSize(g.board.tileMap[0].length*60, g.board.tileMap.length*65);
        jf1.setTitle("Score:"+current_player.getScoreTwo() );
    }
    public GameControl_Tetris(GameTemplate g, Player p1, Player p2){
        super(g,p1,p2);
        jf1.setVisible(true);
        jf1.setSize(g.board.tileMap[0].length*60, g.board.tileMap.length*65);
        jf1.setTitle("Score:"+current_player.getScoreTwo() );
    }

    @Override
    public void Keyboard_control_1() {
        jf1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_A){
                    g.move(1,0,0,0);
                }
                else if(e.getKeyCode()==KeyEvent.VK_D){
                    g.move(2,0,0,0);
                }
                else if(e.getKeyCode()==KeyEvent.VK_W){
                    g.move(3,0,0,0);
                } else if(e.getKeyCode()==KeyEvent.VK_S){
                    g.move(4,0,0,0);
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

    @Override
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
                    if(player_count==1){jf1.setTitle("Score:"+current_player.getScoreTwo() );}
                    else{jf1.setTitle("Player1 Score:"+player_pool[1].getScoreTwo() +" Player2 Score:"+player_pool[1].getScoreTwo());}


                    gp = new GamePanel_Tetris(g,next_player);
                    jf1.getContentPane().add(gp);
                    jf1.setVisible(true);

                    if (deltaTime >= 20) {
                        initialTime = currentTime;
                        g.update(100);
//                        if(g.returnScore()!=0 && matching_indicator == 1){
//                            player_pool[0].score2 +=g.returnScore();
//                            matching_indicator = 0;
//                        }
//                        else{
//                            matching_indicator =1;
//                        }
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
}
