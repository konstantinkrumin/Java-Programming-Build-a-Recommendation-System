
/**
 * Rater is an interface for PlainRater and EfficientRater classes.
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (February 19, 2020)
 */

import java.util.*;

public interface Rater {
    public void addRating(String item, double rating);

    public boolean hasRating(String item);

    public String getID();

    public double getRating(String item);

    public int numRatings();

    public ArrayList<String> getItemsRated();
}
