package CandyCrush_GUI;
import javax.swing.*;
import java.awt.*;
import GameTemplate.*;
import java.awt.geom.Line2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel_CandyCrush extends JPanel{
    private final Color[] tColors = {
            Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green, Color.pink, Color.red
    };
    int[][] gameboard;
    GameTemplate game;
    int player;

    public GamePanel_CandyCrush(GameTemplate game, int player){
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
                g.fillRect(50*i, 50*j, 49, 49);
            }
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        if(player == 1){
            g2.setColor(Color.black);
        }
        else{
            g2.setColor(Color.red);
        }
        g2.drawRect(game.temp1[0]*50,game.temp1[1]*50,49,49);
        g2.drawRect(game.temp2[0]*50,game.temp2[1]*50,49,49);

    }

}
