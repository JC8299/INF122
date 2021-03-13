package GUI;

import GameTemplate.GameTemplate;
import Player.Player;

import javax.swing.*;


public abstract class GameControlTemplate {
    public long initialTime = System.nanoTime();
    public long deltaTime = 0;
    public final long TARGET_FPS = 60;
    public final long TIME_PER_FRAME = 1000000000 / TARGET_FPS;
    public long timer = System.currentTimeMillis();
    public boolean running = true;
    public int exit = 0;

    public GameTemplate g;
    public int player_count = 1;
    public int next_player = 1;

    public Player current_player;
    public Player[] player_pool;
    public JFrame jf1 = new JFrame();
    public JPanel gp;

    public GameControlTemplate(GameTemplate g, Player p1){
        this.g = g;
        this.g.createNewBoard();
        current_player = p1;
        player_pool = new Player[]{p1};
        player_count=1;
    }
    public GameControlTemplate(GameTemplate g,Player p1,Player p2){
        this.g = g;
        this.g.createNewBoard();
        current_player = p1;
        player_pool = new Player[]{p1,p2};
        player_count = 2;
    }
    public abstract void Keyboard_control_1();
    public abstract void Gameloop() throws InterruptedException;
    public long getInitialTime() {
        return initialTime;
    }

    public long getTIME_PER_FRAME() {
        return TIME_PER_FRAME;
    }

    public long getTimer() {
        return timer;
    }

    public void setDeltaTime(long deltaTime) {
        this.deltaTime = deltaTime;
    }
}
