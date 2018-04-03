package sifrematik.ui.sifreuret;

import java.io.IOException;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import java.net.URL;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
//-javaagent:C:\Users\Mert\Documents\NetBeansProjects\SifreMatik\src\JarDosyaları\ScenicView.jar

public class SifreUretController implements Initializable {

    @FXML
    private Label sifre;
    @FXML
    private Slider karakterSayisiSlider;
    @FXML
    private Slider zorlukSlider;
    private Label kirmaSuresi;
    
    Number uzunluk = 8;
    Number zorluk = 1;
    @FXML
    private HBox sifreHBox;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Axis-Tick-Mark: " + zorlukSlider.lookup(".axis-tick-mark"));
        
        zorlukSlider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double n) {
                if (n < 1.5) 
                    return "Kolay";
                if (n < 2.5) 
                    return "Orta";
                return "Zor";

            }

            @Override
            public Double fromString(String s) {
                switch (s) {
                    case "Kolay":
                        return 0d;
                    case "Orta":
                        return 1d;
                    case "Zor":
                        return 2d;

                    default:
                        return 3d;
                }
            }
        });
        
        //kirmaSuresi.setTextFill(Color.RED);
        
        karakterSayisiSlider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> ovKS, Number oldValueKS, Number newValueKS) {
                uzunluk = newValueKS.intValue();
                //kirmaSuresi.setText(sureHesapla(zorluk,uzunluk,kirmaSuresi));
                System.out.println(uzunluk);
            }
        });
        
        zorlukSlider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> ovZ, Number oldValueZ, Number newValueZ) {
                zorluk = newValueZ.intValue();
                //kirmaSuresi.setText(sureHesapla(zorluk,uzunluk,kirmaSuresi));
                System.out.println(zorluk);
            }
        });
          
    }    

    @FXML
    private void sifreUret(ActionEvent event) {
        String sifrem="";
        Random rnd = new Random();
        
        char[] kolayDizi = "qwertyuopasdfghjklizxcvbnm0123456789".toCharArray();
        char[] ortaDizi = ("qwertyuopasdfghjklizxcvbnm0123456789" + "qwertyuopasdfghjklizxcvbnm".toUpperCase(Locale.ENGLISH)).toCharArray();
        char[] zorDizi = ("qwertyuopasdfghjklizxcvbnm0123456789!'^+%&/(@)=?_-#${[]}*.,:;" + "qwertyuopasdfghjklizxcvbnm".toUpperCase(Locale.ENGLISH)).toCharArray();
        System.out.println(zorluk);
        if((int)zorluk == 1){
            for(int i=0;i<(int)uzunluk;i++){
                int rasgeleIndex = rnd.nextInt(kolayDizi.length);
                sifrem += kolayDizi[rasgeleIndex];
            }
        }
        if((int)zorluk == 2){
            for(int i=0;i<(int)uzunluk;i++){
                int rasgeleIndex = rnd.nextInt(ortaDizi.length);
                sifrem += ortaDizi[rasgeleIndex];
            }
        }
        if((int)zorluk == 3){
            for(int i=0;i<(int)uzunluk;i++){
                int rasgeleIndex = rnd.nextInt(zorDizi.length);
                sifrem += zorDizi[rasgeleIndex];
            }
        }   
        
        sifreHBox.setVisible(true);
        sifre.setText(sifrem);
        
    }

    @FXML
    private void kopyala(ActionEvent event) {
        String kopyalanan = sifre.getText();
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(kopyalanan);
        clipboard.setContent(content);
    }
    
    @FXML
    private void bilgi(ActionEvent event) throws IOException {
        loadWindow("/sifrematik/ui/zorlukbilgi/ZorlukBilgi.fxml", null);
    }
    
    private void loadWindow(String loc,String title) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource(loc));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }
    
    /*
    
    private String sureHesapla(Number zorluk, Number uzunluk,Label label){
        switch((int)uzunluk){
            case 8:
                if((int)zorluk == 1){
                    label.setTextFill(Color.RED);
                    return "16 dakika, 33 saniye";
                }
                if((int)zorluk == 2){
                    label.setTextFill(Color.RED);
                    return "21 saat, 21 dakika";
                }
                if((int)zorluk == 3){
                    label.setTextFill(Color.ORANGE);
                    return "10 gün, 2 saat";
                }
            break;
            case 9:
                if((int)zorluk == 1){
                    label.setTextFill(Color.RED);
                    return "9 saat, 56 dakika";
                }
                if((int)zorluk == 2){
                    label.setTextFill(Color.ORANGE);
                    return "1 ay, 25 gün";
                }
                if((int)zorluk == 3){
                    label.setTextFill(Color.GREEN);
                    return "2 yıl, 4 ay";
                }
            break;
            case 10:
                if((int)zorluk == 1){
                    label.setTextFill(Color.ORANGE);
                    return "14 gün, 21 saat";
                }
                if((int)zorluk == 2){
                    label.setTextFill(Color.GREEN);
                    return "9 yıl, 6 ay";
                }
                if((int)zorluk == 3){
                    label.setTextFill(Color.GREEN);
                    return "198 yıl, 26 gün";
                }
            default:
                return "";
        }
        return "";
    }
*/

    
    
}
