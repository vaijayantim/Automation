/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constructcsvfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vaijayanti
 */
public class ConstructCSVFile {

    /**
     * @param args the command line arguments
     */
    static Map<String, String> value = new LinkedHashMap<>();
    static List header = new ArrayList();
    BufferedWriter fw = null;
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ConstructCSVFile obj = new ConstructCSVFile();
        String mappingfile = "C:\\Users\\vaijayanti\\Documents\\NetBeansProjects\\ConstructCSV\\Files\\Mapping_USF.csv";
        obj.initializeFile();
        int rows = 5;
        obj.readFile(mappingfile);
        obj.addHeader();
        for(int i=0;i<5;i++)
        {
            obj.constructCSVrow();
        }
        
       obj.closeFile();
    }
    public void closeFile() throws IOException
    {
         fw.close();
    }
    public void initializeFile() throws IOException
    {
        String csvfile = "C:\\Users\\vaijayanti\\Documents\\NetBeansProjects\\ConstructCSV\\Files\\USF_Cust_Corp_CSV_Generated.csv";
        fw = new BufferedWriter(new FileWriter(csvfile));
    }
    public static void readFile(String file) {

        String line = "";
        //String delim = ",";
        String delim = "\\|";
        int count=0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((line = br.readLine()) != null) {
                String[] lineval = line.split(delim);
              //  if((lineval[0] == null) || (lineval[3] == null))
              //      value.put((String)count,"empty");
                value.put(lineval[0], lineval[3]);
                header.add(lineval[4]);
                count++;
            }

        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {

        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            System.out.println("array size exceed..");
        }
      //  for (String key : value.keySet()) {
      //      System.out.println(key + " " + value.get(key));
     //   }

    }
    
    public void addHeader()
    {
        try
        {
            for(int i=0;i<header.size();i++)
            {
                String val = (String)(header.get(i));
                if(i!=0)
                {
                    fw.append("||");
                    fw.flush();
                }
                
                if(val!=null)
                    fw.append(val);
               
             }
            fw.newLine();
            fw.flush();  
        }
        catch(Exception e)
        {
            
        }
    }
    
    public void constructCSVrow() throws IOException
    {
        
        String val = "";
        
            for(String key : value.keySet())
            {
                String datatype = value.get(key);
                
                if("string".equals(datatype.toLowerCase()))
                {
                    val = generateString();
                   
                }
                else if("number".equals(datatype.toLowerCase()))
                {
                    Random rand = new Random();
                    val = String.valueOf(rand.nextFloat());
                   
                }
                else if("int".equals(datatype.toLowerCase()))
                {
                    Random rand = new Random();
                    val = String.valueOf(rand.nextInt());
                   
                }
               else if("date".equals(datatype.toLowerCase()))
                {
                    val = generateDate();
                   
                }
                else
                    val = "";
                //System.out.println("Val: "+val);
                if(!key.equals("1"))
                {
                    fw.append("||");
                    fw.flush();
                }
                
                    if(val!=null)
                fw.append(val);
               
            }
            fw.newLine();
            fw.flush();
       
        
    }
    public static String generateString()
    {
        Random rand = new Random();
        int length = rand.nextInt(10)+1;
        String characters = "abcdefghijklmnopqrstuvwxyz";
        char[] chars = new char[length];
        for(int i=0;i<length;i++)
            chars[i] = characters.charAt(rand.nextInt(25)+1);
        return new String(chars);
       // return "1234";
    }
    
    public static String generateDate()
    {
        Random rand = new Random();
        String newday="0";
        String newmonth="0";
        String date ="";
        String day = String.valueOf(rand.nextInt(28)+1);
        if(Integer.parseInt(day)<10)
            day=newday.concat(day);
       String month = String.valueOf(rand.nextInt(12)+1);
        if(Integer.parseInt(month)<10)
            month=newmonth.concat(month); 
        String year = String.valueOf(rand.nextInt(9999)+1000);
        date = date.concat(month);
        date = date.concat("/");
        date = date.concat(day);
        date = date.concat("/");
        date = date.concat(year);
        return date;
        
    }

}
