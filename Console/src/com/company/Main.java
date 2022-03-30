import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static ParseNLP parse;
    static String browseMovies = "movies";
    static String browseBooks = "books";
    static String trivia = "trivia";
    static String request = "request";
    static String unclear = "unclear";
    static String exit = "exit";
    static String objective = "";
    static String sentiment = "Neutral";

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Library library = new Library();
        Gallery gallery = new Gallery();
        ChatBot chatBot = new ChatBot();
        Patterns patterns = new Patterns();
        Person user1 = new Person();
        PCA pca = new PCA(user1.getUserVector());
        System.out.println(chatBot.getStatement(0));

        //System.out.println(option.size());
        while(!objective.equalsIgnoreCase("exit")){
            System.out.println("Would you like to: browse books, browse movies, play trivia, or request an item?");
            parse = new ParseNLP(reader.readLine());
            ArrayList<String> option = parse.getStringList();
            for (String s:option) {
                if(s.equalsIgnoreCase(browseMovies)){
                    System.out.println("You have selected: browse movies, is that right?");
                    boolean yes = chatBot.testReaction(reader.readLine()); //can pass string here instead
                    if (yes) {
                        objective = browseMovies;
                    }
                }
                else if(s.equalsIgnoreCase(browseBooks)){
                    System.out.println("You have selected: browse books, is that right?");
                    boolean yes = chatBot.testReaction(reader.readLine()); //can pass string here instead
                    if (yes) {
                        objective = browseBooks;
                    }
                }
                else if(s.equalsIgnoreCase(trivia)){
                    System.out.println("You have selected: trivia, is that right?");
                    boolean yes = chatBot.testReaction(reader.readLine()); //can pass string here instead
                    if (yes) {
                        objective = trivia;
                    }
                }
                else if(s.equalsIgnoreCase(request)){
                    System.out.println("You have selected: request a specific item, is that right?");
                    boolean yes = chatBot.testReaction(reader.readLine()); //can pass string here instead
                    if (yes) {
                        objective = request;
                    }
                }
            }
            mainMenu(chatBot, user1, pca);
        }

    }

    public static void mainMenu(ChatBot chatBot, Person user1, PCA pca, Gallery gallery, Patterns patterns) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        if(objective.equalsIgnoreCase(trivia)) {
            System.out.println("Let's test your skills! Answer 1,2,3,4 to choose your response");
            user1.setUserVector();
            user1.setPcaVector(pca.getStandardUser());
            patterns.getTrivia("trivia");
            objective = "";
        }
        else if(objective.equalsIgnoreCase(browseBooks)) {
            user1.setUserVector();
            user1.setPcaVector(pca.getStandardUser());
            user1.setTopThree(pca.getTopThree());
            chatBot.loopGeneraTitle(user1 ,pca,pca.getTopThree(), false);
            objective = "";
        }
        else if(objective.equalsIgnoreCase(browseMovies)) {
            user1.setUserVector();
            user1.setPcaVector(pca.getStandardUser());
            user1.setTopThree(pca.getTopThree());
            chatBot.loopGeneraTitleMovie(user1 ,pca,pca.getTopThree(), false);
            objective = "";
        }
        else if(objective.contains(request)) {
            System.out.println("Would you like to request for a book or a movie?");
            setObjective(chatBot);
            if(objective.contains(browseBooks)){
                System.out.println("What book would you like to search for? (Enter title)");
                //Book tempB = new Book();
                searchByTitle(chatBot, user1, library);
            }
            else if(objective.contains(browseMovies)){
                System.out.println("What movie would you like to search for? (Enter title)");
                //Movie tempM = new Movie();
                searchByTitleMovie(chatBot, user1, gallery);
            }
        }

        System.out.println("Thank you for using this service, would you like to continue browsing or exit?");
        setObjective(chatBot);


    }

    public static void setObjective(ChatBot chatBot) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        parse = new ParseNLP(reader.readLine());
        ArrayList<String> option = parse.getStringList();
        for (String s:option) {
            if(s.contains(browseMovies)){
                objective = browseMovies;
            }
            else if(s.equalsIgnoreCase(browseBooks)){
                objective = browseBooks;
            }
            else if(s.equalsIgnoreCase(trivia)){
                objective = trivia;
            }
            else if(s.equalsIgnoreCase(request)){
                objective = request;
            }
            else if(s.equalsIgnoreCase(exit)){
                objective = exit;
            }
            else{
                objective = unclear;
            }
        }
    }
    public static void searchByTitle(ChatBot chatBot, Person user1 , Library library) throws IOException{
        Book b = new Book();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        parse = new ParseNLP(reader.readLine());
        ArrayList<String> option = parse.getStringList();
        int i = 0;
        for(String s: option){
            //String a = s.toLowerCase();
            //String c = library.getBookList().get(i).getTitle().toLowerCase();
            //if(c.contains(a)){
            String a = library.getBookList().get(i).getTitle();
            if(s.equalsIgnoreCase(a)){
                System.out.println("Would you like to add "+ a + " to your checkout list?");
                if(chatBot.testReaction(reader.readLine())){
                    b = library.getBookList().get(i);
                    user1.updateTempBookList(b);
                    System.out.println(b.getTitle() + " successfully added to checkout!");
                }

            }
            i++;
        }
        if(b.getTitle() == null){
            System.out.println("Apologies, book not found in library");
        }
    }
    public static void searchByTitleMovie(ChatBot chatBot, Person user1,Gallery gallery) throws IOException{
        Movie m = new Movie();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        parse = new ParseNLP(reader.readLine());
        ArrayList<String> option = parse.getStringList();
        int i = 0;
        for(String s: option){
            System.out.println(s);

            //String a = s.toLowerCase();
            //String c = gallery.getMovieList().get(i).getTitle().toLowerCase();
            //if(c.contains(a)){
            String a = gallery.getMovieList().get(i).getTitle();
            if(s.equalsIgnoreCase(a)){
                System.out.println("Would you like to add "+ a + " to your checkout list?");
                if(chatBot.testReaction(reader.readLine())){
                    m = gallery.getMovieList().get(i);
                    user1.updateTempMovieList(m);
                    System.out.println(m.getTitle() + " successfully added to checkout!");
                }
            }
            i++;
        }
        if(m.getTitle() == null){
            System.out.println("Apologies, movie not found in gallery");
        }
    }
}
