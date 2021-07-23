import main.CLI.altConsole;
import main.CLI.eventsImpl.ConsoleEventHandler;
import main.CLI.eventsImpl.ConsoleEventListener_Imp;
import main.CLI.observerPatternImpl.CapacityObserver;

/*
main und test-Klasse
 */
public class altConsoleExe
{
    public static void main(String[] args)
    {

        altConsole con = new altConsole();
        ConsoleEventHandler handler = new ConsoleEventHandler();
        ConsoleEventListener_Imp listener = new ConsoleEventListener_Imp("listener");
        handler.add(listener);
        con.setConsoleHandler(handler);
        CapacityObserver capacity = new CapacityObserver(con, "capacity");
        con.start();

    }
}
