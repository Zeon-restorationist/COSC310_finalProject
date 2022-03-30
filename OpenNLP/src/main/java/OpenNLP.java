package main.java;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OpenNLP {
    String [] tokens;
    String text;
    public OpenNLP(String text) {
        this.text = text;
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        tokens = tokenizer.tokenize(text);


    }

    public void findNames() throws IOException {
        InputStream tokenModelIn = null;
        InputStream posModelIn = null;

        try {
            // Parts-Of-Speech Tagging
            // reading parts-of-speech model to a stream
            posModelIn = new FileInputStream("src/en-ner-person.bin");

            // loading the parts-of-speech model from stream
            POSModel posModel = new POSModel(posModelIn);
            // initializing the parts-of-speech tagger with model
            POSTaggerME posTagger = new POSTaggerME(posModel);
            // Tagger tagging the tokens
            String tags[] = posTagger.tag(tokens);
            // Getting the probabilities of the tags given to the tokens
            double probs[] = posTagger.probs();

            System.out.println("Token\t:\tTag\t:\tProbability\n---------------------------------------------");
            for(int i=0;i<tokens.length;i++){
                System.out.println(tokens[i]+"\t:\t"+tags[i]+"\t:\t"+probs[i]);
            }

        }
        catch (IOException e) {
            // Model loading failed, handle the error
            e.printStackTrace();
        }
        finally {
            if (tokenModelIn != null) {
                try {
                    tokenModelIn.close();
                }
                catch (IOException e) {
                }
            }
            if (posModelIn != null) {
                try {
                    posModelIn.close();
                }
                catch (IOException e) {
                    return;
                }
            }
        }
    }




    public void printTokens() {
        for (String t:tokens) {
            System.out.println(t);
        }
    }
}
