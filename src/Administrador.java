public class Administrador {
    private final String usuario = "ADMIN";
    private final String contrasenia = "ADMIN1234";

    public boolean autenticar(String usuario, String contraseña) {
        return this.usuario.equals(usuario) && this.contrasenia.equals(contraseña);
    }
}
