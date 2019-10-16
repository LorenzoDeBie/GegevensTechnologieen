package dom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author tiwi
 */
public class Boom {
    
    private Boom[] kinderen;
    private int waarde;
    
    public Boom(int waarde, Boom... kinderen){
        this.waarde = waarde;
        this.kinderen = new Boom[kinderen.length];
        for(int i=0; i<kinderen.length; i++){
            this.kinderen[i] = kinderen[i];
        }
    }
    
    @Override
    public String toString(){
        return toStringRec(this,0);
    }
    
    private String toStringRec(Boom huidigeBoom,int niveau){
        String resultaat = "\n";
        for(int i=0; i<niveau; i++){
            resultaat += ".......";
        }
        resultaat += huidigeBoom.waarde;
        
        for(int i=0; i<huidigeBoom.kinderen.length; i++){
            resultaat += toStringRec(huidigeBoom.kinderen[i],niveau+1);
        }
        
        return resultaat;
    }    
    
    public void schrijfNaarBestand(String bestandsnaam) throws IOException {
        System.out.println("\nHet resultaat is te vinden in het bestand "+bestandsnaam+"\n");
        FileWriter fileWriter = new FileWriter(bestandsnaam, false);  //true = append
        BufferedWriter buffer = new BufferedWriter(fileWriter);
        buffer.write(this.toString());
        buffer.close();
    }
}
