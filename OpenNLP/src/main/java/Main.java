package main.java;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String sentence = " Hi, i am joel. How are you? Welcome to Tutorialspoint. "
                + "We provide free tutorials on various technologies";
        OpenNLP openNLP = new OpenNLP(sentence);
        openNLP.printTokens();
        openNLP.findNames();

    }
}
