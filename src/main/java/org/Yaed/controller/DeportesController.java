package org.Yaed.controller;

import org.Yaed.entity.*;
import org.Yaed.services.GenericServiceImpl;
import org.Yaed.services.IGenericService;
import org.Yaed.util.HibernateUtil;

import java.util.List;
import java.util.Objects;

public class DeportesController {

    public static void saveGrupoDeporte(GrupoDeporte grupo) {
        IGenericService<GrupoDeporte> deporteService = new GenericServiceImpl<>(GrupoDeporte.class, HibernateUtil.getSessionFactory());
        deporteService.save(grupo);
    }
    public static void updateGrupoDeporte(GrupoDeporte grupo) {
        IGenericService<GrupoDeporte> deporteService = new GenericServiceImpl<>(GrupoDeporte.class, HibernateUtil.getSessionFactory());
        deporteService.update(grupo);
    }
    public static void deleteGrupoDeporte(GrupoDeporte grupo) {
        IGenericService<GrupoDeporte> deporteService = new GenericServiceImpl<>(GrupoDeporte.class, HibernateUtil.getSessionFactory());
        deporteService.delete(grupo);
    }
    public static List<GrupoDeporte> getAllGruposDeporte() {
        List<GrupoDeporte> deportes;
        IGenericService<GrupoDeporte> deporteService = new GenericServiceImpl<>(GrupoDeporte.class, HibernateUtil.getSessionFactory());
        deportes = deporteService.getAll();
        return deportes;
    }

    //categorias de deportes

    public static void saveCategoriaDeporte(CategoriaDeportes categoria) {
        IGenericService<CategoriaDeportes> categoriaDeportesIGenericService = new GenericServiceImpl<>(CategoriaDeportes.class, HibernateUtil.getSessionFactory());
        categoriaDeportesIGenericService.save(categoria);
    }
    public static void updateCategoriaDeporte(CategoriaDeportes categoria) {
        IGenericService<CategoriaDeportes> categoriaDeportesIGenericService = new GenericServiceImpl<>(CategoriaDeportes.class, HibernateUtil.getSessionFactory());
        categoriaDeportesIGenericService.update(categoria);
    }
    public static void deleteCategoriaDeporte(CategoriaDeportes categoria) {
        IGenericService<CategoriaDeportes> categoriaDeportesIGenericService = new GenericServiceImpl<>(CategoriaDeportes.class, HibernateUtil.getSessionFactory());
        categoriaDeportesIGenericService.delete(categoria);
    }
    public static List<CategoriaDeportes> getAllCategoriasDeporte() {
        List<CategoriaDeportes> categorias;
        IGenericService<CategoriaDeportes> categoriaDeportesIGenericService = new GenericServiceImpl<>(CategoriaDeportes.class, HibernateUtil.getSessionFactory());
        categorias = categoriaDeportesIGenericService.getAll();
        return categorias;
    }

    //grupos de deportes estudiantes (tabla intermedia)
    public static void  saveGruposDeporteaEstudiante(GruposDeportesEstudiantes grupoEstudiante) {
        IGenericService<GruposDeportesEstudiantes> gruposDeportesEstudiantesIGenericService = new GenericServiceImpl<>(GruposDeportesEstudiantes.class, HibernateUtil.getSessionFactory());
        gruposDeportesEstudiantesIGenericService.save(grupoEstudiante);
    }
    public static void updateGruposDeporteaEstudiante( GruposDeportesEstudiantes grupoEstudiante) {
        IGenericService<GruposDeportesEstudiantes> gruposDeportesEstudiantesIGenericService = new GenericServiceImpl<>(GruposDeportesEstudiantes.class, HibernateUtil.getSessionFactory());
        gruposDeportesEstudiantesIGenericService.update(grupoEstudiante);
    }
    public static void deleteGruposDeporteaEstudiante( GruposDeportesEstudiantes grupoEstudiante) {
        IGenericService<GruposDeportesEstudiantes> gruposDeportesEstudiantesIGenericService = new GenericServiceImpl<>(GruposDeportesEstudiantes.class, HibernateUtil.getSessionFactory());
        gruposDeportesEstudiantesIGenericService.delete(grupoEstudiante);
    }
    public static List<GruposDeportesEstudiantes> getAllGruposDeportesEstudiantes() {
        List<GruposDeportesEstudiantes> grupos;
        IGenericService<GruposDeportesEstudiantes> gruposDeportesEstudiantesIGenericService = new GenericServiceImpl<>(GruposDeportesEstudiantes.class, HibernateUtil.getSessionFactory());
        grupos = gruposDeportesEstudiantesIGenericService.getAll();
        return grupos;
    }
    //TIPO ACTIVIDAD DEPORTE

    public static void saveTipoActividadDeporte(TipoActividadDeporte tipoActividad) {
        IGenericService< TipoActividadDeporte> tipoActividadDeporteIGenericService = new GenericServiceImpl<>(TipoActividadDeporte.class, HibernateUtil.getSessionFactory());
        tipoActividadDeporteIGenericService.save(tipoActividad);
    }
    public static void updateTipoActividadDeporte(TipoActividadDeporte tipoActividad) {
        IGenericService< TipoActividadDeporte> tipoActividadDeporteIGenericService = new GenericServiceImpl<>(TipoActividadDeporte.class, HibernateUtil.getSessionFactory());
        tipoActividadDeporteIGenericService.update(tipoActividad);
    }
    public static void deleteTipoActividadDeporte(TipoActividadDeporte tipoActividad) {
        IGenericService< TipoActividadDeporte> tipoActividadDeporteIGenericService = new GenericServiceImpl<>(TipoActividadDeporte.class, HibernateUtil.getSessionFactory());
        tipoActividadDeporteIGenericService.delete(tipoActividad);
    }
    public static List<TipoActividadDeporte> getAllTipoActividadDeporte() {
        List<TipoActividadDeporte> tipos;
        IGenericService< TipoActividadDeporte> tipoActividadDeporteIGenericService = new GenericServiceImpl<>(TipoActividadDeporte.class, HibernateUtil.getSessionFactory());
        tipos = tipoActividadDeporteIGenericService.getAll();
        return tipos;
    }

    //ACTIVIDAD DEPORTE

    public static void saveActividadDeporte(ActividadDeporte actividad) {
        IGenericService< ActividadDeporte> actividadDeporteIGenericService = new GenericServiceImpl<>(ActividadDeporte.class, HibernateUtil.getSessionFactory());
        actividadDeporteIGenericService.save(actividad);
    }
    public static void updateActividadDeporte(ActividadDeporte actividad) {
        IGenericService< ActividadDeporte> actividadDeporteIGenericService = new GenericServiceImpl<>(ActividadDeporte.class, HibernateUtil.getSessionFactory());
        actividadDeporteIGenericService.update(actividad);
    }
    public static void deleteActividadDeporte(ActividadDeporte actividad) {
        IGenericService< ActividadDeporte> actividadDeporteIGenericService = new GenericServiceImpl<>(ActividadDeporte.class, HibernateUtil.getSessionFactory());
        actividadDeporteIGenericService.delete(actividad);}
    public static List<ActividadDeporte> getAllActividadDeporte() {
        List<ActividadDeporte> actividades;
        IGenericService< ActividadDeporte> actividadDeporteIGenericService = new GenericServiceImpl<>(ActividadDeporte.class, HibernateUtil.getSessionFactory());
        actividades = actividadDeporteIGenericService.getAll();
        return actividades;
    }

    public static Estudiante getEstudianteByNombre(String nombre) {
        List<Estudiante> estudiantes = EstudiantesController.getDeportesEstudiantes();
        for (Estudiante e : estudiantes) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }

        }
            return null;
    }

    //intermedia

    public static void saveActividadesGruposDeportes(ActividadesDeportesEstudiantes actividadGrupoDeporte) {
        IGenericService< ActividadesDeportesEstudiantes> actividadesGruposDeportesIGenericService = new GenericServiceImpl<>(ActividadesDeportesEstudiantes.class, HibernateUtil.getSessionFactory());
        actividadesGruposDeportesIGenericService.save(actividadGrupoDeporte);
    }
    public static void updateActividadesGruposDeportes(ActividadesDeportesEstudiantes actividadGrupoDeporte) {
        IGenericService< ActividadesDeportesEstudiantes> actividadesGruposDeportesIGenericService = new GenericServiceImpl<>(ActividadesDeportesEstudiantes.class, HibernateUtil.getSessionFactory());
        actividadesGruposDeportesIGenericService.update(actividadGrupoDeporte);
    }
    public static void deleteActividadesGruposDeportes(ActividadesDeportesEstudiantes actividadGrupoDeporte) {
        IGenericService< ActividadesDeportesEstudiantes> actividadesGruposDeportesIGenericService = new GenericServiceImpl<>(ActividadesDeportesEstudiantes.class, HibernateUtil.getSessionFactory());
        actividadesGruposDeportesIGenericService.delete(actividadGrupoDeporte);
    }
    public static List<ActividadesDeportesEstudiantes> getAllActividadesGruposDeportes() {
        List<ActividadesDeportesEstudiantes> actividadesGrupos;
        IGenericService< ActividadesDeportesEstudiantes> actividadesGruposDeportesIGenericService = new GenericServiceImpl<>(ActividadesDeportesEstudiantes.class, HibernateUtil.getSessionFactory());
        actividadesGrupos = actividadesGruposDeportesIGenericService.getAll();
        return actividadesGrupos;
    }

    public static GruposDeportesEstudiantes getGrupoDeporteEstudiantePorCarnet(String carnet) {
        List<GruposDeportesEstudiantes> gruposEstudiantes = getAllGruposDeportesEstudiantes();

        for (GruposDeportesEstudiantes gde : gruposEstudiantes) {
            if (gde.getEstudiante() != null &&
                    gde.getEstudiante().getCarnet().equalsIgnoreCase(carnet)) {

                return gde;
            }
        }

        return null; // ✔ Si no está en ningún grupo
    }

}
