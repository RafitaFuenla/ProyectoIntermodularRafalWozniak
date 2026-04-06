# Lenguajes de Marcas

En este módulo he creado un archivo XML con los datos de los socios del box y un esquema XSD que lo valida.

---

## Archivos

**socios.xml** — contiene los datos de los socios exportados de la base de datos. Cada socio tiene su id como atributo y sus datos (nombre, apellidos, dni, email, teléfono, fecha de alta y fecha de baja) como elementos.

**socios.xsd** — es el esquema que define cómo tiene que ser el XML. Especifica qué elementos existen, qué tipo de dato tiene cada uno y cuáles son obligatorios. El XML está enlazado al XSD y validado correctamente.

---

## Estructura del XML

El elemento raíz es `<socios>`, que contiene tantos elementos `<socio>` como socios hay en la base de datos. Cada `<socio>` tiene el atributo `id` obligatorio y los campos mencionados arriba. El único campo opcional es `<fecha_baja>`, ya que un socio puede estar activo y no tener fecha de baja.

---

## Validación

El XML se valida automáticamente contra el XSD gracias a la referencia incluida en la etiqueta raíz. Se puede comprobar abriendo el archivo en IntelliJ IDEA, que indica si hay errores de validación.
