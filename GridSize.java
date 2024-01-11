import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;

public class GridSize extends Application{
    static MainMenu menu = new MainMenu();
    public GridPane grid = new GridPane();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setTitle("Snake");

        Font font = Font.font(40);

        Rectangle rect = new Rectangle(800, 600);
        Text gridText = new Text();
        gridText.setText("Choose grid size:");
        gridText.setX(10);
        gridText.setY(25);
        gridText.setFont(Font.font("Roboto",40));
        gridText.setFill(Color.CORNFLOWERBLUE);

        Slider slider = new Slider(5, 50, 1);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(5);
        slider.setBlockIncrement(10);


        Text size = new Text();
        size.setX(10);
        size.setY(25);
        size.setFont(Font.font("Roboto",40));
        size.setFill(Color.CORNFLOWERBLUE);
        /* size.textProperty().bind((slider.valueProperty().asString())); */
        size.textProperty().bind(slider.valueProperty().asString("%.0f"));

        Button done = new Button("Done");
        done.setMinSize(400, 50);
        done.setFont(font);
        done.setOnAction(event -> {
        MainMenu.grid_size = (int) slider.getValue();
        try {
            menu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        });
        

        VBox vBox = new VBox(50, gridText, slider, size, done);
        vBox.setTranslateX(200);
        vBox.setTranslateY(50);
        root.getChildren().addAll(rect, vBox);
        stage.setScene(scene);
        stage.show();
    }
    
}
