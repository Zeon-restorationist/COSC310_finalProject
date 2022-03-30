import java.util.ArrayList;
import java.util.Arrays;

public class Gallery {
    private ArrayList<Movie> movieList = new ArrayList<>();
    public Gallery() {
        movieList.add(new Movie("Eternals", "Action", 2021, 2.62));
        movieList.add(new Movie("Star Wars", "Sci-Fi", 2021, 2.58));
        movieList.add(new Movie("Perfect Blue", "Drama", 1998, 2));
        movieList.add(new Movie("Howl's Moving Castle - The Animation", "Fantasy", 2004, 2));
        movieList.add(new Movie("The Fellowship of The Rings", "Fantasy", 2001, 3));
        movieList.add(new Movie("Slender Man", "Horror", 2018, 1.6));
        movieList.add(new Movie("Titanic", "Romance", 1997, 3));
        movieList.add(new Movie("E.T", "Sci-Fi", 1982   , 2));
        movieList.add(new Movie("Hathaway's Flash part 1", "Sci-Fi", 2021, 1.5));
        movieList.add(new Movie("Scary movie", "Comedy", 2000, 1.5));
        movieList.add(new Movie("LA 92", "Education", 2017, 2));
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }


    //Return arrayList of movies by specific genre in gallery.
    public ArrayList<Movie> getGeneraList(String genera) {
        ArrayList<Movie> generaList = new ArrayList<Movie>();
        for (Movie m:movieList) {
            if(m.getGenre().equalsIgnoreCase(genera)) { //Don't use == to compare strings in Java, please. Unless you're comparing if they're pointing to the same object.
                generaList.add(m);
            }
        }
        return  generaList;
    }

    //Specially made for callers that requires a list instead of arraylist
    public String [] getAllGeneras() {
        String [] allGenres;
        ArrayList<Movie> genres = new ArrayList<>();
        for (Movie m :movieList) {
            if(!checkDup(m, genres)){
                genres.add(m);
            }
        }
        allGenres = new String[genres.size()];
        for (int i = 0; i < allGenres.length; i++) {
            allGenres[i] = genres.get(i).getGenre();
        }
        return allGenres;
    }
    //Return arrayList of strings of movie titles in gallery.
    public ArrayList<String> getTitleList(ArrayList<Movie> movies) {
        ArrayList<String> titles = new ArrayList<>();
        for (Movie m :movies) {
            titles.add(m.getTitle());
        }
        return titles;
    }


    public Movie byTitle(String s){ //In the future return list, from which user can pick from.
        Movie m = new Movie();
        ArrayList<Movie> temp = new ArrayList<Movie>();
        int a = 1;
        int ran = 0;
        for (int i = 0; i < getMovieList().size(); i++) {
            if(s.equalsIgnoreCase(getMovieList().get(i).getTitle())){
                temp.add(getMovieList().get(i));
            }
        }
        if(temp.size()>0){
            ran = (int) (Math.random()*temp.size());
            m = temp.get(ran);
        }

        return m;
    }
    //Checks for dupes in get genre methods
    //Returns true if a duplicate is found and false otherwise
    public boolean checkDup(Movie in, ArrayList<Movie> out){
        for (int i = 0; i < out.size(); i++) {
            if(in.getGenre().equalsIgnoreCase(out.get(i).getGenre())){
                return true;
            }
        }
        return false;
    }

    /*
    public void setMovieList(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    //Add movie to gallery return boolean value to caller.
    public boolean addMovie(String title, String genre, int year, double duration) {
        for (int i = 0; i < movieList.size(); i++) {
            if(title.equalsIgnoreCase(movieList.get(i).getTitle())){
                return false;
            }
        }
        movieList.add(new Movie(title,genre,year,duration));
        return true;
    }
    
    //Returns arraylist of type integer, of all years movies in gallery were published.
    public ArrayList<Integer> getAllYearList(ArrayList<Movie> movies) {
        ArrayList<Integer> years = new ArrayList<>();
        for (Movie m :movies) {
            if(!checkDupYear(m, years)){
                years.add(m.getYear());
            }
        }
        return years;
    }
    //Return random movie by genera to caller.
    public Movie getGeneraRand(String genera) {
        ArrayList<Movie> generaList = getGeneraList(genera);
        int randNum = (int)(Math.random() * generaList.size());
        Movie rand = new Movie();
        //System.out.println(generaList.size());
        if (generaList.size()>0) {
            rand = generaList.get(randNum);
        }
        //System.out.println(rand.getBookDetails());
        return rand;
    }
    //Return random movie in a specific year.
    public Movie getYearRand(int year) {
        ArrayList<Movie> yearList = getYearList(year);
        int randNum = (int) (Math.random() * yearList.size());
        Movie rand = new Movie();
        if (yearList.size() > 0) {
            rand = yearList.get(randNum);
        }
        return rand;
    }
    //Returns a random movie in gallery to caller.
    public Movie getTitleRandom() {
        int randNum = (int)(Math.random() * movieList.size());
        Movie rand = movieList.get(randNum);
        return rand;
    }



    //Tostring of a movie at given index.
    public String getMovieDetails(int i){
        String tempTitle = "";
        String tempAuthor = "";
        if(movieList.get(i).getTitle().length() > 20){

        }
        return "Title: " + movieList.get(i).getTitle() + "\t\tGenre: " + movieList.get(i).getGenre() + "\t\tYear: " + movieList.get(i).getYear()+ "\t\tDuration: " + movieList.get(i).getDuration();
    }
    //Returns a string that is a list of all the book titles, in the array of books passed to method, to caller.
    public String listString(ArrayList<Movie> movies)  {
        String movieString = "";
        for (Movie m:movies) {
            movieString += m.getTitle() + " \n";
        }
        return movieString;
    }
    //Checks for dupes in get year methods
    //Returns true if a duplicate is found and false otherwise
    public boolean checkDupYear(Movie in, ArrayList<Integer> out){
        for (int i = 0; i < out.size(); i++) {
            if(in.getYear() == out.get(i)){
                return true;
            }
        }
        return false;
    }
    //Return arrayList of movies by specific year in gallery.
    public ArrayList<Movie> getYearList(int y) {
        ArrayList<Movie> yearList = new ArrayList<>();
        for (Movie m: movieList) {
            if(m.getYear() == y) {
                yearList.add(m);
            }
        }
        return  yearList;
    }
    //Return arrayList of movies by specific duration in gallery.
    public ArrayList<Movie> getDurationList(double d) {
        ArrayList<Movie> durationList = getMovieList();
        for (Movie m:movieList) {
            if(m.getDuration() == d) {
                durationList.add(m);
            }
        }
        return  durationList;
    }
    */

}
