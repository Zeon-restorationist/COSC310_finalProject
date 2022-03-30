package com.example.chatbotsimple;

import java.util.List;

public class ChatBot {
	String greeting = "Hello, my name is chat bot, your personal library assistant";
	String qName = "What is your name?";
	String q1a = "Would you like to: browse books, browse movies, play trivia, or request an item?";
	String q1b = "You entered %s, is that right?";
	int level = 1;
	static String objective = "";
	static String browseMovies = "movies";
	static String browseBooks = "books";
	static String trivia = "trivia";
	static String request = "request";
	NLPSimple parse;
	String chatBotReply = "";
	String userReply = "";
	private Person person;
	String stage = "question";
	int phase = 1;


	public boolean testReaction() {
		boolean happy = false;
		parse = new NLPSimple(userReply);
		String sentiment = parse.getSentiment();
		if (sentiment != "Negative") {
			happy = true;
		}
		return happy;
	}


	public ChatBot() {
		person = new Person();

	}

	public String getObjective() {
		String objective = "redo";
		parse = new NLPSimple(userReply);
		List<String> reply = parse.getWords();
		for (String s : reply) {
			if (s.equalsIgnoreCase(browseMovies)) {
				objective = browseMovies;
			} else if (s.equalsIgnoreCase(browseBooks)) {
				objective = browseBooks;
			} else if (s.equalsIgnoreCase(trivia)) {
				objective = trivia;
			} else if (s.equalsIgnoreCase(request)) {
				objective = request;
			}

		}
		return objective;


	}

	public String askQuestion() {

		if (level == 1) {
			if(phase == 1){
				phase = 2;
				chatBotReply = q1a;
			}
			if(phase == 2) {
				phase = 3;
				chatBotReply = String.format(q1b,objective);
			}
			if(phase == 3) {
				if(testReaction()) {
					phase = 4;
					chatBotReply =  String.format("Now executing, %s",objective);
				}
				else {
					phase = 1;
					chatBotReply = "Sorry, im not sure i understand";
				}
			}

		} else if (level == 2) {
			chatBotReply = " level 2";

		}
		return chatBotReply;

		//String out = String.format(question, name);


	}


}