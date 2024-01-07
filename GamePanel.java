import java.util.Random;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GamePanel extends Application {
    private GridPane grid;
    private Rectangle apple;
    public static final int GRID_HEIGHT = 10;
    public static final int GRID_WIDTH = 20;
    public static final int TILE_SIZE = 30;

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
    }
    private void createGrid(){
        for(int row = 0; row<GRID_WIDTH; row++){
            for(int col = 0; col<GRID_HEIGHT; col++){
                Rectangle pane = new Rectangle(TILE_SIZE, TILE_SIZE);
                pane.setFill(Color.BLUE);
                pane.setStroke(Color.BLACK);
                pane.setStrokeWidth(0.5);
                grid.add(pane, row, col);
            }
        }
        spawnApple();
    }

    private void handleKey(KeyEvent event){
        
    }

    public void createSnake(){
        Snake snake = new Snake(GRID_WIDTH/2, GRID_HEIGHT/2);
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
        Rectangle apple = new Rectangle(TILE_SIZE, TILE_SIZE);
        apple.setArcHeight(100);
        apple.setArcWidth(100);
        apple.setFill(Color.RED);
        return apple;
    }
    private void spawnApple() {
        Random random = new Random();
        int appleX, appleY;

        appleX = random.nextInt(GRID_WIDTH);
        appleY = random.nextInt(GRID_HEIGHT);

        apple = createApple();
        grid.add(apple, appleX, appleY);

    }
}
