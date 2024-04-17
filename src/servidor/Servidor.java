package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    /*Especificamos el puerto "4321" y creamos las instancias necesarias puerto el serversocket para el server y el socket y
    el objeto requisitoPass que vamos a necesitar para trabajar con los requisitos
     */
    private final int PUERTO = 4321;
    private ServerSocket serverSocket;
    private Socket socket;
    private RequisitosPass requisitosPass;

    //Definimos el constructor al que pasaremos el serverSocket y el socket
    public Servidor() throws IOException {
        serverSocket = new ServerSocket(PUERTO); //Definimos la conexión
        socket = new Socket(); //Iniciamos el cliente
    }

    //Aqui iniciamos el procedimiento start que iniciará el servidor
    public void start() throws IOException {
        requisitosPass = new RequisitosPass();

        //Mensajes iniciales al iniciar el servidor
        System.out.println("Servidor arrancado");
        System.out.println("Esperando al cliente...");

        //Estamos a la escucha y cuando recibe una conexión acepta
        socket = serverSocket.accept();

        System.out.println("Cliente conectado desde " + socket.getInetAddress());
        //Objeto entrada creado para poder abrir flujos de datos entre servidor y cliente en este caso el de entrada para recibir datos
        DataInputStream entrada = new DataInputStream(socket.getInputStream());

        //Declaramos la variable nom_client para poder saber su nombre
        String nom_client = entrada.readUTF();
        //Sacamos por pantalla el nombre del cliente
        System.out.println("Nombre del cliente: " + nom_client);

        //Guardamos en el objeto requisitosPass por orden de entrada los requisitos que el cliente ha ido introduciendo
        requisitosPass.setNumMinusculas((entrada.readInt()));
        requisitosPass.setNumMayusculas(entrada.readInt());
        requisitosPass.setNumDigitos(entrada.readInt());
        requisitosPass.setNumCaractEspeciales(entrada.readInt());

        System.out.println("Los requisitos del cliente son los siguientes");

        //Sacamos por pantalla los requisitos del cliente
        System.out.println("PasswordReqs{" + "minusculas=" + requisitosPass.getNumMinusculas() + ", " + "mayusculas=" + requisitosPass.getNumMayusculas() + ", "
                + "digitos=" + requisitosPass.getNumDigitos() + ", " + "caracteresEspeciales=" + requisitosPass.getNumCaractEspeciales() + "}");

        System.out.println("Se ha enviado la longitud de la contraseña al cliente");

        //Cogemos la respuesta del cliente a la pregunta de si quiere generar una contraseña
        String respuestaCliente = entrada.readUTF();
        //En funcion de si ha dicho que si o que no entrará en el if o en el else if
        if (respuestaCliente.equalsIgnoreCase("SI")) {
            System.out.println("Se ha enviado la contraseña al cliente");
        } else if (respuestaCliente.equalsIgnoreCase("NO")) {
            System.out.println("El cliente no desea generar una contraseña");
        }

        //Cerramos todas las conexiones abiertas
        entrada.close();
        socket.close();
        serverSocket.close();
    }
}
