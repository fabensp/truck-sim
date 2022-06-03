import java.util.Random;

/**
 * Simulator
 *
 * @author Peter Fabens
 * @version 
 */
public class Simulator
{
    private static int c; // current config
    /**
     * Runs all the individual runthroughs of the simulation, according to proper seeds
     */
    public static void main(String[] args)
    {
        c = 1;
        while (c <= 4)
        {
            Logger.wipe();
            Logger.log(" , SIZE, TRAVELS, WAITS, CP, CD");
            Logger.log("");
            Logger.log("CFG " + c);
            for (int r_count = 1; r_count <= 10; r_count++)
            {
                Logger.log("");
                Logger.log("RUN " + r_count);
                ModelRunner.sim(c);
            }
            Logger.flush();
            c++; // look, it's the thing they named that language after!!!
            try {
                Thread.sleep(100);
            } catch (InterruptedException err) {
                System.out.println(err);
            }
        }
    }
    
    /**
     * Returns the current config
     */
    public static int get_cfg()
    {
        return c;
    }
}
