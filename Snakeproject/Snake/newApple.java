import java.util.Random;

public class NewApple {
    Random random = new Random();
    int appleX;
    int appleY;

    appleX = random.nextInt(GamePanel.GRID_WIDTH);
    appleY = random.nextInt(GamePanel.GRID_HEIGHT);
    
}