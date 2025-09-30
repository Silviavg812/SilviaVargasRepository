package model;

import util.Prioridad;

import java.util.Date;

public class TareaTrabajo extends Tarea{

    // VARIABLES
    public String proyecto;



    // CONSTRUCTORS

    public TareaTrabajo() {}
    public TareaTrabajo(int ID,Prioridad prioridad, Date fecha, String descripcion, String tituloTarea, String proyecto) {
        super(ID,prioridad, fecha, descripcion, tituloTarea);
        this.proyecto = proyecto;
    }





    // METHODWS

    @Override
    public String toString() {
        return "TareaTrabajo{" +
                "proyecto='" + proyecto + '\'' +
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


    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }
}
