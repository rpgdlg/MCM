import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class DataBase {
    
    private static Connection conn;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/mcm";
    
    public DataBase(){
        conectar();
        crearTabla();
        desconectar();
        System.out.println("Base de Datos O.K.");
    }
    
    public void conectar(){
        conn = null;
        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (ClassNotFoundException | SQLException e){
            System.out.println("error al conectar" + e);
        }
    }
    
    public void desconectar(){
        try{
            conn.close();
            conn = null;
        }catch(Exception e){
            System.out.println("error " + e);
        }
    }
    
    public void crearTabla(){
        try{
        Statement st = conn.createStatement();
        st.executeUpdate("CREATE TABLE IF NOT EXISTS registros(" + ""
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "idSensor INT(4) NOT NULL, "
                + "fecha VARCHAR(16) NOT NULL, " 
                + "humedad FLOAT NOT NULL, "
                + "presion FLOAT NOT NULL, "
                + "temperatura FLOAT NOT NULL, "
                + "luminosidad FLOAT NOT NULL)");
        }catch (Exception e){
            System.out.println("error " + e);
        }
    }
    
    public void insertaRegistro(Clima c){
        
    }
    
}
