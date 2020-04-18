
/**
 * Write a description of StepOneTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class StepOneTester {
    public void testLoadMovies() {
        FirstRatings firstRating = new FirstRatings();
        //ArrayList<Movie> listOfMovies = firstRating.loadMovies("data/ratedmovies_short.csv");
        ArrayList<Movie> listOfMovies = firstRating.loadMovies("data/ratedmoviesfull.csv");
        System.out.println("number of movies = " + listOfMovies.size());
        
        int numOfGenre = 0;
        int numOfLen = 0;
        int maxMovies = 0;
        int numOfDirectors = 0;
        StringBuilder ds = new StringBuilder();
        ds.append("[");
        HashMap<String, ArrayList<String>> directorMap = new HashMap<>();
        
        for(Movie movie : listOfMovies) {
            //System.out.println(movie); 
            if(movie.getGenres().contains("Comedy")) {
                numOfGenre ++;
            }    
            if(movie.getMinutes() > 150) {
                numOfLen ++;
            }    
            String[] directors = movie.getDirector().split(",");
            for(String director : directors) {
                director = director.trim();
                ArrayList<String> movies = directorMap.getOrDefault(director, new ArrayList<>());
                movies.add(movie.getTitle());
                directorMap.put(director, movies);
            }
        }
        for(ArrayList<String> al : directorMap.values()) {
            maxMovies = Math.max(al.size(), maxMovies);
        }
        for(String director : directorMap.keySet()) {
            if(directorMap.get(director).size() == maxMovies) {
                numOfDirectors ++;
                ds.append(director).append(" ");
            }    
        }
        System.out.println("how many movies include the Comedy genre = " + numOfGenre
                        + "\nhow many movies are greater than 150 minutes in length = " + numOfLen);
        System.out.println("the maximum number of movies by any director is = " + maxMovies);
        System.out.println("there are " + numOfDirectors + " directors that directed one such movie\n");
        System.out.println("the name = " + ds.toString().trim() + "]");
        
        //print map
        /*
        for(Map.Entry<String, ArrayList<String>> me : directorMap.entrySet()) {
            System.out.println(me.getKey() + " = " + me.getValue());
        }
        */
    }
    
    public void testLoadRaters() {
        FirstRatings firstRating = new FirstRatings();
        //ArrayList<Rater> raterList = firstRating.loadRaters("data/ratings_short.csv");
        ArrayList<Rater> raterList = firstRating.loadRaters("data/ratings.csv");
        System.out.println("total number of raters = " + raterList.size());
        /*
        for(Rater rater : raterList) {
            System.out.println("rater id = " + rater.getID() + "\tnumber of ratings = " + rater.numRatings());
            
            ArrayList<String> items = rater.getItemsRated();
            for(String item : items) {
                double rating = rater.getRating(item);
                System.out.println("movie ID = " + item + "....rating = " + rating);
            }
            System.out.println("\n");
            
        }
        */
        System.out.println("\n[to find the number of ratings for a particular rater]");
        String rater_id = "193";
        for(Rater rater : raterList) {
            if(rater.getID().equals(rater_id)) {
                System.out.println("rater ID is " + rater_id + "....rating number = " + rater.numRatings());
            }
        }
        
        ArrayList<Rater> maxNumRater = new ArrayList<>();
        int maxNum = 0;
        for(Rater rater : raterList) {
            maxNum = Math.max(maxNum, rater.numRatings());
        }
        for(Rater rater : raterList) {
            if(rater.numRatings() == maxNum) {
                maxNumRater.add(rater);
            }    
        }
        System.out.println("\n[to find the maximum number of ratings by any rater]");
        System.out.println("max number = " + maxNum);
        System.out.println("num of such raters = " + maxNumRater.size());
        for(Rater rater : maxNumRater) {
            System.out.println("raterID = " + rater.getID());
        }
        
        System.out.println("\n[to find the number of ratings a particular movie has]");
        String movieID = "1798709";
        int numOfRate = 0;
        for(Rater rater : raterList) {
            if(rater.hasRating(movieID))
                numOfRate ++;
        }
        System.out.println("movieID " + movieID + " it was rated by " + numOfRate + " raters");
        
        System.out.println("\n[to determine how many different movies have been rated by all these raters]");
        HashSet<String> diffSet = new HashSet<>();
        for(Rater rater : raterList) {
            ArrayList<String> ratedItem = rater.getItemsRated();
            for(String item : ratedItem) {
                diffSet.add(item);
            }
        }
        System.out.println("num of different moives rated = " + diffSet.size());
    }
}
