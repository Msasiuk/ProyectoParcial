//Clase Objeto tendra características y métodos generales
public class Profesor extends Persona{
    // Mismos atributos que su clase padre, además tiene un curso Asignado
    private Materia materiaAsignado = null;

    // Existen dos constructores porque se pueden ingresar profesores aunque no tengan curso asignado todavia
    public Profesor(String nombre, String apellido, int dni) {
        super(nombre, apellido, dni);
    }
    public Materia getMateriaAsignado() {
        return materiaAsignado;
    }
    public void setMateriaAsignado(Materia nuevoMateria) {
        validarMateria(nuevoMateria);
        if (materiaAsignado != null) {
            System.out.println("El profesor tenía previamente asignado el curso: " + materiaAsignado.getNombre());
        }
        this.materiaAsignado = nuevoMateria;
        nuevoMateria.setProfesorAsignado(this);
    }

    // Método que valida la Materia antes de ingresarla por el método constructor
    private void validarMateria(Materia materia) {
        if (materia == null) {
            throw new IllegalArgumentException("El materia no puede ser nulo.");
        }
    }

    //Método que devuelve la información del curso
    @Override
    public void obtenerInformacion() {
        System.out.println("ID: " + ID);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        if (materiaAsignado != null) {
            System.out.println("Cursos Asignados:" + materiaAsignado.getNombre());
        } else {
            System.out.println("No tiene curso asignado.");
        }
    }
}