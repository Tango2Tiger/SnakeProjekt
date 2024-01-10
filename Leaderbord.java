import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Leaderbord extends Application{
    @Override
    public void start(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Snake");

        Rectangle rect = new Rectangle(800, 600);


        root.getChildren().addAll(rect);
        stage.show();
    }
    
}
