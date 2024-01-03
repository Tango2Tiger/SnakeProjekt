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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        grid = new GridPane();
        
        Group root = new Group();
        Scene scene = new Scene(root);
        
        stage.setTitle("SNAKE");
        stage.setHeight(550);
        stage.setWidth(750);
        stage.setResizable(false);

        Text points = new Text();
        points.setText("Points:");
        points.setX(10);
        points.setY(25);
        points.setFont(Font.font("Roboto",25));
        points.setFill(Color.LIMEGREEN);
        
        root.getChildren().add(points);
        stage.setScene(scene);
        stage.show();
    }

}
