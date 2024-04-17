package servidor;

/*Definimos la clase RequisitosPass que contendrá y almacenará los requisitos que tendrá que enviar el cliente para poder generar
una contraseña nueva
 */

public class RequisitosPass {
    private int numMayusculas;
    private int numMinusculas;
    private int numDigitos;
    private int numCaractEspeciales;

    public int getNumMayusculas() {
        return numMayusculas;
    }

    public void setNumMayusculas(int numMayusculas) {
        this.numMayusculas = numMayusculas;
    }

    public int getNumMinusculas() {
        return numMinusculas;
    }

    public void setNumMinusculas(int numMinusculas) {
        this.numMinusculas = numMinusculas;
    }

    public int getNumDigitos() {
        return numDigitos;
    }

    public void setNumDigitos(int numDigitos) {
        this.numDigitos = numDigitos;
    }

    public int getNumCaractEspeciales() {
        return numCaractEspeciales;
    }

    public void setNumCaractEspeciales(int numCaractEspeciales) {
        this.numCaractEspeciales = numCaractEspeciales;
    }

    @Override
    public String toString() {
        return "RequisitosPass{" +
                "numMayusculas=" + numMayusculas +
                ", numMinusculas=" + numMinusculas +
                ", numDigitos=" + numDigitos +
                ", numCaractEspeciales=" + numCaractEspeciales +
                '}';
    }
}
