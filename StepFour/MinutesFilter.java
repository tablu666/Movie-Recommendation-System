
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter {
    private int minMinu;
    private int maxMinu;
    
    public MinutesFilter(int min, int max) {
        minMinu = min;
        maxMinu = max;
    }
    
    public boolean satisfies(String id) {
        int minute = MovieDatabase.getMinutes(id);
        return minute >= minMinu && minute <= maxMinu;
    }
}
