package GUI;


import javax.swing.*;
import java.awt.*;
import GameTemplate.*;

public abstract class GamePanelTemplate extends JPanel {
    private final Color[] tColors = {
            Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green, Color.pink, Color.red
    };
    int[][] gameboard;
    public GameTemplate game;
    int player;
    public GamePanelTemplate(GameTemplate game, int player){
        this.gameboard = game.board.tileMap;
        this.game=game;
        this.player = player;
    }
    public abstract void paintComponent(Graphics g);
}
