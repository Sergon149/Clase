import java.util.Random;
import java.util.concurrent.Semaphore;

class Alumno extends Thread {

    int id;
    Semaphore s1;

    public Alumno(int id, Semaphore s1) {
        this.id = id;
        this.s1 = s1;
    }

    @Override
    public void run(){
        try{
            Thread.sleep((new Random().nextInt(5)) * 1000);
            Main.puestos(this);

            Main.permitir();
            s1.acquire();
            System.out.println("El Alumno "+this.id+" ha llegado de los primeros 15");

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
