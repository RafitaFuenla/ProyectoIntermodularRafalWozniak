# Lenguajes de Marcas – BoxRanger

Esta carpeta tiene todo lo del módulo de Lenguajes de Marcas. Básicamente he creado un XML con datos de socios del box y un XSD para validarlo.

---

## Qué hay aquí

**socios.xml** — un XML con los socios de la base de datos. Cada socio tiene su id como atributo y los datos típicos: nombre, apellidos, dni, email, teléfono y fecha de alta. La fecha de baja es opcional porque no todos los socios se han dado de baja.

**socios.xsd** — el esquema que valida el XML. No es un XSD de adorno, tiene restricciones reales:
- El DNI tiene que tener 8 números y una letra mayúscula
- El teléfono tiene que empezar por 6 o 7 y tener 9 dígitos
- El email tiene que tener @ y dominio con punto
- El nombre tiene que tener mínimo 2 caracteres

**SociosIncorrectos.xml** — un XML que falla a propósito para demostrar que el XSD funciona de verdad. Tiene un DNI corto, un email sin @, un teléfono que empieza por 9, un nombre de una letra y una fecha que no es una fecha.

---

## Cómo se valida

Desde terminal con xmllint:

```bash
xmllint --schema socios.xsd socios.xml --noout
xmllint --schema socios.xsd SociosIncorrectos.xml --noout
```

El primero devuelve `validates` y el segundo saca 5 errores. Tengo captura en `validacion_xml.png`.

---

## Relación con el proyecto

Los datos del XML son los mismos socios que están en la base de datos de BoxRanger. La idea es que el XML podría usarse como exportación de datos desde la app, por ejemplo para hacer una copia o para compartir la info en un formato estándar.
