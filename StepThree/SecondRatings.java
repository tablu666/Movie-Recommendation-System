
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings(String moviefile, String ratingfile) {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(moviefile);
        myRaters = firstRatings.loadRaters(ratingfile); 
        //this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        ArrayList<Rater> raterList = new ArrayList<>();
        double sum = 0.0;
        for(Rater rater : myRaters) {
            if(rater.hasRating(id)) {
                raterList.add(rater);
                sum += rater.getRating(id);
            }
        }
        if(raterList.size() < minimalRaters)
            return 0.0;
        return sum / raterList.size();
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> movieWithAvgRating = new ArrayList<>();
        for(Movie movie : myMovies) {
            String movieID = movie.getID();
            double avgRating = getAverageByID(movieID, minimalRaters);
            if(avgRating != 0.0) {
                Rating rating = new Rating(movieID, avgRating);
                movieWithAvgRating.add(rating);
            }    
        }
        return movieWithAvgRating;
    }
    public String getTitle(String id) {
        for(Movie movie : myMovies) {
            if(movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return "No such movie - the ID was not found";
    }
    public String getID(String title) {
        for(Movie movie : myMovies) {
            if(movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }
        return "NO SUCH TITLE";
    }
}
