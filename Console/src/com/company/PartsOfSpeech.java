import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import java.util.List;

public class PartsOfSpeech {

    public static void main(String[] args) {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        String text = "hey, my name is joel. i live in Kelowna. i want to find books regarding the newest technologies";
        CoreDocument coreDocument = new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabelList = coreDocument.tokens();
        int index = 0;
        String lemma = " ";
        for(CoreLabel coreLabel: coreLabelList) {
            lemma = coreLabel.lemma();
            int start = coreLabel.beginPosition();
            int end = coreLabel.endPosition();
            String ner = coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class);
            if (!ner.startsWith("O")) {
                System.out.println(coreLabel.originalText() + " = "+ ner);
            }
            String ot = coreLabel.originalText();
            String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            System.out.println(ot + "/" + lemma+ " POS=" + pos + " position:["+ start + ":" + end+"]");
        }
    }

}
