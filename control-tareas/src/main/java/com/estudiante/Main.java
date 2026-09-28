package com.estudiante;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Tarea> listaTareas = new ArrayList<>();

        listaTareas.add(new Tarea(1L, "Comprar alimentos", "Comprar productos para la semana", "ALTA", false));
        listaTareas.add(new Tarea(2L, "Realizar ejercicios", "Rutina de cardio por 30 minutos", "MEDIA", true));
        listaTareas.add(new Tarea(3L, "Estudiar Programación II", "Repasar conceptos de Maven y REST", "ALTA", false));

        System.out.println("===== LISTADO DE TAREAS =====\n");

        int pendientes = 0;
        int completadas = 0;

        for (Tarea tarea : listaTareas) {
            tarea.mostrarInformacion();
            if (tarea.isCompletada()) {
                completadas++;
            } else {
                pendientes++;
            }
        }

        System.out.println("\nTareas pendientes: " + pendientes);
        System.out.println("Tareas completadas: " + completadas);
    }
}
