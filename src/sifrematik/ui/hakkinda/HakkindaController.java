package sifrematik.ui.hakkinda;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class HakkindaController implements Initializable {

    @FXML
    private Label isletimSistemi;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Font.loadFont(getClass().getResourceAsStream("/Fontlar/Roboto-Thin.ttf"), 25);
        isletimSistemi.setText("Sistem: " + System.getProperty("os.name"));
    }    
    
}
