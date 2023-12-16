package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Leer {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static void readDepartamento(int idDepartamento) {
        try (Session session = sessionFactory.openSession()) {
            String queryStr = "from org.example.DepartamentoEntity where idDepto = :idDep";
            Query<DepartamentoEntity> miQuery = session.createQuery(queryStr);
            miQuery.setParameter("idDep", idDepartamento);
            List<DepartamentoEntity> listaDepartamentos = miQuery.list();
            if (listaDepartamentos.size() == 1) {
                DepartamentoEntity departamento = listaDepartamentos.get(0);
                // Puedes imprimir la información o realizar otras operaciones según tus necesidades
                System.out.println("Departamento ID: " + departamento.getIdDepto());
                System.out.println("Nombre del Departamento: " + departamento.getNomDepto());
                System.out.println("ID de la Sede: " + departamento.getIdSede());

                // Obtén la entidad de sede desde departamento
                SedeEntity sede = departamento.getSede();

                // Imprime el nombre de la sede
                System.out.println("Nombre de la sede: " + sede.getNomSede());

                // Lee e imprime la información de los empleados asociados al departamento
                System.out.println("Empleados del Departamento:");
                for (EmpleadoEntity empleado : departamento.getEmpleadosByIdDepto()) {
                    System.out.println("DNI: " + empleado.getDni());
                    System.out.println("Nombre: " + empleado.getNomEmp());
                    // Puedes imprimir más información según sea necesario
                }
            } else {
                System.out.println("No se encontró el departamento con ID: " + idDepartamento);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static void readEmpleado(String dniEmpleado) {
        try (Session session = sessionFactory.openSession()) {
            String queryStr = "from org.example.EmpleadoEntity where dni = :dni";
            Query<EmpleadoEntity> miQuery = session.createQuery(queryStr);
            miQuery.setParameter("dni", dniEmpleado);
            List<EmpleadoEntity> listaEmpleados = miQuery.list();
            if (listaEmpleados.size() == 1) {
                EmpleadoEntity empleado = listaEmpleados.get(0);
                // Puedes imprimir la información o realizar otras operaciones según tus necesidades
                System.out.println("DNI del Empleado: " + empleado.getDni());
                System.out.println("Nombre del Empleado: " + empleado.getNomEmp());
                System.out.println("ID del Departamento: " + empleado.getIdDepto());

                // Obtén la entidad de departamento desde empleado
                DepartamentoEntity departamento = empleado.getListaDepartamentos();

                // Imprime el nombre del departamento
                System.out.println("Nombre del Departamento: " + departamento.getNomDepto());
            } else {
                System.out.println("No se encontró el empleado con DNI: " + dniEmpleado);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
