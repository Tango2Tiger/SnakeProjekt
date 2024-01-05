import java.util.Random;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GamePanel extends Application {
    private GridPane grid;
    private static final int GRID_HEIGHT = 15;
    private static final int GRID_WIDTH = 30;
    private static final int TILE_SIZE = 60;

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
    private void  createGrid(){
        for(int row = 0; row<GRID_WIDTH; row++){
            for(int col = 0; col<GRID_HEIGHT; col++){
                Rectangle pane = new Rectangle(TILE_SIZE, TILE_SIZE);
                pane.setFill(Color.BLUE);
                pane.setStroke(Color.BLACK);
                pane.setStrokeWidth(0.5);
                grid.add(pane, row, col);
            }
        }
    }

}
