import java.util.ArrayList;

//Clase Objeto tendra características y métodos generales
public class Calificacion {
    private char tipo;
    private int nota;
    private final int NOTA_APROBACION_TP = 70;
    private Materia materiaCalif;
    private ArrayList<Integer> notasExamenes = new ArrayList<>();
    private ArrayList<Integer> notasTP =  new ArrayList<>();
    public Calificacion(){
    }

    public ArrayList<Integer> getNotasExamenes() {
        return notasExamenes;
    }

    public ArrayList<Integer> getNotasTP() {
        return notasTP;
    }

    public void setNotasExamenes(Integer nota) {
        this.notasExamenes.add(nota);
    }

    public void setNotasTP(Integer nota) {
        this.notasTP.add(nota);
    }

    public void setMateriaCalif(Materia materiaCalif) {
        this.materiaCalif = materiaCalif;
    }

    // Metodo que evaluar los TP del alumno para ver si hay, si estan aprobados o existe alguno desaprobado.
    public String evaluarTrabajosPracticos() {
        if (!notasTP.isEmpty()) {
            for (Integer nota : notasTP) {
                if (nota < this.NOTA_APROBACION_TP) {
                    return "Al menos uno desaprobado";
                }
                return "Aprobados";
            }
        }
        return "No tiene tp para evaluar";
    }

    // Método para calcular el promedio de las nota Exámenes
    public int calcularPromedioExamenes() {
        if (!notasExamenes.isEmpty()) {
            int sum = 0;
            for (Integer nota : notasExamenes) {
                sum += nota;
            }
            return sum / notasExamenes.size();
        } else {
            return 0;
        }
    }
}
