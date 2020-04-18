
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String directors;
    
    public DirectorsFilter(String directors) {
        this.directors = directors;
    }
    
    public boolean satisfies(String id) {
        for(String s : directors.split(",")) {
            s = s.trim();
            if(MovieDatabase.getDirector(id).contains(s))
                return true;
        }
        return false;
    }
}
