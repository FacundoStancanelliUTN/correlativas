package org.example;

import java.util.HashSet;
import java.util.Set;

public class Inscripcion {

  private static final String ERROR_INSCRIBIENDOSE = "La inscripcion para la materia %s no esta aprobada.";
  private static final String ERROR_EMPTY_MATERIAS_A_INSCRIBIRSE = "No hay materias para inscribirse.";
  private final Set<Materia> materias;

  public Inscripcion(Set<Materia> materiasAInscribirse, Alumno alumno) {
    this.materias = new HashSet<>();
    this.agregarMaterias(materiasAInscribirse, alumno);  //Esto se ejecuta en el constructor, debido a que (basado en el diagrama) no deberiamos tener una inscripcion sin materias agregadas. Por lo que no se inicializa el set de materias con un set vacio.
  }

  /*
    Se pasa el alumno para no generar un escaping reference y enviarle el set de materias realizadas, debido a que la clase que tiene el set
    debe encargarse de manejarlo
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
