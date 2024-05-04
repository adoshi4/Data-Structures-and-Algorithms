# Sentiment Analysis
My project is a Sentiment Analysis algorithm
implementation using Logistic Regression. I
previously worked on a Sentiment Analysis
project, using NLTK. I wanted to implement the
algorithm this time. My implementation is
oused in the SentimentAnalysis.py file. I
have trained the model with a few sample data
points and then test it on a few strings.
## Stopwords and Bag of Words
I have derived a list of Stopwords using an
NLP library. That includes words like "the",
"and", and "I". This way, we can focus on
analyzing the words in the text that pertain
to Sentiment Analysis.
The Bags of Words algorithm is used to count
the number of times each individual word 
appears in a given input string, and creates a
numerical vector representation of it.
## Logistic Regression
The Logistic Regression starts with a lo
(odds) function, which is linear, consisting
of a feature vector (the Bag of Words output)
and a weight vector (Gradient Descent covered
in next section). The output of the linear
function has to be converted to a probability,
used to classify a given string based on a
threshold. We convert the output to odds first
and then convert to probability. A mapping of
the probabilities would give us the logistic
function.
## Optimization
I calculate the cross-entropy loss, a measure
of the classification performance of the
model. The model's weight parameter is updated
accordingly, over multiple epochs, in order to
minimize that loss function.
## Performance Evaluation
To measure the accuracy of the model, a
Confusion Matrix is used. That measures the
number of times a model makes an error on the
test data, through False Positives or
Negatives. For example, if "This movie is
awful" is classified as positive by the model,
but actually negative, then that is a False
Positive.
