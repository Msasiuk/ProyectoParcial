import java.util.ArrayList;

//Clase Objeto tendra características y métodos generales
public class Materia {
    private static int ultimoID = 0;
    private int ID;
    private String nombre;
    private ArrayList<Alumno> listaAlumnos = new ArrayList<>();
    private Profesor profesorAsignado;
    public Materia(String nombre) {
        this.ID = ++ultimoID;
        this.nombre = nombre;
    }
    public int getID() {
        return ID;
    }
    public ArrayList<Alumno> getListaEstudiantes() {
        return listaAlumnos;
    }
    public String getNombre() {
        return nombre;
    }
    public Profesor getProfesorAsignado() {
        return profesorAsignado;
    }
    public void setProfesorAsignado(Profesor profesorAsignado) {
        this.profesorAsignado = profesorAsignado;
    }

    // Método para agregar un alumno al curso si no está previamente inscrito.
    public void agregarEstudiante(Alumno alumno) {
        if (listaAlumnos.contains(alumno)) {
            System.out.println("Alumno ya inscrito en el curso.");
        } else {
            listaAlumnos.add(alumno);
        }
    }
}
