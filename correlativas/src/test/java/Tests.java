import static junit.framework.Assert.assertEquals;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.example.Alumno;
import org.example.Materia;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
  private static final Materia MATERIA_CORRELATIVA_1 = new Materia("matematica I", Collections.emptySet());
  private static final Materia MATERIA_CORRELATIVA_2 = new Materia("matematica II", Collections.singleton(MATERIA_CORRELATIVA_1));
  private Set<Materia> materiasCorrelativas;
  private Set<Materia> materiasRealizadas;

  @Before
  public void setUp() {
    materiasRealizadas = new HashSet<>();
    materiasCorrelativas = Set.of(MATERIA_CORRELATIVA_1, MATERIA_CORRELATIVA_2);
  }

  @Test
  public void inscribirseEnMateriaOk() {

    Materia materiaRealizada1 = new Materia("matematica I", true, Collections.emptySet());
    Materia materiaRealizada2 = new Materia("matematica II", true, Collections.singleton(materiaRealizada1));
    materiasRealizadas = new HashSet<>() {{add(materiaRealizada1); add(materiaRealizada2);}};

    Alumno alumno = new Alumno(materiasRealizadas);

    Materia materiaAInscribirse1 = new Materia("matematica III", materiasCorrelativas);
    alumno.inscribirse(Set.of(materiaAInscribirse1));

    assertEquals(1, alumno.getCantidadMateriasInscriptas());
  }

  @Test
  public void inscribirseEnMateriasOk() {

    Materia materiaRealizada1 = new Materia("matematica I", true, Collections.emptySet());
    Materia materiaRealizada2 = new Materia("matematica II", true, Collections.singleton(materiaRealizada1));
    materiasRealizadas = new HashSet<>() {{add(materiaRealizada1); add(materiaRealizada2);}};

    Alumno alumno = new Alumno(materiasRealizadas);

    Materia materiaAInscribirse1 = new Materia("matematica III", materiasCorrelativas);
    Materia materiaAInscribirse2 = new Materia("algoritmos");
    alumno.inscribirse(Set.of(materiaAInscribirse1, materiaAInscribirse2));

    assertEquals(2, alumno.getCantidadMateriasInscriptas());
  }

  @Test
  public void inscribirseEnMateriaFailMateriasAInscribirseEmpty() {

    Materia materiaRealizada1 = new Materia("matematica I", true, Collections.emptySet());
    Materia materiaRealizada2 = new Materia("matematica II", true, Collections.singleton(materiaRealizada1));
    materiasRealizadas = new HashSet<>() {{add(materiaRealizada1); add(materiaRealizada2);}};

    Alumno alumno = new Alumno(materiasRealizadas);

    assertEquals("No hay materias para inscribirse.",
      assertThrows(
        RuntimeException.class,
        () -> alumno.inscribirse(Collections.emptySet())).getMessage());
  }

  @Test
  public void inscribirseEnMateriaFailPorNoAprobarMateriasCorrelativas() {

    Materia materiaRealizada1 = new Materia("matematica I", true, Collections.emptySet());
    Materia materiaRealizada2 = new Materia("matematica II", false, Collections.singleton(materiaRealizada1));
    materiasRealizadas = new HashSet<>() {{add(materiaRealizada1); add(materiaRealizada2);}};

    Alumno alumno = new Alumno(materiasRealizadas);

    Materia materiaAInscribirse1 = new Materia("matematica III", materiasCorrelativas);

    assertEquals("La inscripcion para la materia matematica III no esta aprobada.",
      assertThrows(
        RuntimeException.class,
        () -> alumno.inscribirse(Set.of(materiaAInscribirse1))).getMessage());
  }
}
