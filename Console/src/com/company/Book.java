public class Book {
    private String title, genre, author;
    private int pages;
    private Library lib;


    public Book(String title, String genre, String author) {
        this.title = title;
        this.genre = genre;
        this.author = author;
    }
    public Book(String title, String genre, int pages, String author) {
        this.title = title;
        this.genre = genre;
        this.pages = pages;
        this.author = author;
    }
    public Book() {

    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }


    public String getAuthor() {
        return author;
    }


    public String getBookDetails(){
        return "Title: " + title + "\t\tGenre: " + genre + "\t\tAuthor: " + author + "\t\tPages: " + pages;
    }
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", pages='" + pages + '\'' +

                '}';
    }

    /*Unused methods
    public void setTitle(String title) {
        this.title = title;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getPages(){ return pages; }
    */
}