
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class Hilo implements Runnable{
    
    Socket s;
    boolean listo;
    
    public Hilo(){
        listo = true;
    }
    
    public void nuevaConexion(Socket s){
        this.s = s;
        listo = true;
    }
    
    @Override
    public void run() {
        ObjectOutputStream oos;
        ObjectInputStream ois;
        Clima c = null;
        while(listo){
            listo = false;
            try{
                oos = new ObjectOutputStream(s.getOutputStream());
                ois = new ObjectInputStream(s.getInputStream());
                
                c = (Clima) ois.readObject();
                if (c != null){
                    oos.writeBoolean(true);
                    //Escribir en Base de datos
                    Modelo.bd.insertaRegistro(c);
                    Modelo.c.imprime("Se agregó= " + c.toString());
                    
                }else{ 
                    oos.writeBoolean(false);
                }
                oos.flush();
                s.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        listo = true;
    }
    
}
