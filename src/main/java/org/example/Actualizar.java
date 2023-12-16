package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Actualizar {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(DepartamentoEntity.class);
            configuration.addAnnotatedClass(SedeEntity.class);
            configuration.addAnnotatedClass(EmpleadoEntity.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void updateDepNombreSede(int idDepartamento, String nuevoNombreSede) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            DepartamentoEntity departamento = session.get(DepartamentoEntity.class, idDepartamento);

            if (departamento != null) {
                SedeEntity sede = departamento.getSede();
                if (sede != null) {
                    sede.setNomSede(nuevoNombreSede);

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
            e.printStackTrace(System.err);
        }
    }

    public static void updateNombreEmpleadoPorDNI(String dniEmpleado, String nuevoNombre) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Utilizar una consulta para obtener el empleado por DNI
            EmpleadoEntity empleado = (EmpleadoEntity) session.createQuery("FROM EmpleadoEntity WHERE dni = :dni")
                    .setParameter("dni", dniEmpleado)
                    .uniqueResult();

            if (empleado != null) {
                empleado.setNomEmp(nuevoNombre);
                transaction.commit();
                session.refresh(empleado);

                System.out.println("Nombre del empleado actualizado correctamente.");
            } else {
                System.out.println("No se encontró el empleado con DNI: " + dniEmpleado);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    public static void updateNombreDepartamento(int idDepartamento, String nuevoNombreDepartamento) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            DepartamentoEntity departamento = session.get(DepartamentoEntity.class, idDepartamento);

            if (departamento != null) {
                // Actualizar el nombre del departamento
                departamento.setNomDepto(nuevoNombreDepartamento);

                transaction.commit();

                System.out.println("Nombre del departamento actualizado correctamente.");
                mostrarDetallesDepartamento(departamento);
            } else {
                System.out.println("No se encontró el departamento con ID: " + idDepartamento);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }



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
