import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Speed extends Application{
    @Override
    public void start(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Snake");

        Rectangle rect = new Rectangle(800, 600);
        Font font = Font.font(40);

        Button low = new Button();
        low.setFont(font);
        low.setMinSize(400, 50);
        low.setOnAction(event -> MainMenu.game.speed = 300000000);

        Button medium = new Button();
        medium.setFont(font);
        medium.setMinSize(400, 50);
        medium.setOnAction(event -> MainMenu.game.speed = 200000000);

        Button high = new Button();
        high.setFont(font);
        high.setMinSize(400, 50);
        high.setOnAction(event -> MainMenu.game.speed = 100000000);

        root.getChildren().addAll(rect);
        stage.show();
    }
}
