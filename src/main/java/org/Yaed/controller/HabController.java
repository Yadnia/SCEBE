package org.Yaed.controller;

import org.Yaed.entity.Estudiante;
import org.Yaed.entity.Habitacion;
import org.Yaed.entity.HabitacionesEstudiantes;
import org.Yaed.services.GenericServiceImpl;
import org.Yaed.services.IGenericService;
import org.Yaed.util.HibernateUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HabController {

    public static void saveAsignacion(HabitacionesEstudiantes habitacionEstudiante) {
        IGenericService<HabitacionesEstudiantes> habsService = new GenericServiceImpl<>(HabitacionesEstudiantes.class, HibernateUtil.getSessionFactory());
        habsService.save(habitacionEstudiante);
    }
    public static void deleteAsignacion(HabitacionesEstudiantes habitacionEstudiante) {
        IGenericService<HabitacionesEstudiantes> habsService = new GenericServiceImpl<>(HabitacionesEstudiantes.class, HibernateUtil.getSessionFactory());
        habsService.delete(habitacionEstudiante);
    }
    public static void updateAsignacion(HabitacionesEstudiantes habitacionEstudiante) {
        IGenericService<HabitacionesEstudiantes> habsService = new GenericServiceImpl<>(HabitacionesEstudiantes.class, HibernateUtil.getSessionFactory());
        habsService.update(habitacionEstudiante);
    }
    public static List<Habitacion> getHabitaciones() {
        List<Habitacion> habs = new ArrayList<Habitacion>();
        IGenericService<Habitacion> habService = new GenericServiceImpl<>(Habitacion.class, HibernateUtil.getSessionFactory());
        habs = habService.getAll();
        return habs;
    }
    public static List<HabitacionesEstudiantes> getHabitacionesEstudiantes() {
        List<HabitacionesEstudiantes> habsEstudiantes = new ArrayList<HabitacionesEstudiantes>();
        IGenericService<HabitacionesEstudiantes> habsService = new GenericServiceImpl<>(HabitacionesEstudiantes.class, HibernateUtil.getSessionFactory());
        habsEstudiantes = habsService.getAll();
        return habsEstudiantes;
    }

    public static void AsignarMujeres() {
        List<Estudiante> listaTotalEstudiantes = filtrarNoAsignados(EstudiantesController.getEstudiantes());
        List<List<Estudiante>> grupos = dividirMujeresEnCuartos(listaTotalEstudiantes);
        List<Habitacion> habs = HabController.getHabitaciones();

        for (int i = 0; i < grupos.size(); i++) {
            List<Estudiante> grupo = grupos.get(i);
            if (i >= habs.size()) break; // Evita error si hay menos habitaciones
            Habitacion cuarto = habs.get(i);
            for (Estudiante e : grupo) {
                for (Estudiante estudiante : listaTotalEstudiantes) {
                    if (estudiante.getCarnet().equals(e.getCarnet())) {
                        String fechaAsignacion = "I Semestre";
                        String anio = "2025";
                        HabitacionesEstudiantes habEstudiante = new HabitacionesEstudiantes(estudiante, cuarto, anio, fechaAsignacion);
                     long ocupados = getHabitacionesEstudiantes().stream()
                             .filter(he -> he.getHabitacion().getId() == cuarto.getId())
                             .count();

                     if (ocupados < 4) {
                         HabController.saveAsignacion(habEstudiante);
                     }
                    }
                }
            }
        }
    }
    public static void AsignarHombres1() {
        List<Estudiante> grupo1 = new ArrayList<>();
        List<Estudiante> grupo2 = new ArrayList<>();
        List<Estudiante> listaTotalEstudiantes = filtrarNoAsignados(EstudiantesController.getEstudiantes());

        dividirEstudiantesM1Beca2(listaTotalEstudiantes, grupo1, grupo2);

        //casa 1
        for (Estudiante e : grupo1) {
            JLabel label = new JLabel(e.getNombre() + " " + e.getApellido());
            List <Habitacion> habs = HabController.getHabitaciones();
            Habitacion cuarto = habs.get(24);
            for(Estudiante estudiante : listaTotalEstudiantes)
                if (estudiante.getCarnet().equals(e.getCarnet())) {
                    String fechaAsignacion = "I Semestre";
                    String anio = "2025";
                    HabitacionesEstudiantes habEstudiante = new HabitacionesEstudiantes(estudiante, cuarto, anio,fechaAsignacion);
                    long ocupados = getHabitacionesEstudiantes().stream()
                            .filter(he -> he.getHabitacion().getId() == cuarto.getId())
                            .count();

                    if (ocupados < 8) {
                        HabController.saveAsignacion(habEstudiante);
                    }
                }
            label.setForeground(Color.WHITE);
        }
        for (Estudiante e : grupo2) {
            JLabel label = new JLabel(e.getNombre() + " " + e.getApellido());
            List <Habitacion> habs = HabController.getHabitaciones();
            Habitacion cuarto = habs.get(25);
            for(Estudiante estudiante : listaTotalEstudiantes)
                if (estudiante.getCarnet().equals(e.getCarnet())) {
                    String fechaAsignacion = "I Semestre";
                    String anio = "2025";
                    HabitacionesEstudiantes habEstudiante = new HabitacionesEstudiantes(estudiante, cuarto, anio,fechaAsignacion);
                    long ocupados = getHabitacionesEstudiantes().stream()
                            .filter(he -> he.getHabitacion().getId() == cuarto.getId())
                            .count();

                    if (ocupados < 8) {
                        HabController.saveAsignacion(habEstudiante);
                    }
                }
            label.setForeground(Color.WHITE);
        }
    }
    public static void AsignarHombres2() {
        List<Estudiante> grupo1 = new ArrayList<>();
        List<Estudiante> grupo2 = new ArrayList<>();
        List<Estudiante> grupo3 = new ArrayList<>();
        List<Estudiante> grupo4 = new ArrayList<>();
        List<Estudiante> grupo5 = new ArrayList<>();
        List<Estudiante> grupo6 = new ArrayList<>();
        List<Estudiante> grupo7 = new ArrayList<>();
        List<Estudiante> grupo8 = new ArrayList<>();
        List<Estudiante> grupo9 = new ArrayList<>();
        List<Estudiante> grupo10 = new ArrayList<>();
        List<Estudiante> listaTotalEstudiantes = filtrarNoAsignados(EstudiantesController.getEstudiantes());


        dividirEstudiantesM2Beca2(listaTotalEstudiantes, grupo1, grupo2, grupo3, grupo4, grupo5, grupo6, grupo7, grupo8, grupo9, grupo10);

        //casa 1
        for (Estudiante e : grupo1) {
            JLabel label = new JLabel(e.getNombre() + " " + e.getApellido());
            List <Habitacion> habs = HabController.getHabitaciones();
            Habitacion cuarto = habs.get(26);
            for(Estudiante estudiante : listaTotalEstudiantes)
                if (estudiante.getCarnet().equals(e.getCarnet())) {
                    String fechaAsignacion = "I Semestre";
                    String anio = "2025";
                    HabitacionesEstudiantes habEstudiante = new HabitacionesEstudiantes(estudiante, cuarto, anio,fechaAsignacion);
                    long ocupados = getHabitacionesEstudiantes().stream()
                            .filter(he -> he.getHabitacion().getId() == cuarto.getId())
                            .count();

                    if (ocupados < 6) {
                        HabController.saveAsignacion(habEstudiante);
                    }
                }
            label.setForeground(Color.WHITE);
        }
        for (Estudiante e : grupo2) {
            JLabel label = new JLabel(e.getNombre() + " " + e.getApellido());
            List <Habitacion> habs = HabController.getHabitaciones();
            Habitacion cuarto = habs.get(27);
            for(Estudiante estudiante : listaTotalEstudiantes)
                if (estudiante.getCarnet().equals(e.getCarnet())) {
                    String fechaAsignacion = "I Semestre";
                    String anio = "2025";
                    HabitacionesEstudiantes habEstudiante = new HabitacionesEstudiantes(estudiante, cuarto, anio,fechaAsignacion);
                    long ocupados = getHabitacionesEstudiantes().stream()
                            .filter(he -> he.getHabitacion().getId() == cuarto.getId())
                            .count();

                    if (ocupados < 6) {
                        HabController.saveAsignacion(habEstudiante);
                    }
                }
            label.setForeground(Color.WHITE);
        }
        for (Estudiante e : grupo3) {
            JLabel label = new JLabel(e.getNombre() + " " + e.getApellido());
            List <Habitacion> habs = HabController.getHabitaciones();
            Habitacion cuarto = habs.get(28);
            for(Estudiante estudiante : listaTotalEstudiantes)
                if (estudiante.getCarnet().equals(e.getCarnet())) {
                    String fechaAsignacion = "I Semestre";
                    String anio = "2025";
                    HabitacionesEstudiantes habEstudiante = new HabitacionesEstudiantes(estudiante, cuarto, anio,fechaAsignacion);
                    long ocupados = getHabitacionesEstudiantes().stream()
                            .filter(he -> he.getHabitacion().getId() == cuarto.getId())
                            .count();

                    if (ocupados < 6) {
                        HabController.saveAsignacion(habEstudiante);
                    }
                }
            label.setForeground(Color.WHITE);
        }
        for (Estudiante e : grupo4) {
            JLabel label = new JLabel(e.getNombre() + " " + e.getApellido());
            List <Habitacion> habs = HabController.getHabitaciones();
            Habitacion cuarto = habs.get(29);
            for(Estudiante estudiante : listaTotalEstudiantes)
                if (estudiante.getCarnet().equals(e.getCarnet())) {
                    String fechaAsignacion = "I Semestre";
                    String anio = "2025";
                    HabitacionesEstudiantes habEstudiante = new HabitacionesEstudiantes(estudiante, cuarto, anio,fechaAsignacion);
                    long ocupados = getHabitacionesEstudiantes().stream()
                            .filter(he -> he.getHabitacion().getId() == cuarto.getId())
                            .count();

                    if (ocupados < 6) {
                        HabController.saveAsignacion(habEstudiante);
                    }
                }
            label.setForeground(Color.WHITE);
        }
        for (Estudiante e : grupo5) {
            JLabel label = new JLabel(e.getNombre() + " " + e.getApellido());
            List <Habitacion> habs = HabController.getHabitaciones();
            Habitacion cuarto = habs.get(30);
            for(Estudiante estudiante : listaTotalEstudiantes)
                if (estudiante.getCarnet().equals(e.getCarnet())) {
                    String fechaAsignacion = "I Semestre";
                    String anio = "2025";
                    HabitacionesEstudiantes habEstudiante = new HabitacionesEstudiantes(estudiante, cuarto, anio,fechaAsignacion);
                    long ocupados = getHabitacionesEstudiantes().stream()
                            .filter(he -> he.getHabitacion().getId() == cuarto.getId())
                            .count();

                    if (ocupados < 6) {
                        HabController.saveAsignacion(habEstudiante);
                    }
                }
            label.setForeground(Color.WHITE);
        }
        for (Estudiante e : grupo6) {
            JLabel label = new JLabel(e.getNombre() + " " + e.getApellido());
            List <Habitacion> habs = HabController.getHabitaciones();
            Habitacion cuarto = habs.get(31);
            for(Estudiante estudiante : listaTotalEstudiantes)
                if (estudiante.getCarnet().equals(e.getCarnet())) {
                    String fechaAsignacion = "I Semestre";
                    String anio = "2025";
                    HabitacionesEstudiantes habEstudiante = new HabitacionesEstudiantes(estudiante, cuarto, anio,fechaAsignacion);
                    long ocupados = getHabitacionesEstudiantes().stream()
                            .filter(he -> he.getHabitacion().getId() == cuarto.getId())
                            .count();

                    if (ocupados < 6) {
                        HabController.saveAsignacion(habEstudiante);
                    }
                }
            label.setForeground(Color.WHITE);
        }
        for (Estudiante e : grupo7) {
            JLabel label = new JLabel(e.getNombre() + " " + e.getApellido());
            List <Habitacion> habs = HabController.getHabitaciones();
            Habitacion cuarto = habs.get(32);
            for(Estudiante estudiante : listaTotalEstudiantes)
                if (estudiante.getCarnet().equals(e.getCarnet())) {
                    String fechaAsignacion = "I Semestre";
                    String anio = "2025";
                    HabitacionesEstudiantes habEstudiante = new HabitacionesEstudiantes(estudiante, cuarto, anio,fechaAsignacion);
                    long ocupados = getHabitacionesEstudiantes().stream()
                            .filter(he -> he.getHabitacion().getId() == cuarto.getId())
                            .count();

                    if (ocupados < 6) {
                        HabController.saveAsignacion(habEstudiante);
                    }
                }
            label.setForeground(Color.WHITE);
        }
        for (Estudiante e : grupo8) {
            JLabel label = new JLabel(e.getNombre() + " " + e.getApellido());
            List <Habitacion> habs = HabController.getHabitaciones();
            Habitacion cuarto = habs.get(33);
            for(Estudiante estudiante : listaTotalEstudiantes)
                if (estudiante.getCarnet().equals(e.getCarnet())) {
                    String fechaAsignacion = "I Semestre";
                    String anio = "2025";
                    HabitacionesEstudiantes habEstudiante = new HabitacionesEstudiantes(estudiante, cuarto, anio,fechaAsignacion);
                    long ocupados = getHabitacionesEstudiantes().stream()
                            .filter(he -> he.getHabitacion().getId() == cuarto.getId())
                            .count();

                    if (ocupados < 6) {
                        HabController.saveAsignacion(habEstudiante);
                    }
                }
            label.setForeground(Color.WHITE);
        }
        for (Estudiante e : grupo9) {
            JLabel label = new JLabel(e.getNombre() + " " + e.getApellido());
            List <Habitacion> habs = HabController.getHabitaciones();
            Habitacion cuarto = habs.get(34);
            for(Estudiante estudiante : listaTotalEstudiantes)
                if (estudiante.getCarnet().equals(e.getCarnet())) {
                    String fechaAsignacion = "I Semestre";
                    String anio = "2025";
                    HabitacionesEstudiantes habEstudiante = new HabitacionesEstudiantes(estudiante, cuarto, anio,fechaAsignacion);
                    long ocupados = getHabitacionesEstudiantes().stream()
                            .filter(he -> he.getHabitacion().getId() == cuarto.getId())
                            .count();

                    if (ocupados < 6) {
                        HabController.saveAsignacion(habEstudiante);
                    }
                }
            label.setForeground(Color.WHITE);
        }
        for (Estudiante e : grupo10) {
            JLabel label = new JLabel(e.getNombre() + " " + e.getApellido());
            List <Habitacion> habs = HabController.getHabitaciones();
            Habitacion cuarto = habs.get(35);
            for(Estudiante estudiante : listaTotalEstudiantes)
                if (estudiante.getCarnet().equals(e.getCarnet())) {
                    String fechaAsignacion = "I Semestre";
                    String anio = "2025";
                    HabitacionesEstudiantes habEstudiante = new HabitacionesEstudiantes(estudiante, cuarto, anio,fechaAsignacion);
                    long ocupados = getHabitacionesEstudiantes().stream()
                            .filter(he -> he.getHabitacion().getId() == cuarto.getId())
                            .count();

                    if (ocupados < 6) {
                        HabController.saveAsignacion(habEstudiante);
                    }
                }
            label.setForeground(Color.WHITE);
        }
    }
    public static void dividirEstudiantesFBeca2(List<Estudiante> todos,
                                                 List<Estudiante> grupo1,
                                                 List<Estudiante> grupo2,
                                                    List<Estudiante> grupo3,
                                                    List<Estudiante> grupo4,
                                                    List<Estudiante> grupo5,
                                                    List<Estudiante> grupo6,
                                                    List<Estudiante> grupo7,
                                                    List<Estudiante> grupo8
    ) {
        // Limpiar listas
        grupo1.clear(); grupo2.clear();

        // Filtrar estudiantes mujeres con beca ID 2
        List<Estudiante> filtradas = new ArrayList<>();
        for (Estudiante e : todos) {
            if (Character.toUpperCase(e.getSexo()) == 'F' && e.getBecaid() != null && e.getBecaid().getId() == 2) {
                filtradas.add(e);
            }
        }

        // Mezclar aleatoriamente
        Collections.shuffle(filtradas);

        // Dividir en grupos de 12 estudiantes máximo
        for (int i = 0; i < filtradas.size(); i++) {
            Estudiante estudiante = filtradas.get(i);
            int grupo = i / 12;
            switch (grupo) {
                case 0 -> grupo1.add(estudiante);
                case 1 -> grupo2.add(estudiante);
                case 2 -> grupo3.add(estudiante);
                case 3 -> grupo4.add(estudiante);
                case 4 -> grupo5.add(estudiante);
                case 5 -> grupo6.add(estudiante);
                case 6 -> grupo7.add(estudiante);
                case 7 -> grupo8.add(estudiante);
                default -> {}
            }
        }
    }
    public static void dividirEstudiantesM2Beca2(List<Estudiante> todos,
                                                 List<Estudiante> grupo1,
                                                 List<Estudiante> grupo2,
                                                 List<Estudiante> grupo3,
                                                 List<Estudiante> grupo4,
                                                 List<Estudiante> grupo5,
                                                 List<Estudiante> grupo6,
                                                    List<Estudiante> grupo7,
                                                    List<Estudiante> grupo8,
                                                    List<Estudiante> grupo9,
                                                    List<Estudiante> grupo10
    ) {
        // Limpiar listas
        grupo1.clear(); grupo2.clear();

        // Filtrar estudiantes mujeres con beca ID 2
        List<Estudiante> filtradas = new ArrayList<>();
        for (Estudiante e : todos) {
            if (Character.toUpperCase(e.getSexo()) == 'M' && e.getBecaid() != null && e.getBecaid().getId() == 2) {
                filtradas.add(e);
            }
        }

        // Mezclar aleatoriamente
        Collections.shuffle(filtradas);


        // Dividir en grupos de 12 estudiantes máximo
        for (int i = 0; i < filtradas.size(); i++) {
            Estudiante estudiante = filtradas.get(i);
            int grupo = i / 6  ;
            switch (grupo) {
                case 0 -> grupo1.add(estudiante);
                case 1 -> grupo2.add(estudiante);
                case 2 -> grupo3.add(estudiante);
                case 3 -> grupo4.add(estudiante);
                case 4 -> grupo5.add(estudiante);
                case 5 -> grupo6.add(estudiante);
                case 6 -> grupo7.add(estudiante);
                case 7 -> grupo8.add(estudiante);
                case 8 -> grupo9.add(estudiante);
                case 9 -> grupo10.add(estudiante);
                default -> {}
            }
        }
    }
    public static void dividirEstudiantesM1Beca2(List<Estudiante> todos,
                                                 List<Estudiante> grupo1,
                                                 List<Estudiante> grupo2
    ) {
        // Limpiar listas
        grupo1.clear(); grupo2.clear();

        // Filtrar estudiantes mujeres con beca ID 2
        List<Estudiante> filtradas = new ArrayList<>();
        for (Estudiante e : todos) {
            if (Character.toUpperCase(e.getSexo()) == 'M' && e.getBecaid() != null && e.getBecaid().getId() == 2) {
                filtradas.add(e);
            }
        }

        // Mezclar aleatoriamente
        Collections.shuffle(filtradas);

        // Dividir en grupos de 8 estudiantes máximo
        for (int i = 0; i < filtradas.size(); i++) {
            Estudiante estudiante = filtradas.get(i);
            int grupo = i / 8;
            switch (grupo) {
                case 0 -> grupo1.add(estudiante);
                case 1 -> grupo2.add(estudiante);
                default -> {}
            }
        }
    }
    // En HabController.java
    public static List<List<Estudiante>> dividirMujeresEnCuartos(List<Estudiante> todos) {
        List<Estudiante> filtradas = new ArrayList<>();
        for (Estudiante e : todos) {
            if (Character.toUpperCase(e.getSexo()) == 'F' && e.getBecaid() != null && e.getBecaid().getId() == 2) {
                filtradas.add(e);
            }
        }
        Collections.shuffle(filtradas);
        int estudiantesPorCuarto = 4;
        int totalCuartos = 24;
        List<List<Estudiante>> grupos = new ArrayList<>();
        for (int i = 0; i < totalCuartos; i++) {
            grupos.add(new ArrayList<>());
        }
        for (int i = 0; i < filtradas.size(); i++) {
            grupos.get(i / estudiantesPorCuarto).add(filtradas.get(i));
        }
        return grupos;
    }
// 1. Método para obtener carnets ya asignados
public static List<String> getCarnetsAsignados() {
    return getHabitacionesEstudiantes().stream()
            .map(he -> he.getEstudiante().getCarnet())
            .collect(Collectors.toList());
}

// 2. Filtra estudiantes no asignados antes de agrupar/asignar
public static List<Estudiante> filtrarNoAsignados(List<Estudiante> estudiantes) {
    List<String> carnetsAsignados = getCarnetsAsignados();
    return estudiantes.stream()
            .filter(e -> !carnetsAsignados.contains(e.getCarnet()))
            .collect(Collectors.toList());
}
    public static void eliminarHabitaciones(){
        List<HabitacionesEstudiantes> actividades = HabController.getHabitacionesEstudiantes();
        for (HabitacionesEstudiantes actividad : actividades) {
            HabController.deleteAsignacion(actividad);
        }
    }


}
