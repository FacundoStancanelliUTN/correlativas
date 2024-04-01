package org.example;

import java.util.ArrayList;
import java.util.List;

public class Materia {
  private final String nombre;
  private boolean aprobada;
  private List<Materia> materiasCorrelativas = new ArrayList<>();

  public Materia(String nombre, boolean aprobada, List<Materia> materiasCorrelativas) {
    this.nombre = nombre;
    this.aprobada = aprobada;
    this.materiasCorrelativas = materiasCorrelativas;
  }

  public Materia(String nombre, List<Materia> materiasCorrelativas) {
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
