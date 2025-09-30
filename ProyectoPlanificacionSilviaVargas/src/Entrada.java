import controller.Planificador;
import model.Lista;
import model.TareaOcio;
import model.TareaTrabajo;
import util.Prioridad;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Entrada {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Planificador planificador = new Planificador();
        int opcion = 0;

        do {
            System.out.println("==== Bienvenido a tu planificador ====");
            System.out.println("1. Registrar una tarea\n" +
                    "2. Modificar una tarea\n" +
                    "3. Listar tareas\n" +
                    "4. Completar tarea\n" +
                    "5. Listar completadas\n" +
                    "6. Listar incompletas.\n" +
                    "7. Salir\n" +
                    "Elige una opcion:");
            opcion = scanner.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("Que tipo de tarea quiere registrar? (1.Ocio / 2.Trabajo / 3.Lista)");
                    int tipoTarea = scanner.nextInt();

                    if (tipoTarea == 1){
                        System.out.println("Tarea de tipo OCIO seleccionada.");
                        System.out.println("Esta tarea depende de otar tarea? (1.SI / 2.NO)");
                        int dependenciaTarea = scanner.nextInt();

                        if (dependenciaTarea == 1){
                            System.out.println("Introduce el ID de la tarea principal: ");
                            int IDTareaPrincipal = scanner.nextInt();

                            int IDSubareaOcio = planificador.asignarID();
                            System.out.printf("Subarea de tipo OCIO de la tarea principal con el ID %d seleccionada con ID %d\n",IDTareaPrincipal,IDSubareaOcio);
                            scanner.nextLine();

                            System.out.println("Elige el titulo de la tarea: ");
                            String tituloSubtareaOcio = scanner.nextLine();

                            System.out.println("Introduce la descripcion de la tarea: ");
                            String descripcionSubareaOcio = scanner.nextLine();

                            System.out.println("Introduce la fecha de la tarea: (dd/MM/yyyy)");
                            String fechaSubareaOcio = scanner.nextLine();
                            int dia = 2;
                            int mes = 2;
                            int anio = 2020;
                            dia = Integer.parseInt(fechaSubareaOcio.split("/")[0]);
                            mes = Integer.parseInt(fechaSubareaOcio.split("/")[1]);
                            anio = Integer.parseInt(fechaSubareaOcio.split("/")[2]);
                            Date fecha = new Date(dia,mes,anio);

                            System.out.println("Introduce la prioridad de la tarea: (BAJA,MEDIA,ALTA) ");
                            Prioridad prioridadSubareaOcio = Prioridad.valueOf(scanner.nextLine().toUpperCase());

                            System.out.println("Introduce la ubicacion de la tarea de ocio: ");
                            String ubicacion = scanner.nextLine();

                            System.out.println("Introduce el presupuesto de la tarea de ocio: ");
                            double presupuesto = scanner.nextDouble();

                            planificador.aniadirSubtarea(IDTareaPrincipal,new TareaOcio(IDSubareaOcio,prioridadSubareaOcio, fecha,descripcionSubareaOcio,tituloSubtareaOcio,ubicacion,presupuesto));

                            break;
                        } else if (dependenciaTarea == 2) {

                            int IDTareaOcio = planificador.asignarID();
                            System.out.printf("Tarea de tipo TRABAJO seleccionada con ID %d\n",IDTareaOcio);
                            scanner.nextLine();

                            System.out.println("Elige el titulo de la tarea: ");
                            String tituloTareaOcio = scanner.nextLine();

                            System.out.println("Introduce la descripcion de la tarea: ");
                            String descripcionTareaOcio = scanner.nextLine();

                            System.out.println("Introduce la fecha de la tarea: (dd/MM/yyyy)");
                            String fechaTareaOcio = scanner.nextLine();
                            int dia = 2;
                            int mes = 2;
                            int anio = 2020;
                            dia = Integer.parseInt(fechaTareaOcio.split("/")[0]);
                            mes = Integer.parseInt(fechaTareaOcio.split("/")[1]);
                            anio = Integer.parseInt(fechaTareaOcio.split("/")[2]);
                            Date fecha = new Date(dia,mes,anio);

                            System.out.println("Introduce la prioridad de la tarea: (BAJA,MEDIA,ALTA) ");
                            Prioridad prioridadTareaOcio = Prioridad.valueOf(scanner.nextLine().toUpperCase());

                            System.out.println("Introduce la ubicacion de la tarea de ocio: ");
                            String ubicacion = scanner.nextLine();

                            System.out.println("Introduce el presupuesto de la tarea de ocio: ");
                            double presupuesto = scanner.nextDouble();

                            planificador.registrarElemento(new TareaOcio(IDTareaOcio,prioridadTareaOcio, fecha,descripcionTareaOcio,tituloTareaOcio,ubicacion,presupuesto));
                            break;
                        } else {
                            System.out.println("Eleccion no valida");
                        }
                    } else if (tipoTarea == 2) {
                        System.out.println("Tarea de tipo TRABAJO seleccionada.");
                        System.out.println("Esta tarea depende de otar tarea? (1.SI / 2.NO)");
                        int dependenciaTarea = scanner.nextInt();

                        if (dependenciaTarea == 1){
                            System.out.println("Introduce el ID de la tarea principal: ");
                            int IDTareaPrincipal = scanner.nextInt();

                            int IDSubareaTrabajo = planificador.asignarID();
                            System.out.printf("Subarea de tipo TRABAJO de la tarea principal con el ID %d seleccionada con ID %d\n",IDTareaPrincipal,IDSubareaTrabajo);
                            scanner.nextLine();

                            System.out.println("Elige el titulo de la tarea: ");
                            String tituloSubtareaTrabajo = scanner.nextLine();

                            System.out.println("Introduce la descripcion de la tarea: ");
                            String descripcionSubareaTrabajo = scanner.nextLine();

                            System.out.println("Introduce la fecha de la tarea: (dd/MM/yyyy)");
                            String fechaSubareaTrabajo = scanner.nextLine();
                            int dia = 2;
                            int mes = 2;
                            int anio = 2020;
                            dia = Integer.parseInt(fechaSubareaTrabajo.split("/")[0]);
                            mes = Integer.parseInt(fechaSubareaTrabajo.split("/")[1]);
                            anio = Integer.parseInt(fechaSubareaTrabajo.split("/")[2]);
                            Date fecha = new Date(dia,mes,anio);

                            System.out.println("Introduce la prioridad de la tarea: (BAJA,MEDIA,ALTA) ");
                            Prioridad prioridadSubareaTrabajo = Prioridad.valueOf(scanner.nextLine().toUpperCase());

                            System.out.println("Introduce el proyecto de la tarea de rabajo: ");
                            String proyecto = scanner.nextLine();

                            planificador.aniadirSubtarea(IDTareaPrincipal,new TareaTrabajo(IDSubareaTrabajo,prioridadSubareaTrabajo, fecha,descripcionSubareaTrabajo,tituloSubtareaTrabajo,proyecto));

                            break;

                        } else if (dependenciaTarea == 2) {
                            int IDTareaTrabajo = planificador.asignarID();
                            System.out.printf("Tarea de tipo TRABAJO seleccionada con ID %d\n",IDTareaTrabajo);
                            scanner.nextLine();
                            System.out.println("Elige el titulo de la tarea: ");
                            String tituloTareaTrabajo = scanner.nextLine();
                            System.out.println("Introduce la descripcion de la tarea: ");
                            String descripcionTareaTrabajo = scanner.nextLine();
                            System.out.println("Introduce la fecha de la tarea: (dd/MM/yyyy)");
                            String fechaTareaTrabajo = scanner.nextLine();
                            int dia = 2;
                            int mes = 2;
                            int anio = 2020;
                            dia = Integer.parseInt(fechaTareaTrabajo.split("/")[0]);
                            mes = Integer.parseInt(fechaTareaTrabajo.split("/")[1]);
                            anio = Integer.parseInt(fechaTareaTrabajo.split("/")[2]);
                            Date fecha = new Date(dia,mes,anio);
                            System.out.println("Introduce la prioridad de la tarea: (BAJA,MEDIA,ALTA) ");
                            Prioridad prioridadTareaTrabajo = Prioridad.valueOf(scanner.nextLine().toUpperCase());
                            System.out.println("Introduce el proyecto de la tarea de trabajo: ");
                            String proyecto = scanner.nextLine();
                            planificador.registrarElemento(new TareaTrabajo(IDTareaTrabajo,prioridadTareaTrabajo, fecha,descripcionTareaTrabajo,tituloTareaTrabajo,proyecto));
                            break;
                        } else {
                            System.out.println("Eleccion no valida");
                        }
                    } else if (tipoTarea == 3){
                        int IDLista = planificador.asignarID();
                        System.out.printf("Lista seleccionada con ID %d\n",IDLista);
                        String decision = scanner.nextLine();

                        ArrayList<String> listaStrings= new ArrayList<>();

                        do {

                            System.out.println("Añade un elemento:");
                            String elemento = scanner.nextLine();
                            System.out.println("Desea añadir otro elemento? (si/no)");

                            listaStrings.add(elemento);
                            decision = scanner.nextLine().toLowerCase();

                        } while (decision.equalsIgnoreCase("si"));
                        Lista lista = new Lista(IDLista,listaStrings);
                        planificador.registrarElemento(lista);


                    } else {
                        System.out.println("Opcion no valida.");
                    }
                    break;

                case 2:
                    System.out.println("Introduce el ID de la tarea a modificar: ");
                    int IDTarea = scanner.nextInt();
                    planificador.modificarElemento(IDTarea);
                    break;
                case 3:
                    planificador.mostrarTareas();
                    break;
                case 4:
                    System.out.println("Introduce el ID de la tarea que quiere completar: ");
                    int IDCompletada = scanner.nextInt();
                    planificador.completarTarea(IDCompletada);
                    break;
                case 5:
                    planificador.listarCompletas();
                    break;
                case 6:
                    planificador.listarIncompletas();
                    break;
            }
        } while (opcion!= 7);




    }
}
