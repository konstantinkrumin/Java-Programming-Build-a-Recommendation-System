/**
 * 
 * YearAfterFilter can be used to extract only movies released after a particular year specified
 * in the method parameter.
 * 
 */

public class YearAfterFilter implements Filter {
    private int myYear;
    
    public YearAfterFilter(int year) {
        myYear = year;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getYear(id) >= myYear;
    }
}
