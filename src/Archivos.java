
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
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
public class Archivos {
    
    private FileWriter archivo;
    private String nombreArchivo;
    private Calendar hoy;
    private final String FOLDER;
    private final String EXT = ".log";
    
    public Archivos(){
        hoy = Calendar.getInstance();
        String dia, mes, año;
        año = String.valueOf(hoy.get(Calendar.YEAR));
        mes = String.valueOf(hoy.get(Calendar.MONTH));
        dia = String.valueOf(hoy.get(Calendar.DAY_OF_MONTH));
        mes = String.valueOf(Integer.valueOf(mes)+1);
        if(Integer.valueOf(mes) < 10)
            mes = '0' + mes; 
        
        FOLDER = getProgramPath() + "\\log\\";
        File folder = new File(FOLDER);
        folder.mkdir();
        System.out.println("Directorio de registro: " + FOLDER);
    }
    
    private String getProgramPath() {
        URL url = Archivos.class.getProtectionDomain().getCodeSource().getLocation();
        try{
            String jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
            String parentPath = new File(jarPath).getParentFile().getPath();
            return parentPath;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "C:/log/.", "Error", 0);
            return "/log";
        }
   }
    
    private void abrirArchivo(){
        checarFecha();
        try{
            if (new File(FOLDER + nombreArchivo + EXT).exists()==false){
                archivo = new FileWriter(new File(FOLDER + nombreArchivo + EXT),false);
            }else{
                archivo = new FileWriter(FOLDER + nombreArchivo + EXT, true);
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "No se pudo crear registro.", "Error", 0);
        }
    }
    
    private void cerrarArchivo(){
        try{
            archivo.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error al cerrar el archivo.", "Error", 0);
        }
    }
    
    public void guardarRegistros(String s){
        abrirArchivo();
        try{
            archivo.write(s);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error de escritura", "Error", 0);
        }
        cerrarArchivo();
    }
    
    private void checarFecha(){
        Calendar ahora = Calendar.getInstance();
        if(hoy.get(Calendar.DAY_OF_MONTH) != ahora.get(Calendar.DAY_OF_MONTH)){
            hoy = Calendar.getInstance();
            nombreArchivo = String.valueOf(hoy.get(Calendar.YEAR)) 
                + String.valueOf(hoy.get(Calendar.MONTH))
                + String.valueOf(hoy.get(Calendar.DAY_OF_MONTH));
        }
    }
    
}
