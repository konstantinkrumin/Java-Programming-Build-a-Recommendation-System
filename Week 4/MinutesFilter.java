
/**
 * MinutesFilter can be used to extract movies with length in between minimum and maximum
 * specified minutes specified in the method parameter.
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (February 20, 2020)
 */

public class MinutesFilter implements Filter {
    private int minMin;
    private int maxMin;
    
    public MinutesFilter (int min, int max) {
        minMin = min;
        maxMin = max;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= minMin && MovieDatabase.getMinutes(id) <= maxMin;
    }
}
