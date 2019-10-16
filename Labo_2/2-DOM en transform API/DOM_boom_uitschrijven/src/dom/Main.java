package dom;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiwi
 */
public class Main {

    public static void main(String[] args) {

        try {
            Boom boom = new Boom(10, new Boom(20, new Boom(40), new Boom(50), new Boom(60)), new Boom(30, new Boom(70, new Boom(80), new Boom(90))));
            System.out.println(boom);                         // in outputvenster
            boom.schrijfNaarBestand("gewoneBoom.txt");        // naar bestand (er komt een melding in het outputvenster waar de gebruiker moet gaan zoeken)
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }        

    }

}
