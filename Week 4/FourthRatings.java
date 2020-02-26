
/**
 * FourthRatings class contains various methods that can be used to get average ratings
 * (with/without filters) as well as additional helper methods:
 *
 * * getAverageRatings and getAverageRatingsByFilter are used to find movies that have required
 * * number of ratings, and in case with getAverageRatingsByFilter can be filtered using a range
 * * of filters provided in this program.
 * 
 * * getSimilarRatings and getSimilarRatingsByFilter work similarly to the methods mentioned
 * * above but additionally ratings are provided using weighted averages. A user will get more
 * * personalized recommendations because movies that were highly rated by only similar raters
 * * will be returned. numSimilarRaters method parameter can be changed in order to modify
 * * the precision of the provided recommendations.
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (February 20, 2020)
 */

import java.util.*;

public class FourthRatings {
    public FourthRatings () {
        // default constructor
        this("ratings.csv");
    }
    
    public FourthRatings (String ratingsfile) {
        RaterDatabase.initialize(ratingsfile);
    }
     
    private double getAverageByID (String id, int minimalRaters) {
        double sum = 0.0;
        int count = 0;
       
        for (Rater rater : RaterDatabase.getRaters()) {
            if (rater.hasRating(id)) {
                sum += rater.getRating(id);
                count += 1;
            }
        }
        
        if (count >= minimalRaters) {
            return sum / count;
        } else {
            return 0.0;
        }
    }
     
    public ArrayList<Rating> getAverageRatings (int minimalRaters) {
        ArrayList<Rating> averageRatings = new ArrayList<Rating> ();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for (String movieID : movies) {
            double average = Math.round(getAverageByID(movieID, minimalRaters) * 100.0) / 100.0;
            if (average != 0.0) {
                Rating rating = new Rating (movieID, average);
                averageRatings.add(rating);
            }
        }
        
        return averageRatings;
    }
     
    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> averageRatings = new ArrayList<Rating> ();
        ArrayList<String> filteredMovies = MovieDatabase.filterBy(filterCriteria);
         
        for (String movieID : filteredMovies) {
            double average = Math.round(getAverageByID(movieID, minimalRaters) * 100.0) / 100.0;
            if (average != 0.0) {
                Rating rating = new Rating (movieID, average);
                averageRatings.add(rating);
            }
        }
        
        return averageRatings;
    }
    
    private double dotProduct (Rater me, Rater r) {
        double dotProduct = 0.0;
        ArrayList<String> itemsRatedMe = me.getItemsRated();
        
        for (String item : itemsRatedMe) {
            if (r.getItemsRated().contains(item)) {
                double currRatingR = r.getRating(item);
                double currRatingMe = me.getRating(item);
                
                dotProduct += (currRatingR - 5.0) * (currRatingMe - 5.0);
            }
        }
        return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities (String id) {
        ArrayList<Rating> similarities = new ArrayList<Rating> ();
        Rater me = RaterDatabase.getRater(id);
        
        for (Rater currRater : RaterDatabase.getRaters()) {
            if (! currRater.getID().equals(id)) {
               double dotProduct = dotProduct(me, currRater);
               if (dotProduct >= 0) {
                   similarities.add(new Rating(currRater.getID(), dotProduct));
               }
            }
        }
        
        Collections.sort(similarities, Collections.reverseOrder());
        return similarities;
    }
    
    public ArrayList<Rating> getSimilarRatings (String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> weightedRatings = new ArrayList<Rating> ();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        HashMap<String,Double> accumulatedRating = new HashMap<String,Double> ();
        HashMap<String,Integer> accumulatedCount = new HashMap<String,Integer> ();
        
        for (String movieID : movies) {
            double currRating = 0.0;
            int currCount = 0;
            
            for (int k=0; k < numSimilarRaters; k++) {
                Rating r = similarRaters.get(k);
                String raterID = r.getItem();
                double weight = r.getValue();
                
                Rater rater = RaterDatabase.getRater(raterID);
                
                if (rater.hasRating(movieID)) {
                    double rating = rater.getRating(movieID) * weight;
                    currRating += rating;
                    currCount += 1;
                }
            }
            
            if (currCount >= minimalRaters) {
                accumulatedRating.put(movieID, currRating);
                accumulatedCount.put(movieID, currCount);
            }
        }
        
        for (String movieID : accumulatedRating.keySet()) {
            double weightedRating = Math.round((accumulatedRating.get(movieID) / accumulatedCount.get(movieID)) * 100.0) / 100.0;
            Rating rating = new Rating (movieID, weightedRating);
            weightedRatings.add(rating);
        }
        
        Collections.sort(weightedRatings, Collections.reverseOrder());
        return weightedRatings;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter 
    (String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> weightedRatings = new ArrayList<Rating> ();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<String> filteredMovies = MovieDatabase.filterBy(filterCriteria);
        
        HashMap<String,Double> accumulatedRating = new HashMap<String,Double> ();
        HashMap<String,Integer> accumulatedCount = new HashMap<String,Integer> ();
        
        for (String movieID : filteredMovies) {
            double currRating = 0.0;
            int currCount = 0;
            
            for (int k=0; k < numSimilarRaters; k++) {
                Rating r = similarRaters.get(k);
                String raterID = r.getItem();
                double weight = r.getValue();
                
                Rater rater = RaterDatabase.getRater(raterID);
                
                if (rater.hasRating(movieID)) {
                    double rating = rater.getRating(movieID) * weight;
                    currRating += rating;
                    currCount += 1;
                }
            }
            
            if (currCount >= minimalRaters) {
                accumulatedRating.put(movieID, currRating);
                accumulatedCount.put(movieID, currCount);
            }
        }
        
        for (String movieID : accumulatedRating.keySet()) {
            double weightedRating = Math.round((accumulatedRating.get(movieID) / accumulatedCount.get(movieID)) * 100.0) / 100.0;
            Rating rating = new Rating (movieID, weightedRating);
            weightedRatings.add(rating);
        }
        
        Collections.sort(weightedRatings, Collections.reverseOrder());
        return weightedRatings;
    }
}
