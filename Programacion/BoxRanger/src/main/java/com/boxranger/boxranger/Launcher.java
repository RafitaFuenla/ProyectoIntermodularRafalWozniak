package com.boxranger.boxranger;

import javafx.application.Application;

/**
 * Clase de arranque de la aplicación BoxRanger.
 * Se utiliza como punto de entrada alternativo para evitar problemas
 * de compatibilidad con el módulo JavaFX al ejecutar desde el JAR.
 * Delega el inicio a BoxRangerApplication.
 */
public class Launcher {

    /**
     * Método principal que lanza la aplicación JavaFX.
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        Application.launch(BoxRangerApplication.class, args);
    }
}