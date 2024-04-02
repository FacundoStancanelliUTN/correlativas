package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Alumno {
  private final String legajo;
  private final String nombre;
  private final Set<Materia> materiasRealizadas;
  private Set<Materia> materiasAprobadas;
  private List<Inscripcion> inscripcionesRealizadas = new ArrayList<>();

  public Alumno(String legajo, String nombre, Set<Materia> materiasRealizadas) {
    this.legajo = legajo;
    this.nombre = nombre;
    this.materiasRealizadas = materiasRealizadas;
    this.setMateriasAprobadas();
  }

  private void setMateriasAprobadas() {
    this.materiasAprobadas = materiasRealizadas.stream().filter(Materia::isAprobada).collect(Collectors.toSet());
  }

  public void inscribirse(Set<Materia> materiasAInscribirse) {

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
