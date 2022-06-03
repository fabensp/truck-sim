import java.io.*;
import java.util.Scanner;

/**
 * Configurator
 *
 * @author Peter Fabens
 * @version 
 */
public class Configurator
{
    public static void config(String filename)
    {
        File f = new File (filename);
        try {
            Scanner s = new Scanner (f);
            while(s.hasNextLine())
            {
                String l = s.nextLine();
                Scanner line = new Scanner (l);
                String key = line.next();
                switch (key)
                {
                    case "Model":   ModelRunner.config(line.nextInt(), line.nextInt());
                                    break;
                                    
                    case "Truck":   Truck.config(line.nextInt());
                                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
