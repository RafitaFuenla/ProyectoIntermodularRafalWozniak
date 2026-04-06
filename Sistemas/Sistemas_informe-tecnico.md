# Sistemas Informáticos — Informe Técnico de Entorno de Ejecución

## Descripción del entorno

BoxRanger es una aplicación de escritorio desarrollada en Java con JavaFX. Se ejecuta en local, lo que significa que tanto la aplicación como la base de datos corren en el mismo equipo. No necesita conexión a internet ni servidor externo.

El entorno de desarrollo y ejecución es el siguiente:

- Equipo: MacBook Air 15" (2026)
- Chip: Apple M5
- Memoria RAM: 24 GB
- Almacenamiento: 494 GB SSD (359 GB disponibles)
- Sistema operativo: macOS Tahoe 26.4
- Pantalla: Liquid Retina 15,3 pulgadas (2880 x 1864)

---

## Sistema operativo

La aplicación se ha desarrollado y probado sobre macOS Tahoe 26.4. Al estar construida con Java y JavaFX, es multiplataforma y podría ejecutarse también en Windows o Linux siempre que se cumplan los requisitos de software indicados más abajo.

Se elige macOS como entorno principal porque es el sistema sobre el que se ha desarrollado el proyecto y donde se han realizado todas las pruebas.

---

## Requisitos de hardware

Mínimos para ejecutar la aplicación:

- Procesador: cualquier procesador de 64 bits moderno (Intel, AMD o Apple Silicon)
- RAM: 4 GB
- Almacenamiento: 500 MB libres
- Pantalla: resolución mínima de 1280 x 720

Recomendados para una experiencia fluida:

- Procesador: Apple M1 o equivalente
- RAM: 8 GB o más
- Almacenamiento: 1 GB libres
- Pantalla: resolución 1920 x 1080 o superior

---

## Requisitos de software

Para poder ejecutar BoxRanger es necesario tener instalado:

- Java 21 (se recomienda Amazon Corretto 21)
- JavaFX 21.0.6
- XAMPP con MySQL/MariaDB activo en el puerto 8012
- Maven 3.x (si se ejecuta desde el código fuente)
- IntelliJ IDEA (recomendado para desarrollo)

---

## Guía de instalación

1. Instalar Java 21 desde https://aws.amazon.com/corretto/
2. Instalar XAMPP desde https://www.apachefriends.org/ y arrancarlo con el servicio MySQL activo
3. Abrir phpMyAdmin (http://localhost:8012/phpmyadmin) y ejecutar en orden:
   - `BaseDatos/scripts/ScriptCreacionBBDD.sql`
   - `BaseDatos/scripts/ScriptDatosBBDD.sql`
4. Clonar el repositorio del proyecto
5. Abrir la carpeta `Programacion/BoxRanger` con IntelliJ IDEA como proyecto Maven
6. Ejecutar la clase `Launcher.java` como clase principal

---

## Usuarios y permisos

La aplicación accede a la base de datos con el usuario `root` sin contraseña, que es la configuración por defecto de XAMPP en un entorno local de desarrollo.

En un entorno real de producción lo correcto sería crear un usuario específico para la aplicación con permisos limitados solo a la base de datos BoxRanger, y nunca usar root con contraseña vacía. Para este proyecto educativo en local es suficiente.

---

## Mantenimiento

- La base de datos se puede respaldar exportando un volcado SQL desde phpMyAdmin
- Si se añaden nuevas funcionalidades, basta con actualizar el código y volver a ejecutar desde IntelliJ
- XAMPP debe estar arrancado siempre que se quiera usar la aplicación
- Se recomienda mantener Java y XAMPP actualizados a versiones estables

---

## Evidencias de funcionamiento

Las capturas de la aplicación en funcionamiento se encuentran en la carpeta `docs/capturas/` del repositorio, donde se puede ver el menú principal y las distintas secciones de la app ejecutándose correctamente en el entorno descrito.
