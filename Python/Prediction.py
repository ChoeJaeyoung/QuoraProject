import pandas as pd
import numpy as np
import pickle

model = pickle.load(open('finalized_model.pkl', 'rb'))
print ("Model_load_success")

def model_prediction(test_data, model):
    prediction = model.predict(test_data)
    print ("prediction : ", prediction)
    return prediction