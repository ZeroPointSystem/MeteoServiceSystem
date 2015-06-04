package meteoserver.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by Zetro on 08.05.2015.
 */
public class ServerConsoleView extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("serverConsole.fxml"));
            stage.setTitle("Meteodata server by ZTR Systems Inc.");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.getIcons().add(new Image("icon.png"));
            stage.setResizable(false);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
