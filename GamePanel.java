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



public class GamePanel extends Application {
    public GridPane grid;
    private Rectangle apple;
    public static int GRID_HEIGHT = 17;
    public static int GRID_WIDTH = 17;
    public static final int TILE_SIZE = 30;
    public static Snake snake = new Snake((int)(1/2)*GRID_WIDTH, (int)(1/2)*GRID_HEIGHT, Color.LIMEGREEN);
    public boolean isAlive = true;
    MyAnimationTimer animationTimer = new MyAnimationTimer();
    public int scoreCounter;
    Text score = new Text();
    public static Stage stage;
    public int segmentSize = snake.segments.size();
    public static long speed = 200000000;
    ArrayList<String> dirLs = new ArrayList<>();

    static String path = "Dog.mp3"; 
    static Media media = new Media(new File(path).toURI().toString());
    static MediaPlayer mediaPlayer = new MediaPlayer(media);
    

     public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.1);
        grid = new GridPane();
        stage = primaryStage;
        
        Group root = new Group();
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("SNAKE");
        primaryStage.setResizable(true);

        createGrid();
        createSnake();
        
        score.setText("Score:" + scoreCounter);
        score.setX(10);
        score.setY(25);
        score.setFont(Font.font("Roboto",30));
        score.setFill(Color.CORNFLOWERBLUE);
        
        root.getChildren().add(grid);
        root.getChildren().add(score);
        scene.setFill(Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        animationTimer.start();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKey);
    }


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


    private Rectangle createApple(){
        Rectangle apple = new Rectangle(TILE_SIZE-5, TILE_SIZE-5);
        apple.setArcHeight(30);
        apple.setArcWidth(15);
        apple.setFill(Color.TOMATO);
        return apple;
    }


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


    //Putting barriers so the snake cannot go in the opposite way of what it is currently moving.
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
                animationTimer.stop();
                //gameOver();
            }
        }
        
        int appleX = GridPane.getColumnIndex(apple);
        int appleY = GridPane.getRowIndex(apple);

        if (headX == appleX && headY == appleY) {
            eatApple();
        }
    }


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


