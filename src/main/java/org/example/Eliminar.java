    package org.example;

    import org.hibernate.Session;
    import org.hibernate.SessionFactory;
    import org.hibernate.Transaction;
    import org.hibernate.cfg.Configuration;
    import org.hibernate.query.Query;

    import java.util.List;
    import java.util.Scanner;

    public class Eliminar {
        private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        private static Scanner scanner = new Scanner(System.in);

        public static void deleteDep(SessionFactory sessionFactory, String nomDepartamento) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();

                String query = "from org.example.DepartamentoEntity where nomDepto = :nomDep";
                Query<DepartamentoEntity> miQuery = session.createQuery(query);
                miQuery.setParameter("nomDep", nomDepartamento);
                List<DepartamentoEntity> listaDepartamentos = miQuery.list();

                if (listaDepartamentos.size() == 1) {
                    DepartamentoEntity departamento = listaDepartamentos.get(0);
                    mostrarDetallesDepartamento(departamento);

                    System.out.println("¿Estás seguro de que deseas eliminar este departamento? (S/N)");
                    String confirmacion = scanner.nextLine();

                    if (confirmacion.equalsIgnoreCase("S")) {
                        // Elimina los empleados asociados al departamento
                        for (EmpleadoEntity empleado : departamento.getEmpleadosByIdDepto()) {
                            session.delete(empleado);
                        }

                        // Elimina el departamento
                        session.delete(departamento);

                        System.out.println("Departamento y empleados eliminados correctamente.");
                    } else {
                        System.out.println("Eliminación cancelada.");
                    }
                } else {
                    System.out.println("No se encontró el departamento con nombre: " + nomDepartamento);
                }

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace(System.err);
            }
        }

        public static void deleteSede(SessionFactory sessionFactory, String nomSede) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();

                String querySede = "from org.example.SedeEntity where nomSede = :nomSede";
                Query<SedeEntity> miQuerySede = session.createQuery(querySede);
                miQuerySede.setParameter("nomSede", nomSede);
                List<SedeEntity> listaSedes = miQuerySede.list();

                if (listaSedes.size() == 1) {
                    SedeEntity sede = listaSedes.get(0);
                    mostrarDetallesSede(sede);

                    // Verifica si hay departamentos que referencian a esta sede
                    String queryDepartamentos = "from org.example.DepartamentoEntity where sede = :sede";
                    Query<DepartamentoEntity> miQueryDepartamentos = session.createQuery(queryDepartamentos);
                    miQueryDepartamentos.setParameter("sede", sede);
                    List<DepartamentoEntity> departamentosReferenciados = miQueryDepartamentos.list();

                    if (departamentosReferenciados.isEmpty()) {
                        System.out.println("¿Estás seguro de que deseas eliminar esta sede? (S/N)");
                        String confirmacion = scanner.nextLine();

                        if (confirmacion.equalsIgnoreCase("S")) {
                            // Elimina la sede
                            session.delete(sede);

                            System.out.println("Sede eliminada correctamente.");
                        } else {
                            System.out.println("Eliminación cancelada.");
                        }
                    } else {
                        System.out.println("Existen departamentos que hacen referencia a esta sede. Elimina esos departamentos primero.");
                    }
                } else {
                    System.out.println("No se encontró la sede con nombre: " + nomSede);
                }

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace(System.err);
            }
        }



        public static void deleteEmpleado(SessionFactory sessionFactory, String nomEmpleado) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                String query = "from org.example.EmpleadoEntity where nomEmp = :nomEmp";
                Query<EmpleadoEntity> miQuery = session.createQuery(query);
                miQuery.setParameter("nomEmp", nomEmpleado);
                List<EmpleadoEntity> listaEmpleados = miQuery.list();

                if (listaEmpleados.size() == 1) {
                    EmpleadoEntity empleado = listaEmpleados.get(0);
                    mostrarDetallesEmpleado(empleado);

                    System.out.println("¿Estás seguro de que deseas eliminar este empleado? (S/N)");
                    String confirmacion = scanner.nextLine();

                    if (confirmacion.equalsIgnoreCase("S")) {
                        transaction = session.beginTransaction();
                        session.delete(empleado);
                        transaction.commit();
                        System.out.println("Empleado eliminado correctamente.");
                    } else {
                        System.out.println("Eliminación cancelada.");
                    }
                } else {
                    System.out.println("No se encontró el empleado con nombre: " + nomEmpleado);
                }
            } catch (Exception e) {
                e.printStackTrace(System.err);
                if (transaction != null)
                    transaction.rollback();
            }
        }





        /**
         * Muestra los detalles de una sede.
         *
         * @param sede SedeEntity cuyos detalles se mostrarán
         */
        private static void mostrarDetallesSede(SedeEntity sede) {
            System.out.println("Detalles de la Sede:");
            System.out.println("ID de la Sede: " + sede.getIdSede());
            System.out.println("Nombre de la Sede: " + sede.getNomSede());
        }

        /**
         * Muestra los detalles de un departamento.
         *
         * @param departamento DepartamentoEntity cuyos detalles se mostrarán
         */
        private static void mostrarDetallesDepartamento(DepartamentoEntity departamento) {
            System.out.println("Detalles del Departamento:");
            System.out.println("ID del departamento: " + departamento.getIdDepto());
            System.out.println("Nombre del departamento: " + departamento.getNomDepto());
        }
        /**
         * Muestra los detalles de un empleado.
         *
         * @param empleado EmpleadoEntity cuyos detalles se mostrarán
         */
        private static void mostrarDetallesEmpleado(EmpleadoEntity empleado) {
            System.out.println("Detalles del Empleado:");
            System.out.println("Nombre del empleado: " + empleado.getNomEmp());
            System.out.println("DNI del empleado: " + empleado.getDni());
            System.out.println("ID del departamento: " + empleado.getIdDepto());
            // Agrega más detalles según la estructura de tu entidad EmpleadoEntity
        }

    }
