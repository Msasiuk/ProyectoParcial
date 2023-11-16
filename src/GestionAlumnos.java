import javax.swing.*;
import java.util.ArrayList;
public class GestionAlumnos {

    // Lista de alumnos
    private static ArrayList<Alumno> listaAlumnos;

    public static ArrayList<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public static void verAlumnos() {
        if (listaAlumnos == null || listaAlumnos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados.");
        } else {
            StringBuilder alumnos = new StringBuilder("Alumnos:\n");
            for (Alumno alumno : listaAlumnos) {
                alumnos.append("Nombre: ").append(alumno.getNombre()).append("\n");
                alumnos.append("Apellido: ").append(alumno.getApellido()).append("\n");
                alumnos.append("DNI: ").append(alumno.getDni()).append("\n");
                alumnos.append("ID: ").append(alumno.getID()).append("\n");
                ArrayList<Materia> materiasInscritas = alumno.getListaMaterias();
                if (!materiasInscritas.isEmpty()) {
                    alumnos.append("Materias Inscritas:\n");
                    for (Materia materia : materiasInscritas) {
                        alumnos.append("- ").append(materia.getNombre()).append("\n");
                    }
                } else {
                    alumnos.append("No está inscrito en ninguna materia.\n");
                }
                alumnos.append("----------------------\n");
            }
            JOptionPane.showMessageDialog(null, alumnos.toString());
        }
    }

    // Metodo donde se asigna materia a alumno y viceversa
    public static void asignarAlumnoAMateria() {
        if (listaAlumnos == null || listaAlumnos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados para asignar a una materia.");
            return;
        }
        try {
        // Se solicita ID del alumno y la materia
        int idAlumno = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del alumno:"));
        int idMateria = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la materia:"));

        // Se verifica si el alumno y la materia existen para asignar o no asignar
        Alumno alumno = buscarAlumnoPorID(idAlumno);
        Materia materia = GestionMaterias.buscarMateriaPorID(idMateria);
        if (alumno != null && materia != null) {
            materia.agregarEstudiante(alumno);
            alumno.ingresarMaterias(materia);
            JOptionPane.showMessageDialog(null, "Alumno asignado correctamente a la materia.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el alumno y/o la materia con los IDs proporcionados.");
        }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido.");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.");
        }
    }

    // Método para crear alumnos validando que los datos no sean nulos o vacios y que si existe un DNI lo devuelva
    public static void crearAlumno() {
        if (listaAlumnos == null) {
            listaAlumnos = new ArrayList<>();
        }
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del nuevo alumno:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del nuevo alumno:");
        int dni = obtenerDNI();
        if (nombre == null || nombre.trim().isEmpty() || apellido == null || apellido.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos.");
            return;
        }
        if (existeAlumnoConDNI(dni)) {
            JOptionPane.showMessageDialog(null, "Error: El DNI ya está registrado para otro alumno.");
            return;
        }
        Alumno nuevoAlumno = new Alumno(nombre, apellido, dni);
        listaAlumnos.add(nuevoAlumno);
        JOptionPane.showMessageDialog(null, "Alumno creado: " + nombre + " " + apellido);
    }

    // Método para obtener y validar el DNI
    private static int obtenerDNI() {
        int dni;
        do {
            try {
                String dniStr = JOptionPane.showInputDialog("Ingrese el número de DNI del nuevo alumno:");
                if (dniStr == null) {
                    return 0;  // Manejar cancelación
                }
                dni = Integer.parseInt(dniStr);
                // Validar rango del DNI
                if (dni <= 1000000 || dni > 99999999) {
                    throw new IllegalArgumentException("DNI debe ser un número positivo entre 1.000.000 y 99.999.999.");
                }
                return dni;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "DNI debe ser un número.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } while (true);
    }

    // Método para verificar duplicados por DNI
    private static boolean existeAlumnoConDNI(int dni) {
        for (Alumno alumno : listaAlumnos) {
            if (alumno.getDni() == dni) {
                return true;
            }
        }
        return false;
    }

    // Método para buscar un alumno por su ID
    private static Alumno buscarAlumnoPorID(int id) {
        for (Alumno alumno : listaAlumnos) {
            if (alumno.getID() == id) {
                return alumno;
            }
        }
        return null;
    }
    public static void eliminarAlumno() {
        if (listaAlumnos == null || listaAlumnos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados para eliminar.");
            return;
        }
        int idAlumnoEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del alumno a eliminar:"));
        Alumno alumnoAEliminar = buscarAlumnoPorID(idAlumnoEliminar);
        if (alumnoAEliminar != null) {
            listaAlumnos.remove(alumnoAEliminar);
            JOptionPane.showMessageDialog(null, "Alumno eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún alumno con el ID proporcionado.");
        }
    }
}