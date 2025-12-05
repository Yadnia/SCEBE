package org.Yaed.controller;

import org.Yaed.entity.*;
import org.Yaed.services.GenericServiceImpl;
import org.Yaed.services.IGenericService;
import org.Yaed.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class CulturaController {

    //GRUPO CULTURA
    public static void saveGrupoCultura(GrupoCultura grupoCultura) {
        IGenericService<GrupoCultura> culturaService = new org.Yaed.services.GenericServiceImpl<>(GrupoCultura.class, HibernateUtil.getSessionFactory());
        culturaService.save(grupoCultura);
    }
    public static void updateGrupoCultura(GrupoCultura grupoCultura) {
        IGenericService<GrupoCultura> culturaService = new org.Yaed.services.GenericServiceImpl<>(GrupoCultura.class, HibernateUtil.getSessionFactory());
        culturaService.update(grupoCultura);
    }
    public static void deleteGrupoCultura(GrupoCultura grupoCultura) {
        IGenericService<GrupoCultura> culturaService = new org.Yaed.services.GenericServiceImpl<>(GrupoCultura.class, HibernateUtil.getSessionFactory());
        culturaService.delete(grupoCultura);
    }
    public static List<GrupoCultura> getAllGrupoCultura() {
        List<GrupoCultura> lista;
        IGenericService<GrupoCultura> culturaService = new org.Yaed.services.GenericServiceImpl<>(GrupoCultura.class, HibernateUtil.getSessionFactory());
        lista = culturaService.getAll();
        return lista;
    }
    //categorias de cultura

    public static void saveCateforiaCultura(CategoriasCultura categoriaCultura) {
        IGenericService<CategoriasCultura> culturaService = new org.Yaed.services.GenericServiceImpl<>(CategoriasCultura.class, HibernateUtil.getSessionFactory());
        culturaService.save(categoriaCultura);
    }
    public static void updateCategoriaCultura(CategoriasCultura categoriaCultura) {
        IGenericService<CategoriasCultura> culturaService = new org.Yaed.services.GenericServiceImpl<>(CategoriasCultura.class, HibernateUtil.getSessionFactory());
        culturaService.update(categoriaCultura);
    }
    public static void deleteCategoriaCultura(CategoriasCultura categoriaCultura) {
        IGenericService<CategoriasCultura> culturaService = new org.Yaed.services.GenericServiceImpl<>(CategoriasCultura.class, HibernateUtil.getSessionFactory());
        culturaService.delete(categoriaCultura);
    }
    public static List<CategoriasCultura> getAllCategoriaCultura() {
        List<CategoriasCultura> lista;
        IGenericService<CategoriasCultura> culturaService = new org.Yaed.services.GenericServiceImpl<>(CategoriasCultura.class, HibernateUtil.getSessionFactory());
        lista = culturaService.getAll();
        return lista;
    }
    // grupos de cultura estudiantes (intermedia)

    public static void saveGrupoCulturaEstudiante(GruposCulturaEstudiantes grupoCulturaEstudiante) {
        IGenericService<GruposCulturaEstudiantes> culturaService = new org.Yaed.services.GenericServiceImpl<>(GruposCulturaEstudiantes.class, HibernateUtil.getSessionFactory());
        culturaService.save(grupoCulturaEstudiante);
    }
    public static void updateGrupoCulturaEstudiante(GruposCulturaEstudiantes grupoCulturaEstudiante) {
        IGenericService<GruposCulturaEstudiantes> culturaService = new org.Yaed.services.GenericServiceImpl<>(GruposCulturaEstudiantes.class, HibernateUtil.getSessionFactory());
        culturaService.update(grupoCulturaEstudiante);}
    public static void deleteGrupoCulturaEstudiante(GruposCulturaEstudiantes grupoCulturaEstudiante) {
        IGenericService<GruposCulturaEstudiantes> culturaService = new org.Yaed.services.GenericServiceImpl<>(GruposCulturaEstudiantes.class, HibernateUtil.getSessionFactory());
        culturaService.delete(grupoCulturaEstudiante);
    }
    public static List<GruposCulturaEstudiantes> getAllGrupoCulturaEstudiante() {
        List<GruposCulturaEstudiantes> lista;
        IGenericService<GruposCulturaEstudiantes> culturaService = new org.Yaed.services.GenericServiceImpl<>(GruposCulturaEstudiantes.class, HibernateUtil.getSessionFactory());
        lista = culturaService.getAll();
        return lista;
    }

    //TIPO ACTIVIDAD CULTURA

    public static void saveTipoActividadCultura(TipoActividadCultura tipoActividadCultura) {
        IGenericService<TipoActividadCultura> culturaService = new org.Yaed.services.GenericServiceImpl<>(TipoActividadCultura.class, HibernateUtil.getSessionFactory());
        culturaService.save(tipoActividadCultura);
    }
    public static void updateTipoActividadCultura(TipoActividadCultura tipoActividadCultura) {
        IGenericService<TipoActividadCultura> culturaService = new org.Yaed.services.GenericServiceImpl<>(TipoActividadCultura.class, HibernateUtil.getSessionFactory());
        culturaService.update(tipoActividadCultura);
    }
    public static void deleteTipoActividadCultura(TipoActividadCultura tipoActividadCultura) {
        IGenericService<TipoActividadCultura> culturaService = new org.Yaed.services.GenericServiceImpl<>(TipoActividadCultura.class, HibernateUtil.getSessionFactory());
        culturaService.delete(tipoActividadCultura);
    }
    public static List<TipoActividadCultura> getAllTipoActividadCultura() {
        List<TipoActividadCultura> lista;
        IGenericService<TipoActividadCultura> culturaService = new org.Yaed.services.GenericServiceImpl<>(TipoActividadCultura.class, HibernateUtil.getSessionFactory());
        lista = culturaService.getAll();
        return lista;
    }
    // ACTIVIDAD CULTURA
    public static void saveActividadCultura(ActividadCultura actividadCultura) {
        IGenericService<ActividadCultura> culturaService = new org.Yaed.services.GenericServiceImpl<>(ActividadCultura.class, HibernateUtil.getSessionFactory());
        culturaService.save(actividadCultura);
    }
    public static void updateActividadCultura(ActividadCultura actividadCultura) {
        IGenericService<ActividadCultura> culturaService = new org.Yaed.services.GenericServiceImpl<>(ActividadCultura.class, HibernateUtil.getSessionFactory());
        culturaService.update(actividadCultura);
    }
    public static void deleteActividadCultura(ActividadCultura actividadCultura) {
        IGenericService<ActividadCultura> culturaService = new org.Yaed.services.GenericServiceImpl<>(ActividadCultura.class, HibernateUtil.getSessionFactory());
        culturaService.delete(actividadCultura);
    }
    public static List<ActividadCultura> getAllActividadCultura() {
        List<ActividadCultura> lista;
        IGenericService<ActividadCultura> culturaService = new org.Yaed.services.GenericServiceImpl<>(ActividadCultura.class, HibernateUtil.getSessionFactory());
        lista = culturaService.getAll();
        return lista;
    }

    //ESTUDIANTES

    public static List<Estudiante> getCulturaEstudiantes() {
        List<Estudiante> estudiantesDeportes = new ArrayList<>();
        IGenericService<Estudiante> estudianteService = new GenericServiceImpl<>(Estudiante.class, HibernateUtil.getSessionFactory());
        for (Estudiante e : estudianteService.getAll()) {
            if (e.getTipoEstudiante() != null &&
                    e.getTipoEstudiante().getIdTipoEstudiante() == 2) { // Suponiendo que el ID para estudiantes de cultura es 2
                estudiantesDeportes.add(e);
            }
        }
        return estudiantesDeportes;
    }

    //GRUPOS
    public static GruposCulturaEstudiantes getGrupoCulturaEstudiantePorCarnet(String carnet) {
        List<GruposCulturaEstudiantes> gruposEstudiantes = getAllGrupoCulturaEstudiante();

        for (GruposCulturaEstudiantes gde : gruposEstudiantes) {
            if (gde.getEstudiante() != null &&
                    gde.getEstudiante().getCarnet().equalsIgnoreCase(carnet)) {

                return gde;
            }
        }

        return null; // ✔ Si no está en ningún grupo
    }
    public static List<ActividadesEstudiantesCultura> getAllActividadesGruposCultura() {
        List<ActividadesEstudiantesCultura> actividadesGrupos;
        IGenericService< ActividadesEstudiantesCultura> actividadesGruposDeportesIGenericService = new GenericServiceImpl<>(ActividadesEstudiantesCultura.class, HibernateUtil.getSessionFactory());
        actividadesGrupos = actividadesGruposDeportesIGenericService.getAll();
        return actividadesGrupos;
    }

    public static void saveActividadesGruposCultura(ActividadesEstudiantesCultura actividadesEstudiantesCultura) {
        IGenericService< ActividadesEstudiantesCultura> actividadesGruposDeportesIGenericService = new GenericServiceImpl<>(ActividadesEstudiantesCultura.class, HibernateUtil.getSessionFactory());
        actividadesGruposDeportesIGenericService.save(actividadesEstudiantesCultura);
    }
    public static void updateActividadesGruposCultura(ActividadesEstudiantesCultura actividadesEstudiantesCultura) {
        IGenericService< ActividadesEstudiantesCultura> actividadesGruposDeportesIGenericService = new GenericServiceImpl<>(ActividadesEstudiantesCultura.class, HibernateUtil.getSessionFactory());
        actividadesGruposDeportesIGenericService.update(actividadesEstudiantesCultura);
    }
    public static void deleteActividadesGruposCultura(ActividadesEstudiantesCultura actividadesEstudiantesCultura) {
        IGenericService< ActividadesEstudiantesCultura> actividadesGruposDeportesIGenericService = new GenericServiceImpl<>(ActividadesEstudiantesCultura.class, HibernateUtil.getSessionFactory());
        actividadesGruposDeportesIGenericService.delete(actividadesEstudiantesCultura);
    }
}
