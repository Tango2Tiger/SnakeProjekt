import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class Goglerifx extends Application{
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Gøgleri");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600);

        Rectangle rect = new Rectangle(10,10);
        rect.setX(50);
        rect.setY(50);
        System.out.println(rect.getX());

        root.getChildren().add(rect);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}