import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class Strawberry extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);

        Canvas canvas = new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.fillOval(10, 10, 100, 70);

        root.getChildren().add(canvas);
        
    }
}