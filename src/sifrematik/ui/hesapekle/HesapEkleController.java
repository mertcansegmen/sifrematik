package sifrematik.ui.hesapekle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import sifrematik.database.DatabaseHandler;


public class HesapEkleController implements Initializable {
    
    @FXML
    private BorderPane rootPane;
    @FXML
    private TextField idTF;
    @FXML
    private PasswordField sifrePF;
    @FXML
    private VBox vbox;
    @FXML
    private ComboBox<String> comboBox;
    
    DatabaseHandler databaseHandler;
    String secilenSite;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        databaseHandler = DatabaseHandler.getInstance();
        comboBox.getItems().addAll("Facebook","Instagram","Twitter","Reddit","Spotify","Outlook","Google","Steam","Origin","League of Legends","Diğer");
        comboBox.setPromptText("Platform seçin");
        comboBox.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            System.out.println(oldValue);
            if(newValue.equals("Diğer")){
                arkaplaniGoster(newValue,oldValue);
                comboBox.setEditable(true);
                comboBox.setPromptText("Diğer..");
            }
            else if( newValue.equals("Facebook") || newValue.equals("Instagram") || newValue.equals("Twitter") || newValue.equals("Reddit") || newValue.equals("Spotify") || 
            newValue.equals("Outlook") || newValue.equals("Google") || newValue.equals("Steam") || newValue.equals("Origin") || newValue.equals("League of Legends") ){
                arkaplaniGoster(newValue,oldValue);
                comboBox.setEditable(false);
                comboBox.setPromptText(newValue);
            }
            else{
                newValue="Diğer";
                arkaplaniGoster(newValue,oldValue);
            }
        });
        
    }
    
    //Combobox'tan seçilen platforma bağlı olarak arka planı fade animasyonuyla değiştiren fonksiyon
    private void arkaplaniGoster(String newValue,String oldValue){
        if(oldValue==null)
            oldValue="Diğer";
        String oldImage = HesapEkleController.class.getResource("/Görseller/" + oldValue + "BG.png").toExternalForm();
        vbox.setStyle("-fx-background-image: url('" + oldImage + "'); " + "-fx-background-position: center center; " + 
                      "-fx-background-repeat: stretch;" + "-fx-background-size: 600px 400px;" );
        FadeTransition ft = new FadeTransition(Duration.millis(300), vbox);
        ft.setFromValue(1);
        ft.setToValue(0.3);
        ft.setCycleCount(1);
        ft.play();
        
        //ft FadeTransition objesi işini bitirince aşağıdaki kod parçası çalışacak
        ft.setOnFinished(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    String image = HesapEkleController.class.getResource("/Görseller/" + newValue + "BG.png").toExternalForm();
                    vbox.setStyle("-fx-background-image: url('" + image + "'); " + "-fx-background-position: center center; " + 
                                  "-fx-background-repeat: stretch;" + "-fx-background-size: 600px 400px;" );
                    FadeTransition ft2 = new FadeTransition(Duration.millis(300), vbox);
                    ft2.setFromValue(0.3);
                    ft2.setToValue(1.0);
                    ft2.setCycleCount(1);
                    ft2.play();
                }
            });
    }
    
    @FXML
    private void hesapEkle(ActionEvent event) { 
        String site= comboBox.getValue();
        String id = idTF.getText();
        String sifre = sifrePF.getText();
        
        if((site == null || site.isEmpty()) && (id.isEmpty() || sifre.isEmpty())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Hesap Eklenemedi");
            alert.setContentText("Lütfen hesap türünü seçip bütün alanları doldurun.");
            alert.showAndWait();
            return;
        }
        
        if(site == null || site.isEmpty() ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Hesap Eklenemedi");
            alert.setContentText("Lütfen hesap türünü seçiniz.");
            alert.showAndWait();
            return;
        }
        
        if(id.isEmpty() || sifre.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Hesap Eklenemedi");
            alert.setContentText("Lütfen bütün alanları doldurun.");
            alert.showAndWait();
            return;
        }
        
        
        String qu = "INSERT INTO HESAP VALUES ("+
                    "'" + site + "'," +
                    "'" + id + "'," +
                    "'" + sifre + "'" +
                    ")";
        
        if(databaseHandler.execAction(qu)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Başarılı");
            alert.setContentText(site + " hesabınız: " + id + " başarıyla eklendi.");
            alert.showAndWait();
            idTF.clear();
            sifrePF.clear();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Başarısız.");
            alert.setContentText("Hesap eklenemedi.");
            alert.showAndWait();
        }

    }
    
    @FXML
    private void iptalEt(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/sifrematik/ui/anasayfa/AnaSayfa.fxml"));
        rootPane.getChildren().remove(vbox);
        rootPane.setCenter(parent);
    }
    
}
