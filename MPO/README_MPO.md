# MPO — Ampliación de Programación

## De qué va este módulo

En Programación se valora que la app funcione. En el MPO se valora cómo está construida por dentro, que el código esté ordenado y que si alguien lo coge en el futuro pueda entenderlo sin volverse loco.

---

## Qué he hecho

La mejora principal que he aplicado es separar el código en capas usando MVC (Modelo-Vista-Controlador) y DAO (Data Access Object).

La idea es básicamente no mezclar todo en el mismo sitio. Si el código de la interfaz, la lógica y la base de datos están todos juntos en una clase, cuando algo falla no sabes dónde mirar y cualquier cambio pequeño puede romper cosas que no tienen nada que ver.

Separándolo en capas cada parte hace solo una cosa. Por ejemplo, si en el futuro se quisiera cambiar MySQL por otra base de datos, solo habría que tocar los DAOs. El resto no se toca. Lo mismo con la interfaz.

---

## Cómo está organizado el código

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

Cada capa tiene su función:

- **modelo/** — las clases Java que representan los datos: Socio, Entrenador, Clase, Pago
- **database/** — todo lo que tiene que ver con la base de datos. Los DAOs son los únicos que lanzan consultas SQL
- **controller/** — gestiona lo que pasa cuando el usuario hace clic en algo

---

## POO que he aplicado

**Encapsulación** — los atributos de las clases del modelo son privados y se accede a ellos con getters y setters. Al principio me parecía un rollo hacer tantos métodos pero tiene sentido para controlar cómo se accede a los datos.

**Responsabilidad única** — cada clase hace una cosa. SocioDAO solo gestiona datos de socios, SociosController solo gestiona los eventos de esa pantalla, y Socio solo representa los datos.

**Constructores** — cada entidad tiene un constructor que inicializa bien todos sus atributos. Tuve algún problema con esto al principio porque me olvidé el `this.` en las asignaciones y los ids me salían siempre a 0.

**Relaciones entre clases** — las clases se relacionan igual que en la base de datos, lo que hace que todo tenga coherencia.

---

## Diagrama UML

El diagrama está en `docs/uml/diagramaUML.png` y en la carpeta de MPO.

---

## Lo que más me ha costado

Entender el patrón DAO al principio fue lo más difícil. No veía para qué separar tanto las cosas si al final hacen lo mismo. Pero cuando empecé a hacer los controladores y vi que podía llamar a `socioDAO.obtenerTodos()` sin preocuparme de cómo funcionaba por dentro, empecé a entenderlo.
