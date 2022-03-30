package com.example.chatbotsimple;

import java.util.List;

import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;

public class NLPSimple {

    List<Sentence> sentences;
    Sentence sent;
    Document doc;
    public NLPSimple(String input) {
        doc = new Document(input);

    }
    public List<Sentence> getSentences() {
        List<Sentence> sentences = doc.sentences();
        return sentences;
    }
    public Sentence getSentence() {
        Sentence sentence = doc.sentences().get(0);
        return sentence;
    }
    public List<String> getWords() {
        sent = sentences.get(0);
        List<String> words = sent.words();
        return words;
    }
    public String getSentiment() {
        sent = sentences.get(0);
        String sentiment = sent.sentiment().toString();
        return sentiment;
    }
    public String getName() {
        String userName = "Buddy";
        sent = sentences.get(0);
        List<String> names = sent.nerTags();
        List<String> words = sent.words();
        int index = 0;
        for (String name:names) {
            if(name.equalsIgnoreCase("PERSON")) {
                userName = words.get(index).toString();
                break;
            }
            index++;
        }
        return userName;
    }


}