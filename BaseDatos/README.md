# BoxRanger – Base de Datos

Este es el README de la parte de bases de datos del proyecto BoxRanger, que es una aplicación para gestionar un box de CrossFit. Aquí explico cómo está montada la base de datos y cómo usarla.

---

## ¿Qué es esto?

BoxRanger es mi proyecto intermodular. La idea es hacer una app para gestionar un gimnasio de CrossFit (un "box"), donde puedas llevar el control de los socios, los entrenadores, las clases que hay, los pagos y quién está apuntado a qué clase.

Esta carpeta tiene todo lo relacionado con la base de datos: los diagramas, los scripts SQL y las consultas.

---

## Entidades que hay

Estuve pensando qué datos necesitaba guardar y al final me quedé con estas cinco tablas:

- **Socios** – la gente que está apuntada al box
- **Entrenadores** – los que dan las clases
- **Clases** – cada tipo de entrenamiento que se imparte
- **Pagos** – el historial de pagos de cada socio
- **Inscripciones** – tabla intermedia para relacionar socios con clases (muchos a muchos)

---

## Relaciones

- Un entrenador puede dar varias clases, pero cada clase la da un solo entrenador (1:N)
- Un socio puede pagar varias veces, y cada pago es de un socio (1:N)
- Un socio puede apuntarse a varias clases, y una clase puede tener varios socios (N:M) → por eso existe la tabla Inscripciones

---

## Cómo montar la base de datos

Necesitas tener XAMPP instalado. Yo lo tengo corriendo en el puerto **8012** (no el 3306 por defecto).

1. Arranca XAMPP y pon en marcha Apache y MySQL
2. Abre phpMyAdmin o una terminal con MySQL
3. Crea una base de datos que se llame `boxranger`
4. Ejecuta primero el script de creación de tablas: `creacion.sql`
5. Luego ejecuta el de datos de ejemplo: `insercion.sql`
6. Ya puedes probar las consultas del archivo `consultas.sql`

Si usas línea de comandos sería algo así:

```bash
mysql -u root -P 8012 -p boxranger < sql/creacion.sql
mysql -u root -P 8012 -p boxranger < sql/insercion.sql
```

---

## Consultas que he hecho

He intentado que las consultas sean cosas que tendría sentido usar en la app real, no solo ejemplos de relleno. Algunas de las que hay:

- Listar todos los socios activos
- Ver qué clases imparte cada entrenador
- Ver los pagos pendientes o fallidos
- Contar cuántos socios hay apuntados a cada clase
- Ver el total de pagos que ha hecho un socio
- Buscar socios por nombre o por especialidad del entrenador

Hay 20 consultas en total, con JOINs, filtros y alguna de agregación.

---

## Estructura de archivos

```
bbdd/
├── sql/
│   ├── creacion.sql       # Crea todas las tablas
│   ├── insercion.sql      # Mete datos de ejemplo
│   └── consultas.sql      # Las 20 consultas
├── diagramas/
│   ├── er_boxranger.png   # Diagrama Entidad-Relación
│   └── modelo_relacional.png
└── README.md              # Este archivo
```

---

## Tecnología usada

- MySQL (via XAMPP)
- phpMyAdmin para visualizar
- El puerto que uso es el 8012 porque el 3306 me daba conflictos

---

## Notas

La base de datos está pensada para conectarse con la aplicación Java que hago en el módulo de Programación. Uso JDBC para eso. El campo `fecha_baja` de Socios puede estar vacío (es nullable) porque no todos los socios se han dado de baja. Los pagos tienen un campo `estado` que puede ser pagado, pendiente o fallido.
