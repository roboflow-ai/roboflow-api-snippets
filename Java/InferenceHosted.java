import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class InferenceHosted {
    public static void main(String[] args) {
        String imageURL = "https://i.imgur.com/PEEvqPN.png"; // Replace Image URL
        String API_KEY = ""; // Your API Key
        String MODEL_ENDPOINT = "xx-your-model--1"; // model endpoint

        // Upload URL
        String uploadURL = "https://infer.roboflow.com/" + MODEL_ENDPOINT + "?access_token=" + API_KEY + "&image="
                + URLEncoder.encode(imageURL, StandardCharsets.UTF_8);

        // Http Request
        HttpURLConnection connection = null;
        try {
            // Configure connection to URL
            URL url = new URL(uploadURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", Integer.toString(uploadURL.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            // Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(uploadURL);
            wr.close();

            // Get Response
            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
