/* package GameOverPackage;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOver extends Application {
    public static final int GRID_HEIGHT = 200;
    public static final int GRID_WIDTH = 200;
    public static final double GRID_SIZE = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        new GridPane();

        Group root = new Group();
        Scene scene = new Scene(root, GRID_HEIGHT, GRID_WIDTH);

        stage.setTitle("Game Over");
        stage.setResizable(true);

        Text go = new Text();
        go.setText("Game Over!");
        go.setY(GRID_HEIGHT/3);
        go.setX(GRID_WIDTH/3);
        go.setFill(Color.RED);

        Text pa = new Text();
        pa.setText("Press ENTER to start a new game");
        pa.setY(GRID_HEIGHT/2);
        pa.setX(GRID_WIDTH/15);
        pa.setFill(Color.WHITE);

        root.getChildren().add(go);
        root.getChildren().add(pa);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }
    private void showGameOverScene() {
        Group gameOverRoot = new Group();
        Scene gameOverScene = new Scene(gameOverRoot, GameOver.GRID_HEIGHT, GameOver.GRID_WIDTH);
    
        Stage gameOverStage = new Stage();
        gameOverStage.setTitle("Game Over");
        gameOverStage.setResizable(false);

        Text go = new Text();
        go.setText("Game Over!");
        go.setY(GameOver.GRID_HEIGHT / 3);
        go.setX(GameOver.GRID_WIDTH / 3);
        go.setFill(Color.RED);

        Text pa = new Text();
        pa.setText("Press ENTER to start a new game");
        pa.setY(GameOver.GRID_HEIGHT / 2);
        pa.setX(GameOver.GRID_WIDTH / 15);
        pa.setFill(Color.WHITE);

        gameOverRoot.getChildren().add(go);
        gameOverRoot.getChildren().add(pa);
        gameOverScene.setFill(Color.BLACK);
        gameOverStage.setScene(gameOverScene);

        gameOverStage.show();
    }

}
 */