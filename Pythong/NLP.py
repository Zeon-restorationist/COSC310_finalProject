from nltk.tokenize import sent_tokenize, word_tokenize
from nltk.sentiment import SentimentIntensityAnalyzer
import nltk

from main import nlp

nltk.download('punkt')
nltk.download("averaged_perceptron_tagger")
nltk.download('vader_lexicon')


class NLP:

    def __init__(self, text):
        self.sentence = text
        self.tokens = word_tokenize(self.sentence)

    def getPOS(self):
        self.POS = nltk.pos_tag(self.tokens)
        return self.POS

    def getName(self):
        pos_tag = nltk.pos_tag(nlp.getTokens())
        name = " "
        for index in range(0, len(pos_tag)):
            # print(pos_tag[index])
            if (pos_tag[index][1] == 'NNP'):
                name = pos_tag[index][0]
        return name

    def getTokens(self):
        return self.tokens

    def getSentiment(self):
        sia = SentimentIntensityAnalyzer()
        self.sentiment = sia.polarity_scores(self.sentence)
        neg = self.sentiment.get('neg')
        pos = self.sentiment.get('pos')
        compound = self.sentiment.get('compound')
        return compound

