import java.util.Random;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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
import java.lang.Math;

//Clement
public class GamePanel extends Application {
    //We create our snake grid with and set different constants
    public GridPane grid;
    private Rectangle apple;
    public static int GRID_HEIGHT = 17;
    public static int GRID_WIDTH = 17;
    public static int TILE_SIZE = 30;
    public static Snake snake = new Snake((1/2)*GRID_WIDTH, (1/2)*GRID_HEIGHT, Color.LIMEGREEN);
    public boolean isAlive = true;
    //We use the animationtimer to create the different frames of the game, so the snake is moving
    MyAnimationTimer animationTimer = new MyAnimationTimer();
    public int scoreCounter;
    //We create the scorecounter
    Text score = new Text();
    public static Stage stage;
    //We create the snake segments that is used with the arraylist to update the snakelenght
    public int segmentSize = snake.segments.size();
    //The speed of the snake
    public static long speed = 200000000;
    ArrayList<String> dirLs = new ArrayList<>();

    //We implemented the music, so we have background music when the game is played
    static Media media = new Media(MainMenu.class.getResource("/resources/Dog.mp3").toString());
    static MediaPlayer mediaPlayer = new MediaPlayer(media);

    private StackPane root;
    MainMenu menu = new MainMenu();
    Stage primaryStage;
    Scene scene;

    // Main method to launch the program
     public static void main(String[] args) {
        launch(args);
    }

//Magnus
    // The override method is used from the application class to set up the stage
    @Override
    public void start(Stage primaryStage) throws Exception {
        //This sets the music to start whenever you run the MainMenu class.
        if(MainMenu.soundOn){
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setVolume(0.1);
        }
        
        //We create our snake game panel as a stage
        this.primaryStage = primaryStage;
        grid = new GridPane();
        root = new StackPane();
        root.getChildren().add(grid);

        scene = new Scene(root);

        primaryStage.setTitle("SNAKE");
        primaryStage.setResizable(true);

        /* calcTileSize();
        snake.create((int)(Math.floor(GRID_WIDTH/2)), (int)(Math.floor(GRID_HEIGHT/2)), TILE_SIZE);
        */
        createGrid();
        createSnake();

        //We design the scorecounter and put it in the corner
        score.setTranslateX(scene.getWidth()- GRID_WIDTH*13); // Adjust the X-coordinate as needed
        score.setTranslateY(scene.getHeight()- GRID_HEIGHT*14.5);
        score.setText("Score:" + scoreCounter);
        score.setFont(Font.font(25));
        score.setFill(Color.CORNFLOWERBLUE);
        root.getChildren().add(score);
        scene.setFill(Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
        animationTimer.start();
        //We add the keyevent to the primarystage so that we can
        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKey);
    }

//Jonas
    //Method to create the standard sized grid for the snake
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

    //This method is used to calculate the size of the tiles
    public void calcTileSize(){
        int maxi = Math.max(GRID_WIDTH, GRID_HEIGHT);
        TILE_SIZE = (int)(Math.floor(650/maxi));
    } 

    //This method is for the creation of the snake
    public void createSnake(){
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
//Holger
    //Here is the creation of the scene for the gameover scene.
    private void showGameOverScene() {
        Rectangle gameOverOverlay = new Rectangle(800, 600, Color.BLACK);
        
        Text ys = new Text("You're score was: " + scoreCounter);
        ys.setFont(Font.font(30));
        ys.setFill(Color.BLUE);

        Text go = new Text("Game Over!");
        go.setFont(Font.font(50));
        go.setFill(Color.RED);

        Text pa = new Text("Press ENTER to start a new game");
        pa.setFill(Color.WHITE);

        Text mm = new Text("Press ESC to go back to the main menu");
        mm.setFill(Color.WHITE);

        VBox gameOverContent = new VBox(50, go, ys, pa, mm);
        gameOverContent.setSpacing(30);
        gameOverContent.setAlignment(Pos.CENTER);
        mediaPlayer.pause();
        root.getChildren().addAll(gameOverOverlay, gameOverContent);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            try {
                handleKeys(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }); 
    }

    //Here is the method that handles the keyevents on the showGameOverScene scene.
    private void handleKeys(KeyEvent event) throws Exception{
        if (event.getCode() == KeyCode.ESCAPE && !isAlive){
            GamePanel.speed = 2000000000;
            restartgame();
            menu.start(primaryStage);
        } else if(event.getCode() == KeyCode.ENTER && !isAlive){
            mediaPlayer.play();
            restartgame();
        } else{
            return;
        }
    }

    //This is the method to restart the game
    private void restartgame() throws Exception {
        animationTimer.stop();
        grid.getChildren().clear();
        snake = new Snake((1/2)*GRID_HEIGHT, (1/2)*GRID_WIDTH, Color.LIMEGREEN);
        
        GamePanel newGame = new GamePanel();
        newGame.start(primaryStage);
    }

    //This class is used for the animation timer for the game updates
    public class MyAnimationTimer extends AnimationTimer {
        private long lastUpdateTime = 0;
        private final long updateInterval = GamePanel.speed;
    
        @Override
        public void handle(long now) {
             if(now - lastUpdateTime >= updateInterval) {
                // Move the snake.
                move();
                checkCollision();
                // Update the last update time
                lastUpdateTime = now;
            }
        }
    }
    
//Magnus
    //This method is to move the snake based on its current direction
    public void move(){
        Rectangle head = snake.segments.get(0);
        
        GridPane.setConstraints(snake.segments.get(snake.segments.size()-1), GridPane.getColumnIndex(head), GridPane.getRowIndex(head));
        snake.segments.add(1, snake.segments.get(snake.segments.size()-1));
        snake.segments.remove(snake.segments.size()-1);

        if(!dirLs.isEmpty()){
            snake.direction = dirLs.get(0);
            dirLs.remove(0);
        }
        
        switch (snake.direction) {
            case "UP":
                if(GridPane.getRowIndex(head) > 0){
                    GridPane.setConstraints(head, GridPane.getColumnIndex(head), GridPane.getRowIndex(head)-1);
                } else{
                    GridPane.setConstraints(head, GridPane.getColumnIndex(head), GRID_HEIGHT-1);
                }
                break;

            case "DOWN":
                if(GridPane.getRowIndex(head) < GRID_HEIGHT-1){
                    GridPane.setConstraints(head, GridPane.getColumnIndex(head), GridPane.getRowIndex(head)+1);
                } else{
                    GridPane.setConstraints(head, GridPane.getColumnIndex(head), 0);
                }
                break;

            case "LEFT":
                if(GridPane.getColumnIndex(head) > 0){
                    GridPane.setConstraints(head, GridPane.getColumnIndex(head)-1, GridPane.getRowIndex(head));
                } else{
                    GridPane.setConstraints(head, GRID_WIDTH-1, GridPane.getRowIndex(head));
                }
                break;

            case "RIGHT":
                if(GridPane.getColumnIndex(head) < GRID_WIDTH-1){
                    GridPane.setConstraints(head, GridPane.getColumnIndex(head)+1, GridPane.getRowIndex(head));
                } else{
                    GridPane.setConstraints(head, 0, GridPane.getRowIndex(head));
                }
                break;
        
            default:
                break;
        }
    }

//Jonas
    //This method is to create the apple the snake is eating
    private Rectangle createApple(){
        Rectangle apple = new Rectangle(TILE_SIZE-5, TILE_SIZE-5);
        apple.setArcHeight(30);
        apple.setArcWidth(15);
        apple.setFill(Color.TOMATO);
        return apple;
    }

    //This method is for spawning the apple
    public void spawnApple() {
        Random random = new Random();
        int appleX = random.nextInt(GRID_WIDTH);
        int appleY = random.nextInt(GRID_HEIGHT);

        apple = createApple();
        GridPane.setHalignment(apple, HPos.CENTER);
        GridPane.setValignment(apple, VPos.CENTER);
        grid.add(apple, appleX, appleY);

        //The apple doesn't spawn on to snake.
        for(Rectangle a: snake.segments){
            if(GridPane.getColumnIndex(apple) == GridPane.getColumnIndex(a) && GridPane.getRowIndex(apple) == GridPane.getRowIndex(a)){
                grid.getChildren().remove(apple);
                spawnApple();
            }
        }
    }

//Clement
    //Method for putting barriers so the snake cannot go in the opposite way of what it is currently moving.
    private void handleKey(KeyEvent event){
        switch (event.getCode()) {
            case UP:
                if(!dirLs.contains("DOWN") && !snake.direction.equals("DOWN") && dirLs.size() < 2){
                    dirLs.add("UP");
                }
                break;

            case DOWN:
                if(!dirLs.contains("UP")&& !snake.direction.equals("UP") && dirLs.size() < 2){
                    dirLs.add("DOWN");
                }
                break;

            case LEFT:
                if(!dirLs.contains("RIGHT")&& !snake.direction.equals("RIGHT") && dirLs.size() < 2){
                    dirLs.add("LEFT");
                }
                break;

            case RIGHT:
                if(!dirLs.contains("LEFT")&& !snake.direction.equals("LEFT") && dirLs.size() < 2){
                    dirLs.add("RIGHT");
                }
                break;
                
        
            default:
                break;
        } 
        return;
    }

//Jonas
    //Method for checking collisions, both with the snake itself, but also with the apple.
    private void checkCollision() {
        int headX = GridPane.getColumnIndex(snake.segments.get(0));
        int headY = GridPane.getRowIndex(snake.segments.get(0));

        // Check collision with itself     
        for (int i = 1; i < snake.segments.size(); i++) {
            Rectangle position = snake.segments.get(i);
            int x = GridPane.getColumnIndex(position);
            int y = GridPane.getRowIndex(position);

            //This shows the game over scene if the snake collides with itself
            if (headX == x && headY == y) {
                isAlive = false;
                animationTimer.stop();
                showGameOverScene();
                return;
            }
        }
        
        int appleX = GridPane.getColumnIndex(apple);
        int appleY = GridPane.getRowIndex(apple);

        //Snake eats the apple if it collides with it.
        if (headX == appleX && headY == appleY) {
            eatApple();
        }
    }

    //Method that is used for when the snake eats the apple, and then for the snake to grow
    private void eatApple() {
        scoreCounter++;
        score.setText("Score:" + scoreCounter);

        grid.getChildren().remove(apple); // Remove apple from the grid
        Rectangle body = new Rectangle(TILE_SIZE, TILE_SIZE);
        body.setFill(Color.LIMEGREEN);
        
        snake.segments.add(body);
        grid.add(body, GridPane.getColumnIndex(snake.segments.get(segmentSize - 1)), GridPane.getRowIndex(snake.segments.get(segmentSize - 1)));
    
        spawnApple(); // spawn a new apple 
        segmentSize = snake.segments.size();
    }
}
