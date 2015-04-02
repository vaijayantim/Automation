/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valjson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vaijayanti
 */
public class validate {
    
    static Gson gson = new Gson();
    static Map<String, String> entityvalues = new LinkedHashMap<>();
    static Map<String, String> attributevalues = new LinkedHashMap<>();
    public static void main(String args[])
    {
        String jsonfile = "C:\\Users\\vaijayanti\\Documents\\JsonCreatorTest\\out\\100_USF_customer_merged_with_opco_20032015.json";
        generateRecord(jsonfile);
        //display();
    }
    
    public static void generateRecord(String file)
    {
        String delim="\\|";
        String line="";
        String json="";
        String[] row;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) 
        {
            while ((line = br.readLine()) != null)
            {
                row = line.split(delim);
                validateJsonRecord(row[1]);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(validate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(validate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void validateJsonRecord(String record)
    {
        //record = record.substring(1,record.length()-1);
        JsonArray entity = gson.fromJson(record,JsonArray.class);
              
           // System.out.println("Exception caught.."+e.getMessage());
        
        //System.out.println("hello");
        //JsonObject entityobject = entity.get(0).getAsJsonObject();
        String crosswalkValue = entity.get(0).getAsJsonObject().get("crosswalks").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString();
        System.out.println("Crosswalk value: "+crosswalkValue);
        JsonObject attributes = entity.get(0).getAsJsonObject().get("attributes").getAsJsonObject();
        getNestedvalues(attributes);
        //JsonArray attributeArray =(JsonArray) attributes;
      /*  for(int i=0;i<attributes.size();i++)
        {
            String element = attributes.get(0).getAsJsonObject().getAsString();
            String value = attributes.get(0).getAsJsonObject().get("value").getAsString();
            System.out.println("Element: "+element+"  Value: "+value);
            attributevalues.put(element,value);
        }*/
        
    }
    
   public static void getNestedvalues(JsonObject attributes)
    {
        for (Map.Entry<String, JsonElement> entry : attributes.entrySet()) {
            JsonElement jsonElements = entry.getValue();

            for (JsonElement jsonElement : jsonElements.getAsJsonArray()) {
                JsonObject attribute = jsonElement.getAsJsonObject();

                String attributeName = entry.getKey();
                if (entry.getKey().equalsIgnoreCase("SYYN") || entry.getKey().equalsIgnoreCase("USFN") || entry.getKey().equalsIgnoreCase("Address")||entry.getKey().equalsIgnoreCase("Phone")) {
                    JsonArray nestedAttributesArray = entry.getValue().getAsJsonArray();
                    if (nestedAttributesArray.get(0).isJsonObject()) {
                        if (nestedAttributesArray.get(0).getAsJsonObject().get("value").isJsonObject()) {
                            JsonObject parentAttributes = nestedAttributesArray.get(0).getAsJsonObject().get("value").getAsJsonObject();

                            getNestedvalues(parentAttributes);
                        }
                    }
                }
                String attributeValue = attribute.get("value").getAsString();

                attributevalues.put(attributeName, attributeValue);

            }
        }
    }
     
    public static void display()
    {
        for (String key : attributevalues.keySet()) {
            System.out.println(key + " " + attributevalues.get(key));
    }
}
}
