
/**
 * getItemsToRate method is used to provide users with 20 randomly selected MovieDatabase entries.
 * The following information is used to compare user's ratings with ratings provided by other
 * users and to give the personalized recommendations.
 * 
 * printRecommendationsFor method is used to print a table with recommended movies based on
 * user's ratings.
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (February 24, 2020)
 */

import java.util.*;

public class RecommendationRunner implements Recommender {
    
    public ArrayList<String> getItemsToRate () {
        ArrayList<String> itemsToRate = new ArrayList<String> ();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
       
        for (int i=0; i < 20; i++) {
            Random rand = new Random();
            int random = rand.nextInt(movies.size());
            if (! itemsToRate.contains(movies.get(random))) {
                itemsToRate.add(movies.get(random));
            }
        }
        
        return itemsToRate;
    }
    
    public void printRecommendationsFor (String webRaterID) {
        FourthRatings fourthRatings = new FourthRatings ();
        MovieDatabase.initialize("ratedmoviesfull");
        RaterDatabase.initialize("ratings");
        
        System.out.println("<p>Read data for " + Integer.toString(RaterDatabase.size()) + " raters</p>");
        System.out.println("<p>Read data for " + Integer.toString(MovieDatabase.size()) + " movies</p>");
        
        int numSimilarRaters = 50; // variable
        int minNumOfRatings = 5; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(webRaterID, numSimilarRaters, minNumOfRatings);
        
        if (similarRatings.size() == 0) {
            System.out.println("No matching movies were found");
        } else {
            String header = ("<table> <tr> <th>Movie Title</th> <th>Rating Value</th>  <th>Genres</th> </tr>");
            String body = "";
            for (Rating rating : similarRatings) {
                body += "<tr> <td>" + MovieDatabase.getTitle(rating.getItem()) + "</td> <td>" 
                + Double.toString(rating.getValue()) + "</td> <td>" + MovieDatabase.getGenres(rating.getItem())
                + "</td> </tr> ";
            }
            System.out.println(header  + body + "</table>");
        }
    }
}
