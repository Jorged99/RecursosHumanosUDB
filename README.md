# RecursosHumanosUDB
La Universidad Don Bosco, solicita a usted y su grupo de desarrolladores, la creación de un sistema web, que permita gestionar el recurso humano que trabaja dentro de la institución. 

* **Desarrolladores:**
* 1
* 2
* 3
* 4
  
## Netbeans 25 y JDK 21 para este proyecto

<img width="1892" height="873" alt="image" src="https://github.com/user-attachments/assets/f3947d9a-5541-4d2d-b556-aacd8ef31f2c" />
br
br
<img width="1882" height="868" alt="image" src="https://github.com/user-attachments/assets/bac8fb5e-6bfa-49e5-aede-50973a1532fa" />

# Sistema de Gestión de Recursos Humanos (RRHH UDB) 💼

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Jakarta EE](https://img.shields.io/badge/Jakarta_EE-001439?style=for-the-badge&logo=eclipse-ide&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)

## 📝 Descripción del Proyecto
El **Sistema de Recursos Humanos (RRHH UDB)** es una aplicación web diseñada para gestionar integralmente el ciclo de vida laboral del personal de la Universidad Don Bosco. Desarrollado con herramientas nativas de **Java Netbeans** y la plataforma **Jakarta EE**, el sistema centraliza la administración de empleados, departamentos, cargos y tipos de contratación en un módulo robusto de "Contrataciones".

## 🏛️ Arquitectura del Sistema
El proyecto implementa el **Patrón de Diseño MVC (Modelo-Vista-Controlador)**, lo que permite una separación clara de responsabilidades:

* **Vista:** Interfaz de usuario dinámica desarrollada con **JSP** y estilizada con **Bootstrap**.
* **Controlador:** Lógica de navegación y control mediante **Servlets**.
* **Modelo:** Representación de datos y lógica de negocio a través de **POJOs** y **DAOs** con **JDBC**.

---

## 🗄️ Estructura de la Base de Datos
El sistema utiliza una base de datos relacional en **MySQL** (`rrhh_udb2`) altamente normalizada.

### 1. Tablas de Catálogo (Independientes)
* **Empleados:** Datos personales, contacto y credenciales (`numeroDui`, `nombrePersona`, `usuario`, `correoInstitucional`).
* **Departamento:** Áreas estructurales (`nombreDepartamento`, `descripcionDepartamento`).
* **Cargos:** Puestos disponibles e indicador de jerarquía (`jefatura`).
* **TipoContratacion:** Modalidades de empleo (Permanente, Medio Tiempo, etc.).

### 2. Tabla Transaccional (Dependiente)
* **Contrataciones:** El núcleo del sistema. Utiliza **Llaves Foráneas (FK)** para enlazar los catálogos y gestiona datos específicos como `fechaContratacion`, `salario` y `estado`. Incluye integridad referencial mediante `ON DELETE CASCADE`.

---

## 📂 Organización del Código (Paquetes)

### `com.udb.model` (Capa de Modelo)
* **Clases Base:** Entidades simples como `Empleado.java`, `Cargo.java`, etc.
* **Clase Compuesta:** `Contratacion.java`. Aplica el principio de **Composición de Objetos**, permitiendo que un contrato contenga objetos completos de otras clases en lugar de solo IDs.

### `com.udb.dao` (Capa de Acceso a Datos)
Centraliza la comunicación con MySQL.
* Uso de `PreparedStatement` para garantizar seguridad contra **Inyección SQL**.
* Clases principales: `EmpleadoDAO.java`, `ContratacionDAO.java`.

### `com.udb.controller` (Capa de Controlador)
* **Servlets:** Actúan como directores de orquesta, interceptando peticiones HTTP, procesando datos de formularios y redirigiendo a las vistas correspondientes.

---

## 🛠️ Métodos Principales y Flujo CRUD

El sistema se rige por operaciones estandarizadas. Ejemplo del flujo en el módulo de **Contrataciones**:

| Método | Ubicación | Función |
| :--- | :--- | :--- |
| `listar()` | DAO | Ejecuta consultas con `INNER JOIN` para obtener nombres legibles de los catálogos. |
| `doGet()` | Servlet | Carga dinámicamente los elementos de los formularios (`<select>`) desde la DB. |
| `agregar()` | DAO | Recibe el objeto compuesto y ejecuta el `INSERT INTO` en la base de datos. |
| `doPost()` | Servlet | Captura parámetros del formulario, ensambla el objeto y solicita la persistencia al DAO. |

---

## 🚀 Instalación y Requisitos
1.  Clonar el repositorio.
2.  Importar la base de datos `rrhh_udb2.sql` en MySQL.
3.  Configurar la conexión JDBC en la clase de conexión del proyecto.
     * Usuario: root
     * contraseña: ""
5.  Desplegar en **Payara7** desde Netbeans. [Descargar aqui](https://www.udb.edu.sv](https://payara.fish/downloads/payara-platform-community-edition/?utm_campaign=community_project&utm_source=payara.org&utm_medium=referral&utm_content=awareness_community_webpage_&utm_term=payara_community))
