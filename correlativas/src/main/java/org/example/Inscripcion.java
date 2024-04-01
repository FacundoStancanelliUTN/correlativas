package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Inscripcion {

  private int numeroInscripcion;
  private static final String ERROR_INSCRIBIENDOSE = "La inscripcion para la materia %s no esta aprobada.";
  private static final String ERROR_EMPTY_MATERIAS_A_INSCRIBIRSE = "No hay materias para inscribirse.";
  private final List<Materia> materias;
  private static final Random RANDOM = new Random();

  public Inscripcion(List<Materia> materiasAInscribirse, Alumno alumno) {
    this.numeroInscripcion = RANDOM.nextInt();
    this.materias = new ArrayList<>();
    this.agregarMaterias(materiasAInscribirse, alumno);
  }

  /*
    Se pasa el alumno para no generar un escaping reference y enviarle la lista de materias realizadas, debido a que la clase que tiene la lista
    debe encargarse de manejarla
  */
  public void agregarMaterias(List<Materia> materiasAInscribirse, Alumno alumno) {

    validarMateriasAInscribirse(materiasAInscribirse);

    materiasAInscribirse.forEach(materiaAInscribirse -> {
      if (aprobada(materiaAInscribirse, alumno)) {
        materias.add(materiaAInscribirse);
      } else {
        throw new RuntimeException(String.format(ERROR_INSCRIBIENDOSE, materiaAInscribirse.getNombre()));
      }
    });
  }

  private void validarMateriasAInscribirse(List<Materia> materiasAInscribirse) {
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
