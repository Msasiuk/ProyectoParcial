//Clase Padre tendra características y métodos generales
public abstract class Persona {
    private static int ultimoID = 0;
    protected int ID;
    protected String nombre;
    protected String apellido;
    protected int dni;

    //Constructor de persona, debe tener obligatoriamente estos atributos
    //No se aceptan vacíos, nulos o valores de DNI menores al 1.000.000 y mayores al 99.999.999
    public Persona(String nombre, String apellido, int dni) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede ser nulo o vacío.");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("Apellido no puede ser nulo o vacío.");
        }
        if (dni <= 1000000 || dni > 99999999) {
            throw new IllegalArgumentException("DNI debe ser un número positivo entre 1.000.000 y 99.999.999.");
        }
        this.ID = ++ultimoID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }
    public int getID() {
        return ID;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public int getDni() {
        return dni;
    }
    public String getNombreCompleto(){
        return apellido + " " + nombre;
    }
    public abstract void obtenerInformacion();
}