package Test;

import com.company.Book;
import com.company.Library;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void test1() {
        Library library = new Library();
        ArrayList<Book> books_in_genera = new ArrayList<>();
        ArrayList<Book> books_in_author = new ArrayList<>();
        System.out.println("here is everything by this author");
        books_in_author = library.getAuthorList("G.R.R. Martin");
        System.out.println(books_in_author.get(0));


        System.out.println("-------");
        String gen = "Sci-Fi";
        System.out.println("here are some titles for you");
        books_in_genera = library.getGeneraList(gen);
        System.out.println(books_in_genera);
        System.out.println("-------");
        String auth = "J.R. Tolkien";
        books_in_author = library.getAuthorList(auth);
        System.out.println(books_in_author);
        System.out.println("-------");

        Book randG1 = library.getGeneraRand("comedy");
        System.out.println("random of this genera: " +randG1.getTitle());
        Book randG2 = library.getGeneraRand("Sci-Fi");
        System.out.println("random of this genera: " +randG2.getTitle());

        String title = library.getAuthRand("J. R. Tolkien").getTitle();
        Book totalRandom = library.getTitleRandom();
        System.out.println("total shot in the dark here");
        System.out.println(totalRandom.getTitle());
    }
}