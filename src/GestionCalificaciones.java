import javax.swing.*;
import java.util.Map;
public class GestionCalificaciones {
    private static Calificacion nuevaCalificacion = new Calificacion();

    // Método para agregar notas de examen, valida si el alumno tiene calificaciones para la materia selecccionada, pide la nota
    // setea los valores en una calificacion para ponerlo en el MAP atraves del .put
    public static void agregarNotaExamen(Alumno alumno, Materia materia) {
        if (materia == null) {
            JOptionPane.showMessageDialog(null, "El alumno no está inscrito en la materia seleccionada.");
            return;
        }
        int nuevaNotaExamen = obtenerNota("Ingrese la nota del examen para la materia " + materia.getNombre() + ":");
        Map<Materia, Calificacion> calificacionesPorCurso = alumno.getCalificacionesPorCurso();
        Calificacion calificacionExistente = calificacionesPorCurso.get(materia);
        if (calificacionExistente == null) {
            calificacionExistente = new Calificacion();
            calificacionesPorCurso.put(materia, calificacionExistente);
        }
        calificacionExistente.setNotasExamenes(nuevaNotaExamen);
        calificacionExistente.setMateriaCalif(materia);
        JOptionPane.showMessageDialog(null, "Nota de examen agregada correctamente para la materia: " + materia.getNombre());
    }

    // Método para agregar notas de tp, valida si el alumno tiene calificaciones para la materia selecccionada, pide la nota
    // setea los valores en una calificacion para ponerlo en el MAP atraves del .put
    public static void agregarNotaTP(Alumno alumno, Materia materia) {
        if (materia == null) {
            JOptionPane.showMessageDialog(null, "El alumno no está inscrito en la materia seleccionada.");
            return;
        }
        int nuevaNotaTP = obtenerNota("Ingrese la nota del trabajo práctico para la materia " + materia.getNombre() + ":");
        Map<Materia, Calificacion> calificacionesPorCurso = alumno.getCalificacionesPorCurso();
        Calificacion calificacionExistente = calificacionesPorCurso.get(materia);
        if (calificacionExistente == null) {
            calificacionExistente = new Calificacion();
            calificacionesPorCurso.put(materia, calificacionExistente);
        }
        calificacionExistente.setNotasTP(nuevaNotaTP);
        calificacionExistente.setMateriaCalif(materia);
        JOptionPane.showMessageDialog(null, "Nota de trabajo práctico agregada correctamente para la materia: " + materia.getNombre());
    }

    // Método para obtener y validar una nota dentro del rango de 0 a 100
    private static int obtenerNota(String mensaje) {
        do {
            try {
                String notaStr = JOptionPane.showInputDialog(mensaje);
                if (notaStr == null) {
                    return 0;
                }
                int nota = Integer.parseInt(notaStr);
                if (nota < 0 || nota > 100) {
                    throw new IllegalArgumentException("La nota debe estar en el rango de 0 a 100.");
                }
                return nota;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } while (true);
    }

    // Método para ver las Notas del alumno en una materia específica
    public static void verNotas(Alumno alumno, Materia materiaSeleccionada) {
        if (alumno.getCalificacionesPorCurso().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El alumno no tiene calificaciones registradas.");
            return;
        }
        StringBuilder notas = new StringBuilder("Notas del Alumno:\n");
        for (Map.Entry<Materia, Calificacion> entry : alumno.getCalificacionesPorCurso().entrySet()) {
            Materia materia = entry.getKey();
            Calificacion calificacion = entry.getValue();
            notas.append("Materia: ").append(materia.getNombre()).append("\n");
            notas.append("Notas de Examen: ");
            if (!calificacion.getNotasExamenes().isEmpty()) {
                notas.append("Promedio: ").append(calificacion.calcularPromedioExamenes()).append("\n");
            }
            notas.append("Notas de Trabajos Prácticos: ");
            if (!calificacion.getNotasTP().isEmpty()) {
                notas.append(calificacion.evaluarTrabajosPracticos()).append("\n");
            }
            notas.append("----------------------\n");
        }
        JOptionPane.showMessageDialog(null, notas.toString(), "Notas del Alumno", JOptionPane.PLAIN_MESSAGE);
    }

    // Método para eliminar todas las notas del alumno en una materia especifica
    public static void eliminarNotas(Alumno alumno, Materia materia) {
        int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de que desea eliminar todas las notas del alumno para la materia " + materia.getNombre() + "?",
                "Confirmar Eliminación de Notas",
                JOptionPane.YES_NO_OPTION
        );
        if (confirmacion == JOptionPane.YES_OPTION) {
            Map<Materia, Calificacion> calificacionesPorCurso = alumno.getCalificacionesPorCurso();
            calificacionesPorCurso.remove(materia);
            alumno.setCalificacionesPorCurso(materia, calificacionesPorCurso);
            JOptionPane.showMessageDialog(null, "Notas eliminadas correctamente para la materia: " + materia.getNombre());
        } else {
            JOptionPane.showMessageDialog(null, "La eliminación de notas ha sido cancelada.");
        }
    }
}