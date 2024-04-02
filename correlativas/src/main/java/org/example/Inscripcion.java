package org.example;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Inscripcion {

  private int numeroInscripcion;
  private static final String ERROR_INSCRIBIENDOSE = "La inscripcion para la materia %s no esta aprobada.";
  private static final String ERROR_EMPTY_MATERIAS_A_INSCRIBIRSE = "No hay materias para inscribirse.";
  private final Set<Materia> materias;
  private static final Random RANDOM = new Random();

  public Inscripcion(Set<Materia> materiasAInscribirse, Alumno alumno) {
    this.numeroInscripcion = RANDOM.nextInt();
    this.materias = new HashSet<>();
    this.agregarMaterias(materiasAInscribirse, alumno);
  }

  /*
    Se pasa el alumno para no generar un escaping reference y enviarle la lista de materias realizadas, debido a que la clase que tiene la lista
    debe encargarse de manejarla
  */
  public void agregarMaterias(Set<Materia> materiasAInscribirse, Alumno alumno) {

    validarMateriasAInscribirse(materiasAInscribirse);

    materiasAInscribirse.forEach(materiaAInscribirse -> {
      if (aprobada(materiaAInscribirse, alumno)) {
        materias.add(materiaAInscribirse);
      } else {
        throw new RuntimeException(String.format(ERROR_INSCRIBIENDOSE, materiaAInscribirse.getNombre()));
      }
    });
  }

  private void validarMateriasAInscribirse(Set<Materia> materiasAInscribirse) {
    if (materiasAInscribirse.isEmpty()) {
      throw new RuntimeException(ERROR_EMPTY_MATERIAS_A_INSCRIBIRSE);
    }
  }

  private boolean aprobada(Materia materiaAInscribirse, Alumno alumno) {
    return materiaAInscribirse.getNombreCorrelativas().stream().allMatch(alumno::tieneMateriaAprobada);
  }

  public int getCantidadMateriasInscriptas() {
    return this.materias.size();
  }
}
