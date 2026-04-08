# Sistemas Informáticos — Informe técnico

## En qué equipo corre la aplicación

BoxRanger es una aplicación de escritorio que corre en local. Eso significa que tanto la app como la base de datos están en el mismo ordenador, no hace falta internet ni ningún servidor externo.

Mi equipo es un MacBook Air 15" con chip Apple M5, 24 GB de RAM y 494 GB de SSD. El sistema operativo es macOS Tahoe 26.4.

He elegido ejecutarla en local porque para un proyecto de primer curso tiene todo el sentido: es más sencillo, no depende de nada externo y es más fácil de depurar cuando algo falla.

---

## Sistema operativo

He desarrollado y probado todo en macOS. Como la app está hecha en Java con JavaFX, técnicamente podría correr también en Windows o Linux, pero yo solo la he probado en Mac así que no puedo garantizar que funcione igual en otros sistemas sin hacer ajustes.

---

## Requisitos de hardware

Lo mínimo para que arranque:

- Procesador de 64 bits (Intel, AMD o Apple Silicon, cualquiera sirve)
- 4 GB de RAM
- 500 MB de espacio libre
- Pantalla con resolución mínima de 1280x720

Lo recomendado para que vaya bien:

- Apple M1 o equivalente (o un Intel/AMD decente)
- 8 GB de RAM o más
- 1 GB libre en disco
- Pantalla de 1920x1080 o mejor

Con menos de 4 GB de RAM puede ir lento si tienes muchas cosas abiertas a la vez, sobre todo con XAMPP corriendo.

---

## Requisitos de software

Para ejecutar BoxRanger hace falta tener instalado:

- Java 21 — yo uso Amazon Corretto 21, que es gratuito
- JavaFX 21.0.6
- XAMPP con MySQL activo en el puerto 8012
- Maven 3.x (solo si ejecutas desde el código fuente)
- IntelliJ IDEA (recomendado, aunque no es obligatorio si tienes Maven)

---

## Cómo instalarlo paso a paso

1. Descargar e instalar Java 21 desde https://aws.amazon.com/corretto/
2. Descargar e instalar XAMPP desde https://www.apachefriends.org/ y arrancarlo con MySQL activo
3. Abrir phpMyAdmin en http://localhost:8012/phpmyadmin y ejecutar estos scripts en orden:
   - `BaseDatos/scripts/ScriptCreacionBBDD.sql`
   - `BaseDatos/scripts/ScriptDatosBBDD.sql`
4. Clonar el repositorio del proyecto
5. Abrir la carpeta `Programacion/BoxRanger` con IntelliJ IDEA como proyecto Maven
6. Ejecutar la clase `Launcher.java`

Si algo falla al arrancar, lo primero es comprobar que XAMPP está corriendo y que MySQL está en el puerto 8012, que es donde está configurada la conexión.

---

## Usuarios y permisos

La app se conecta a la base de datos con el usuario `root` sin contraseña, que es lo que viene por defecto en XAMPP. Para un proyecto en local de primer curso va bien así.

Si fuera una app real habría que crear un usuario específico con permisos solo para la base de datos de BoxRanger y nunca dejar root sin contraseña, pero para esto es suficiente.

---

## Mantenimiento

- Si se quiere hacer una copia de seguridad de la base de datos, se puede exportar desde phpMyAdmin como archivo SQL
- XAMPP tiene que estar arrancado siempre que se use la app
- Si se añade código nuevo, basta con actualizar desde el repositorio y volver a ejecutar desde IntelliJ
- Conviene mantener Java y XAMPP en versiones estables, no hace falta estar siempre en la última

---

## Evidencias

Las capturas de la app funcionando están en `docs/capturas/` del repositorio. Se puede ver el menú principal y las distintas pantallas ejecutándose en el entorno que he descrito aquí.

El esquema del sistema está en `docs/sistemas/esquema_sistema.png`.
