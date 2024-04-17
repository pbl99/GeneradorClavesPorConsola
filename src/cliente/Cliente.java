package cliente;

import servidor.RequisitosPass;
import servidor.ServicioPass;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cliente {
    private RequisitosPass requisitosPass;
    private ServicioPass servicioPass;

    //Añadimos el host "localhost"
    private final String HOST = "localhost";
    //Añadimos el puerto 4321
    private final int PUERTO = 4321;
    private Socket socket;

    //Creamos una instancia de la clase Scanner para poder trabajar y escribir por consola
    Scanner sc = new Scanner(System.in);

    public Cliente() throws IOException {
        socket = new Socket(HOST, PUERTO);
    }

    /*Creamos el método interactua que contendrá toda la lógica para la parte del cliente la cual interactuara con el servidor y
    con las clases RequisitosPass y ServicioPass etc..
     */
    public void interactua() throws IOException {
        requisitosPass = new RequisitosPass();
        servicioPass = new ServicioPass(requisitosPass);

        DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
        System.out.println("Hola, soy un servidor. ¿Cómo te llamas?");
        //Ponemos el nombre que queramos por consola
        String nombre = sc.nextLine();
        //Nos saldra el mensaje por pantalla desde el servidor
        salida.writeUTF(nombre);
        System.out.println("Te doy la bienvenida, " + nombre + "\nVoy a solicitarte distintos requisitos para la contraseña que voy a generar");
        //Aqui escribimos el numero de tareas y nos metera en un bucle
        boolean exit = false;
        bucleDo:
        do {
            try {
                System.out.println("¿Cuántas minúsculas debe tener la contraseña?");
                int numMinusculas = sc.nextInt();

                System.out.println("¿Cuántas mayúsculas debe tener la contraseña?");
                int numMayusculas = sc.nextInt();

                System.out.println("¿Cuántos dígitos debe tener la contraseña?");
                int numDigitos = sc.nextInt();

                System.out.println("¿Cuántos caracteres especiales debe tener la contraseña?");
                int numCaractEspeciales = sc.nextInt();

                if (numMinusculas < 0 || numMayusculas < 0 || numDigitos < 0 || numCaractEspeciales < 0) {
                    System.out.println("Has introducido algún número negativo");
                    System.out.println("Empecemos de cero");
                    continue bucleDo;
                }

                // Configura los requisitos de la contraseña
                requisitosPass.setNumMinusculas(numMinusculas);
                requisitosPass.setNumMayusculas(numMayusculas);
                requisitosPass.setNumDigitos(numDigitos);
                requisitosPass.setNumCaractEspeciales(numCaractEspeciales);

                // Envía los requisitos de la contraseña al otro extremo de la conexión
                salida.writeInt(numMinusculas);
                salida.writeInt(numMayusculas);
                salida.writeInt(numDigitos);
                salida.writeInt(numCaractEspeciales);

                exit = true;
            } catch (InputMismatchException ex) {
                System.out.println("No has introducido un número válido.");
                System.out.println("Empecemos de cero");
                sc.next(); // Limpia el búfer de entrada
            } catch (IOException ex) {
                System.out.println("Error de E/S al enviar datos al servidor.");
                ex.printStackTrace();
                // También podrías establecer exit a true aquí si quieres salir del bucle
            }
        } while (!exit);
        //Usamos el método longitudPass para pasarla por pantalla
        System.out.println("La longitud de la contraseña que se va a generar es de " + servicioPass.longitudPass() + " caracteres.");
        System.out.println("¿Quieres generar una contraseña ahora? [Si/No]");

        //Controlamos con scanner el "si" o el "no" de si quiere generar una contraseña
        String opcionPassword = sc.next();
        //escribimos la variable en la salida para poder utilizarla en el servidor
        salida.writeUTF(opcionPassword);
        //En función de si es si llamaremos al método generaPass y si es no no haremos nada enviaremos un mensaje
        if (opcionPassword.equalsIgnoreCase("SI")) {
            System.out.println("La contraseña generada es: " + servicioPass.generaPass());

        } else if (opcionPassword.equalsIgnoreCase("NO")) {
            System.out.println("No se generará ninguna contraseña. Hasta la próxima.");
        }

        //Cerramos todas las conexiones abiertas
        salida.close();
        socket.close();
    }
}
