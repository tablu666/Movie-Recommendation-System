
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> listOfMovie = new ArrayList<>();
        //header row = [id,title,year,country,genre,director,minutes,poster]
        for(CSVRecord rec : parser) {
            String id = rec.get("id");
            String title = rec.get("title");
            String year = rec.get("year");
            String country = rec.get("country");
            String genre = rec.get("genre");
            String director = rec.get("director");
            int minutes = Integer.parseInt(rec.get("minutes"));
            String poster = rec.get("poster");
            Movie movie = new Movie(id,title,year,genre,director,country,poster,minutes);
            listOfMovie.add(movie);
        }
        return listOfMovie;
    }
    public ArrayList<Rater> loadRaters(String filename) {
        CSVParser parser = new FileResource(filename).getCSVParser();
        ArrayList<Rater> listOfRater = new ArrayList<>();
        Rater currRater = null;
        //head row = [rater_id,movie_id,rating,time]
        for(CSVRecord rec : parser) {
            String id = rec.get(0);
            String item = rec.get(1);
            double val = Double.parseDouble(rec.get(2));
            if(currRater == null) {
                Rater rater = new Rater(id);
                rater.addRating(item, val);
                currRater = rater;
            } else if(currRater.getID().equals(id)) {
                currRater.addRating(item, val);
            } else {
                listOfRater.add(currRater);//add prev
                Rater rater = new Rater(id);
                rater.addRating(item, val);
                currRater = rater;
            }
        }
        if(currRater != null)
            listOfRater.add(currRater);
        return listOfRater;
    }
}
