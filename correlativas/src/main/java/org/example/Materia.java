package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Materia {
  private final String nombre;
  private boolean aprobada;
  private Set<Materia> materiasCorrelativas = new HashSet<>();

  public Materia(String nombre, boolean aprobada, Set<Materia> materiasCorrelativas) {
    this.nombre = nombre;
    this.aprobada = aprobada;
    this.materiasCorrelativas = materiasCorrelativas;
  }

  public Materia(String nombre, Set<Materia> materiasCorrelativas) {
    this.nombre = nombre;
    this.materiasCorrelativas = materiasCorrelativas;
  }

  public Materia(String nombre) {
    this.nombre = nombre;
  }

  /*
    Se obtiene el nombre de las correlativas para no generar un escaping reference y devolver la lista de materias correlativas, debido
    a que la clase que tiene la lista debe encargarse de manejarla
  */
  public List<String> getNombreCorrelativas() {
    return materiasCorrelativas.stream().map(Materia::getNombre).toList();
  }

  public boolean equalsNombre(String nombreAEvaluar) {
    return this.nombre.equalsIgnoreCase(nombreAEvaluar);
  }

  public String getNombre() {
    return nombre;
  }

  public boolean isAprobada() {
    return aprobada;
  }
}
