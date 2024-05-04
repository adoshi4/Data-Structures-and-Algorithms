import pandas as pd
import numpy as np
import re
import nltk
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from math import exp

# NLTK package downloads
nltk.download('stopwords')
nltk.download('punkt')


def preprocess_text(text):
    """
    Preprocess the inputted data by converting everything
    to lowercase characters, removing stopwords such as "and"
    & "the".

    @property text, string representing the text to be processed
    by the function
    @return the text afte removing stopwords and converting to lowercase
    """
    if not isinstance(text, str):
        return ""  # Handle non-string data
    
    text = text.lower()
    text = re.sub(r'[^a-zA-Z\s]', '', text)
    words = word_tokenize(text)
    words = [word for word in words if word not in stopwords.words('english')]
    return ' '.join(words)

# Training dataset for the logistic regression
training_data = [
    {"text": "I love this movie!", "sentiment": "Positive"},
    {"text": "This movie was awful.", "sentiment": "Negative"},
    {"text": "An amazing film with great acting!", "sentiment": "Positive"},
    {"text": "I didn't like the movie.", "sentiment": "Negative"}
]

# Test dataset for the logistic regression to evaluate model
# performance
test_data = [
    {"text": "A great movie!", "sentiment": "Positive"},
    {"text": "This movie was boring.", "sentiment": "Negative"},
    {"text": "I hate this ice cream", "sentiment": "Negative"},
    {"text": "That soccer match was quite fun!", "sentiment": "Positive"},
]

# Convert the data to a Pandas dataframe
train_df = pd.DataFrame(training_data)
test_df = pd.DataFrame(test_data)

# Print out the dataframes
print("Training Dataset Columns:\n", train_df.columns)
print("\nTraining Dataset Sample:\n", train_df.head())

print("\nTest Dataset Columns:\n", test_df.columns)
print("\nTest Dataset Sample:\n", test_df.head())

# Assigning binary values to the positive/negative sentiment
sentiment_mapping = {
    "Positive": 1,
    "Negative": 0,
}

# Map the text data
train_df['sentiment'] = train_df['sentiment'].map(sentiment_mapping)
test_df['sentiment'] = test_df['sentiment'].map(sentiment_mapping)

# Pass text data through the preprocessing function
train_df['clean_text'] = train_df['text'].apply(preprocess_text)
test_df['clean_text'] = test_df['text'].apply(preprocess_text)

# Create vocabulary from the training set text corpus
vocabulary = set(word for text in train_df['clean_text'] for word in text.split())
vocab_list = sorted(list(vocabulary))

def text_to_bow(text, vocab):
    """
    Convert the text to a bag of words representation, which is a vector
    representing the count of each word, and included based on the vocabulary

    @property text, string representing text to be processed by the function
    @property vocab, the vocabulary we are using for the Bag of Words representation
    @returns Bag of Words vector representation of the text
    """
    words = text.split()
    bow = np.zeros(len(vocab))
    for word in words:
        if word in vocab:
            idx = list(vocab).index(word)
            bow[idx] += 1
    return bow

x_train = np.array([text_to_bow(text, vocab_list) for text in train_df['clean_text']])
y_train = train_df['sentiment'].values

x_test = np.array([text_to_bow(text, vocab_list) for text in test_df['clean_text']])
y_test = test_df['sentiment'].values

def sigmoid(z):
    """
    Compute the logistic function, mapping z to a probability
    value between 0 and 1

    @property z, float representing the value to be mapped
    @returns float output of the logistic function
    """
    return 1 / (1 + exp(-z))

def logistic_loss(y_true, y_pred):
    """
    Compute the cross-entropy less between the true and predicted probabilites.

    @property y_true, the true values
    @property y_pred, the predicted values
    @returns the calculated cross-entropy loss
    """
    y_pred = np.clip(y_pred, 1e-12, 1 - 1e-12)
    return -np.mean(y_true * np.log(y_pred) + (1 - y_true) * np.log(1 - y_pred))

def logistic_regression(x_train, y_train, learning_rate=0.01, epochs=1000, tol=1e-6, early_stopping=True, patience=10):
    """
    Logistic regression function, building out the log(odds) to probability.
    Utilize gradient descent to find the optimal weight over multiple
    epochs.

    @property x_train, x values of the training data
    @property y_train, labels of the training data
    @property learning_rate, the learning rate for gradient descent, rate to update parameters
    @property epochs, the number of epochs
    @property tol, early stop tolerance
    @property early_stopping, boolean value representing whether an early stop should be used
    @property patience, patience for early stop
    @returns tuple with the weight and bias in vector and float form
    """
    n_features = x_train.shape[1]
    weights = np.zeros(n_features)
    bias = 0
    no_improvement_epochs = 0
    last_loss = None

    for epoch in range(epochs):
        linear_model = np.dot(x_train, weights) + bias
        y_pred = np.array([sigmoid(z) for z in linear_model])
        dw = np.dot(x_train.T, (y_pred - y_train)) / len(y_train)
        db = np.sum(y_pred - y_train) / len(y_train)
        weights -= learning_rate * dw
        bias -= learning_rate * db
        loss = logistic_loss(y_train, y_pred)

        if epoch % 100 == 0:
            print(f"Epoch {epoch}: Loss {loss:.5f}")

        if last_loss is not None and abs(last_loss - loss) < tol:
            no_improvement_epochs += 1
            if early_stopping and no_improvement_epochs >= patience:
                print(f"Early stopping at epoch {epoch} due to no significant loss improvement.")
                break
        else:
            no_improvement_epochs = 0

        last_loss = loss
    return weights, bias

weights, bias = logistic_regression(x_train, y_train)

def predict(x, weights, bias):
    """
    The labels for the inputted test data on the logistic regresison model.

    @property x the x-values for the test datra
    @property weights, weight coefficients for the model, computed in logistic regression
    function
    @property bias, bias calculated for the logistic regression model
    @returns the predictions (1 or 0) for the test dataset
    """
    linear_model = np.dot(x, weights) + bias
    y_pred = np.array([sigmoid(z) for z in linear_model])
    return np.round(y_pred)

# Prediction values for the test dataset
y_pred = predict(x_test, weights, bias)

def evaluate(y_test, y_pred):
    """
    Evaluate the performance of the logistic regression classification
    model by calculating a Confusion Matrix. Finds all of the True/False
    Positives and Negatives. 

    @property y_test, the actual/correct labels for the test data
    @property y_pred, the prediction labels for test dataset
    """
    accuracy = np.sum(y_test == y_pred) / len(y_test)
    tp = np.sum((y_test == 1) & (y_pred == 1))
    tn = np.sum((y_test == 0) & (y_pred == 0))
    fp = np.sum((y_test == 0) & (y_pred == 1))
    fn = np.sum((y_test == 1) & (y_pred == 0))
    print("Accuracy:", accuracy)
    print("Confusion Matrix:")
    print(f"TP: {tp} | TN: {tn} | FP: {fp} | FN: {fn}")

evaluate(y_test, y_pred)
