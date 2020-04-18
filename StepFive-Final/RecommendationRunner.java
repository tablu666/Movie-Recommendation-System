
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class RecommendationRunner implements Recommender {
    public ArrayList<String> getItemsToRate() {
        AllFilters allFilters = new AllFilters();
        int year = 2005;
        String genre = "Action";
        Filter filter1 = new YearAfterFilter(year);
        Filter filter2 = new GenreFilter(genre);
        allFilters.addFilter(filter1);
        allFilters.addFilter(filter2);
        
        ArrayList<String> sourceMovies = MovieDatabase.filterBy(allFilters);
        ArrayList<String> res = new ArrayList<>();
        Random rd = new Random();
        for(int i = 0; i < 10; i++) {
            res.add(sourceMovies.get(rd.nextInt(sourceMovies.size())));
        }
        return res;
    }
    
    public void printRecommendationsFor(String webRaterID) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String ratingfile = "ratings.csv";
        FourthRatings fr = new FourthRatings(ratingfile);
        
        int minimalRaters = 1;
        int numSimilarRaters = 1;
        ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);
        
        System.out.println("<style>*body{font-family: sans-serif;}#myTable{padding: 20px 600px; font-size: 15px; text-align: center; border: 2px solid #696969; margin: margin; border-radius: 25px;}img{height: 50%}</style>");
        if(ratings.size() == 0){
           System.out.println("<style>#header{height: 150px;}</style>");
           System.out.println("<div id=\"header\"><h2>Sorry! No movies are recommended!</h2></div>");
           return;
        }           
        int display = Math.min(10, ratings.size());                                                                     
        System.out.println("<h1>What to watch - The most similar top movies</h1><p1><table id=\"myTable\"><tr><th>Poster</th><th>\t</th><th>Title</th><th>\t</th><th>Genre</th><th>\t</th><th>Year</th><th>\t</th><th>Time(Minute)</th></tr>");                                                                         
        
        for(int i=0; i< display; i++) {
            String id = ratings.get(i).getItem();
            if(RaterDatabase.getRater(webRaterID).hasRating(id))
                continue;
            String poster = "http://www.dukelearntoprogram.com/capstone/data/" + MovieDatabase.getPoster(id).substring(7);
            
            System.out.println("<td><img src=");
            System.out.println("\"" + poster + "\"");
            System.out.println("/> </td>");
            System.out.println("<td>\t</td>");
            System.out.println("<td>" + MovieDatabase.getTitle(id) + "</td>");
            System.out.println("<td>\t</td>");
            System.out.println("<td>" + MovieDatabase.getGenres(id) + "</td>");
            System.out.println("<td>\t</td>");
            System.out.println("<td>" + MovieDatabase.getYear(id) + "</td>");
            System.out.println("<td>\t</td>");
            System.out.println("<td>" + MovieDatabase.getMinutes(id) + "</td></tr>");
        }
        System.out.println("</p1>");
    }
}
