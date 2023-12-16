package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.Scanner;

import java.util.List;
import java.util.Scanner;

public class Ejercicio3_3 {
    @SuppressWarnings("unused")
    private static Scanner scanner = Scannner.getScanner();

    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();





    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Que quieres hacer?");
        System.out.println("1. Insertar");
        System.out.println("2. Borrar");
        System.out.println("3. Actualizar");
        System.out.println("4. Leer");
        String numero = scanner.nextLine();

        switch (numero) {
            case "1":
                Insertar.insert();

                break;
            case "2":
                System.out.println("Ingrese el nombre del departamento a eliminar:");
                String nomDepartamentoEliminar = scanner.nextLine();
                Eliminar.deleteDep(sessionFactory, nomDepartamentoEliminar);

                System.out.println("Ingrese el nombre de la sede a eliminar:");
                String nomSedeEliminar = scanner.nextLine();
                Eliminar.deleteSede(sessionFactory, nomSedeEliminar);

                System.out.println("Ingrese el nombre del empleado a eliminar:");
                String nomEmpleadoEliminar = scanner.nextLine();
                Eliminar.deleteEmpleado(sessionFactory, nomEmpleadoEliminar);
                break;





            case "3":
                System.out.println("Seleccione la entidad a actualizar:\n1. Sede\n2. Empleado\n3. Departamento");
                String opcionEntidad = scanner.nextLine();

                switch (opcionEntidad) {
                    case "1":
                        System.out.println("Ingrese el ID del departamento a actualizar:");
                        int idDepartamentoSede = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea después de nextInt
                        System.out.println("Ingrese el nuevo nombre de la sede:");
                        String nuevoNombreSede = scanner.nextLine();
                        Actualizar.updateDepNombreSede(idDepartamentoSede, nuevoNombreSede);
                        break;

                    case "2":
                        System.out.println("Ingrese el DNI del empleado a actualizar:");
                        String dniEmpleado = scanner.nextLine();
                        System.out.println("Ingrese el nuevo nombre del empleado:");
                        String nuevoNombreEmpleado = scanner.nextLine();
                        Actualizar.updateNombreEmpleadoPorDNI(dniEmpleado, nuevoNombreEmpleado);
                        break;



                    case "3":
                        System.out.println("Ingrese el ID del departamento a actualizar:");
                        int idDepartamento = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea
                        System.out.println("Ingrese el nuevo nombre del departamento:");
                        String nuevoNombreDepartamento = scanner.nextLine();
                        Actualizar.updateNombreDepartamento(idDepartamento, nuevoNombreDepartamento);
                        break;


                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
                break;


            case "4":
                System.out.println("Ingrese el ID del departamento a leer:");
                int idDepartamentoLeer = scanner.nextInt();
                Leer.readDepartamento(idDepartamentoLeer);
                break;
            default:
                System.out.println("Número no válido");
        }

    }
}
