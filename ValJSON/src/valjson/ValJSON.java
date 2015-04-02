/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valjson;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vaijayanti
 */
public class ValJSON {

    /**
     * @param args the command line arguments
     */

   //static Multimap<String,String> mapvalue = ArrayListMultimap.create();
   //static Multimap<String,String> jsonvalue = ArrayListMultimap.create();
    static Map<String, String> mapvalue = new LinkedHashMap<>();
    static Map<String, String> jsonvalue = new LinkedHashMap<>();
    public static void main(String[] args) {
        // TODO code application logic here
      String csvfile = "C:\\Users\\vaijayanti\\Documents\\NetBeansProjects\\ConstructCSV\\Files\\USF_Cust_Corp_CSV_Generated.csv";
      String mappingfile = "C:\\Users\\vaijayanti\\Documents\\NetBeansProjects\\ConstructCSV\\Files\\Mapping_USF.csv";
      String jsonfile = "C:\\Users\\vaijayanti\\Documents\\JsonCreatorTest\\out\\100_USF_customer_merged_with_opco_20032015.json";

      readFile(mappingfile);

    }
    public static void readFile(String file) {

        String line = "";
        //String delim = ",";
        String delim = "\\|";
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((line = br.readLine()) != null) {
                String[] lineval = line.split(delim);
                mapvalue.put(lineval[0], lineval[1]);
                //mapvalue.put(lineval[0], lineval[2]);
             /*   if(lineval[2]!=null)
                {
                    ECvalue.put(lineval[0], lineval[2]);
                }
            */
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            System.out.println("array size exceed..");
        }
         //  Set<String> keys =mapvalue.keySet();
      //  for (String key : mapvalue.keySet()) {
       //     System.out.println(key + " " + mapvalue.get(key));
        //}

    }
    
    public static void readCSV(String file)
    {
        String line;
        String delim = "||";
        StringTokenizer st = null;
        int tokenno = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) 
        {
            while ((line = br.readLine()) != null) 
            {
                st = new StringTokenizer(line,delim);
                while(st.hasMoreTokens())
                {
                    tokenno++;
                    mapvalue.put(String.valueOf(tokenno),st.nextToken());
                }
            }
         }

       catch (FileNotFoundException ex) {
           Logger.getLogger(ValJSON.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(ValJSON.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    public static void validateJSON(String file)
    {
       
        

    }
}
