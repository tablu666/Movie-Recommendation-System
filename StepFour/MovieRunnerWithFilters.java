
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        MovieDatabase.initialize("ratedmovies_short.csv");
        String ratingfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingfile);
        
        int numOfRater = tr.getRaterSize();
        int numOfMovie = MovieDatabase.size();
        System.out.println("read data for " + numOfRater + " raters");
        System.out.println("read data for " + numOfMovie + " movies");
        
        int minimalRaters = 1;
        ArrayList<Rating> movies = tr.getAverageRatings(minimalRaters);
        System.out.println("found " + movies.size() + " movies");
        
        Collections.sort(movies);
        for(Rating rating : movies) {
            System.out.println(rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYear() {
        MovieDatabase.initialize("ratedmovies_short.csv");
        String ratingfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingfile);
        
        int numOfRater = tr.getRaterSize();
        int numOfMovie = MovieDatabase.size();
        System.out.println("read data for " + numOfRater + " raters");
        System.out.println("read data for " + numOfMovie + " movies");
        
        int year = 2000;
        int minimalRaters = 1;
        Filter filter = new YearAfterFilter(year);
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minimalRaters, filter);
        System.out.println("found " + movies.size() + " movies");
        
        Collections.sort(movies);
        for(Rating rating : movies) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " "
                                + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre() {
        MovieDatabase.initialize("ratedmovies_short.csv");
        String ratingfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingfile);
        
        int numOfRater = tr.getRaterSize();
        int numOfMovie = MovieDatabase.size();
        System.out.println("read data for " + numOfRater + " raters");
        System.out.println("read data for " + numOfMovie + " movies");
        
        String genre = "Crime";
        int minimalRaters = 1;
        Filter filter = new GenreFilter(genre);
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minimalRaters, filter);
        System.out.println("found " + movies.size() + " movies");
        
        Collections.sort(movies);
        for(Rating rating : movies) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes() {
        MovieDatabase.initialize("ratedmovies_short.csv");
        String ratingfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingfile);
        
        int numOfRater = tr.getRaterSize();
        int numOfMovie = MovieDatabase.size();
        System.out.println("read data for " + numOfRater + " raters");
        System.out.println("read data for " + numOfMovie + " movies");
        
        int minMinute = 110;
        int maxMinute = 170;
        int minimalRaters = 1;
        Filter filter = new MinutesFilter(minMinute, maxMinute);
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minimalRaters, filter);
        System.out.println("found " + movies.size() + " movies");
        
        Collections.sort(movies);
        for(Rating rating : movies) {
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " "
                                + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectors() {
        MovieDatabase.initialize("ratedmovies_short.csv");
        String ratingfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingfile);
        
        int numOfRater = tr.getRaterSize();
        int numOfMovie = MovieDatabase.size();
        System.out.println("read data for " + numOfRater + " raters");
        System.out.println("read data for " + numOfMovie + " movies");
        
        String director = "Charles Chaplin,Michael Mann,Spike Jonze";
        int minimalRaters = 1;
        Filter filter = new DirectorsFilter(director);
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minimalRaters, filter);
        System.out.println("found " + movies.size() + " movies");
        
        Collections.sort(movies);
        for(Rating rating : movies) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        MovieDatabase.initialize("ratedmovies_short.csv");
        String ratingfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingfile);
        AllFilters allFilters = new AllFilters();
        
        int numOfRater = tr.getRaterSize();
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
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println(movies.size() + " movie(s) matched");
        
        Collections.sort(movies);
        for(Rating rating : movies) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " "
                                + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));                    
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        MovieDatabase.initialize("ratedmovies_short.csv");
        String ratingfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingfile);
        AllFilters allFilters = new AllFilters();
        
        int numOfRater = tr.getRaterSize();
        int numOfMovie = MovieDatabase.size();
        System.out.println("read data for " + numOfRater + " raters");
        System.out.println("read data for " + numOfMovie + " movies");
        
        int minMinu = 30;
        int maxMinu = 170;
        String director = "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola";
        int minimalRaters = 1;
        Filter filter1 = new MinutesFilter(minMinu, maxMinu);
        Filter filter2 = new DirectorsFilter(director);
        allFilters.addFilter(filter1);
        allFilters.addFilter(filter2);
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println(movies.size() + " movie(s) matched");
        
        Collections.sort(movies);
        for(Rating rating : movies) {
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " "
                                + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(rating.getItem()));                    
        }
    }
}
