"""
This file contains examples of how to interact with Lexigram APIs in Python
Each of the examples inspect the responses printing some relevant properties.
For comprehensive documentation that includes information of all API responses
please visit http://docs.lexigram.io
"""

import requests
import json

# You can grab your API KEY from your user profile at https://app.lexigram.io
apiKey = "Bearer " + "YOUR_COPY_HERE"


# Entity extraction from a note sample.
def exampleEntityExtraction():
    url = "https://api.lexigram.io/v1/extract/entities"
    text = "The patient was given some hydrocodone for control of her pain." + \
           "The patient suffers from bulimia and eating disorder, bipolar disorder," + \
           "and severe hypokalemia. She thinks her potassium might again be low."

    r = requests.post(url, data=json.dumps({'text': text}),
                      headers={'Authorization': apiKey, 'Content-Type': 'application/json'})
    response = json.loads(r.text)

    '''For loop that inspects the response printing to console the extracted concepts.
 