
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {
    
    public FourthRatings() {
        this("ratings.csv");
    }
    
    public FourthRatings(String ratingsfile) {
        RaterDatabase.addRatings("data/" + ratingsfile);
    }
    
    public int getRaterSize() {
        return RaterDatabase.size();
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        ArrayList<Rater> raterList = new ArrayList<>();
        double sum = 0.0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
    
    private double dotProduct(Rater me, Rater r) {
        ArrayList<String> myMovieID = me.getItemsRated();
        double dotP = 0.0;
        for(String movieID : myMovieID) {
            double myRating = me.getRating(movieID);
            double otherRating = r.getRating(movieID);
            if(myRating != -1 && otherRating != -1) {
                dotP += (myRating - 5) * (otherRating - 5);
            }
        }
        return dotP;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> sim = new ArrayList<>();
        ArrayList<Rater> allRaters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        for(Rater rater : allRaters) {
            if(!rater.getID().equals(id)) {
                double dotP = dotProduct(me, rater);
                if(dotP > 0) {
                    Rating rating = new Rating(rater.getID(), dotP);
                    sim.add(rating);
                }
            }
        }
        Collections.sort(sim, Collections.reverseOrder());
        return sim;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> movieWithSimRating = new ArrayList<>();
        HashMap<String, ArrayList<Double>> movieToRater = new HashMap<>();
        for(int i = 0; i < numSimilarRaters; i++) {
            Rating rating = getSimilarities(id).get(i);
            String raterID = rating.getItem();
            double dotP = rating.getValue();
            //fliter by rating value of simlilar raters
            for(String movieID : MovieDatabase.filterBy(new TrueFilter())) {
                if(RaterDatabase.getRater(raterID).hasRating(movieID)) {
                    ArrayList<Double> al = movieToRater.getOrDefault(movieID, new ArrayList<>());
                    double weightedVal = RaterDatabase.getRater(raterID).getRating(movieID) * dotP;
                    al.add(weightedVal);
                    movieToRater.put(movieID, al);
                }
            }
        }
        for(Map.Entry<String, ArrayList<Double>> me : movieToRater.entrySet()) {
            if(me.getValue().size() >= minimalRaters) {
                double sum = 0.0;
                for(double weight : me.getValue()) {
                    sum += weight;
                }
                Rating rating = new Rating(me.getKey(), sum / me.getValue().size());
                movieWithSimRating.add(rating);
            }
        }
        Collections.sort(movieWithSimRating, Collections.reverseOrder());
        return movieWithSimRating;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> movieWithSimRating = new ArrayList<>();
        HashMap<String, ArrayList<Double>> movieToRater = new HashMap<>();
        for(int i = 0; i < numSimilarRaters; i++) {
            Rating rating = getSimilarities(id).get(i);
            String raterID = rating.getItem();
            double dotP = rating.getValue();
            //fliter by rating value of simlilar raters
            for(String movieID : MovieDatabase.filterBy(filterCriteria)) {
                if(RaterDatabase.getRater(raterID).hasRating(movieID)) {
                    ArrayList<Double> al = movieToRater.getOrDefault(movieID, new ArrayList<>());
                    double weightedVal = RaterDatabase.getRater(raterID).getRating(movieID) * dotP;
                    al.add(weightedVal);
                    movieToRater.put(movieID, al);
                }
            }
        }
        for(Map.Entry<String, ArrayList<Double>> me : movieToRater.entrySet()) {
            if(me.getValue().size() >= minimalRaters) {
                double sum = 0.0;
                for(double weight : me.getValue()) {
                    sum += weight;
                }
                Rating rating = new Rating(me.getKey(), sum / me.getValue().size());
                movieWithSimRating.add(rating);
            }
        }
        Collections.sort(movieWithSimRating, Collections.reverseOrder());
        return movieWithSimRating;
    }
    
}
