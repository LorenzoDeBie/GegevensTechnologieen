package dom;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiwi
 */
public class DOMMain {
    
    public static void main(String[] args) { 
        
        // haal onderstaande code uit commentaar
        try {
            DOMBoom inlezer = new DOMBoom("C:\\Users\\Lorenzo De Bie\\OneDrive\\Universiteit Gent\\3e BACH\\Gegevenstechnologiën\\Labo\\Labo_2\\2-DOM en transform API\\DOM_boom_uitschrijven\\stratenInGentKlein.xml");
            inlezer.schrijfNaarBestand("C:\\Users\\Lorenzo De Bie\\OneDrive\\Universiteit Gent\\3e BACH\\Gegevenstechnologiën\\Labo\\Labo_2\\2-DOM en transform API\\DOM_boom_uitschrijven\\stratenDOMBoom.txt");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
