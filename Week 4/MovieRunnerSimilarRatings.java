
/**
 * MovieRunnerSimilarRatings class contains various tests for the methods from FourthRatings.
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (February 20, 2020)
 */

import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings () {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        int minNumOfRatings = 35; // variable
        ArrayList<Rating> averageRatings = fourthRatings.getAverageRatings(minNumOfRatings);
        System.out.println("There are " + averageRatings.size() + " movies with " +
        minNumOfRatings + " or more rating(s) :");
        
        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre () {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        int year = 1990; // variable
        YearAfterFilter yaf = new YearAfterFilter (year);
        
        String genre = "Drama"; // variable
        GenreFilter gf = new GenreFilter (genre);
        
        AllFilters af = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(gf);
        
        int minNumOfRatings = 8; // variable
        ArrayList<Rating> avgRatings = fourthRatings.getAverageRatingsByFilter(minNumOfRatings, af);
        System.out.println("There is(are) " + avgRatings.size() + " movie(s)  in genre of \""
        + genre + "\" that was(were) directed after " + year + " with " + minNumOfRatings 
        + " or more rating(s) :");
        
        Collections.sort(avgRatings);
        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem())
            + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Genre : " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printSimilarRatings () {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        String id = "71"; // variable
        int numSimilarRaters = 20; // variable
        int minimalRaters = 5; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(id, numSimilarRaters, minimalRaters);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " with " + minimalRaters
        + " or more rating(s). " + numSimilarRaters + " closest raters were considered.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre () {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        String genre = "Mystery"; // variable
        GenreFilter gf = new GenreFilter(genre);
         
        String id = "964"; // variable
        int numSimilarRaters = 20; // variable
        int minimalRaters = 5; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarRaters, minimalRaters, gf);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " and with " + minimalRaters
        + " or more rating(s), in \"" + genre + "\" genre. " + numSimilarRaters + " closest raters were considered.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Genre: " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector () {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"; // variable
        DirectorsFilter df = new DirectorsFilter(directors);
         
        String id = "120"; // variable
        int numSimilarRaters = 10; // variable
        int minimalRaters = 2; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarRaters, minimalRaters, df);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " and with " + minimalRaters
        + " or more rating(s), that was(were) directed by either of the following directors: "
        + directors + ". " + numSimilarRaters + " closest raters were considered.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Directed by : " + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes () {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        String genre = "Drama"; // variable
        GenreFilter gf = new GenreFilter (genre);
        
        int minMin = 80; // variable
        int maxMin = 160; // variable
        MinutesFilter mf = new MinutesFilter (minMin, maxMin);
        
        AllFilters af = new AllFilters();
        af.addFilter(gf);
        af.addFilter(mf);
        
        String id = "168"; // variable
        int numSimilarRaters = 10; // variable
        int minimalRaters = 3; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarRaters, minimalRaters, af);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " and with " + minimalRaters
        + " or more rating(s), in \"" + genre + "\" genre, that is(are) between " + minMin
        + " and " + maxMin + " minutes in length. " + numSimilarRaters + " closest raters were considered.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem())
            + " Time: " + MovieDatabase.getMinutes(rating.getItem()));
            System.out.println("Genre: " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes () {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        int year = 1975; // variable
        YearAfterFilter yaf = new YearAfterFilter (year);
        
        int minMin = 70; // variable
        int maxMin = 200; // variable
        MinutesFilter mf = new MinutesFilter (minMin, maxMin);
        
        AllFilters af = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(mf);
        
        String id = "314"; // variable
        int numSimilarRaters = 10; // variable
        int minimalRaters = 5; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarRaters, minimalRaters, af);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " and with " + minimalRaters
        + " or more rating(s), that is(are) between " + minMin + " and " + maxMin 
        + " minutes in length and released after year " + year + ". " + numSimilarRaters 
        + " closest raters were considered.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem())
            + " Year: " + MovieDatabase.getYear(rating.getItem()) + " Time: " 
            + MovieDatabase.getMinutes(rating.getItem()));
        }
    }
}
