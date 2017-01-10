
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class Modelo implements Runnable{
    
    //Configuracion
    private final int puerto = 1234;
    private final int POOL = 4;
    //Termina Configuracion
    
    static Controlador c;
    
    Archivos log;
    static DataBase bd;
    ServerSocket servidor;
    Socket cliente;
    public static Hilo[] hilos;
    boolean estado;
    
    public Modelo(Controlador c){
        this.c = c;
        bd = new DataBase();
        estado = false;
        log = new Archivos();
        hilos = new Hilo[POOL];
        for(int i=0; i<POOL; i++)
            hilos[i] = new Hilo();
    }
    
    public void conectar() throws Exception{
        servidor = new ServerSocket(puerto);
        ThreadPoolExecutor pool = (ThreadPoolExecutor)Executors.newFixedThreadPool(POOL);
        estado = true;
        try{
            while(estado){
                cliente = servidor.accept();
                for(int i = 0; i < POOL; i++){
                    if(hilos[i].listo){
                        hilos[i].nuevaConexion(cliente);
                        pool.execute(hilos[i]);
                        i = POOL;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        servidor.close();
    }
    
    public void desconectar() throws Exception{
        
        //Se necesita agregar algoritmo para garantizar la coherencia de los datos
        estado = false;
    }

    @Override
    public void run(){
        try {
            conectar();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
}
