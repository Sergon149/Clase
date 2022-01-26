import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static AtomicInteger puestos = new AtomicInteger(1);
    static AtomicInteger permit = new AtomicInteger(1);
    static Semaphore Per = new Semaphore(0,true);

    public static void main(String[] args) {
        Semaphore s1 = Main.Per;

        for (int i=1;i<=30;i++){
            Alumno Alu = new Alumno(i, s1);
            Alu.start();
        }





    }
    public synchronized static void puestos(Alumno alumno){
        int puesto=Main.puestos.getAndIncrement();
        System.out.println("El alumno "+alumno.id+" ha llegado en el puesto "+puesto);
        if (puesto<=5){
            System.out.println("El alumno "+alumno.id+" se lleva positivo y tiene que hacer cuarentena");
        }else if (puesto>5 && puesto <=25){
            System.out.println("El alumno "+alumno.id+" no recibe nada");
        }else{
            System.out.println("El alumno "+alumno.id+" se lleva un negativo porque es un bandido");
        }
    }

    public synchronized static void permitir() {
        Main.permit.set(Main.permit.get()+1);
        if (Main.permit.get() == 15){
            Main.Per.release(15);
            Main.profe();
        }

    }

    public static void profe(){
        Profesor Pro = new Profesor();
        Pro.start();
        System.out.println("Comienza la clase");
    }
}
