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
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.event.*;


public class GamePanel extends Application {
    private GridPane grid;
    private Rectangle apple;
    public static final int GRID_HEIGHT = 10;
    public static final int GRID_WIDTH = 20;
    public static final int TILE_SIZE = 30;
    Snake snake = new Snake(GRID_WIDTH/2, GRID_HEIGHT/2);

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
                pane.setStrokeWidth(1);
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

    public void drawSnake(){
        ArrayList<Rectangle> l = snake.segments;
        String dir = snake.direction;

        int headX = grid.getColumnIndex(l.get(0));
        int headY = grid.getRowIndex(l.get(0));

        Rectangle head = l.get(0);
        
        //grid.add(l.get(0),headX,headY);
        //grid.getChildren().set(0, l.get(0));
        System.out.println(grid.getChildren());
    }


    public class MyAnimationTimer extends AnimationTimer {

        private long lastUpdateTime = 0;
        private final long updateInterval = 300000000; // 0,3 sekund
    
        @Override
        public void handle(long now) {
            if (now - lastUpdateTime >= updateInterval) {
                // Her skal vi opdateret slangen så den rykker.
                System.out.println("Printing something...");
                snake.move();
                drawSnake();
                System.out.println(snake.segments.get(0).getX());
                // Update the last update time
                lastUpdateTime = now;
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
    private void spawnApple() {
        Random random = new Random();
        int appleX, appleY;

        appleX = random.nextInt(GRID_WIDTH);
        appleY = random.nextInt(GRID_HEIGHT);

        apple = createApple();
        GridPane.setHalignment(apple, HPos.CENTER);
        GridPane.setValignment(apple, VPos.CENTER);
        grid.add(apple, appleX, appleY);

    }

        private void handleKey(KeyEvent event){
        if(event.getCode() == KeyCode.UP && !snake.direction.equals("DOWN")){
            snake.direction = "UP";
            grid.setConstraints(snake.segments.get(0), grid.getColumnIndex(snake.segments.get(0)), grid.getRowIndex(snake.segments.get(0))-1);
        } else if(event.getCode() == KeyCode.DOWN && !snake.direction.equals("UP")){
            snake.direction = "DOWN";
            grid.setConstraints(snake.segments.get(0), grid.getColumnIndex(snake.segments.get(0)), grid.getRowIndex(snake.segments.get(0))+1);
        } else if(event.getCode() == KeyCode.RIGHT && !snake.direction.equals("LEFT")){
            snake.direction = "RIGHT";
            grid.setConstraints(snake.segments.get(0), grid.getColumnIndex(snake.segments.get(0))+1, grid.getRowIndex(snake.segments.get(0)));
        } else if(event.getCode() == KeyCode.LEFT && !snake.direction.equals("RIGHT")){
            snake.direction = "LEFT";
            grid.setConstraints(snake.segments.get(0), grid.getColumnIndex(snake.segments.get(0))-1, grid.getRowIndex(snake.segments.get(0)));
        } else{
            return;
        }
    }
}
