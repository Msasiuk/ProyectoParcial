import javax.swing.*;
import java.util.ArrayList;

// Clas que manejará el Menu del proyecto
public class ManejadorMenu {
    public void mostrarMenu() {
        while (true) {
            String[] opciones = {"Gestión Materias", "Gestión Profesores", "Gestión Alumnos", "Salir"};
            int seleccion = mostrarOpciones("Seleccione una opción:", "Menú Principal", opciones);
            switch (seleccion) {
                case 0: // Gestión Materias
                    gestionMaterias();
                    break;
                case 1: // Gestión Profesores
                    gestionProfesores();
                    break;
                case 2: // Gestión Alumnos
                    gestionAlumnos();
                    break;
                case 3: // Salir
                    JOptionPane.showMessageDialog(null, "Saliendo del programa.");
                    System.exit(0);
            }
        }
    }

    private void gestionMaterias() {
        int contador = 1;
        do {
            String[] opciones = {"Ver Materias", "Crear Nueva Materia", "Eliminar Materia", "Salir"};
            int seleccion = mostrarOpciones("Seleccione una opción:", "Gestión Materias", opciones);
            switch (seleccion) {
                case 0: // Ver Materias
                    GestionMaterias.verMaterias();
                    break;
                case 1: // Crear Nueva Materia
                    GestionMaterias.crearMateria();
                    break;
                case 2: // Eliminar Materia
                    GestionMaterias.eliminarMateria();
                    break;
                case 3: // Salir
                    contador = 0;
                    break;
            }
        } while (contador != 0);
    }

    private void gestionProfesores() {
        int contador = 1;
        do {
            String[] opciones = {"Ver Profesores", "Crear nuevo Profesor", "Asignar Curso a Profesor", "Eliminar Profesor", "Salir"};
            int seleccion = mostrarOpciones("Seleccione una opción:", "Gestión Profesores", opciones);
            switch (seleccion) {
                case 0: // Ver Profesores
                    GestionProfesores.verProfesores();
                    break;
                case 1: // Crear Nuevo Profesor
                    GestionProfesores.crearProfesor();
                    break;
                case 2: // Asignar Curso a Profesor
                    GestionProfesores.asignarMateriaAProfesor();
                    break;
                case 3: // Eliminar Profesor
                    GestionProfesores.eliminarProfesor();
                    break;
                case 4: // Salir
                    contador = 0;
                    break;
            }
        } while (contador != 0);
    }

    private void gestionAlumnos() {
        int contador = 1;
        do {
            String[] opciones = {"Ver Alumnos", "Crear nuevo Alumno", "Asignar Materia a Alumno", "Eliminar Alumno", "Gestion Calificaciones", "Salir"};
            int seleccion = mostrarOpciones("Seleccione una opción:", "Gestión Alumno", opciones);
            switch (seleccion) {
                case 0: // Ver Alumno
                    GestionAlumnos.verAlumnos();
                    break;
                case 1: // Crear Nuevo Alumno
                    GestionAlumnos.crearAlumno();
                    break;
                case 2: // Asignar Curso a Alumno
                    GestionAlumnos.asignarAlumnoAMateria();
                    break;
                case 3: // Eliminar Alumno
                    GestionAlumnos.eliminarAlumno();
                    break;
                case 4: // Gestion Calificaciones
                    gestionCalificaciones();
                    break;
                case 5: // Salir
                    contador = 0;
                    break;
            }
        } while (contador != 0);
    }

    private void gestionCalificaciones(Alumno alumno, Materia materia) {
        int contador = 1;
        do {
            String[] opciones = {"Agregar Nota Examen", "Agregar Nota Trabajo Práctico", "Eliminar Notas", "Ver Notas", "Salir"};
            int seleccion = mostrarOpciones("Seleccione una opción:", "Gestión de Calificaciones", opciones);
            switch (seleccion) {
                case 0: // Agregar Nota Examen
                    GestionCalificaciones.agregarNotaExamen(alumno, materia);
                    break;
                case 1: // Agregar Nota Trabajo Práctico
                    GestionCalificaciones.agregarNotaTP(alumno, materia);
                    break;
                case 2: // Eliminar Notas
                    GestionCalificaciones.eliminarNotas(alumno, materia);
                    break;
                case 3: // Ver Notas
                    GestionCalificaciones.verNotas(alumno, materia);
                    break;
                case 4: // Salir
                    contador = 0;
                    break;
            }
        } while (contador != 0);
    }

    private void gestionCalificaciones() {
        Alumno alumnoSeleccionado = seleccionarAlumno();
        if (alumnoSeleccionado != null && !alumnoSeleccionado.getListaMaterias().isEmpty()) {
            Materia materiaSeleccionada = seleccionarMateria(alumnoSeleccionado.getListaMaterias());
            gestionCalificaciones(alumnoSeleccionado, materiaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(null, "El alumno no tiene materias asignadas.");
        }
    }
    private int mostrarOpciones(String mensaje, String titulo, String[] opciones) {
        return JOptionPane.showOptionDialog(null, mensaje, titulo, JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
    }
    private Alumno seleccionarAlumno() {
        if (GestionAlumnos.getListaAlumnos() == null || GestionAlumnos.getListaAlumnos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados.");
            return null;
        }
        String[] opcionesAlumnos = new String[GestionAlumnos.getListaAlumnos().size()];
        for (int i = 0; i < GestionAlumnos.getListaAlumnos().size(); i++) {
            opcionesAlumnos[i] = GestionAlumnos.getListaAlumnos().get(i).toString();
        }
        int seleccionAlumno = JOptionPane.showOptionDialog(
                null,
                "Seleccione un alumno:",
                "Selección de Alumno",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcionesAlumnos,
                opcionesAlumnos[0]
        );
        return (seleccionAlumno >= 0 && seleccionAlumno < GestionAlumnos.getListaAlumnos().size())
                ? GestionAlumnos.getListaAlumnos().get(seleccionAlumno)
                : null;
    }
    private Materia seleccionarMateria(ArrayList<Materia> materias) {
        if (materias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El alumno no tiene materias asignadas.");
            return null;
        }

        String[] opcionesMaterias = new String[materias.size()];
        for (int i = 0; i < materias.size(); i++) {
            opcionesMaterias[i] = materias.get(i).getNombre();
        }

        int seleccionMateria = JOptionPane.showOptionDialog(
                null,
                "Seleccione una materia:",
                "Selección de Materia",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcionesMaterias,
                opcionesMaterias[0]
        );

        return (seleccionMateria >= 0 && seleccionMateria < materias.size())
                ? materias.get(seleccionMateria)
                : null;
    }
}