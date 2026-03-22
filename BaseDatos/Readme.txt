# Base de datos – BoxRanger

## Descripción
La base de datos está diseñada para gestionar un gimnasio de CrossFit, permitiendo almacenar información sobre socios, entrenadores, clases, pagos e inscripciones.

---

## Análisis
Se han identificado las siguientes entidades principales:

- Socios  
- Entrenadores  
- Clases  
- Pagos  
- Inscripciones  

---

## Relaciones
Las relaciones principales del modelo son:

- Un entrenador puede impartir varias clases (1:N)  
- Un socio puede tener varios pagos (1:N)  
- Un socio puede apuntarse a varias clases y una clase puede tener varios socios (N:M)  

---

## Modelo relacional
Se han creado las siguientes tablas:

- Entrenadores  
- Socios  
- Clases  
- Pagos  
- Inscripciones  

Cada tabla incluye su clave primaria y las claves foráneas necesarias para relacionarlas entre sí.

---

## Implementación
Se han desarrollado:

- Script SQL para la creación de tablas  
- Script SQL con datos de ejemplo  
- Script de consultas para trabajar con la base de datos  

---

## Consultas realizadas
Se han añadido varias consultas SQL pensadas para cubrir necesidades reales de la aplicación, no solo ejemplos sueltos.

Entre ellas se incluyen:

- Listados de socios, entrenadores y clases  
- Consultas con JOIN para relacionar tablas (por ejemplo, clases con entrenadores o pagos con socios)  
- Búsquedas por distintos criterios (nombre, especialidad, estado de pago, etc.)  
- Consultas de control, como pagos pendientes o fallidos  
- Consultas de agregación (conteo de inscritos por clase o total de pagos por socio)  

La idea es que estas consultas puedan utilizarse directamente desde la aplicación desarrollada en programación.

---

## Características
- Uso de claves primarias autoincrementales  
- Uso de claves foráneas para mantener la integridad  
- Control del estado de los pagos  
- Relación muchos a muchos mediante tabla intermedia  

---

## Datos de prueba
Se han añadido datos de ejemplo para poder comprobar el funcionamiento de la base de datos y facilitar su uso en la aplicación.

---

## Conclusión
La base de datos está pensada para integrarse con la aplicación desarrollada en programación, permitiendo realizar operaciones reales como consultas, inserciones y modificaciones de datos.