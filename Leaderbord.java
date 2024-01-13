import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Leaderbord extends Application{
    @Override
    public void start(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root);
        
        stage.setTitle("Snake");

        Rectangle rect = new Rectangle(800, 600);
        Font font = Font.font(40);


        Button high = new Button("High");
        high.setFont(font);
        high.setMinSize(400, 50);
        high.setOnAction(event -> {
        MainMenu.speed = 100000000;
        try {
            /* menu.start(stage); */
        } catch (Exception e) {
            e.printStackTrace();
        }
        });

        VBox vBox = new VBox(50, high);
        vBox.setTranslateX(200);
        vBox.setTranslateY(50);

        root.getChildren().addAll(rect, vBox);
        stage.setScene(scene);
        stage.show();
    }
    
}
