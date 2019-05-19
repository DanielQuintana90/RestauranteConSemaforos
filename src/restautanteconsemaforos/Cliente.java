package restautanteconsemaforos;

public class Cliente extends Thread {

    private int id;
    private boolean haComido;
    private int velocidadParaComer;
    
    
    public Cliente(int id) {
        this.id = id;
        this.haComido = false;
        this.velocidadParaComer = ((int)(Math.random() * 10) + 1) * 1000;
    }

    public void run() {
        try {

            while (!this.haComido) {

                Principal.semMesas.acquire();

                if (!Principal.debeEsperar) {
                    comer();
                    sleep(velocidadParaComer);
                    retirar();
                    Principal.semMesas.release();
                } else {
                    hacerCola();
                }

            }

        } catch (Exception e) {
        }
    }

    void comer() {
        System.out.println("Cliente " + this.id + " ha obtenido mesa y ha comenzado a comer");
        Principal.comiendo++;
        this.haComido = true;

        if (Principal.esperando > 0) {
            Principal.esperando--;
        }

        if (Principal.comiendo == 5) {
            Principal.debeEsperar = true;
        }

    }

    void retirar() {
        System.out.println("Cliente " + this.id + " se retira");
        Principal.comiendo--;

        if (Principal.comiendo == 0 && Principal.esperando > 0) {
            Principal.debeEsperar = false;
        }

    }

    void hacerCola() {
        System.out.println("Cliente " + this.id + " está haciendo cola");
        Principal.esperando++;
    }

}
