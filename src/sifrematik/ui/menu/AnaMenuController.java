package sifrematik.ui.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AnaMenuController implements Initializable {

    @FXML
    private BorderPane rootPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem cik;
    @FXML
    private MenuItem hakkinda;
    @FXML
    private Button anaSayfaBtn;
    @FXML
    private Button hesapEkleBtn;
    @FXML
    private Button hesaplarimBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setOnCloseRequest(e -> {
            e.consume();
            carpiTiklandi();
        });
        */
    }    

    @FXML
    private void hesapEkle(ActionEvent event) throws IOException{
        loadScene("/sifrematik/ui/hesapekle/HesapEkle.fxml");
    }

    @FXML
    private void hesaplarim(ActionEvent event) throws IOException {
        loadScene("/sifrematik/ui/hesaplarim/Hesaplarim.fxml");
    }
    
    @FXML
    private void anaSayfa(ActionEvent event) throws IOException {
        loadScene("/sifrematik/ui/anasayfa/AnaSayfa.fxml");
    }
    
    @FXML
    private void sifreUret(ActionEvent event) throws IOException {
        loadScene("/sifrematik/ui/sifreuret/SifreUret.fxml");
    }
    
    @FXML
    private void hakkindaPenceresi(ActionEvent event) throws IOException {
        loadWindow("/sifrematik/ui/hakkinda/Hakkinda.fxml","Şifrematik Hakkında");
    }
    
    
    private void loadScene(String loc) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource(loc));
        rootPane.setCenter(parent);
        
    }
    
    private void loadWindow(String loc,String title) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource(loc));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }

    @FXML
    private void cikisYap(ActionEvent event) {
        /*
        ButtonType evet = new ButtonType("Evet", ButtonBar.ButtonData.OK_DONE);
        ButtonType hayir = new ButtonType("Hayır", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Çıkmak istediğinizden emin misiniz?", evet, hayir);
        alert.setHeaderText(null);
        alert.setTitle(null);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == evet)*/
            Platform.exit();
    }

    /*
    private void carpiTiklandi() {
        ButtonType evet = new ButtonType("Evet", ButtonBar.ButtonData.OK_DONE);
        ButtonType hayir = new ButtonType("Hayır", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Çıkmak istediğinizden emin misiniz?", evet, hayir);
        alert.setHeaderText(null);
        alert.setTitle(null);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == evet)
            Platform.exit();
    }
*/

    
    
}
