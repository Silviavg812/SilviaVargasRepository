package model;
import util.Prioridad;

import java.util.ArrayList;
import java.util.Date;

abstract public class Tarea implements Elemento {

    // VARIABLES]

    // Una tarea podrá tener subtareas de cualquier tipo. Esto se puede conseguir mediante arraylist.
    public int ID;
    public String tituloTarea;
    public String descripcion;
    public Date fecha;
    public Prioridad prioridad;
    public Boolean completada;
    public ArrayList<Tarea> subtareas;

    // CONSTRUCTORS
    public Tarea() {}
    public Tarea(int ID, Prioridad prioridad, Date fecha, String descripcion, String tituloTarea) {
        this.ID = ID;
        this.completada = false;
        this.prioridad = prioridad;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tituloTarea = tituloTarea;
        this.subtareas = new ArrayList<>();
    }

    // METHODS




    @Override
    public String toString() {
        return "Tarea{" +
                "ID=" + ID +
                ", tituloTarea='" + tituloTarea + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", prioridad=" + prioridad +
                ", completada=" + completada +
                ", subtareas=" + subtareas +
                '}';
    }


    // GETTER AND SETTER


    public int getID() {
        return ID;
    }
    public String getTituloTarea() {
        return tituloTarea;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public Date getFecha() {
        return fecha;
    }
    public Prioridad getPrioridad() {
        return prioridad;
    }
    public Boolean getCompletada() {
        return completada;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTituloTarea(String tituloTarea) {
        this.tituloTarea = tituloTarea;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public void setCompletada(Boolean completada) {
        this.completada = completada;
    }
}

