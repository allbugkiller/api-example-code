
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