package sk.itsovy.projectGLshop;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Internet {

    public String getRequest()
    {
        String rslt="";

        // Setting URL
        String url_str = "http://data.fixer.io/api/latest?access_key=2fbb3c1dc0e7c39d13453ad95551cafa";
        try {

            URL url = new URL(url_str);

            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();
            jsonobj = jsonobj.getAsJsonObject("rates");

            //System.out.println(jsonobj);

            rslt = jsonobj.get("USD").getAsString();
            System.out.println(rslt);

        }catch (Exception e){
            e.printStackTrace();
        }
        return rslt;
    }

}

