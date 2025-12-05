package org.Yaed.controller;

import org.Yaed.entity.ActividadDeporte;
import org.Yaed.entity.ActividadInternado;
import org.Yaed.entity.ActividadesEstudiantesInternado;
import org.Yaed.entity.Estudiante;
import org.Yaed.services.GenericServiceImpl;
import org.Yaed.services.IGenericService;
import org.Yaed.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ActController {

    public static void saveAct(ActividadesEstudiantesInternado actividad) {
        IGenericService<ActividadesEstudiantesInternado> service = new GenericServiceImpl<>(ActividadesEstudiantesInternado.class, HibernateUtil.getSessionFactory());
        service.save(actividad);
    }

    public static void updateAct(ActividadesEstudiantesInternado actividad) {
        IGenericService<ActividadesEstudiantesInternado> service = new GenericServiceImpl<>(ActividadesEstudiantesInternado.class, HibernateUtil.getSessionFactory());
        service.update(actividad);
    }

    public static void deleteAct(ActividadesEstudiantesInternado actividad) {
        IGenericService<ActividadesEstudiantesInternado> service = new GenericServiceImpl<>(ActividadesEstudiantesInternado.class, HibernateUtil.getSessionFactory());
        service.delete(actividad);
    }
    public static List<ActividadesEstudiantesInternado> getActividades() {
        IGenericService<ActividadesEstudiantesInternado> service = new GenericServiceImpl<>(ActividadesEstudiantesInternado.class, HibernateUtil.getSessionFactory());
        return service.getAll();
    }
    public static List<ActividadInternado> getActividadesInternado() {
        IGenericService<ActividadInternado> service = new GenericServiceImpl<>(ActividadInternado.class, HibernateUtil.getSessionFactory());
        return service.getAll();
    }
    public static void dividirActividades(List<Estudiante> todos,
                                                 List<Estudiante> grupo1,
                                                 List<Estudiante> grupo2,
                                                 List<Estudiante> grupo3) {
        // Limpiar listas
        grupo1.clear();
        grupo2.clear();
        grupo3.clear();

        // Filtrar estudiantes hombres con beca ID 2
        List<Estudiante> filtrados = new ArrayList<>();
        for (Estudiante e : todos) {
            if (e.getBecaid().getId() == 2) {
                filtrados.add(e);
            }
        }

        // Mezclar aleatoriamente
        Collections.shuffle(filtrados);

        // Dividir en 3 grupos de 8 estudiantes máximo
        for (int i = 0; i < filtrados.size(); i++) {
            Estudiante estudiante = filtrados.get(i);
            int grupo = i % 3;
            switch (grupo) {
                case 0 -> grupo1.add(estudiante);
                case 1 -> grupo2.add(estudiante);
                case 2 -> grupo3.add(estudiante);
                default -> {} // Ignora estudiantes extras si hay más de 24
            }
        }
    }
public static void AsignarActividades(){
        List <Estudiante> grupo1 = new ArrayList<>();
        List <Estudiante> grupo2 = new ArrayList<>();
        List <Estudiante> grupo3 = new ArrayList<>();
        List<Estudiante> todos = filtrarEstudiantesNoAsignadosAActividades(EstudiantesController.getEstudiantesInternos());

        dividirActividades(todos, grupo1, grupo2, grupo3);

        //actividad 1
        for (Estudiante estudiante : grupo1) {
            ActividadesEstudiantesInternado actividad = new ActividadesEstudiantesInternado();
           List<ActividadInternado> actividades = getActividadesInternado();
           ActividadInternado actLimp = actividades.getFirst();
            actividad.setEstudiante(estudiante);
            actividad.setActividad(actLimp);
            saveAct(actividad);
        }
    //actividad 2
    for (Estudiante estudiante : grupo2) {
        ActividadesEstudiantesInternado actividad = new ActividadesEstudiantesInternado();
        List<ActividadInternado> actividades = getActividadesInternado();
        ActividadInternado actLimp = actividades.get(1);
        actividad.setEstudiante(estudiante);
        actividad.setActividad(actLimp);
        saveAct(actividad);
    }
    //actividad 3
    for (Estudiante estudiante : grupo3) {
        ActividadesEstudiantesInternado actividad = new ActividadesEstudiantesInternado();
        List<ActividadInternado> actividades = getActividadesInternado();
        ActividadInternado actLimp = actividades.get(2);
        actividad.setEstudiante(estudiante);
        actividad.setActividad(actLimp);
        saveAct(actividad);
    }
}
    public static List<String> getCarnetsAsignadosAActividades() {
        return getActividades().stream()
                .map(ae -> {
                    Estudiante estudiante = ae.getEstudiante();
                    return (estudiante != null) ? estudiante.getCarnet() : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    public static List<Estudiante> filtrarEstudiantesNoAsignadosAActividades(List<Estudiante> estudiantes) {
        List<String> carnetsAsignados = getCarnetsAsignadosAActividades();

        return estudiantes.stream()
                .filter(e -> e != null && e.getCarnet() != null)
                .filter(e -> !carnetsAsignados.contains(e.getCarnet()))
                .collect(Collectors.toList());
    }
    public static void eliminarActividades(){
        List<ActividadesEstudiantesInternado> actividades = ActController.getActividades();
        for (ActividadesEstudiantesInternado actividad : actividades) {
            ActController.deleteAct(actividad);
        }
    }


}
