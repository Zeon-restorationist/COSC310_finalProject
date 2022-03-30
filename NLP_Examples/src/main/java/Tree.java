import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

public class Tree {
    CoreDocument coreDocument;
    StanfordCoreNLP stanfordCoreNLP;
    public Tree(String input) {
        stanfordCoreNLP = Pipeline.getPipeline();
        coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);
    }
    public List<CoreSentence> getSentences(){
        return coreDocument.sentences();
    }
}
