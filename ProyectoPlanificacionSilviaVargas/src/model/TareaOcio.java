package model;

import lombok.Getter;
import lombok.Setter;
import util.Prioridad;

import java.util.Date;
@Getter
@Setter

public class TareaOcio extends Tarea{

    // VARIABLES
    public String ubicacion;
    public double presupuesto;


    // CONSTRUCTORS
    public TareaOcio() {}
    public TareaOcio(int ID, Prioridad prioridad, Date fecha, String descripcion, String tituloTarea, String ubicacion, double presupuesto) {
        super(ID, prioridad, fecha, descripcion, tituloTarea);
        this.ubicacion = ubicacion;
        this.presupuesto = presupuesto;
    }


    // METHODS

    @Override
    public String toString() {
        return "TareaOcio{" +
                "ubicacion='" + ubicacion + '\'' +
                ", presupuesto=" + presupuesto +
                ", ID=" + ID +
                ", tituloTarea='" + tituloTarea + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", prioridad=" + prioridad +
                ", completada=" + completada +
                ", subtareas=" + subtareas +
                '}';
    }


    // GETTER AND SETTER


    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }
}
