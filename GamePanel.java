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
import javafx.animation.*;
import javafx.event.*;


public class GamePanel extends Application {
    private GridPane grid;
    private Rectangle apple;
    public static final int GRID_HEIGHT = 15;
    public static final int GRID_WIDTH = 20;
    public static final int TILE_SIZE = 30;
    Snake snake = new Snake(GRID_WIDTH/2, GRID_HEIGHT/2);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        grid = new GridPane();
        
        Group root = new Group();
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("SNAKE");
        primaryStage.setResizable(true);

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
        primaryStage.setScene(scene);
        primaryStage.show();

        MyAnimationTimer animationTimer = new MyAnimationTimer();
        animationTimer.start();

        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKey);
    }
    private void showGameOver(Stage stage) {

        Group root = new Group();
        Scene scene = new Scene(root, GRID_HEIGHT, GRID_WIDTH);

        stage.setTitle("Game Over");
        stage.setResizable(true);

        Text go = new Text();
        go.setText("Game Over!");
        go.setY(GRID_HEIGHT/3);
        go.setX(GRID_WIDTH/3);
        go.setFill(Color.RED);

        Text pa = new Text();
        pa.setText("Press ENTER to start a new game");
        pa.setY(GRID_HEIGHT/2);
        pa.setX(GRID_WIDTH/15);
        pa.setFill(Color.WHITE);

        root.getChildren().add(go);
        root.getChildren().add(pa);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                restartGame(stage);

            }
        });
    }
    private void restartGame(Stage stage) {
        IsAlive = true;
        start(stage);
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

    private Rectangle createApple(){
        Rectangle apple = new Rectangle(TILE_SIZE-5, TILE_SIZE-5);
        apple.setArcHeight(30);
        apple.setArcWidth(15);
        apple.setFill(Color.TOMATO);
        return apple;
    }

    private void spawnApple() {
        Random random = new Random();
        int appleX = random.nextInt(GRID_WIDTH);
        int appleY = random.nextInt(GRID_HEIGHT);

        //The apple dosent spawn on to snake.
        for(Rectangle a: snake.segments){
            if(appleX == a.getX() && appleY == a.getY()){
                    spawnApple();
            }
        }
        apple = createApple();
        GridPane.setHalignment(apple, HPos.CENTER);
        GridPane.setValignment(apple, VPos.CENTER);
        grid.add(apple, appleX, appleY);
    
    }
    //Putting barriers so the snake cannot go in the opposite way of what it is currently moving.
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

    public class MyAnimationTimer extends AnimationTimer {
        private long lastUpdateTime = 0;
        private final long updateInterval = 300000000; // 0,3 sekund
    
        @Override
        public void handle(long now) {
            if (now - lastUpdateTime >= updateInterval) {
                // Here we update the snake for everytime it moves.
                snake.move();
                System.out.println(snake.segments.get(0).getX());
                // Update the last update time
                lastUpdateTime = now;
            }
        }
    }
}