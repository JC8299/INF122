package Tetris_GUI;

import GameTemplate.GameTemplate;

import javax.swing.*;
import java.awt.*;

public class GamePanel_Tetris extends JPanel {
    private final Color[] tColors = {
            Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green, Color.pink, Color.red,Color.black,Color.darkGray,Color.magenta,Color.white
    };
    int[][] gameboard;
    GameTemplate game;
    int player;

    public GamePanel_Tetris(GameTemplate game, int player){
//       super(game,player);
        this.gameboard = game.board.tileMap;
        this.game=game;
        this.player = player;
    }

    public void paintComponent(Graphics g){

        g.fillRect(0,0,100*gameboard[0].length,100*gameboard.length);

        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[0].length; j++) {
                g.setColor(tColors[gameboard[i][j]]);
                g.fillRect(50*j, 50*i,49, 49);
            }
        }
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setStroke(new BasicStroke(10));
//        if(player == 1){
//            g2.setColor(Color.black);
//        }
//        else{
//            g2.setColor(Color.red);
//        }
//        g2.drawRect(game.temp1[0]*100,game.temp1[1]*100,100,100);
//        g2.drawRect(game.temp2[0]*100,game.temp2[1]*100,100,100);

    }
}
