import java.util.Random;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.*;
import java.io.File;


//Magnus
public class Multiplayer extends Application {
    public GridPane grid;
    private Rectangle apple;
    public static int GRID_WIDTH = 17;
    public static int GRID_HEIGHT = 17;
    public static final int TILE_SIZE = 30;
    public static Snake snake1 = new Snake((int) Math.round((2/3)*GRID_WIDTH), (int) Math.round((1/2)*GRID_HEIGHT), Color.LIMEGREEN);
    //Adding the second snake
    public static Snake snake2 = new Snake((int) Math.round((1/3)*GRID_WIDTH), (int) Math.round((1/2)*GRID_HEIGHT), Color.AQUAMARINE);
    public boolean isAlive = true;
    public boolean ateApple = false;
    MyAnimationTimer animationTimer = new MyAnimationTimer();
    
    Text score1 = new Text();
    //Adding another scorecounter
    Text score2 = new Text();
    Stage stage;
    static String path = "DOG.mp3"; 
    static Media media = new Media(new File(path).toURI().toString());
    static MediaPlayer mediaPlayer = new MediaPlayer(media);
    public static long speed = 200000000;

    //Main method to launch the multiplayer program
    public static void main(String[] args) {
        launch(args);
    }

    //override method to set up the multiplayer game display
    @Override
    public void start(Stage primaryStage) throws Exception {
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.1);

        snake1.playerName = "Player 1";
        snake2.playerName = "Player 2";

        grid = new GridPane();
        stage = primaryStage;
        
        Group root = new Group();
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("SNAKE");
        primaryStage.setResizable(true);

        createGrid();
        createSnake(snake1);
        createSnake(snake2);
        
        snake1.scoretxt.setText(snake1.playerName + ": " + snake1.score);
        snake1.scoretxt.setX(10);
        snake1.scoretxt.setY(25);
        snake1.scoretxt.setFont(Font.font("Roboto",30));
        snake1.scoretxt.setFill(Color.LIMEGREEN);

        snake2.scoretxt.setText(snake2.playerName + ": " + snake2.score);
        snake2.scoretxt.setX(340);
        snake2.scoretxt.setY(25);
        snake2.scoretxt.setFont(Font.font("Roboto",30));
        snake2.scoretxt.setFill(Color.RED);
        
        root.getChildren().add(grid);
        root.getChildren().addAll(snake1.scoretxt, snake2.scoretxt);
        scene.setFill(Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        animationTimer.start();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKey);
    }
//Holger
    //The method for creating the game grid
    private void createGrid(){
        for(int row = 0; row<GRID_WIDTH; row++){
            for(int col = 0; col<GRID_HEIGHT; col++){
                Rectangle pane = new Rectangle(TILE_SIZE, TILE_SIZE);
                pane.setFill(Color.WHITESMOKE);
                pane.setStroke(Color.BLACK);
                pane.setStrokeWidth(0.75);
                grid.add(pane, row, col);
            }
        }
        spawnApple();
    }

    //The method for creating the snakes on the grid
    public void createSnake(Snake snake){
        ArrayList<Rectangle> l = snake.segments;
        int xrect = 0;
        int yrect = 0;

        for(int i = 0; i < l.size(); i++){
            xrect = (int)(l.get(i).getX());
            yrect = (int)(l.get(i).getY());
            Rectangle rect = l.get(i);
            grid.add(rect,xrect,yrect);
        }
    }

    //This class is used for the animation timer for the game updates
    public class MyAnimationTimer extends AnimationTimer {
        private long lastUpdateTime = 0;
        private final long updateInterval = MainMenu.speed; // 0,1 sekund
    
        @Override
        public void handle(long now) {
            
             if(now - lastUpdateTime >= updateInterval) {
                // Her skal vi opdateret slangen så den rykker.
                move(snake1);
                move(snake2);
                checkCollision(snake1, snake2);
                checkCollision(snake2, snake1);
                // Update the last update time
                lastUpdateTime = now;
                }
            }
        
        }

//Clement
    //Method to move the snakes
    public void move(Snake snake){
        GridPane.setConstraints(snake.segments.get(snake.segments.size()-1), GridPane.getColumnIndex(snake.segments.get(0)), GridPane.getRowIndex(snake.segments.get(0)));
        snake.segments.add(1, snake.segments.get(snake.segments.size()-1));
        snake.segments.remove(snake.segments.size()-1);
        
        switch (snake.direction) {
            case "UP":
                if(GridPane.getRowIndex(snake.segments.get(0)) > 0){
                    GridPane.setConstraints(snake.segments.get(0), GridPane.getColumnIndex(snake.segments.get(0)), GridPane.getRowIndex(snake.segments.get(0))-1);
                } else{
                    GridPane.setConstraints(snake.segments.get(0), GridPane.getColumnIndex(snake.segments.get(0)), GRID_HEIGHT-1);
                }
                break;

            case "DOWN":
                if(GridPane.getRowIndex(snake.segments.get(0)) < GRID_HEIGHT-1){
                    GridPane.setConstraints(snake.segments.get(0), GridPane.getColumnIndex(snake.segments.get(0)), GridPane.getRowIndex(snake.segments.get(0))+1);
                } else{
                    GridPane.setConstraints(snake.segments.get(0), GridPane.getColumnIndex(snake.segments.get(0)), 0);
                }
                break;

            case "LEFT":
                if(GridPane.getColumnIndex(snake.segments.get(0)) > 0){
                    GridPane.setConstraints(snake.segments.get(0), GridPane.getColumnIndex(snake.segments.get(0))-1, GridPane.getRowIndex(snake.segments.get(0)));
                } else{
                    GridPane.setConstraints(snake.segments.get(0), GRID_WIDTH-1, GridPane.getRowIndex(snake.segments.get(0)));
                }
                break;

            case "RIGHT":
                if(GridPane.getColumnIndex(snake.segments.get(0)) < GRID_WIDTH-1){
                    GridPane.setConstraints(snake.segments.get(0), GridPane.getColumnIndex(snake.segments.get(0))+1, GridPane.getRowIndex(snake.segments.get(0)));
                } else{
                    GridPane.setConstraints(snake.segments.get(0), 0, GridPane.getRowIndex(snake.segments.get(0)));
                }
                break;
        
            default:
                break;
        }
    }

//Jonas
    //Method to crate the apple
    private Rectangle createApple(){
        Rectangle apple = new Rectangle(TILE_SIZE-5, TILE_SIZE-5);
        apple.setArcHeight(30);
        apple.setArcWidth(15);
        apple.setFill(Color.TOMATO);
        return apple;
    }

    //Method to spawn the apple
    public void spawnApple() {
        Random random = new Random();
        int appleX = random.nextInt(GRID_WIDTH);
        int appleY = random.nextInt(GRID_HEIGHT);

        apple = createApple();
        GridPane.setHalignment(apple, HPos.CENTER);
        GridPane.setValignment(apple, VPos.CENTER);
        grid.add(apple, appleX, appleY);

        //The apple dosent spawn on to snake.
        for(Rectangle a: snake1.segments){
            if(GridPane.getColumnIndex(apple) == GridPane.getColumnIndex(a) && GridPane.getRowIndex(apple) == GridPane.getRowIndex(a)){
                grid.getChildren().remove(apple);
                spawnApple();
            }
        }
        for(Rectangle b: snake2.segments){
            if(GridPane.getColumnIndex(apple) == GridPane.getColumnIndex(b) && GridPane.getRowIndex(apple) == GridPane.getRowIndex(b)){
                grid.getChildren().remove(apple);
                spawnApple();
            }
        }
        ateApple = false;
    }

//Magnus
    //Putting barriers so the snake cannot go in the opposite way of what it is currently moving.
    private void handleKey(KeyEvent event){
        switch (event.getCode()) {
            case UP:
                if(!snake1.direction.equals("DOWN")){
                    snake1.direction = "UP";
                }
                break;

            case DOWN:
                if(!snake1.direction.equals("UP")){
                    snake1.direction = "DOWN";
                }
                break;

            case LEFT:
                if(!snake1.direction.equals("RIGHT")){
                    snake1.direction = "LEFT";
                }
                break;

            case RIGHT:
                if(!snake1.direction.equals("LEFT")){
                    snake1.direction = "RIGHT";
                }
                break;
                
            case W:
                if(!snake2.direction.equals("DOWN")){
                    snake2.direction = "UP";
                }
                break;

            case S:
                if(!snake2.direction.equals("UP")){
                    snake2.direction = "DOWN";
                }
                break;

            case A:
                if(!snake2.direction.equals("RIGHT")){
                    snake2.direction = "LEFT";
                }
                break;

            case D:
                if(!snake2.direction.equals("LEFT")){
                    snake2.direction = "RIGHT";
                }
                break;
                
        
            default:
                break;
        } 
        return;
    }


    //Method to check for collision with the two snakes and with the apple
    private void checkCollision(Snake snake, Snake other) {
        int headX = GridPane.getColumnIndex(snake.segments.get(0));
        int headY = GridPane.getRowIndex(snake.segments.get(0));

        // Check collision with itself     
        for (int i = 1; i < snake.segments.size(); i++) {
            Rectangle position = snake.segments.get(i);
            int x = GridPane.getColumnIndex(position);
            int y = GridPane.getRowIndex(position);

            if (headX == x && headY == y) {
                isAlive = false;
                animationTimer.stop();
                //gameOver();
            }
        }

        /* for (int i = 0; i < other.segments.size(); i++) {
            Rectangle position = other.segments.get(i);
            int x = GridPane.getColumnIndex(position);
            int y = GridPane.getRowIndex(position);

            if (headX == x && headY == y) {
                isAlive = false;
                animationTimer.stop();
                //gameOver();
            }
        } */
        
        int appleX = GridPane.getColumnIndex(apple);
        int appleY = GridPane.getRowIndex(apple);

        if (headX == appleX && headY == appleY) {
            ateApple = true; // changes boolean value to true which leaves a tail segment behind
            eatApple(snake);
        }
    }
//Jonas
    //Method to grow the snakes if they eat the apple
    private void eatApple(Snake snake) {
        snake.score++;
        snake.scoretxt.setText(snake.playerName + ": " + snake.score);

        grid.getChildren().remove(apple); // Remove apple from the grid
        Rectangle body = new Rectangle(TILE_SIZE, TILE_SIZE);
        body.setFill(snake.col);
        
        snake.segments.add(body);
        grid.add(body, GridPane.getColumnIndex(snake.segments.get(snake.segmentSize - 1)), GridPane.getRowIndex(snake.segments.get(snake.segmentSize - 1)));
        
        spawnApple(); // spawn a new apple 
        snake.segmentSize = snake.segments.size();
    }
}


