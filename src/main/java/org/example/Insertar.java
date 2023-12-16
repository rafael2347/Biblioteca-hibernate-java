package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

/**
 * Clase que proporciona métodos para insertar nuevos registros en la base de datos.
 */
public class Insertar {
    // Configuración de la fábrica de sesiones de Hibernate.
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    // Instancia de la clase Scanner para la entrada del usuario.
    private static Scanner scanner = Scannner.getScanner();

    /**
     * Inserta una nueva sede y un nuevo departamento asociado a esa sede en la base de datos.
     */
    public static void insert() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // Comienza la transacción para realizar cambios en la base de datos.
            transaction = session.beginTransaction();

            // Crea una nueva instancia de SedeEntity.
            SedeEntity sede = new SedeEntity();

            // Obtiene el nombre de la sede desde la entrada del usuario.
            String nomSede = Scannner.getStringInput("Introduce el nombre de la sede");

            // Establece el nombre de la sede en la entidad.
            sede.setNomSede(nomSede);

            // Guarda la sede en la base de datos y obtiene el ID asignado.
            int idSede = (int) session.save(sede);

            // Crea una nueva instancia de DepartamentoEntity.
            DepartamentoEntity departamento = new DepartamentoEntity();

            // Obtiene el nombre del departamento desde la entrada del usuario.
            String nomDepartamento = Scannner.getStringInput("Introduce el nombre del departamento");

            // Establece el nombre del departamento en la entidad.
            departamento.setNomDepto(nomDepartamento);

            // Establece el ID de la sede en el departamento.
            departamento.setIdSede(idSede);

            // Establece la relación con la sede.
            departamento.setSede(sede);

            // Guarda el departamento en la base de datos.
            session.save(departamento);

            // Crea una nueva instancia de EmpleadoEntity.
            EmpleadoEntity empleado = new EmpleadoEntity();

            // Obtiene el DNI del empleado desde la entrada del usuario.
            String dni = Scannner.getStringInput("Introduce el DNI del empleado");

            // Obtiene el nombre del empleado desde la entrada del usuario.
            String nomEmpleado = Scannner.getStringInput("Introduce el nombre del empleado");

            // Establece el DNI, nombre y el ID del departamento al que pertenece el empleado en la entidad.
            empleado.setDni(dni);
            empleado.setNomEmp(nomEmpleado);
            empleado.setIdDepto(departamento.getIdDepto());

            // Establece la relación con el departamento.
            empleado.setListaDepartamentos(departamento);

            // Guarda el empleado en la base de datos.
            session.save(empleado);

            // Finaliza la transacción.
            transaction.commit();
        } catch (Exception e) {
            // Maneja las excepciones e imprime detalles del error.
            e.printStackTrace(System.err);

            // Si hay una transacción en progreso, realiza un rollback.
            if (transaction != null)
                transaction.rollback();
        }
    }

}
