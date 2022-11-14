
/*
This file contains examples of how to interact with Lexigram APIs in Javacript.

Each of the examples inspect the responses printing some relevant properties.
For comprehensive documentation that includes information of all API responses
please visit http://docs.lexigram.io
*/



/* You can grab your API KEY from your user profile at https://app.lexigram.io */
var apiKey = "Bearer " + " COPY YOUR KEY HERE ";

/*
 Entity extraction from a note sample.
*/
function exampleEntityExtraction(){
  var text = "The patient was given some hydrocodone for control of her pain."+
           "The patient suffers from bulimia and eating disorder, bipolar disorder,"+
           " and severe hypokalemia. She thinks her potassium might again be low.";
  var data = { text: text };    
  var url = "https://api.lexigram.io/v1/extract/entities";

  var httpRequest = new XMLHttpRequest();
  httpRequest.open("POST", url, true);
  httpRequest.setRequestHeader("Content-Type", "application/json");
  httpRequest.setRequestHeader("Authorization", apiKey);
  httpRequest.onreadystatechange = function() {
    if (httpRequest.readyState != 4 || httpRequest.status != 200) {
      return;
    }
    var response = JSON.parse(httpRequest.responseText);
    
    /* For loop that inspects the response printing to console the extracted concepts.
       It prints the Lexigraph concept ID, the type of concepts extracted (problem, drug, etc)
       and the context (negation, speculation, ...) */
    for(var i = 0; i < response.matches.length; i++){
      console.log("Concept ID: ", response.matches[i].label + 
                  " types:", response.matches[i].types + 
                  " context: ", response.matches[i].contexts);
    }
    
  };
  httpRequest.send(JSON.stringify(data));    
}


/*
 Entity highlight from a note sample.
*/
function exampleHighlightEntities(){
  var text = "The patient was given some hydrocodone for control of her pain."+
             "The patient suffers from bulimia and eating disorder, bipolar disorder,"+
             " and severe hypokalemia. She thinks her potassium might again be low.";
  var data = {text: text};    
  var url = ="https://api.lexigram.io/v1/highlight/entities";

  var httpRequest = new XMLHttpRequest();
  httpRequest.open("POST", url, true);
  httpRequest.setRequestHeader("Content-Type", "application/json");
  httpRequest.setRequestHeader("Authorization", apiKey);
  httpRequest.onreadystatechange = function() {
    if (httpRequest.readyState != 4 || httpRequest.status != 200) {
      return;
    }
    var response = JSON.parse(httpRequest.responseText);
    
    /* Returns the html formated text from a note sample */
    console.log("The Response with html format", response);
  };
  httpRequest.send(JSON.stringify(data.text));    
}

