package cliente;

import java.io.IOException;

public class MainCliente {
    public static void main(String[] args) {
        //Creamos el objeto cliente de la clase Cliente
        Cliente cliente = null;
        //Controlamos la excepción IOException ,instanciamos cliente y llamamos al método interactua de la clase cliente
        try {
            cliente = new Cliente();
            cliente.interactua();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
