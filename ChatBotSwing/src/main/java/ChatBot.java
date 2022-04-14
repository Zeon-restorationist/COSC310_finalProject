import java.util.*;

public class ChatBot {
	private String browseMovies = "browse movies";
	private String browseBooks = "browse books";
	private String trivia = "trivia";
	private String request = "request";
	Library library = new Library();
	Gallery gallery = new Gallery();
	Scanner sc = new Scanner(System.in);
	private String p1 = "yes";
	private String p2 = "yeah";
	private String p3 = "yep";
	private String p4 = "yeet";
	private String p5 = "sure";
	private String p6 = "y";
	private ArrayList<String> positiveFeedBack = new ArrayList<>();
	private Person person;
	private ArrayList<String> statements = new ArrayList<>();
	String reply;
	public ChatBot(){
		statements.add("Hello, my name is Haro your personal entertainment assistant"); //0
		statements.add("I would recommend the following book(s): "); //1
		statements.add("May I suggest %s(type yes/y to add to checkout, otherwise type no/n): "); //2
		statements.add("Today you've checked out the following book(s): "); //3
		statements.add("Today you've checked out the following movie(s): "); //4
		statements.add("Would you require additional service? (Type yes/y for more features)"); //5
		positiveFeedBack.add(p1);
		positiveFeedBack.add(p2);
		positiveFeedBack.add(p3);
		positiveFeedBack.add(p4);
		positiveFeedBack.add(p5);
		positiveFeedBack.add(p6);

	}
	public static int getInt() {
		return 2;
	}
	public boolean testReaction(String reply) {
		boolean happy = false;
		ParseNLP parse = new ParseNLP(reply);
		String sentiment = parse.getSentiment();
		if (!sentiment.equalsIgnoreCase("negative")) {
			happy = true;
		}
		return happy;
	}

	//.next usage noted
	//PCA loop until user picks a book.
	//Library loopgeneratitle
	public void loopGeneraTitle(Person person,PCA pca, ArrayList<String> suggest, boolean last) throws InterruptedException, java.io.IOException {
		boolean happy = false;
		boolean addToCart = false;
		boolean continueBrowsing = false;
		int loopNum = 0;
		for (String s: suggest) {
			if(loopNum>suggest.size()) {
				return;
			}
			getConsolation(loopNum);
			GUI.cbMsg = "Would you like to browse something in our "+s + " section?";
			GUI.getCBM(GUI.cbMsg);
			GUI.getUserIN();
			reply = GUI.userMsg;
			happy = testReaction(reply);
			if(happy) {
				GUI.cbMsg = "That's great";
				GUI.getCBM(GUI.cbMsg);
				ArrayList<String> titles = library.getTitleList(library.getGeneraList(s));
				for (String t:titles) {
					GUI.cbMsg = "Can i suggest: "+t+ " ?";
					GUI.getCBM(GUI.cbMsg);
					GUI.getUserIN();
					reply = GUI.userMsg;
					addToCart = testReaction(reply);
					if(addToCart) {
						person.updateTempBookList(library.byTitle(t));
						GUI.cbMsg = "Added the book: " + t + " to checkout list. \n Continue browsing?";
						GUI.getCBM(GUI.cbMsg);
						GUI.getUserIN();
						reply = GUI.userMsg;
						continueBrowsing = testReaction(reply);
						if(!continueBrowsing) {
							return;
						}
						else {
							continue;
						}
					}
				}
			}
			loopNum++;
		}
	}
	//Gallery loopgeneratitle
	public void loopGeneraTitleMovie(Person person, PCA pca, ArrayList<String> suggest, boolean last) throws InterruptedException, java.io.IOException {

		Scanner sc = new Scanner(System.in);
		boolean happy = false;
		boolean addToCart = false;
		boolean continueBrowsing = false;
		int loopNum = 0;
		for (String s: suggest) {
			if(loopNum>suggest.size()) {
				return;
			}
			GUI.cbMsg = "Would you like to browse something in our "+s + " section?";
			GUI.getCBM(GUI.cbMsg);
			GUI.getUserIN();
			reply = GUI.userMsg;
			happy = testReaction(reply);
			if(happy) {
				GUI.cbMsg = "Thats great";
				GUI.getCBM(GUI.cbMsg);
				ArrayList<String> titles = gallery.getTitleList(gallery.getGeneraList(s));
				for (String m:titles) {
					GUI.cbMsg = "Can i suggest: "+m+ " ?";
					GUI.getCBM(GUI.cbMsg);
					GUI.getUserIN();
					reply = GUI.userMsg;
					addToCart = testReaction(reply);
					if(addToCart) {
						person.updateTempMovieList(gallery.byTitle(m));
						GUI.cbMsg = "Added the movie: " + m + " to checkout list. \n Continue browsing?";
						GUI.getCBM(GUI.cbMsg);
						GUI.getUserIN();
						reply = GUI.userMsg;
						continueBrowsing = testReaction(reply);
						if(!continueBrowsing) {
							return;
						}
						else {
							continue;
						}
					}
				}
			}
			loopNum++;
		}

	}


	public String getConsolation(int loopNum) {
		String consolation = " ";
		switch (loopNum) {
			case(1): {
				consolation = "Searching...";
				break;
			}
			case(2): {
				consolation = "Ok, Searching...";
				break;
			}
			case(3): {
				consolation = "Ok, lets try, Searching...";
				break;
			}
			default: {
				consolation = "Searching";
			}

		}
		return consolation;
	}

	public String getStatement(int i) {
		String q = "";

		if (statements.size() >i ) {
			q = statements.get(i);
			if (q.contains("%s")) {
				q = String.format(q,"");
			}
			return q;

		}
		else
			return "I can't think of anything else";
	}

	private ArrayList<Person> people = new ArrayList<>();

	public String askByName(String question, String name) {
		String out = String.format(question,name);
		return out;
	}

}