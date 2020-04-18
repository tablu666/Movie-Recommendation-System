
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    /*
    public void printAverageRatings() {
        MovieDatabase.initialize("ratedmovies_short.csv");
        String ratingfile = "ratings_short.csv";
        FourthRatings fr = new FourthRatings(ratingfile);
        
        int numOfRater = fr.getRaterSize();
        int numOfMovie = MovieDatabase.size();
        System.out.println("read data for " + numOfRater + " raters");
        System.out.println("read data for " + numOfMovie + " movies");
        
        int minimalRaters = 1;
        ArrayList<Rating> movies = fr.getAverageRatings(minimalRaters);
        System.out.println("found " + movies.size() + " movies");
        
        Collections.sort(movies);
        for(Rating rating : movies) {
            System.out.println(rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        MovieDatabase.initialize("ratedmovies_short.csv");
        String ratingfile = "ratings_short.csv";
        FourthRatings fr = new FourthRatings(ratingfile);
        AllFilters allFilters = new AllFilters();
        
        int numOfRater = fr.getRaterSize();
        int numOfMovie = MovieDatabase.size();
        System.out.println("read data for " + numOfRater + " raters");
        System.out.println("read data for " + numOfMovie + " movies");
        
        int year = 1980;
        String genre = "Romance";
        int minimalRaters = 1;
        Filter filter1 = new YearAfterFilter(year);
        Filter filter2 = new GenreFilter(genre);
        allFilters.addFilter(filter1);
        allFilters.addFilter(filter2);
        ArrayList<Rating> movies = fr.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println(movies.size() + " movie(s) matched");
        
        Collections.sort(movies);
        for(Rating rating : movies) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " "
                                + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));                    
        }
    }
    */
    public void printSimilarRatings() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String ratingfile = "ratings.csv";
        FourthRatings fr = new FourthRatings(ratingfile);
        
        int numOfRater = fr.getRaterSize();
        int numOfMovie = MovieDatabase.size();
        System.out.println("read data for " + numOfRater + " raters");
        System.out.println("read data for " + numOfMovie + " movies");
        
        String raterID = "65";
        int minimalRaters = 5;
        int numSimilarRaters = 20;
        ArrayList<Rating> movies = fr.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
        System.out.println("\n[recommended movies:]\n");
        for(Rating rating : movies) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\t" + rating.getValue());
        }
    }
    
    public void printSimilarRatingsByGenre() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String ratingfile = "ratings.csv";
        FourthRatings fr = new FourthRatings(ratingfile);
        
        int numOfRater = fr.getRaterSize();
        int numOfMovie = MovieDatabase.size();
        System.out.println("read data for " + numOfRater + " raters");
        System.out.println("read data for " + numOfMovie + " movies");
        
        String raterID = "65";
        int minimalRaters = 5;
        int numSimilarRaters = 20;
        String genre = "Action";
        Filter filter = new GenreFilter(genre);
        ArrayList<Rating> movies = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filter);
        System.out.println("\n[recommended movies:]\n");
        for(Rating rating : movies) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\t" + rating.getValue());
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));                    
        }
    }
    
    public void printSimilarRatingsByDirector() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String ratingfile = "ratings.csv";
        FourthRatings fr = new FourthRatings(ratingfile);
        
        int numOfRater = fr.getRaterSize();
        int numOfMovie = MovieDatabase.size();
        System.out.println("read data for " + numOfRater + " raters");
        System.out.println("read data for " + numOfMovie + " movies");
        
        String raterID = "1034";
        int minimalRaters = 3;
        int numSimilarRaters = 10;
        String director = "Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone";
        Filter filter = new DirectorsFilter(director);
        ArrayList<Rating> movies = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filter);
        System.out.println("\n[recommended movies:]\n");
        for(Rating rating : movies) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\t" + rating.getValue());
            System.out.println("\t" + MovieDatabase.getDirector(rating.getItem()));                    
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String ratingfile = "ratings.csv";
        FourthRatings fr = new FourthRatings(ratingfile);
        AllFilters allFilters = new AllFilters();
        
        int numOfRater = fr.getRaterSize();
        int numOfMovie = MovieDatabase.size();
        System.out.println("read data for " + numOfRater + " raters");
        System.out.println("read data for " + numOfMovie + " movies");
        
        String raterID = "65";
        int minimalRaters = 5;
        int numSimilarRaters = 10;
        int minMinu = 100;
        int maxMinu = 200;
        String genre = "Adventure";
        Filter filter1 = new MinutesFilter(minMinu, maxMinu);
        Filter filter2 = new GenreFilter(genre);
        allFilters.addFilter(filter1);
        allFilters.addFilter(filter2);
        ArrayList<Rating> movies = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, allFilters);
        System.out.println("\n[recommended movies:]\n");
        for(Rating rating : movies) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + " " 
                                + MovieDatabase.getMinutes(rating.getItem()) + " " + rating.getValue());
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));                  
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String ratingfile = "ratings.csv";
        FourthRatings fr = new FourthRatings(ratingfile);
        AllFilters allFilters = new AllFilters();
        
        int numOfRater = fr.getRaterSize();
        int numOfMovie = MovieDatabase.size();
        System.out.println("read data for " + numOfRater + " raters");
        System.out.println("read data for " + numOfMovie + " movies");
        
        String raterID = "65";
        int minimalRaters = 5;
        int numSimilarRaters = 10;
        int minMinu = 80;
        int maxMinu = 100;
        int year = 2000;
        Filter filter1 = new MinutesFilter(minMinu, maxMinu);
        Filter filter2 = new YearAfterFilter(year);
        allFilters.addFilter(filter1);
        allFilters.addFilter(filter2);
        ArrayList<Rating> movies = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, allFilters);
        System.out.println("\n[recommended movies:]\n");
        for(Rating rating : movies) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + " " + MovieDatabase.getYear(rating.getItem())
                                + MovieDatabase.getMinutes(rating.getItem()) + " " + rating.getValue());                  
        }
    }
}
