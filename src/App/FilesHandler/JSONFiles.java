package App.FilesHandler;

import App.Exceptions.FileErrorException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class JSONFiles
{
    private String fileName;
    
    public JSONFiles(String fileName)
    {
        this.fileName = fileName;
    }
    
    public void writeJSON(JSONArray ja) throws FileErrorException
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(ja.toString());
            writer.flush();
        }
        catch (IOException e)
        {
            throw new FileErrorException("Error writing JSON file");
        }
    }
    
    public JSONArray readJSON() throws FileErrorException, JSONException
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null)
            {
                sb.append(line);
                line = reader.readLine();
            }
        }
        catch (IOException e)
        {
            throw new FileErrorException("Error reading JSON file");
        }
        
        return new JSONArray(sb.toString());
    }
    
    
    
}
