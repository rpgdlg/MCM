
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @version 0.1
 * @author Daniel García Landeros
 */
public class Controlador implements ActionListener{
    
    private Vista v;
    private Modelo m;
    private boolean estado;
    
    public Controlador (){
        v = new Vista(this);
        m = new Modelo(this);
        estado = false;
    }
    
    static public void main(String [] args){
        Controlador c = new Controlador();
    }
    
    public void imprime(String txt){
        v.txtarea.setText("> "+ new Date() + " " + txt + "\n"+ v.txtarea.getText());
        v.txtarea.setCaretPosition(0);
        guardaRegistro(txt);
    }

    private void guardaRegistro(String txt){
        m.log.guardarRegistros("> "+ new Date() + " " + txt + "\n");
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("BTN"))
            try{
                if (estado){
                    v.estado.setText("Desconectado");
                    v.estado.setForeground(Color.RED);
                    v.boton.setText("Conectar");
                    v.boton.setBackground(Color.RED);
                    estado = false;
                    m.desconectar();
                    
                    imprime("Desconectado");
                }else{
                    v.boton.setBackground(Color.GREEN);
                    v.estado.setText("Conectado");
                    v.estado.setForeground(Color.GREEN);
                    v.boton.setText("Desconectar");
                    estado = true;
                    new Thread (m).start();
                    imprime("Conectado");
                }
            }catch(Exception e){
                imprime("Error");
            }
        else{
            switch (v.txtfield.getText()){
                case "/ayuda":
                    imprime("Lista de comandos:\n"
                            + " /ayuda\n"
                            + " /cerrar");
                    break;
                case "/cerrar":
                    if (!estado)
                        System.exit(0);
                    else
                        imprime("Desconecte antes de salir.");
                    break;
                default:
                    imprime("Comando invalido!");
            }
            v.txtfield.setText("");
        }
    }
}
