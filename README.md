```markdown
# Proyecto de Gestión de Recursos con Hibernate ©

Este proyecto utiliza Hibernate para gestionar entidades relacionadas como departamentos, sedes y empleados. Proporciona métodos para actualizar el nombre de la sede para un departamento específico, el nombre de un empleado por DNI y el nombre de un departamento por su ID.

## Requisitos

- Java 8 o superior
- MySQL
- Maven
- Hibernate

## Configuración

1. Clona el repositorio: `git clone https://github.com/tu-usuario/tu-repositorio.git`
2. Configura la base de datos en `hibernate.cfg.xml` con tu información de conexión.

## Ejemplos de Uso

### Actualizar nombre de la sede para un departamento

```java
Actualizar.updateDepNombreSede(1, "Nueva Sede");
```

### Actualizar nombre de un empleado por DNI

```java
Actualizar.updateNombreEmpleadoPorDNI("12345678A", "Nuevo Nombre");
```

### Actualizar nombre de un departamento por ID

```java
Actualizar.updateNombreDepartamento(1, "Nuevo Departamento");
```

## Contribuciones

Las contribuciones son bienvenidas. Si encuentras algún problema o tienes alguna mejora, abre un problema o envía una solicitud de extracción.

## Licencia

Este proyecto está bajo la Licencia MIT - consulta el archivo [LICENSE.md](LICENSE.md) para más detalles.
```

Código realizado por Rafael©.
