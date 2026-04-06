# MPO — Ampliación de Programación

## Qué se evalúa en este módulo

En Programación se valora que la aplicación funcione.
En el MPO se valora cómo está construida: que el código esté organizado y sea fácil de mantener o ampliar en el futuro.

---

## Mejora aplicada: Arquitectura MVC + DAO

La mejora principal que he aplicado es separar el código en capas usando los patrones MVC (Modelo-Vista-Controlador) y DAO (Data Access Object).

La idea es sencilla: "divide y vencerás". Si todo el código está junto en una sola clase, cualquier cambio pequeño puede romper cosas que no tienen nada que ver. Separándolo en capas, cada parte tiene su función y no interfiere con las demás.

Por ejemplo, si en el futuro se quisiera cambiar MySQL por otra base de datos, solo habría que tocar los DAOs. El resto del proyecto no se vería afectado. Lo mismo si se quisiera cambiar la interfaz: solo se cambian las vistas y los controladores.

---

## Arquitectura del proyecto

```
Vista (FXML)
     |
     v
Controlador  ------>  DAO  ------>  Base de Datos (MySQL)
     |                  |
     v                  v
  Eventos          Consultas SQL (JDBC)
     |
     v
  Modelo (clases Java)
```

Cada capa tiene una responsabilidad concreta:

- Modelo: clases Java que representan los datos (Socio, Entrenador, Clase, Pago)
- Vista: archivos FXML con el diseño de la interfaz
- Controlador: gestiona lo que ocurre cuando el usuario interactúa con la app
- DAO: es la única parte que habla con la base de datos, aquí están todas las consultas SQL

---

## Principios de POO aplicados

Encapsulación: los atributos de las clases del modelo son privados y se accede a ellos mediante getters y setters.

Responsabilidad única: cada clase hace una sola cosa. SocioDAO solo gestiona el acceso a datos de socios, SociosController solo gestiona los eventos de esa pantalla, y Socio solo representa los datos de un socio.

Constructores: cada entidad tiene un constructor que inicializa todos sus atributos correctamente.

Relaciones entre clases: las clases se relacionan de forma coherente con el modelo de datos, igual que en la base de datos.

---

## Estructura del código

```
src/main/java/com/boxranger/boxranger/
├── modelo/
│   ├── Socio.java
│   ├── Entrenador.java
│   ├── Clase.java
│   ├── Pago.java
│   └── Inscripcion.java
├── database/
│   ├── ConexionDB.java
│   ├── SocioDAO.java
│   ├── EntrenadorDAO.java
│   ├── ClaseDAO.java
│   └── PagoDAO.java
└── controller/
    ├── MenuController.java
    ├── SociosController.java
    ├── EntrenadoresController.java
    ├── ClasesController.java
    └── PagosController.java
```

---

## Conclusión

Gracias a esta arquitectura, añadir algo nuevo al proyecto (por ejemplo una sección de reservas) solo requiere crear su clase modelo, su DAO y su controlador, sin tocar nada de lo que ya existe.
