import java.util.*;


/**
 * Implement this interface to allow your code to be integrated with our
 * web site.
 * 
 * When users first visit the recommender website, our code will call the
 * method <code>getItemsToRate()</code> to get a list of movies to display
 * on the web page for users to rate.
 * 
 * When a user submits their ratings, our code will call the method <code>
 * printRecommendationsFor</code> to get your recommendations based on the
 * user's ratings. The ID given to this method is for a new Rater that we 
 * have already added to the RaterDatabase with ratings for the movies 
 * returned by the first method.  Whatever is printed from that method will 
 * be displayed on the web page: HTML, plain text, or debugging information.
 * 
 */
public interface Recommender {
    /**
     * This method returns a list of movie IDs that will be used to look up 
     * the movies in the MovieDatabase and present them to users to rate. 
     *  
     * The movies returned in the list will be displayed on a web page, so
     * the number you choose may affect how long the page takes to load and
     * how willing users are to rate the movies.  For example, 10-20 should
     * be fine, 50 or more would be too many.
     * 
     * There are no restrictions on the method you use to generate this list
     * of movies: the most recent movies, movies from a specific genre, 
     * randomly chosen movies, or simply your favorite movies.
     * 
     * The ratings for these movies will make the profile for a new Rater 
     * that will be used to compare to for finding recommendations.
     */
    public ArrayList<String> getItemsToRate ();

    /**
     * This method returns nothing, but prints out an HTML table of the 
     * movies recommended for the given rater.
     * 
     * The HTML printed will be displayed on a web page, so the number you
     * choose to display may affect how long the page takes to load.  For 
     * example, you may want to limit the number printed to only the top 
     * 20-50 movies recommended or to movies not rater by the given rater.
     * 
     * You may also include CSS styling for your table using the &lt;style&gt;
     * tag before you print the table.  There are no restrictions on which 
     * movies you print, what order you print them in, or what information
     * you include about each movie. 
     * 
     * @param webRaterID the ID of a new Rater that has been already added to 
     *        the RaterDatabase with ratings for the movies returned by the 
     *        method getItemsToRate
     */
    public void printRecommendationsFor (String webRaterID);
}
