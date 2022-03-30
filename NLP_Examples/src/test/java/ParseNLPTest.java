import edu.stanford.nlp.pipeline.CoreSentence;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class ParseNLPTest extends TestCase {
    ParseNLP parse;
    public ParseNLPTest() {
        String text = "hi, my name is joel. i am from Kelowna, i am going " +
                "on vacation in Switzerland " +
                "what is your name? i dont like AIs, how old are you?"+
                "buddy is a dog, he dislikes meatballs.";

        parse = new ParseNLP(text);
    }

    public void testGetPosList() {
    }

    public void testGetSentences() {
        List<CoreSentence> sentences = parse.getSentences();
        System.out.println("start----sentences----");
        System.out.println(sentences.size() + " sentences");
        System.out.println(sentences);
        System.out.println("end----sentences----");
    }

    public void testGetStringList() {
        System.out.println("start----strings----");
        ArrayList<String> strings = parse.getStringList();
        System.out.println(strings);
        System.out.println("end----strings----");
    }

    public void testGetLemmaList() {
        System.out.println("start----lemmas----");
        ArrayList<String> lemmaList = parse.getCoreLemmaList();
        System.out.println(lemmaList);
        System.out.println("end----lemmas----");

    }

    public void testgetPosList() {
        System.out.println("start----POSlist----");
        ArrayList<String> posStrings = parse.getPosList();
        System.out.println(posStrings);
        System.out.println("end----POSlist----");

    }


    public void testGetNames() {
        System.out.println("start----Namelist----");
        ArrayList<String> nameList = parse.getNames();

        System.out.println(nameList);
        System.out.println("end----Namelist----");

    }

    public void testGetCoreLabelList() {
        System.out.println(parse.getCoreLabelList());
    }

    public void testGetWords() {
        System.out.println(parse.getWords());
    }
}