package consoleapplicatie;

import data.jdbc.JDBCDataStorageRowSets;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class TestProgramma7 {
    public static void main(String[] args) {
        try {
            JDBCDataStorageRowSets database = new JDBCDataStorageRowSets();

            System.out.println("aantal offices: "+database.countOffices());
            //database.addOffice("a","b","c","d","e","f","g","h");
            //database.addOffice("A","B","C","D","E","F","G","H");

            //database.wijzigOfficeDate(1,new Date());
            // Controleer zelf of 1999 in de datum van vandaag veranderd werd
            // Pas het hoofdprogramma zo aan dat niet de huidige dag, maar 31 januari 2000 gebruik wordt.
            // Werd dit juist aangepast in de databank?
            // Vind je "2000-01-31" terug of toch een andere datum??

            database.changeEmailAddresses(); // laat dit weg als je de bonusvraag niet oplost.

            database.bewaarAlles();
            System.out.println("NA TOEVOEGEN, aantal offices: "+database.countOffices());

            Map<String, Set<String>> mailsPerCity = database.mailsPerCity(); // laat dit weg als je de extra vraag niet oplost
            System.out.println(mailsPerCity);                               //


        } catch (Exception ex) {
            System.out.println("HOOFDPROGR:"+ex.getMessage());
        }

    }
}
