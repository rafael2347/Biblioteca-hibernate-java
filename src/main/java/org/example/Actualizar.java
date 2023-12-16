package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Clase que proporciona métodos para actualizar entidades relacionadas con departamentos, sedes y empleados.
 */
public class Actualizar {

    // Configuración de la fábrica de sesiones de Hibernate.
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Configuración de Hibernate desde hibernate.cfg.xml
            Configuration configuration = new Configuration().configure();
            // Registrar las clases de entidad en la configuración
            configuration.addAnnotatedClass(DepartamentoEntity.class);
            configuration.addAnnotatedClass(SedeEntity.class);
            configuration.addAnnotatedClass(EmpleadoEntity.class);
            // Construir la fábrica de sesiones
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            // Manejo de excepciones en caso de error en la inicialización
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Actualiza el nombre de la sede para un departamento específico.
     *
     * @param idDepartamento ID del departamento que se actualizará.
     * @param nuevoNombreSede Nuevo nombre de la sede para el departamento.
     */
    public static void updateDepNombreSede(int idDepartamento, String nuevoNombreSede) {
        try (Session session = sessionFactory.openSession()) {
            // Iniciar transacción
            Transaction transaction = session.beginTransaction();

            DepartamentoEntity departamento = session.get(DepartamentoEntity.class, idDepartamento);

            if (departamento != null) {
                SedeEntity sede = departamento.getSede();
                if (sede != null) {
                    sede.setNomSede(nuevoNombreSede);

                    // Finalizar la transacción
                    transaction.commit();
                    session.refresh(departamento);

                    System.out.println("Nombre de la sede actualizado correctamente.");
                    mostrarDetallesDepartamento(departamento);
                } else {
                    System.out.println("El departamento no tiene una sede asociada.");
                }
            } else {
                System.out.println("No se encontró el departamento con ID: " + idDepartamento);
            }
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace(System.err);
        }
    }

    /**
     * Actualiza el nombre de un empleado específico por su DNI.
     *
     * @param dniEmpleado    DNI del empleado que se actualizará.
     * @param nuevoNombre    Nuevo nombre para el empleado.
     */
    public static void updateNombreEmpleadoPorDNI(String dniEmpleado, String nuevoNombre) {
        try (Session session = sessionFactory.openSession()) {
            // Iniciar transacción
            Transaction transaction = session.beginTransaction();

            // Utilizar una consulta para obtener el empleado por DNI
            EmpleadoEntity empleado = (EmpleadoEntity) session.createQuery("FROM EmpleadoEntity WHERE dni = :dni")
                    .setParameter("dni", dniEmpleado)
                    .uniqueResult();

            if (empleado != null) {
                empleado.setNomEmp(nuevoNombre);

                // Finalizar la transacción
                transaction.commit();
                session.refresh(empleado);

                System.out.println("Nombre del empleado actualizado correctamente.");
            } else {
                System.out.println("No se encontró el empleado con DNI: " + dniEmpleado);
            }
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace(System.err);
        }
    }

    /**
     * Actualiza el nombre de un departamento específico por su ID.
     *
     * @param idDepartamento          ID del departamento que se actualizará.
     * @param nuevoNombreDepartamento Nuevo nombre para el departamento.
     */
    public static void updateNombreDepartamento(int idDepartamento, String nuevoNombreDepartamento) {
        try (Session session = sessionFactory.openSession()) {
            // Iniciar transacción
            Transaction transaction = session.beginTransaction();

            DepartamentoEntity departamento = session.get(DepartamentoEntity.class, idDepartamento);

            if (departamento != null) {
                // Actualizar el nombre del departamento
                departamento.setNomDepto(nuevoNombreDepartamento);

                // Finalizar la transacción
                transaction.commit();

                System.out.println("Nombre del departamento actualizado correctamente.");
                mostrarDetallesDepartamento(departamento);
            } else {
                System.out.println("No se encontró el departamento con ID: " + idDepartamento);
            }
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace(System.err);
        }
    }

    /**
     * Muestra los detalles de un departamento.
     *
     * @param departamento DepartamentoEntity cuyos detalles se mostrarán.
     */
    private static void mostrarDetallesDepartamento(DepartamentoEntity departamento) {
        if (departamento != null) {
            System.out.println("Detalles del Departamento:");
            System.out.println("ID del departamento: " + departamento.getIdDepto());
            System.out.println("Nombre del departamento: " + departamento.getNomDepto());

            SedeEntity sede = departamento.getSede();
            if (sede != null) {
                System.out.println("ID de la sede asociada: " + sede.getIdSede());
                System.out.println("Nombre de la sede asociada: " + sede.getNomSede());
            } else {
                System.out.println("El departamento no tiene una sede asociada actualmente.");
            }
        } else {
            System.out.println("Departamento no encontrado.");
        }
    }

    /**
     * Muestra los detalles de un empleado.
     *
     * @param empleado EmpleadoEntity cuyos detalles se mostrarán.
     */
    private static void mostrarDetallesEmpleado(EmpleadoEntity empleado) {
        if (empleado != null) {
            System.out.println("Detalles del Empleado:");
            System.out.println("DNI del empleado: " + empleado.getDni());
            System.out.println("Nombre del empleado: " + empleado.getNomEmp());
            System.out.println("ID del departamento asociado: " + empleado.getIdDepto());
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }
}
