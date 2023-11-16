import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        /** HABILITAR CUANDO ESTE FINALIZADO EL PROYECTO
        //Crear instancia de Administrador para autenticación
        Administrador admin = new Administrador();
        int contador = 3;
        do{
        // Autenticación del administrador
            String usuario = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
            String contraseña = JOptionPane.showInputDialog("Ingrese la contraseña:");

            if (admin.autenticar(usuario, contraseña)) {
                // Crear instancia de la clase que maneja el menú y llamar a su método principal
                ManejadorMenu manejadorMenu = new ManejadorMenu();
                manejadorMenu.mostrarMenu();
                contador = 0;
            } else {
                contador--;
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Le quedan " + contador + " intentos.");
            }
        }while (contador > 0);
        */
        ManejadorMenu manejadorMenu = new ManejadorMenu();
        manejadorMenu.mostrarMenu();
    }
}