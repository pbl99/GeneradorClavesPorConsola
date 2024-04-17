package servidor;

import java.io.IOException;

public class MainServidor {
    public static void main(String[] args) {
        //Creamos el objeto servidor de la clase Servidor
        Servidor servidor = null;

        //Controlamos la IOException instanciamos el objeto servidor y llamamos al método start de la clase servidor
        try {
            servidor = new Servidor();
            servidor.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
