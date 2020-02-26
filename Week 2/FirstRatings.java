
/**
 * loadMovies and loadRaters methods add data from the related csv files into corresponding
 * ArrayLists.
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (February 14, 2020)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies (String filename) {
        ArrayList<Movie> movieData = new ArrayList<Movie> ();
        
        FileResource fr = new FileResource("data/" + filename + ".csv");
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord record: parser) {
            String currentID = record.get(0);
            String currentTitle = record.get(1);
            String currentYear = record.get(2);
            String currentCountry = record.get(3);
            String currentGenre = record.get(4);
            String currentDirector = record.get(5);
            int currentMinutes = Integer.parseInt(record.get(6));
            String currentPoster = record.get(7);
            
            Movie currentMovie = new Movie(currentID, currentTitle, currentYear, currentGenre, currentDirector, 
            currentCountry, currentPoster, currentMinutes);
            
            movieData.add(currentMovie);
        }
        
        return movieData;
    }
    
    public ArrayList<Rater> loadRaters (String filename) {
        ArrayList<Rater> ratersData = new ArrayList<Rater> ();
        ArrayList<String> listOfIDs = new ArrayList<String> ();
        
        FileResource fr = new FileResource("data/" + filename + ".csv");
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord record : parser) {
            String currentRaterID = record.get(0);
            String currentMovieID = record.get(1);
            double currentMovieRating = Double.parseDouble(record.get(2));
            
            if (! listOfIDs.contains(currentRaterID)) {
                Rater currentRater = new Rater(currentRaterID);
                ratersData.add(currentRater);
                currentRater.addRating(currentMovieID, currentMovieRating);
            
            } else {
                for (int k=0; k < ratersData.size(); k++) {
                    if (ratersData.get(k).getID().equals(currentRaterID)) {
                        ratersData.get(k).addRating(currentMovieID, currentMovieRating);
                    }
                }
            }
            
            listOfIDs.add(currentRaterID);
        }
        
        return ratersData;
    }
}
