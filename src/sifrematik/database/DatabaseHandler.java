
package sifrematik.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sifrematik.ui.hesaplarim.HesaplarimController.Hesap;


public class DatabaseHandler {
    private static DatabaseHandler handler;
    
    private static final String DB_URL = "jdbc:derby:database;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;
    
    private DatabaseHandler(){
        createConnection();
        setupHesapTable();
    }  
    
    public static DatabaseHandler getInstance(){
        if(handler == null)
            handler = new DatabaseHandler();
        return handler;
    }
    
    void createConnection(){
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    void setupHesapTable(){
        String TABLE_NAME = "HESAP";
        try{
            stmt = conn.createStatement();
            
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null,null, TABLE_NAME.toUpperCase(),null);
            
            if(tables.next()){
                System.out.println(TABLE_NAME + " tablosu zaten mevcut, hazır.");
            }else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "(" 
                +  "site varchar(200),\n"
                +  "id varchar(200),\n"
                +  "sifre varchar(200)"
                +  ")" );             
            }
        }catch(SQLException e){
            System.err.println(e.getMessage() + " ... setupDatabase");
        }
    }
    
    public boolean execAction(String qu){
        try{
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return false;
        }finally{
        }
    }
    
    public ResultSet execQuery(String query){
        ResultSet result;
        try{
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        }catch(SQLException ex){
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }finally{  
        }
        return result;       
    }
    
    public boolean hesapSil(Hesap hesap) {
        
        try {
            String deleteStatement = "DELETE FROM HESAP WHERE id = ? AND site = ? AND sifre = ?";
            PreparedStatement stmt = conn.prepareStatement(deleteStatement);
            stmt.setString(1, hesap.getId());
            stmt.setString(2, hesap.getSite());
            stmt.setString(3, hesap.getSifre());
            int res = stmt.executeUpdate();
            System.out.println(res);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
