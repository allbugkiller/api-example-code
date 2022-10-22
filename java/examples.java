
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

    /*
    This file contains examples of how to interact with Lexigram APIs in Java.

    Each of the examples inspect the responses printing some relevant properties.
    For comprehensive documentation that includes information of all API responses
    please visit http://docs.lexigram.io
    */
public class examples {
    /*
    Replace YOUR_KEY_HERE with your API KEY from your user profile at https://app.lexigram.io
    */
    private static String API_KEY = "Bearer " + "YOUR_KEY_HERE";

    public static void main(String[] args) throws UnirestException {
        exampleEntityExtraction();
        exampleHighlightEntities();
        exampleSearchConcepts();
        exampleConcept();
        exampleConceptAncestors();
        exampleConceptDescendants();
    }

    /*
    Entity extraction from a note sample.
    */
    private static void exampleEntityExtraction() throws UnirestException {
        String url = "https://api.lexigram.io/v1/extract/entities";
        String text = "The patient was given some hydrocodone for control of her pain."+
                "The patient suffers from bulimia and eating disorder, bipolar disorder,"+
                " and severe hypokalemia. She thinks her potassium might again be low.";

        JSONObject data= new JSONObject();
        data.put("text", text);
        HttpResponse<JsonNode> response = Unirest.post(url)
                .header("authorization", API_KEY)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(data)
                .asJson();
        JSONObject body = new JSONObject(response.getBody());
        JSONArray result = body.getJSONArray("array").getJSONObject(0).getJSONArray("matches");

        /*
        prints the extracted concepts
        */
        for(int i = 0; i < result.length(); i++) {
            JSONObject item = result.getJSONObject(i);
            String types = item.getJSONArray("types").toString() + " ";
            String context = item.getJSONArray("contexts").toString() + " ";

            System.out.println("Concept ID: "+ item.getString("id") +
                    "Concept label: "+ item.getString("label") +
                    " types:"+ types +
                    " context: "+ context);
        }
        for(int i = 0; i < result.length(); i++) {
            JSONObject item = result.getJSONObject(i);
            String types = item.getJSONArray("types").toString() + " ";
            String context = item.getJSONArray("contexts").toString() + " ";

            System.out.println("Concept ID: "+ item.getString("id") +
                               "Concept label: "+ item.getString("label") +
                               " types:"+ types +
                               " context: "+ context);
        }
    }

    /*
    Entity highlight from a note sample.
    */
    private static void exampleHighlightEntities() throws UnirestException {
        String text = "The patient was given some hydrocodone for control of her pain."+
                "The patient suffers from bulimia and eating disorder, bipolar disorder,"+
                " and severe hypokalemia. She thinks her potassium might again be low.";
        JSONObject data= new JSONObject();
        data.put("text", text);
        String url = "https://api.lexigram.io/v1/highlight/entities";


        HttpResponse<JsonNode> response = Unirest.post(url)
                .header("authorization", API_KEY)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(data)
                .asJson();
        JSONObject body = new JSONObject(response.getBody());

        /*
        Returns the html formatted text from the note sample
        */
        System.out.println("Highlighted Option" + body);
    }

    /*
    Keyword search of "diabetes"
    */
    private static void exampleSearchConcepts() throws UnirestException {
        String keyword = "diabetes";
        String url = "https://api.lexigram.io/v1/lexigraph/search?q="+ keyword;
        HttpResponse<JsonNode> response = Unirest.get(url)
                .header("authorization", API_KEY)
                .asJson();
        JSONObject body = new JSONObject(response.getBody());
        JSONArray result = body.getJSONArray("array").getJSONObject(0).getJSONArray("conceptSearchHits");

        /*
        prints concepts found from the search of diabetes
        */