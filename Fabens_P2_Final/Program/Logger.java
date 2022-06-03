import java.io.*;
import java.util.Scanner;

/**
 * Logger
 *
 * @author Peter Fabens
 * @version 
 */
public class Logger
{
    private static String data = "";
    
    /**
     * Print info to the data file without overwriting the data that was there previously. 
     * This works pretty inefficiently by copying over everything that was there for every addition,
     * but it's so that the info isn't lost if the file crashes before a flush.
     * 
     * @param l text to insert at end of file
     */
    public static void debug(String l)
    {
        try{
            File f = new File("debug" + Simulator.get_cfg() + ".txt");
            if (f.exists())
            {   
                Scanner s = new Scanner(f);
                PrintStream p_file = new PrintStream(f);
                while (s.hasNextLine())
                {
                    p_file.println(s.nextLine());
                }
                p_file.print(l);
                p_file.close();
                s.close();
            } else {
                PrintStream p_file = new PrintStream(f);
                p_file.print(l);
                p_file.close();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Add a line to the data output buffer
     */
    public static void log(String l)
    {
        data += l + "\n";
    }
    
    /**
     * Flush the data string to a file.
     * 
     */
    public static void flush()
    {
        try{
            File f = new File("data" + Simulator.get_cfg() + ".csv");
            PrintStream p_file = new PrintStream(f);
            p_file.print(data);
            p_file.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Start off with a clean slate! Deletes the debug and data files so that new ones can be created
     */
    public static void wipe()
    {
        data = "";
        try{
            (new File("debug" + Simulator.get_cfg() + ".txt")).delete();
        } catch (Exception e)
        {
            System.out.println(e);
        }
        try{
            (new File("data" + Simulator.get_cfg() + ".csv")).delete();
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
