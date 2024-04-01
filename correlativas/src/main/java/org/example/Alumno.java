package org.example;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
  private final String legajo;
  private final String nombre;
  private final List<Materia> materiasRealizadas;
  private List<Materia> materiasAprobadas;
  private List<Inscripcion> inscripcionesRealizadas = new ArrayList<>();

  public Alumno(String legajo, String nombre, List<Materia> materiasRealizadas) {
    this.legajo = legajo;
    this.nombre = nombre;
    this.materiasRealizadas = materiasRealizadas;
    this.setMateriasAprobadas();
  }

  private void setMateriasAprobadas() {
    this.materiasAprobadas = materiasRealizadas.stream().filter(Materia::isAprobada).toList();
  }

  public void inscribirse(List<Materia> materiasAInscribirse) {

    Inscripcion inscripcion = new Inscripcion(materiasAInscribirse, this);
    inscripcionesRealizadas.add(inscripcion);
  }

  public boolean tieneMateriaAprobada(String nombreMateriaAEvaluar) {
    return materiasAprobadas.stream().anyMatch(materiaAprobada -> materiaAprobada.equalsNombre(nombreMateriaAEvaluar));
  }

  public int getCantidadMateriasInscriptas() {
    return this.inscripcionesRealizadas.stream().mapToInt(Inscripcion::getCantidadMateriasInscriptas).sum();
  }
}
