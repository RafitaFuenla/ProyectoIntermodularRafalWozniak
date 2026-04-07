# BoxRanger - Gestión de Box de CrossFit

## Descripción

BoxRanger es una aplicación de escritorio en Java con JavaFX para gestionar un box de CrossFit. Desde ella se pueden administrar socios, consultar entrenadores, ver clases y controlar el estado de los pagos.

Es el Proyecto Intermodular de 1º de DAM, así que integra cosas de varios módulos: Bases de Datos, Programación, Lenguajes de Marcas, Sistemas Informáticos, Entornos de Desarrollo y MPO.

---

## Qué problema resuelve

Un box de CrossFit necesita llevar el control de sus socios, saber qué entrenadores tiene y qué clases hay disponibles, y también saber quién ha pagado y quién no. BoxRanger junta todo eso en una sola aplicación conectada a una base de datos MySQL.

---

## Tecnologías utilizadas

| Tecnología | Versión | Para qué se usa |
|---|---|---|
| Java (Amazon Corretto) | 21 | Lenguaje principal |
| JavaFX | 21.0.6 | Interfaz gráfica |
| FXML + SceneBuilder | 21 | Diseño de las vistas |
| MySQL (MariaDB via XAMPP) | 10.4 | Base de datos |
| JDBC (MySQL Connector) | 8.0.33 | Conexión Java-BD |
| Maven | 3.x | Gestión de dependencias |
| Git + GitHub | — | Control de versiones |
| IntelliJ IDEA | — | IDE |

---

## Estructura del repositorio

```
BoxRanger/
├── BaseDatos/               # Módulo 0484 – Bases de Datos
│   ├── diagramas/           # Diagrama E/R y Modelo Relacional
│   ├── scripts/             # Scripts SQL de creación, datos y consultas
│   └── README.md
│
├── Programacion/            # Módulo 0485 – Programación
│   └── BoxRanger/           # Proyecto Java/JavaFX
│       ├── src/
│       │   └── main/
│       │       ├── java/com/boxranger/boxranger/
│       │       │   ├── controller/    # Controladores JavaFX
│       │       │   ├── database/      # DAOs y conexión JDBC
│       │       │   └── modelo/        # Clases entidad
│       │       └── resources/         # FXML e imágenes
│       └── pom.xml
│
├── LenguajesDeMarcas/       # Módulo 0373 – Lenguajes de Marcas
│   ├── datos.xml
│   ├── esquema.xsd
│   └── docs/
│
├── Sistemas/                # Módulo 0483 – Sistemas Informáticos
│   └── informe-tecnico.md
│
├── MPO/                     # Ampliación de Programación
│   └── README.md
│
├── Empleabilidad/           # Módulo 1709
│   └── perfil-profesional.md
│
└── README.md
```

---

## Cómo ejecutarlo

### Requisitos

- Java 21 (Amazon Corretto)
- XAMPP con MySQL activo en el puerto 8012
- Maven 3.x
- IntelliJ IDEA

### Pasos

1. Clona el repositorio:
```bash
git clone https://github.com/tu-usuario/BoxRanger.git
```

2. Ábrelo en IntelliJ IDEA como proyecto Maven.

3. Arranca el servicio MySQL desde XAMPP.

4. Importa la base de datos. Puedes hacerlo desde phpMyAdmin o MySQL Workbench ejecutando estos scripts en orden:
```
BaseDatos/scripts/ScriptCreacionBBDD.sql
BaseDatos/scripts/ScriptDatosBBDD.sql
```

5. Comprueba que los datos de conexión en `ConexionDB.java` coinciden con tu configuración:
```java
private static final String url = "jdbc:mysql://127.0.0.1:8012/BoxRanger";
private static final String usuario = "root";
private static final String contrasena = "";
```

6. Ejecuta `Launcher.java` como clase principal.

---

## Funcionalidades

| Sección | Qué puedes hacer |
|---|---|
| Socios | Listar, añadir, editar y eliminar socios |
| Entrenadores | Consultar entrenadores y sus especialidades |
| Clases | Ver clases y horarios disponibles |
| Pagos | Consultar pagos y su estado (pagado / pendiente / fallido) |

---

## Arquitectura

El proyecto usa el patrón MVC con una capa DAO para acceder a la base de datos:

```
Vista (FXML)  →  Controlador  →  DAO  →  Base de Datos
     ↑               ↓
  JavaFX          Modelo (entidades Java)
```

- **Modelo:** clases que representan las entidades (`Socio`, `Entrenador`, `Clase`, `Pago`, `Inscripcion`)
- **Vista:** archivos FXML diseñados con SceneBuilder
- **Controlador:** gestiona los eventos de la interfaz
- **DAO:** encapsula las consultas SQL con JDBC

---

## Capturas

### Menú Principal
![Menú Principal](docs/capturas/menu-principal.png)

### Socios
![Socios](docs/capturas/socios.png)

### Entrenadores
![Entrenadores](docs/capturas/entrenadores.png)

### Clases
![Clases](docs/capturas/clases.png)

### Pagos
![Pagos](docs/capturas/pagos.png)

---

## Autor

Rafal Wozniak Bebenek — 1º DAM Virtual — Prometeo by The Power  
GitHub: https://github.com/RafitaFuenla/ProyectoIntermodularRafalWozniak.git

---

## Licencia

Proyecto educativo para el Intermodular de 1º DAM.
