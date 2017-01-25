
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class Clima implements Serializable{
    
    private int id;
    private int idSensor;
    private String fecha;
    private float humedad;
    private float presion;
    private float temperatura;
    private float luminosidad;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(int idSensor) {
        this.idSensor = idSensor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getHumedad() {
        return humedad;
    }

    public void setHumedad(float humedad) {
        this.humedad = humedad;
    }

    public float getPresion() {
        return presion;
    }

    public void setPresion(float presion) {
        this.presion = presion;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getLuminosidad() {
        return luminosidad;
    }

    public void setLuminosidad(float luminosidad) {
        this.luminosidad = luminosidad;
    }

    @Override
    public String toString() {
        return "Clima{" + "id=" + id + ", idSensor=" + idSensor + ", fecha=" + fecha + ", humedad=" + humedad + ", presion=" + presion + ", temperatura=" + temperatura + ", luminosidad=" + luminosidad + '}';
    }
    
}