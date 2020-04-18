
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsfile); 
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movieID : movies) {
            double avgRating = getAverageByID(movieID, minimalRaters);
            if(avgRating != 0.0) {
                Rating rating = new Rating(movieID, avgRating);
                movieWithAvgRating.add(rating);
            }
        }
        return movieWithAvgRating;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> movieWithAvgRating = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        for(String movieID : movies) {
            double avgRating = getAverageByID(movieID, minimalRaters);
            if(avgRating != 0.0) {
                Rating rating = new Rating(movieID, avgRating);
                movieWithAvgRating.add(rating);
            }
        }
        return movieWithAvgRating;
    }
}
