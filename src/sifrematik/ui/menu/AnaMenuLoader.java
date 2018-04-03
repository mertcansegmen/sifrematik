
package sifrematik.ui.menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class AnaMenuLoader extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sifrematik/ui/giris/Giris.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Şifrematik Giriş");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/Görseller/logo.png"));
        stage.setScene(scene);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

    
    
    
}
