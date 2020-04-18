
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        String moviefile = "data/ratedmovies_short.csv";
        String ratingfile = "data/ratings_short.csv";
        SecondRatings secondRatings = new SecondRatings(moviefile, ratingfile);
        int numOfMovie = secondRatings.getMovieSize();
        int numOfRater = secondRatings.getRaterSize();
        System.out.println("movie number = " + numOfMovie + "\trater number = " + numOfRater);
        
        int minimalRaters = 3;
        ArrayList<Rating> movies = secondRatings.getAverageRatings(minimalRaters);
        Collections.sort(movies);
        for(Rating rating : movies) {
            System.out.println(rating.getValue() + "\t" + secondRatings.getTitle(rating.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie() {
        String moviefile = "data/ratedmovies_short.csv";
        String ratingfile = "data/ratings_short.csv";
        SecondRatings secondRatings = new SecondRatings(moviefile, ratingfile);
        String title = "The Godfather";
        int minimalRaters = 3;
        
        String id = secondRatings.getID(title);
        double rating = 0.0;
        ArrayList<Rating> ratings = secondRatings.getAverageRatings(minimalRaters);
        for(Rating r : ratings) {
            if(r.getItem().equals(id)) {
                rating = r.getValue();
                break;
            }
        }
        System.out.println("the average for the movie [" + title + "] would be " + rating);
    }
}
