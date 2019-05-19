package restautanteconsemaforos;

import java.util.concurrent.Semaphore;

public class Principal {

    //Variables sugeridas
    static boolean debeEsperar;
    static int esperando;
    static int comiendo;
    static int numeroDeMesas;

    static int numeroDeClientes;
    static Semaphore semMesas;

    public static void main(String[] args) {

        debeEsperar = false;
        esperando = 0;
        comiendo = 0;
        numeroDeMesas = 5;
        numeroDeClientes = 13;
        semMesas = new Semaphore(numeroDeMesas);
        
        for (int i = 0; i < numeroDeClientes; i++) {
            Cliente cliente = new Cliente(i + 1);
            cliente.start();
        }

    }

}
