import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.example.Alumno;
import org.example.Materia;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
  private static final Materia MATERIA_CORRELATIVA_1 = new Materia("matematica I", Collections.emptyList());
  private static final Materia MATERIA_CORRELATIVA_2 = new Materia("matematica II", Collections.singletonList(MATERIA_CORRELATIVA_1));
  private static final String LEGAJO = "123ABC";
  private static final String NOMBRE_ALUMNO = "Juan";
  private List<Materia> materiasCorrelativas;
  private List<Materia> materiasRealizadas;

  @Before
  public void setUp() {
    materiasRealizadas = new ArrayList<>();
    materiasCorrelativas = List.of(MATERIA_CORRELATIVA_1, MATERIA_CORRELATIVA_2);
  }

  @Test
  public void inscribirseEnMateriaOk() {

    Materia materiaRealizada1 = new Materia("matematica I", true, Collections.emptyList());
    Materia materiaRealizada2 = new Materia("matematica II", true, Collections.singletonList(materiaRealizada1));
    materiasRealizadas = new ArrayList<>() {{add(materiaRealizada1); add(materiaRealizada2);}};

    Alumno alumno = new Alumno(LEGAJO, NOMBRE_ALUMNO, materiasRealizadas);

    Materia materiaAInscribirse1 = new Materia("matematica III", materiasCorrelativas);
    alumno.inscribirse(List.of(materiaAInscribirse1));

    assertEquals(1, alumno.getCantidadMateriasInscriptas());
  }

  @Test
  public void inscribirseEnMateriasOk() {

    Materia materiaRealizada1 = new Materia("matematica I", true, Collections.emptyList());
    Materia materiaRealizada2 = new Materia("matematica II", true, Collections.singletonList(materiaRealizada1));
    materiasRealizadas = new ArrayList<>() {{add(materiaRealizada1); add(materiaRealizada2);}};

    Alumno alumno = new Alumno(LEGAJO, NOMBRE_ALUMNO, materiasRealizadas);

    Materia materiaAInscribirse1 = new Materia("matematica III", materiasCorrelativas);
    Materia materiaAInscribirse2 = new Materia("algoritmos");
    alumno.inscribirse(List.of(materiaAInscribirse1, materiaAInscribirse2));

    assertEquals(2, alumno.getCantidadMateriasInscriptas());
  }

  @Test
  public void inscribirseEnMateriaFailMateriasAInscribirseEmpty() {

    Materia materiaRealizada1 = new Materia("matematica I", true, Collections.emptyList());
    Materia materiaRealizada2 = new Materia("matematica II", true, Collections.singletonList(materiaRealizada1));
    materiasRealizadas = new ArrayList<>() {{add(materiaRealizada1); add(materiaRealizada2);}};

    Alumno alumno = new Alumno(LEGAJO, NOMBRE_ALUMNO, materiasRealizadas);

    assertEquals("No hay materias para inscribirse.",
      assertThrows(
        RuntimeException.class,
        () -> alumno.inscribirse(Collections.emptyList())).getMessage());
  }

  @Test
  public void inscribirseEnMateriaFailPorNoAprobarMateriasCorrelativas() {

    Materia materiaRealizada1 = new Materia("matematica I", true, Collections.emptyList());
    Materia materiaRealizada2 = new Materia("matematica II", false, Collections.singletonList(materiaRealizada1));
    materiasRealizadas = new ArrayList<>() {{add(materiaRealizada1); add(materiaRealizada2);}};

    Alumno alumno = new Alumno(LEGAJO, NOMBRE_ALUMNO, materiasRealizadas);

    Materia materiaAInscribirse1 = new Materia("matematica III", materiasCorrelativas);

    assertEquals("La inscripcion para la materia matematica III no esta aprobada.",
      assertThrows(
        RuntimeException.class,
        () -> alumno.inscribirse(List.of(materiaAInscribirse1))).getMessage());
  }
}
