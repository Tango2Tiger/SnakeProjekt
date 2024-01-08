import java.util.Random;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.*;import javafx.event.*;


public class GamePanel extends Application {
    private GridPane grid;
    private Rectangle apple;
    public static final int GRID_HEIGHT = 10;
    public static final int GRID_WIDTH = 20;
    public static final int TILE_SIZE = 30;
    Snake snake = new Snake(GRID_WIDTH/2, GRID_HEIGHT/2);
    public boolean isAlive = true;
    public boolean ateApple = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        grid = new GridPane();
        
        Group root = new Group();
        Scene scene = new Scene(root);
        
        stage.setTitle("SNAKE");
        stage.setResizable(true);

        createGrid();
        createSnake();

        Text points = new Text();
        points.setText("Points:");
        points.setX(10);
        points.setY(25);
        points.setFont(Font.font("Roboto",25));
        points.setFill(Color.LIMEGREEN);
        
        root.getChildren().add(grid);
        root.getChildren().add(points);
        scene.setFill(Color.WHITE);
        stage.setScene(scene);
        stage.show();

        MyAnimationTimer animationTimer = new MyAnimationTimer();
        animationTimer.start();



        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKey);
    }
    private void createGrid(){
        for(int row = 0; row<GRID_WIDTH; row++){
            for(int col = 0; col<GRID_HEIGHT; col++){
                Rectangle pane = new Rectangle(TILE_SIZE, TILE_SIZE);
                pane.setFill(Color.SEAGREEN);
                pane.setStroke(Color.WHITE);
                pane.setStrokeWidth(1.5);
                grid.add(pane, row, col);
            }
        }
        spawnApple();
    }

    public void createSnake(){
        
        ArrayList<Rectangle> l = snake.getlist();
        int xrect = 0;
        int yrect = 0;

        for(int i = 0; i < l.size(); i++){
            xrect = (int)(l.get(i).getX());
            yrect = (int)(l.get(i).getY());

            grid.add(l.get(i),xrect,yrect);
        }
    }
    public class MyAnimationTimer extends AnimationTimer {

        private long lastUpdateTime = 0;
        private final long updateInterval = 300000000; // 0,3 sekund
    
        @Override
        public void handle(long now) {
            
             if(now - lastUpdateTime >= updateInterval) {
                while(isAlive){
                // Her skal vi opdateret slangen så den rykker.
                snake.move();
                System.out.println(snake.segments.get(0).getX());
                // Update the last update time
                lastUpdateTime = now;
                }
                return;
            }
        
        }
    }

    private Rectangle createApple(){
        Rectangle apple = new Rectangle(TILE_SIZE-5, TILE_SIZE-5);
        apple.setArcHeight(30);
        apple.setArcWidth(15);
        apple.setFill(Color.TOMATO);
        return apple;
    }
    public void spawnApple() {
        Random random = new Random();
        int appleX, appleY;

        appleX = random.nextInt(GRID_WIDTH);
        appleY = random.nextInt(GRID_HEIGHT);

        apple = createApple();
        GridPane.setHalignment(apple, HPos.CENTER);
        GridPane.setValignment(apple, VPos.CENTER);
        grid.add(apple, appleX, appleY);
        ateApple = false;

    }

        private void handleKey(KeyEvent event){
        if(event.getCode() == KeyCode.UP && !snake.direction.equals("DOWN")){
            snake.direction = "UP";
        } else if(event.getCode() == KeyCode.DOWN && !snake.direction.equals("UP")){
            snake.direction = "DOWN";
        } else if(event.getCode() == KeyCode.RIGHT && !snake.direction.equals("LEFT")){
            snake.direction = "RIGHT";
        } else if(event.getCode() == KeyCode.LEFT && !snake.direction.equals("RIGHT")){
            snake.direction = "LEFT";
        } else{
            return;
        }
    }
    private void checkCollision() {
        int headX = GridPane.getColumnIndex(snake.segments.get(0));
        int headY = GridPane.getRowIndex(snake.segments.get(0));

        // Check collision with itself
        for (int i = 1; i < snake.segments.size(); i++) {
            Rectangle position = snake.segments.get(i);
            int x = GridPane.getColumnIndex(position);
            int y = GridPane.getRowIndex(position);

            if (headX == x && headY == y) {
                isAlive = false;
                gameOver();
            }
        }
        
        int appleX = GridPane.getColumnIndex(apple);
        int appleY = GridPane.getRowIndex(apple);
        if (headX == appleX && headY == appleY) {
            ateApple = true; // changes boolean value to true which leaves a tail segment behind
            eatApple();
        }
    }

    private void eatApple() {
        grid.getChildren().remove(apple); // Remove apple from the grid
    
        spawnApple(); // spawn a new apple 
    }
}


