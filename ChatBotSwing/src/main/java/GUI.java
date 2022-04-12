import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class GUI<JTimer> implements ActionListener {
    //
    static PCA pca;
    static ParseNLP parse;
    static String browseMovies = "movies";
    static String browseBooks = "books";
    static String trivia = "trivia";
    static String request = "request";
    static String objective = "";
    static String sentiment = "Neural";
    static String english = "english";
    static String indonesian = "indonesia";
    static String userMsg;
    static String cbMsg;

    static boolean IN = true;

    static Trivia myTrivia;
    //
    Timer timer;
    int cb_user = 0;
    int numClicks = 0;
    JLabel label;
    JFrame frame;
    JButton btnSend;
    JPanel panel;
    static JTextArea textArea;
    JScrollPane scrollPane;
    static JTextArea textInput;
    int TOP = 300;
    int BOT = 300;
    int LEFT = 300;
    int RIGHT = 300;

    public GUI() {

        frame = new JFrame();
        ImageIcon img = new ImageIcon("C:\\Users\\JJ\\IdeaProjects\\ChatBotSwing\\src\\main\\resources\\bot.png");
        frame.setIconImage(img.getImage());
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(TOP,LEFT,BOT,RIGHT));
        panel.setBackground(new Color(0, 204, 255));
        panel.setPreferredSize(new Dimension(1200, 800));
        label = new JLabel(String.valueOf(numClicks));
        textArea = new JTextArea(100,6);
        textArea.setPreferredSize(new Dimension(600, 300));
        textArea.setAutoscrolls(true);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        //scrollPane.setAutoscrolls(true);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textInput = new JTextArea(2,6);
        textInput.setPreferredSize(new Dimension(400, 40));
        btnSend = new JButton("Send");
        btnSend.setPreferredSize(new Dimension(100, 40));
        btnSend.addActionListener(this);
        panel.add(scrollPane);
        panel.add(textInput);
        panel.add(btnSend);
        //GridLayout gridLayout = new GridLayout(10,10);

        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setTitle("Chat Bot");
        frame.pack();
        frame.setVisible(true);

    }
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException, Exception {
        new GUI();
        Library library = new Library();
        Gallery gallery = new Gallery();
        ChatBot chatBot = new ChatBot();
        Person user1 = new Person();
        getCBM(chatBot.getStatement(0));
        bingTranslate bingT = new bingTranslate();

        boolean outterRun = true;
        boolean innerRun = true;

        while(outterRun) {

            while(true) {
                getCBM("Choose from two languages: English, bahasa Indonesian");
                getUserIN();
                userMsg = bingT.translate(userMsg);
                parse = new ParseNLP(userMsg);
                ArrayList<String> option = parse.getStringList();
                if(option.contains(english)){
                    getCBM("english");
                }
                else if(option.contains(indonesian)){
                    getCBM("indonesian");
                }

                getCBM("Would you like to: browse books, browse movies, play trivia, or request an item?");
                getUserIN();
                userMsg = bingT.translate(userMsg);
                parse = new ParseNLP(userMsg);
                option = parse.getStringList();
                if (option.contains(browseMovies)) {
                    getCBM("You have selected: browse movies, is that right?");
                    getUserIN();
                    userMsg = bingT.translate(userMsg);
                    boolean yes = chatBot.testReaction(userMsg); //can pass string here instead
                    if (yes) {
                        objective = browseMovies;
                        break;
                    } else {
                        continue;
                    }
                } else if (option.contains(browseBooks)) {
                    getCBM("You have selected: browse books, is that right?");
                    getUserIN();
                    userMsg = bingT.translate(userMsg);
                    boolean yes = chatBot.testReaction(userMsg); //can pass string here instead
                    if (yes) {
                        objective = browseBooks;
                        break;
                    } else {
                        continue;
                    }

                } else if (option.contains(trivia)) {
                    getCBM("You have selected: trivia, is that right?");
                    getUserIN();
                    userMsg = bingT.translate(userMsg);

                    boolean yes = chatBot.testReaction(userMsg); //can pass string here instead
                    if (yes) {
                        objective = trivia;
                        break;
                    } else {
                        continue;
                    }
                } else if (option.contains(request)) {
                    getCBM("You have selected: request a specific item, is that right?");
                    getUserIN();
                    userMsg = bingT.translate(userMsg);

                    boolean yes = chatBot.testReaction(userMsg); //can pass string here instead
                    if (yes) {
                        objective = request;
                        break;
                    } else {
                        continue;
                    }
                }
                else{
                    //todo handling unexpected inputs
                }
            }
            System.out.println("Done initial branch: " + objective);
            while(innerRun) {
                if (objective.equalsIgnoreCase(trivia)) {
                    getCBM("Now starting Trivia :)");
                    myTrivia = new Trivia();
                    myTrivia.play();
                }
                else if (objective.equalsIgnoreCase(browseBooks)) {
                    cbMsg = "What is your favorite genera?";
                    getCBM(cbMsg);
                    getUserIN();
                    userMsg = bingT.translate(userMsg);

                    user1.setFavoriteGenera(userMsg);
                    pca = new PCA(user1.getUserVector());
                    user1.setUserVector();
                    user1.setPcaVector(pca.getStandardUser());
                    user1.setTopThree(pca.getTopThree());
                    chatBot.loopGeneraTitle(user1, pca, pca.getTopThree(), false);
                }
                else if (objective.equalsIgnoreCase(browseMovies)) {
                    cbMsg = "What is your favorite genera?";
                    getCBM(cbMsg);
                    getUserIN();
                    userMsg = bingT.translate(userMsg);

                    user1.setFavoriteGenera(userMsg);
                    pca = new PCA(user1.getUserVector());
                    user1.setUserVector();
                    user1.setPcaVector(pca.getStandardUser());
                    user1.setTopThree(pca.getTopThree());
                    chatBot.loopGeneraTitleMovie(user1, pca, pca.getTopThree(), false);
                }
                else if (objective.equalsIgnoreCase(request)) {
                    cbMsg = "Would you like to request for a book or a movie?";
                    getCBM(cbMsg);
                    getUserIN();
                    userMsg = bingT.translate(userMsg);

                    ParseNLP parseNLP = new ParseNLP(userMsg);
                    ArrayList<String> words = parseNLP.getWords();
                    String search = "";
                    for(String word : words) {
                        if(word.toLowerCase().contains("book")){
                            cbMsg = "What is the title of the book?";
                            getCBM(cbMsg);
                            getUserIN();
                            userMsg = bingT.translate(userMsg);

                            for (int i = 0; i < library.getBookList().size(); i++) {
                                //getCBM("in1");
                                if(library.getBookList().get(i).getTitle().toLowerCase().contains(userMsg.toLowerCase())){
                                    search = library.getBookList().get(i).getTitle();
                                    user1.updateTempBookList(library.getBookList().get(i));
                                    cbMsg = "Added " + search + " to cart";
                                    getCBM(cbMsg);
                                    break;
                                }

                            }
                            if(search == null){
                                cbMsg = "Sorry, we don't have that one.";
                                getCBM(cbMsg);
                                break;
                            }
                            break;
                        }
                        else if(word.toLowerCase().contains("movie")){
                            cbMsg = "What is the title of the movie?";
                            getCBM(cbMsg);
                            getUserIN();
                            userMsg = bingT.translate(userMsg);

                            for (int i = 0; i < gallery.getMovieList().size(); i++) {
                                //getCBM("in2");
                                if(gallery.getMovieList().get(i).getTitle().toLowerCase().contains(userMsg.toLowerCase())){
                                    search = gallery.getMovieList().get(i).getTitle();
                                    user1.updateTempMovieList(gallery.getMovieList().get(i));
                                    cbMsg = "Added " + search + " to cart";
                                    getCBM(cbMsg);
                                    break;
                                }
                            }
                            if(search == null){
                                cbMsg = "Sorry, we don't have that one.";
                                getCBM(cbMsg);
                                break;
                            }
                            break;
                        }
                    }
                }
                cbMsg = "Thank you for using this service, would you like to continue browsing?";
                getCBM(cbMsg);
                getUserIN();
                userMsg = bingT.translate(userMsg);

                boolean yes = chatBot.testReaction(userMsg); //can pass string here instead
                if(yes) {
                    innerRun = true;
                    outterRun = true;
                    break;
                } else {
                    innerRun = false;
                    outterRun = false;
                    break;
                }
            }
        }
        //Print books and movies borrowed during session.
        if(user1.getTempBookList().size()>0){
            getCBM("Books borrowed:");
            for (int i = 0; i < user1.getTempBookList().size(); i++) {
                getCBM(user1.getTempBookList().get(i).getBookDetails());
            }
        }
        else{
            getCBM("\nNo books borrowed");
        }
        if(user1.getTempMovieList().size()>0){
            getCBM("Movies borrowed:");
            for (int i = 0; i < user1.getTempMovieList().size(); i++) {
                getCBM(user1.getTempMovieList().get(i).getMovieDetails());
            }
        }
        else{
            getCBM("\nNo movies borrowed");
        }
        getCBM("Enjoy, until next time!");

    }


    public static void getCBM(String m) throws InterruptedException {
        Thread.sleep(10);
        cbMsg = "Chat Bot: "+m+"\n";
        textArea.append(cbMsg);
    }
    public static void getUserIN() throws InterruptedException {
        while(IN) {
            Thread.sleep(10);
        }
        userMsg = textInput.getText().toLowerCase(Locale.ROOT);
        textArea.append("User: "+userMsg + "\n");
        textInput.setText("");
        IN = true;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        IN = false;
    }
}
