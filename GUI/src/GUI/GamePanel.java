package GUI;
import javax.swing.*;
import java.awt.*;
import GameTemplate.*;
import java.awt.geom.Line2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel{
    private final Color[] tColors = {
            Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green, Color.pink, Color.red
    };
    int[][] gameboard;
    GameTemplate game;
    int player;
    public GamePanel( GameTemplate game, int player){
        this.gameboard = game.board.tileMap;
        this.game=game;
        this.player = player;
    }

    public void paintComponent(Graphics g){

        g.fillRect(0,0,100*gameboard[0].length,100*gameboard.length);

        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[0].length; j++) {
                g.setColor(tColors[gameboard[i][j]]);
                g.fillRect(100*i, 100*j, 99, 99);
            }
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
        if(player == 1){
            g2.setColor(Color.black);
        }
        else{
            g2.setColor(Color.red);
        }
        g2.drawRect(game.temp1[0]*100,game.temp1[1]*100,100,100);
        g2.drawRect(game.temp2[0]*100,game.temp2[1]*100,100,100);

    }

}
