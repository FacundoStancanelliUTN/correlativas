package org.example;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Alumno {
  private Set<Materia> materiasRealizadas = new HashSet<>();
  private final Set<Inscripcion> inscripcionesRealizadas = new HashSet<>();

  public Alumno() {}
  
  public Alumno(Set<Materia> materiasRealizadas) {
    this.materiasRealizadas = materiasRealizadas;
  }

  public void inscribirse(Set<Materia> materiasAInscribirse) {

    Inscripcion inscripcion = new Inscripcion(materiasAInscribirse, this);
    inscripcionesRealizadas.add(inscripcion);
  }

  public boolean tieneMateriaAprobada(String nombreMateriaAEvaluar) {
    return getMateriasAprobadas().stream().anyMatch(materiaAprobada -> materiaAprobada.equalsNombre(nombreMateriaAEvaluar));
  }

  private Set<Materia> getMateriasAprobadas() {
    return materiasRealizadas.stream().filter(Materia::isAprobada).collect(Collectors.toSet());
  }

  public int getCantidadMateriasInscriptas() {
    return this.inscripcionesRealizadas.stream().mapToInt(Inscripcion::getCantidadMateriasInscriptas).sum();
  }

  public void agregarMateriaRealizada(Materia materiaRealizada) {
    this.materiasRealizadas.add(materiaRealizada);
  }
}
