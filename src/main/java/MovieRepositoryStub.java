import java.util.*;

/**
 * Created by cathlene on 8/02/2016.
 */
public class MovieRepositoryStub implements MovieRepository{
    private Map<String, Movie> movies;
    public MovieRepositoryStub(){

        movies= new HashMap<String, Movie>();

        this.addMovie( new Movie("PublicEnemy", 118,new Actor("Johnny", "Depp", 55,"John")));
    }
    public void addMovie(Movie movie) {
        if (movie.getTitle()==null || movie.getTitle().isEmpty()){
            throw new IllegalArgumentException("Geen geldige movie");
        }
        movies.put(movie.getTitle(),movie);

    }

    public void removeMovie(Movie movie) {
        if (movie.getTitle()==null || movie.getTitle().isEmpty() || !movies.containsKey(movie.getTitle())){
            throw new IllegalArgumentException("Geen geldige movie");
        }
        movies.remove(movie.getTitle());
    }

    public void updateMovie(Movie movie) {
        if (movie.getTitle()==null || movie.getTitle().isEmpty() || !movies.containsKey(movie.getTitle())){
            throw new IllegalArgumentException("Geen geldige movie");
        }
        movies.put(movie.getTitle(),movie);

    }


    public Movie getMovie(String title) {
        if (title==null || title.isEmpty()|| !movies.containsKey(title)){
            throw new IllegalArgumentException("Geen geldige title");
        }
        return movies.get(title);
    }
    public List<Movie> getMoviesWithSpecificDuration(int duur){
        List<Movie> moviesWithDuration =getAllMovies();
        ListIterator<Movie> itr=moviesWithDuration.listIterator();
        while(itr.hasNext()){
            if(itr.next().getDuur()> duur){
                itr.remove();
            }
        }
        return moviesWithDuration;
    }
    public List<Movie> getMoviesWithSpecificActor(Actor actor){
        List<Movie> moviesWithActor =getAllMovies();
        ListIterator<Movie> itr=moviesWithActor.listIterator();
        while(itr.hasNext()){
            if(!actor.getId().equals(itr.next().getHoofdrolSpeler().getId())){
                itr.remove();
            }
        }
        return moviesWithActor;

    }
    public List<Movie> getAllMovies(){
        return new ArrayList<Movie>(movies.values());
    }
    public int getAantalMovies(){
        return movies.size();
    }
}
