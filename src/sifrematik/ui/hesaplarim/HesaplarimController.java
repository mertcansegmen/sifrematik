
package sifrematik.ui.hesaplarim;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sifrematik.database.DatabaseHandler;


public class HesaplarimController implements Initializable {

    @FXML
    private TableView<Hesap> tableView;
    @FXML
    private TableColumn<Hesap, String> siteSutun;
    @FXML
    private TableColumn<Hesap, String> idSutun;
    @FXML
    private TableColumn<Hesap, String> sifreSutun;
    
    ObservableList<Hesap> list = FXCollections.observableArrayList();
    @FXML
    private Button silBtn;
    
    DatabaseHandler databaseHandler;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler.getInstance();
        initCol();
        LoadData();
        tableView.setPlaceholder(new Label("Henüz bir hesap eklemediniz."));
    }    
    
    private void initCol(){
        idSutun.setCellValueFactory(new PropertyValueFactory<>("id")); // it has to be exactly the same with the object
        sifreSutun.setCellValueFactory(new PropertyValueFactory<>("sifre"));
        siteSutun.setCellValueFactory(new PropertyValueFactory<>("site"));
    }
    
    private void LoadData(){
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String qu = "SELECT * FROM HESAP ORDER BY site,id";
        ResultSet rs = handler.execQuery(qu);
        try {
            while(rs.next()){
                String id = rs.getString("id");
                String sifre = rs.getString("sifre");
                String site = rs.getString("site");
                list.add(new Hesap(site,id,sifre));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HesaplarimController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.getItems().setAll(list);
    }

    @FXML
    private void hesapSil(ActionEvent event) {
        
        ButtonType evet = new ButtonType("Evet", ButtonBar.ButtonData.OK_DONE);
        ButtonType hayir = new ButtonType("Hayır", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Bu hesabı silmek istediğinizden emin misiniz?", evet, hayir);
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setTitle(null);
        Optional<ButtonType> confirmationResult = confirmationAlert.showAndWait();
        if(confirmationResult.get() == evet){
            
            ObservableList<Hesap> secilenHesap, butunHesaplar;
            butunHesaplar = tableView.getItems();
            secilenHesap = tableView.getSelectionModel().getSelectedItems();
            Hesap secilenHesapDB = tableView.getSelectionModel().getSelectedItem();
            
            if(secilenHesap.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Hesap Silinemedi");
                alert.setContentText("Silmek için bir hesap seçmediniz.");
                alert.showAndWait();
                return;
            }
            
            Boolean result = DatabaseHandler.getInstance().hesapSil(secilenHesapDB);
            if(result){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Başarılı");
                alert.setContentText(secilenHesapDB.getSite() + " hesabınız: " + secilenHesapDB.getId() + " başarıyla silindi.");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Başarısız.");
                alert.setContentText("Hesap silinemedi.");
                alert.showAndWait();
            }
              
            secilenHesap.forEach(butunHesaplar::remove);
            
        }
            
    }
    
    public static class Hesap{
        
        private final SimpleStringProperty id;
        private final SimpleStringProperty sifre;
        private final SimpleStringProperty site;
        
        Hesap(String site,String id, String sifre){
            this.id = new SimpleStringProperty(id);
            this.sifre = new SimpleStringProperty(sifre);
            this.site = new SimpleStringProperty(site);
        }

        public String getId() {
            return id.get();
        }

        public String getSifre() {
            return sifre.get();
        }
        
        public String getSite() {
            return site.get();
        }
        
    }    
}
