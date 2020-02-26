
/**
 * MovieRunnerWithFilters contains tests for ThirdRatings class.
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (February 19, 2020)
 */

import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings () {
        ThirdRatings thirdRatings = new ThirdRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        int minNumOfRatings = 35; // variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatings(minNumOfRatings);
        System.out.println("There are " + averageRatings.size() + " movies with " +
        minNumOfRatings + " or more rating(s) :");
        
        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfter () {
        ThirdRatings thirdRatings = new ThirdRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        int year = 2000; // variable
        YearAfterFilter yaf = new YearAfterFilter (year);
        
        int minNumOfRatings = 20; // variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, yaf);
        System.out.println("There are " + averageRatings.size() + " movies released after "
        + year + " with " + minNumOfRatings + " or more rating(s) : ");
        
        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem())
            + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre () {
        ThirdRatings thirdRatings = new ThirdRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        String genre = "Comedy"; // variable
        GenreFilter gf = new GenreFilter (genre);
        
        int minNumOfRatings = 20; // variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, gf);
        System.out.println("There are " + averageRatings.size() + " movies  in genre of \""
        + genre + "\" with " + minNumOfRatings + " or more rating(s) :");
        
        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Genre(s) : " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes () {
        ThirdRatings thirdRatings = new ThirdRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        int minMinutes = 105; // variable
        int maxMinutes = 135; // variable
        MinutesFilter mf = new MinutesFilter (minMinutes, maxMinutes);
        
        int minNumOfRatings = 5; // variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, mf);
        System.out.println("There are " + averageRatings.size() + " movies that are between " 
        + minMinutes + " and " + maxMinutes + " length with " + minNumOfRatings + " or more rating(s) :");
        
        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem())
            + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectors () {
        ThirdRatings thirdRatings = new ThirdRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        String directorsList = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"; // variable
        DirectorsFilter df = new DirectorsFilter (directorsList);
        
        int minNumOfRatings = 4; // variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, df);
        System.out.println("There are " + averageRatings.size() + " movies that were directed " 
        + "by either of those directors : " +  directorsList + ", with " + minNumOfRatings 
        + " or more rating(s) :");
        
        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Directed by : " + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre () {
        ThirdRatings thirdRatings = new ThirdRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        int year = 1990; // variable
        YearAfterFilter yaf = new YearAfterFilter (year);
        
        String genre = "Drama"; // variable
        GenreFilter gf = new GenreFilter (genre);
        
        AllFilters af = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(gf);
        
        int minNumOfRatings = 8; // variable
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, af);
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
    
    public void printAverageRatingsByDirectorsAndMinutes () {
        ThirdRatings thirdRatings = new ThirdRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + thirdRatings.getRaterSize() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        String directorsList = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"; // variable
        DirectorsFilter df = new DirectorsFilter (directorsList);
        
        int minMinutes = 90; // variable
        int maxMinutes = 180; // variable
        MinutesFilter mf = new MinutesFilter (minMinutes, maxMinutes);
        
        AllFilters af = new AllFilters();
        af.addFilter(df);
        af.addFilter(mf);
        
        int minNumOfRatings = 3; // variable
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatingsByFilter(minNumOfRatings, af);
        System.out.println("There is(are) " + avgRatings.size() + " movie(s) that were filmed by"
        + " either one of these directors : " + directorsList + "; and between " 
        + minMinutes + " and " + maxMinutes + " in length, with " + minNumOfRatings
        + " or more rating(s) :"); 
        
        Collections.sort(avgRatings);
        for (Rating rating : avgRatings) {
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem())
            + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Directed by : " + MovieDatabase.getDirector(rating.getItem()));
        }
    }
}
