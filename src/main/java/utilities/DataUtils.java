package utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtils {
    private static final String Test_Data_Path = "src/test/resources/TestData/";

    //TODO:reading data from json file
    public static String getJsonData(String filename, String field) throws FileNotFoundException  //file+field
    {
        try {
            //define object file reader
            FileReader reader = new FileReader(Test_Data_Path + filename + ".json");
            //src/test/resources/testdata+validlogindata+.json
            //parse json directry into jsonelement
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return jsonElement.getAsJsonObject().get(field).getAsString();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    //TODO:reading data from properties file
    public static String getpropertyvalue(String filename, String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(Test_Data_Path + filename + ".properties"));
        return properties.getProperty(key);
    }
}
