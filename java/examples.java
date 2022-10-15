
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