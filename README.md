# BoxRanger — Gestión de Box de CrossFit

BoxRanger es una aplicación de escritorio hecha en Java con JavaFX para gestionar un box de CrossFit. Permite llevar el control de socios, entrenadores, clases y pagos desde una interfaz gráfica conectada a una base de datos MySQL.

Es mi Proyecto Intermodular de 1º de DAM, así que mezcla cosas de varios módulos: Bases de Datos, Programación, Lenguajes de Marcas, Sistemas Informáticos, Entornos de Desarrollo y MPO.

---

## Por qué existe esto

Un box de CrossFit tiene que llevar el control de sus socios, saber qué entrenadores tiene, qué clases hay y quién ha pagado y quién no. Normalmente eso se lleva en hojas de cálculo o incluso en papel. BoxRanger junta todo en una sola app conectada a base de datos.

---

## Tecnologías que he usado

| Tecnología | Versión | Para qué |
|---|---|---|
| Java (Amazon Corretto) | 21 | Lenguaje principal |
| JavaFX | 21.0.6 | Interfaz gráfica |
| FXML + SceneBuilder | 21 | Diseño de las vistas |
| MySQL via XAMPP | 10.4 | Base de datos |
| JDBC (MySQL Connector) | 8.0.33 | Conexión Java-BD |
| Maven | 3.x | Gestión de dependencias |
| Git + GitHub | — | Control de versiones |
| IntelliJ IDEA | — | IDE |

---

## Cómo ejecutarlo

**Lo que necesitas tener instalado:**
- Java 21 (Amazon Corretto)
- XAMPP con MySQL corriendo en el puerto 8012
- Maven 3.x
- IntelliJ IDEA

**Pasos:**

1. Clona el repositorio:
```bash
git clone https://github.com/RafitaFuenla/ProyectoIntermodularRafalWozniak.git
```

2. Ábrelo en IntelliJ IDEA como proyecto Maven.

3. Arranca XAMPP y pon en marcha el servicio MySQL.

4. Importa la base de datos ejecutando estos scripts en orden desde phpMyAdmin:
```
BaseDatos/scripts/ScriptCreacionBBDD.sql
BaseDatos/scripts/ScriptDatosBBDD.sql
```

5. Comprueba que la conexión en `ConexionDB.java` está bien configurada:
```java
private static final String url = "jdbc:mysql://127.0.0.1:8012/BoxRanger";
private static final String usuario = "root";
private static final String contrasena = "";
```

6. Ejecuta `Launcher.java` como clase principal.

Si algo no arranca, lo primero es comprobar que XAMPP está corriendo y que MySQL está en el puerto 8012.

---

## Qué puedes hacer con la app

| Sección | Funcionalidades |
|---|---|
| Socios | Listar, añadir, editar y eliminar socios |
| Entrenadores | Consultar entrenadores y especialidades |
| Clases | Ver clases y horarios |
| Pagos | Ver pagos y su estado (pagado / pendiente / fallido) |

---

## Cómo está organizado el código

El proyecto usa MVC con patrón DAO para separar la interfaz, la lógica y el acceso a la base de datos:

```
Vista (FXML)  →  Controlador  →  DAO  →  Base de Datos
                     ↓
                  Modelo (clases Java)
```

- **modelo/** — las clases que representan los datos: Socio, Entrenador, Clase, Pago, Inscripcion
- **database/** — los DAOs y la conexión JDBC, lo único que habla con MySQL
- **controller/** — gestiona lo que pasa cuando el usuario interactúa con la app

---

## Estructura del repositorio

```
BoxRanger/
├── BaseDatos/               # Bases de Datos
│   ├── diagramas/
│   ├── scripts/
│   └── README.md
├── Programacion/            # Programación
│   └── BoxRanger/
│       ├── src/
│       └── pom.xml
├── LenguajesDeMarcas/       # Lenguajes de Marcas
│   ├── socios.xml
│   ├── socios.xsd
│   ├── SociosIncorrectos.xml
│   └── README.md
├── docs/
│   ├── capturas/
│   ├── sistemas/
│   ├── uml/
│   └── empleabilidad/
├── MPO/
│   └── README.md
└── README.md
```

---

## Capturas

### Menú principal
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
GitHub: https://github.com/RafitaFuenla/ProyectoIntermodularRafalWozniak
