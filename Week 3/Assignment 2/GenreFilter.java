
/**
 * GenreFilter can be used to extract movies in specified genre in the method parameter.
 * 
 * @ Konstantin Krumin 
 * @ Version: 1.0 (February 20, 2020)
 */

public class GenreFilter implements Filter {
    private String myGenre;
    
    public GenreFilter (String genre) {
        myGenre = genre;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(myGenre);
    }
}
