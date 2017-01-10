
import java.awt.*;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class Vista extends JFrame{
    //Configuracion
    private final int x = 400;
    private final int y = 400;
    private final boolean resizable = false;
    //Termina Configuracion
    
    
    private Controlador c;
    JButton boton;
    JTextField txtfield;
    JTextArea txtarea;  
    JScrollPane scroll;
    JLabel estado;
    
    Vista(Controlador c){
        super("Servidor");
        this.c = c;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x, y);
        
        Container contenedor = this.getContentPane();
        contenedor.setLayout(new BorderLayout());
        
        inicializarComponentes();
        contenedor.add(boton, BorderLayout.NORTH);
        contenedor.add(scroll, BorderLayout.CENTER);
        
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(estado, BorderLayout.EAST);
        p.add(txtfield, BorderLayout.CENTER);
        contenedor.add(p, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(resizable);
    }
    
    public void inicializarComponentes(){
        boton = new JButton("Conectar");
        boton.setBackground(Color.RED);
        boton.setActionCommand("BTN");
        boton.addActionListener(c);
        txtfield = new JTextField();
        txtfield.setActionCommand("TXT");
        txtfield.addActionListener(c);
        
        txtarea = new JTextArea();
        txtarea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtarea.setBackground(Color.BLACK);
        txtarea.setForeground(Color.LIGHT_GRAY);
        scroll = new JScrollPane(txtarea);
        txtarea.setEditable(false);
        estado = new JLabel("Desconectado");
    }
}
