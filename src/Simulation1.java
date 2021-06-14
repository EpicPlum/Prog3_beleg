import main.GL.Automat;
import main.Simulation.addThread;
import main.Simulation.removeThread;

public class Simulation1
{
    public static void main(String[] args)
    {
        Automat auto = new Automat(5);
        addThread at = new addThread(auto, 1);
        removeThread rt = new removeThread(auto, 1);
        at.start();
        rt.start();

        //at.sleep(1000);
        //auto.listVerkaufsobjekte(2);


    }
}
