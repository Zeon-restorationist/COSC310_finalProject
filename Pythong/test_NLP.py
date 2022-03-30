import NLP


def test_get_name():
    nlp = NLP.NLP("i like butss")
    print(nlp.sentence)


def test_get_pos():
    nlp = NLP.NLP("i like butss")
    print(nlp.tokenize())


def test_get_pos():
    nlp = NLP.NLP("my name is joel i like this game")
    #nlp = NLP.NLP("i like butss")

    print(nlp.getPOS())

