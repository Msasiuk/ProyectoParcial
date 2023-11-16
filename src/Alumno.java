import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Clase Objeto tendra características y métodos generales
public class Alumno extends Persona{

    private ArrayList<Materia> listaMaterias = new ArrayList<>();
    private Map<Materia, Calificacion> calificacionesPorCurso = new HashMap<>();

    public Map<Materia, Calificacion> getCalificacionesPorCurso() {
        return calificacionesPorCurso;
    }

    public void setCalificacionesPorCurso(Materia materia, Map<Materia, Calificacion> calificacionesPorCurso) {
        this.calificacionesPorCurso = calificacionesPorCurso;
    }

    // Método para ingresar materias al alumno, valida si la Materia a ingresar es nula o si ya fue asignada al Alumno
    public void ingresarMaterias(Materia materia) {
        if (materia != null) {
            if (!this.listaMaterias.contains(materia)) {
                this.listaMaterias.add(materia);
            } else {
                JOptionPane.showMessageDialog(null, "La materia '" + materia.getNombre() + "' ya está asignada a este alumno.");
            }
        }
    }

    // Método para obtener la lista de materias asignadas al alumno, valida si la lista es nula y devuelve una lista vacía en ese caso
    public ArrayList<Materia> getListaMaterias() {
        return listaMaterias != null ? listaMaterias : new ArrayList<>();
    }

    public ArrayList<Materia> obtenerMateriasInscritas() {
        return new ArrayList<>(calificacionesPorCurso.keySet());
    }
    public Alumno(String nombre, String apellido, int dni) {
        super(nombre, apellido, dni);
    }

    // Muestra la información del estudiante, incluyendo sus calificaciones por materia
    public void obtenerInformacion() {
        System.out.println("ID: " + super.ID);
        System.out.println("Nombre: " + super.nombre);
        System.out.println("Apellido: " + super.apellido);
        System.out.println("- Notas -");
        if (calificacionesPorCurso != null) {
            for (Map.Entry<Materia, Calificacion> entry : calificacionesPorCurso.entrySet()) {
                Materia materia = entry.getKey();
                Calificacion calificacion = entry.getValue();
                System.out.println("Materia: " + materia.getNombre());
                System.out.println("Promedio Exámenes: " + calificacion.getNotasExamenes());
                System.out.println("Trabajos Prácticos: " + calificacion.getNotasTP());
            }
        } else {
            System.out.println("No existen calificaciones");
        }
    }

    // Método para que no devuelva dirección en memoria
    @Override
    public String toString() {
        return getNombreCompleto();
    }
}