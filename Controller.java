package sample;

public class Controller {
    //public Player player1;
    //public Player player2;
    //private List<GameTemplate> gameList;
    //public GUI gui;
    private boolean running = true;

    public void run(){
        //put anything that need to be initialized before the loop
        long initialTime = System.nanoTime();
        long deltaTime = 0;
        final long TARGET_FPS = 60;
        final long TIME_PER_FRAME = 1000000000 / TARGET_FPS;
        long timer = System.currentTimeMillis();
        int updates = 0;

        //Gameloop
        //it will be attempting to run at TARGET_FPS
        while(running) {
            long currentTime = System.nanoTime();

            //used to calculate time between loops
            deltaTime = (currentTime - initialTime) / TIME_PER_FRAME;

            //will only be called when the time from last loop is greater than one second divided by TARGET_FPS
            //anything called outside of this if will be dependent on machine
            if (deltaTime >= 1) {
                initialTime = currentTime;
                
                //call GameTemplate.update()
                //update GUI
                updates++;
            }

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
