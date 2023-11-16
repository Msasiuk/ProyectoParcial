import javax.swing.*;
import java.util.ArrayList;
public class GestionMaterias {

    // Lista de materias
    private static ArrayList<Materia> listaMaterias;

    // Método para ver materias, valida si existen en el array y muestra si tiene asignados profesores y alumnos
    public static void verMaterias() {
        if (listaMaterias == null || listaMaterias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay materias cargadas.");
        } else {
            StringBuilder materias = new StringBuilder("Materias:\n");
            for (Materia materia : listaMaterias) {
                materias.append("ID: ").append(materia.getID()).append("\n");
                materias.append("Nombre: ").append(materia.getNombre()).append("\n");
                Profesor profesorAsignado = materia.getProfesorAsignado();
                if (profesorAsignado != null) {
                    materias.append("Profesor Asignado: ").append(profesorAsignado.getNombreCompleto()).append("\n");
                } else {
                    materias.append("Profesor Asignado: No asignado\n");
                }
                ArrayList<Alumno> alumnosInscritos = materia.getListaEstudiantes();
                if (!alumnosInscritos.isEmpty()) {
                    materias.append("Alumnos Inscritos:\n");
                    for (Alumno alumno : alumnosInscritos) {
                        materias.append("- ").append(alumno.getNombreCompleto()).append("\n");
                    }
                } else {
                    materias.append("No hay alumnos inscritos.\n");
                }
                materias.append("----------------------\n");
            }
            JOptionPane.showMessageDialog(null, materias.toString());
        }
    }

    // Método para crear materias, si no existen anteriormente se inicializa y también valida que el nombre de la materia no exista
    public static void crearMateria() {
        if (listaMaterias == null) {
            listaMaterias = new ArrayList<>();
        }
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la nueva materia:");
        if (existeMateriaConNombre(nombre)) {
            JOptionPane.showMessageDialog(null, "Error: La materia ya existe.");
            return;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos.");
            return;
        }
        Materia nuevaMateria = new Materia(nombre);
        listaMaterias.add(nuevaMateria);
        JOptionPane.showMessageDialog(null, "Materia creada: " + nombre);
    }

    // Método para verificar si ya existe una materia con un nombre dado
    private static boolean existeMateriaConNombre(String nombre) {
        for (Materia materia : listaMaterias) {
            if (materia.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    // Método para eliminar una materia, verifica que existan materias, pide un ID e intenta borrarlo si existe
    public static void eliminarMateria() {
        if (listaMaterias == null || listaMaterias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay materias cargadas para eliminar.");
            return;
        }
        int idMateriaEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la materia a eliminar:"));
        Materia materiaAEliminar = buscarMateriaPorID(idMateriaEliminar);
        if (materiaAEliminar != null) {
            listaMaterias.remove(materiaAEliminar);
            if (materiaAEliminar.getProfesorAsignado() != null) {
                materiaAEliminar.getProfesorAsignado().setMateriaAsignado(null);
            }
            JOptionPane.showMessageDialog(null, "Materia eliminada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ninguna materia con el ID proporcionado.");
        }
    }

    // Método para buscar una materia por su ID
    public static Materia buscarMateriaPorID(int id) {
        for (Materia materia : listaMaterias) {
            if (materia.getID() == id) {
                return materia;
            }
        }
        return null;
    }
}