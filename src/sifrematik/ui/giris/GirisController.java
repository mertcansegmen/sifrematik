/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sifrematik.ui.giris;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class GirisController implements Initializable {

    @FXML
    private TextField idTF;
    @FXML
    private TextField sifreTF;
    @FXML
    private Label hataliGiris;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void girisYap(ActionEvent event) throws IOException {
        String sifre = sifreTF.getText();
        String id = idTF.getText();
        
        
        if(id.equalsIgnoreCase("admin") && sifre.equals("admin")){
            Stage stage = (Stage)idTF.getScene().getWindow();
            stage.close();
            loadWindow("/sifrematik/ui/menu/AnaMenu.fxml", "Şifrematik");
        }
        else{
            hataliGiris.setVisible(true);
        }
    }

    @FXML
    private void cikisYap(ActionEvent event) {
        System.exit(0);
    }
    
    private void loadWindow(String loc,String title) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource(loc));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.getIcons().add(new Image("/Görseller/logo.png"));
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }

    @FXML
    private void onEnter(ActionEvent event) throws IOException {
        String sifre = sifreTF.getText();
        String id = idTF.getText();
        
        
        if(id.equalsIgnoreCase("admin") && sifre.equals("admin")){
            Stage stage = (Stage)idTF.getScene().getWindow();
            stage.close();
            loadWindow("/sifrematik/ui/menu/AnaMenu.fxml", "Şifrematik");
        }
        else{
            hataliGiris.setVisible(true);
        }
    }

    
}
