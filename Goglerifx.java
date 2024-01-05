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
        primaryStage.setTitle("Snake Game");
        //Stackpane root = new StackPane();
        Group root = new Group();
        Stage stage = new Stage();
        Scene scene = new Scene(root, 600, 600);

        Rectangle rect = new Rectangle();
        Rectangle[] retcs = new Rectangle[10];
        
        for(int i = 0; i<10;i++){
            rect.setX(i*10);
            rect.setY(i*10);
            rect.setWidth(40);
            rect.setHeight(40);
            rect.setFill(Color.AQUA);
            
        }
        root.getChildren().add(rect);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}