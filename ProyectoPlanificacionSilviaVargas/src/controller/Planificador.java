package controller;

import model.*;
import util.Prioridad;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Planificador {
    Scanner scanner = new Scanner(System.in);

    public ArrayList<Elemento> listaTareas;

    public Planificador() {
        listaTareas = new ArrayList<>();
    }

    public void registrarElemento(Elemento elemento) {
        listaTareas.add(elemento);
    }


    public boolean buscarTarea (int id){
        for (Elemento item: listaTareas){
            if (item.getID() == id){
                return true;
            }
        }
        return false;
    }

    public boolean buscarElemento (int id) {
        for (Elemento item: listaTareas){

        }
        return false;
    }

    public void modificarElemento(int id){
        if (buscarTarea(id)){
            System.out.println("Tarea encontrada.");
            for (Elemento item: listaTareas){
                if (item.getID() == id && item instanceof TareaOcio){
                    System.out.println("Que aspecto de la tarea desea modificar?\n" +
                            "(1.Titulo / 2.Prioridad / 3.Fecha / 4.Descripcion / 5.Ubicacio / 6.Presupuesto)");
                    int opcion = scanner.nextInt();
                    switch (opcion){
                        case 1:
                            System.out.println("Introduzca el nuevo titulo: ");
                            ((TareaOcio) item).tituloTarea = scanner.nextLine();
                            break;
                        case 2:
                            System.out.println("Introduzca la nueva prioridad: ");
                            ((TareaOcio) item).prioridad = null;
                            ((TareaOcio) item).prioridad = Prioridad.valueOf(scanner.nextLine().toUpperCase());
                            break;
                        case 3:
                            System.out.println("Introduzca la nueva fecha: ");
                            String nuevaFechaTarea = scanner.nextLine();
                            int dia = 2;
                            int mes = 2;
                            int anio = 2020;
                            dia = Integer.parseInt(nuevaFechaTarea.split("/")[0]);
                            mes = Integer.parseInt(nuevaFechaTarea.split("/")[1]);
                            anio = Integer.parseInt(nuevaFechaTarea.split("/")[2]);
                            ((TareaOcio) item).fecha = new Date(dia,mes,anio);
                            break;
                        case 4:
                            System.out.println("Introduzca la nueva descripcion: ");
                            ((TareaOcio) item).descripcion = scanner.nextLine();
                            break;
                        case 5:
                            System.out.println("Introduzca la nueva ubicacion: ");
                            ((TareaOcio) item).ubicacion = scanner.nextLine();
                            break;
                        case 6:
                            System.out.println("Introduzca el nuevo presupuesto: ");
                            ((TareaOcio) item).presupuesto = scanner.nextDouble();
                            break;
                    }

                } else if (item.getID() == id && item instanceof TareaTrabajo) {
                    System.out.println("Que aspecto de la tarea desea modificar?\n" +
                            "(1.Titulo / 2.Prioridad / 3.Fecha / 4.Descripcion / 5.Proyecto)");
                    int opcion = scanner.nextInt();
                    switch (opcion){
                        case 1:
                            System.out.println("Introduzca el nuevo titulo: ");
                            ((TareaTrabajo) item).tituloTarea = scanner.nextLine();
                            break;
                        case 2:
                            System.out.println("Introduzca la nueva prioridad: ");
                            ((TareaTrabajo) item).prioridad = Prioridad.valueOf(scanner.nextLine().toUpperCase());
                            break;
                        case 3:
                            System.out.println("Introduzca la nueva fecha: ");
                            String nuevaFechaTarea = scanner.nextLine();
                            int dia = 2;
                            int mes = 2;
                            int anio = 2020;
                            dia = Integer.parseInt(nuevaFechaTarea.split("/")[0]);
                            mes = Integer.parseInt(nuevaFechaTarea.split("/")[1]);
                            anio = Integer.parseInt(nuevaFechaTarea.split("/")[2]);
                            ((TareaTrabajo) item).fecha = new Date(dia,mes,anio);
                            break;
                        case 4:
                            System.out.println("Introduzca la nueva descripcion: ");
                            ((TareaTrabajo) item).descripcion = scanner.nextLine();
                            break;
                        case 5:
                            System.out.println("Introduzca el nuevo proyecto: ");
                            ((TareaTrabajo) item).proyecto = scanner.nextLine();
                            break;
                    }
                }
            }
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }
    public void aniadirSubtarea (int id, Tarea subtarea) {
        for (Elemento item : listaTareas) {
            if (item.getID() == id){
                ((Tarea) item).subtareas.add(subtarea);
                System.out.println("Subtarea creada con exito.");
            } else {
                System.out.println("Tarea no encontrada.");
            }
        }
    }
    public int asignarID (){
        if (listaTareas.isEmpty()) {
            return 1;
        }
        return listaTareas.getLast().ID + 1;
    }
    public void mostrarTareas () {
        for(Elemento item: listaTareas){
            System.out.println(item);
        }
    }
    public void completarTarea(int id) {
        boolean tareaEncontrada = false;
        boolean operacionExitosa = false;

        for (Elemento item : listaTareas) {
            if (item instanceof Tarea && item.getID() == id) {
                tareaEncontrada = true;
                Tarea tarea = (Tarea) item;

                if (tarea.subtareas.isEmpty()) {
                    tarea.completada = true;
                    System.out.println("Tarea completada.");
                    operacionExitosa = true;
                } else {
                    System.out.println("No se puede completar: la tarea tiene subtareas pendientes.");
                }
                break;
            }
        }

        if (!tareaEncontrada) {
            System.out.println("No se encontró ninguna tarea con ID: " + id);
        }
    }
    public void listarCompletas (){
        for (Elemento item : listaTareas){
            if (item instanceof Tarea){
                if (((Tarea) item).getCompletada() == true){
                    System.out.println(item);
                } else {
                    System.out.println("No se encontaron tareas completadas.");
                }
            }
        }
    }
    public void listarIncompletas (){
        for (Elemento item : listaTareas){
            if (item instanceof Tarea){

                if (((Tarea) item).completada == false){
                    System.out.println(item);
                } else {
                    System.out.println("No se encontaron tareas incompletas.");
                }
            }
        }
    }





}

