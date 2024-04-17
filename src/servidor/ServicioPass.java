package servidor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*Esta clase tendra a RequisitosPass como atributo y parámetro para su creación y servirá para crear la lógica para generar la
contraseña y su longitud a través de los dos métodos abajo creados los cuales interactuarán con los paramétros que el cliente
haya pasado a través de los requisitos
 */
public class ServicioPass {

    /*Se sigue aquí y en el constructor de debajo la inyección de dependencias y ServicioPass requiere de RequisitosPass para poder
    crear los métodos generaPass y longitudPass
     */
    private RequisitosPass requisitosPass;

    public ServicioPass(RequisitosPass requisitosPass) {
        this.requisitosPass = requisitosPass;
    }

    //Este método nos generará la contraseña con la longitudPass
    public String generaPass() {
        // Variables con los caracteres , letras etc posibles a generar en la contraseña
        String abecedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        String digitos = "0123456789";
        String caracEspeciales = "!@#$%^&*()_-+=.:?";
        String password = "";

        // Crear un objeto Random para generar números aleatorios
        Random rand = new Random();

        // StringBuilder para construir la contraseña
        StringBuilder contrasena = new StringBuilder();

        // Seleccionar aleatoriamente letras del abecedario para formar la contraseña
        for (int i = 0; i < requisitosPass.getNumMayusculas(); i++) {
            // Generar un índice aleatorio dentro del rango del abecedario
            int index = rand.nextInt(abecedario.length());
            // Agregar la letra correspondiente al índice aleatorio a la contraseña
            contrasena.append(abecedario.charAt(index));
        }
        // Seleccionar letras minúsculas aleatoriamente para la contraseña
        for (int i = 0; i < requisitosPass.getNumMinusculas(); i++) {
            int index = rand.nextInt(abecedario.toLowerCase().length());
            contrasena.append(abecedario.toLowerCase().charAt(index));
        }
        // Seleccionar dígitos aleatorios para la contraseña
        for (int i = 0; i < requisitosPass.getNumDigitos(); i++) {
            int index = rand.nextInt(digitos.length());
            contrasena.append(digitos.charAt(index));
        }
        // Seleccionar caracteres especiales aleatorios para la contraseña
        for (int i = 0; i < requisitosPass.getNumCaractEspeciales(); i++) {
            int index = rand.nextInt(caracEspeciales.length());
            contrasena.append(caracEspeciales.charAt(index));
        }

        //Asignamos a la variable string el stringbuilder para poder tratarlo adecuadamente con las List de debajo
        password = contrasena.toString();

        // Convertir la cadena a una lista de caracteres
        List<Character> listaCaracteres = new ArrayList<>();
        for (char c : password.toCharArray()) {
            listaCaracteres.add(c);
        }

        //El método shuffle coge los elementos de una lista y los desordena
        Collections.shuffle(listaCaracteres);

        // Convertir la lista mezclada de nuevo a una cadena
        StringBuilder cadenaDesordenada = new StringBuilder(password.length());
        for (char c : listaCaracteres) {
            cadenaDesordenada.append(c);
        }

        //Devolvemos finalmente la cadena convertida a string
        return cadenaDesordenada.toString();
    }


    // Sacamos la longitud total que va a tener la contraseña
    public int longitudPass() {
        return requisitosPass.getNumMayusculas() + requisitosPass.getNumMinusculas()
                + requisitosPass.getNumDigitos() + requisitosPass.getNumCaractEspeciales();
    }
}
