import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

import static edu.stanford.nlp.ling.CoreAnnotations.*;

public class NameExtract {
    public static void main(String[] args) {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        String text = "Hey! my name is Joel Johnson. I live in Kelowna";
        CoreDocument coreDocument = new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabelList = coreDocument.tokens();
        for(CoreLabel coreLabel: coreLabelList) {
            String ner = coreLabel.get(NamedEntityTagAnnotation.class);
            System.out.println(coreLabel.originalText() + " = "+ ner);
        }
    }
}
