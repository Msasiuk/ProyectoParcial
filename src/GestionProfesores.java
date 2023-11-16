import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
public class GestionProfesores {

    // Lista de profesores
    private static List<Profesor> listaProfesores;
    public static void verProfesores() {
        if (listaProfesores == null || listaProfesores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay profesores registrados.");
        } else {
            StringBuilder profesores = new StringBuilder("Profesores:\n");
            for (Profesor profesor : listaProfesores) {
                profesores.append("Nombre: ").append(profesor.getNombre()).append("\n");
                profesores.append("Apellido: ").append(profesor.getApellido()).append("\n");
                profesores.append("DNI: ").append(profesor.getDni()).append("\n");
                profesores.append("ID: ").append(profesor.getID()).append("\n");
                if (profesor.getMateriaAsignado() != null) {
                    profesores.append("Materia Asignada: ").append(profesor.getMateriaAsignado().getNombre()).append("\n");
                } else {
                    profesores.append("Materia Asignada: Ninguna\n");
                }
                profesores.append("----------------------\n");
            }
            JOptionPane.showMessageDialog(null, profesores.toString());
        }
    }

    // Método para asignar materia a profesor y viceversa
    public static void asignarMateriaAProfesor() {
        if (listaProfesores == null || listaProfesores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay profesores registrados para asignar materias.");
            return;
        }
        try {
            int idMateria = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del curso:"));
            int idProfesor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del profesor:"));

            // Verificar si el materia y el profesor existen
            Materia materia = GestionMaterias.buscarMateriaPorID(idMateria);
            Profesor profesor = buscarProfesorPorID(idProfesor);
            if (materia != null && profesor != null) {
                materia.setProfesorAsignado(profesor);
                profesor.setMateriaAsignado(materia);
                JOptionPane.showMessageDialog(null, "Materia asignada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el materia y/o el profesor con los IDs proporcionados.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido.");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.");
        }
    }

    // Método para buscar un profesor por su ID
    private static Profesor buscarProfesorPorID(int id) {
        for (Profesor profesor : listaProfesores) {
            if (profesor.getID() == id) {
                return profesor;
            }
        }
        return null;
    }

    // Método para crear profesores, si el arreglo es nulo lo inicializa, luego pide nombre y valida DNI
    public static void crearProfesor() {
        if (listaProfesores == null) {
            listaProfesores = new ArrayList<>();
        }
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del nuevo profesor:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del nuevo profesor:");
        int dni = obtenerDNI();
        if (nombre == null || nombre.trim().isEmpty() || apellido == null || apellido.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos.");
            return;
        }
        if (existeProfesorConDNI(dni)) {
            JOptionPane.showMessageDialog(null, "Error: El DNI ya está registrado para otro profesor.");
            return;
        }
        Profesor nuevoProfesor = new Profesor(nombre, apellido, dni);
        listaProfesores.add(nuevoProfesor);
        JOptionPane.showMessageDialog(null, "Profesor creado: " + nombre + " " + apellido);
    }

    // Método para obtener DNI
        private static int obtenerDNI() {
        int dni;
        do {
            try {
                String dniStr = JOptionPane.showInputDialog("Ingrese el número de DNI del nuevo profesor:");
                if (dniStr == null) {
                    return 0;
                }
                dni = Integer.parseInt(dniStr);
                if (dni <= 1000000 || dni > 99999999) {
                    throw new IllegalArgumentException("DNI debe ser un número positivo.");
                }
                return dni;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "DNI debe ser un número.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } while (true);
    }

    // Método para validad si ya existe un profesor con ese DNI
    private static boolean existeProfesorConDNI(int dni) {
        for (Profesor profesor : listaProfesores) {
            if (profesor.getDni() == dni) {
                return true;
            }
        }
        return false;
    }

    // Método para eliminar un profesor, si tiene asignada una materia también lo elimina de ahí
    public static void eliminarProfesor() {
        if (listaProfesores == null || listaProfesores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay profesores registrados para eliminar.");
            return;
        }
        int idProfesorEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del profesor a eliminar:"));
        Profesor profesorAEliminar = buscarProfesorPorID(idProfesorEliminar);
        if (profesorAEliminar != null) {
            listaProfesores.remove(profesorAEliminar);
            if (profesorAEliminar.getMateriaAsignado() != null) {
                profesorAEliminar.getMateriaAsignado().setProfesorAsignado(null);
            }
            JOptionPane.showMessageDialog(null, "Profesor eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún profesor con el ID proporcionado.");
        }
    }
}