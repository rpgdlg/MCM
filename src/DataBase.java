import java.sql.*;
import javax.swing.JOptionPane;
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
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/mcm";

    private static Connection conn;

    /*PreparedStatement en vez de Statement para evitar SQLInjection,pese a que en esta app eso no es relevante.*/
    private PreparedStatement instruccion = null;
    
    public DataBase() {
        conectar();
        crearTabla();
        desconectar();
        //JOptionPane.showMessageDialog(null,"Conexión exitosa a la base de datos.");
    }
    
    public void conectar() {
        conn = null;
        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (ClassNotFoundException | SQLException e){
        	JOptionPane.showMessageDialog(null,"¡Error al conectar a la base de datos!.Exepción generada: "+e);
        }
    }
    
    public void desconectar() {
        try {
            conn.close();
            conn = null;
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null,"¡Error al desconectar la base de datos!.Exepción generada: "+e);
        }
    }
    
    public void crearTabla() {
    	String creaTabla="CREATE TABLE IF NOT EXISTS REGISTRO(" + ""
                + "Id INT AUTO_INCREMENT PRIMARY KEY, "
                + "IdSensor INT(4) NOT NULL, "
                + "Fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " 
                + "Humedad FLOAT NOT NULL, "
                + "Presion FLOAT NOT NULL, "
                + "Temperatura FLOAT NOT NULL, "
                + "Luminosidad FLOAT NOT NULL)";
        try{
            instruccion = conn.prepareStatement(creaTabla);
            instruccion.executeUpdate();
            instruccion.close();
        }catch (Exception e){
        	JOptionPane.showMessageDialog(null,"¡Error al crear la tabla!.Excepción generada: "+e);
        }
    }
    
    public void insertaRegistro(Clima c) {
    	String insercion = "INSERT INTO REGISTRO VALUES(null,?,null,?,?,?,?)";
        try{
            conectar();
            instruccion = conn.prepareStatement(insercion);
            instruccion.setInt(1, c.getIdSensor());
            instruccion.setFloat(2, c.getHumedad());
            instruccion.setFloat(3, c.getPresion());
            instruccion.setFloat(4, c.getTemperatura());
            instruccion.setFloat(5, c.getLuminosidad());
            instruccion.executeUpdate();
            instruccion.close();
            desconectar();
        }catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "¡Error al insertar!.Excepción generada: " + e);
            e.printStackTrace();
            desconectar();
        }
    }
}