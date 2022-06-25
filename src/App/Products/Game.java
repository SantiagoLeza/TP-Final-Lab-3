package App.Products;

import App.AppDate;
import App.Review;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Game extends Product
{

    private ArrayList <String> genders;
    private ArrayList <String> labels;
    private ArrayList <String> language;
    private ArrayList <String> platafomrs;
    private final AppDate releaseDate;
    private Boolean multiplayer;
    private String developer;
    private String editor;
    private float sizeGB;

    /**
     * Use this constructor to create a new game.
     */
    public Game(String name, float price, ESRBClassification ESRB, AppDate releaseDate, Boolean multiplayer, String developer, String editor, float sizeGB)
    {
        super(name, price, ESRB);
        this.genders = new ArrayList<>();
        this.labels = new ArrayList<>();
        this.language = new ArrayList<>();
        this.platafomrs = new ArrayList<>();
        this.releaseDate = releaseDate;
        this.multiplayer = multiplayer;
        this.developer = developer;
        this.editor = editor;
        this.sizeGB = sizeGB;
    }

    /**
     * Use this constructor when importing a game from a file.
     */
    public Game(int id, String name, float price, ArrayList<Review> reviews, ESRBClassification ESRB, AppDate releaseDate, Boolean multiplayer, String developer, String editor, float sizeGB)
    {
        super(id, name, price, reviews, ESRB);
        genders = new ArrayList<>();
        this.labels = new ArrayList<>();
        this.language = new ArrayList<>();
        this.platafomrs = new ArrayList<>();
        this.releaseDate = releaseDate;
        this.multiplayer = multiplayer;
        this.developer = developer;
        this.editor = editor;
        this.sizeGB = sizeGB;
    }

    public Game( JSONObject obJSOn ) throws JSONException
    {
        super(

                new JSONObject("{" + "id:" + obJSOn.get("id").toString() + "}"),
                new JSONObject("{" + "name:" + obJSOn.get("name").toString() + "}"),
                new JSONObject("{" + "price:" + obJSOn.get("price").toString() + "}"),
                (JSONArray) obJSOn.get("reviews"),
                new JSONObject("{" + "ESRB:" + obJSOn.get("ESRB").toString() + "}"),
                new JSONObject("{" + "income:" + obJSOn.get("income").toString() + "}")
        );

        JSONArray gendresArray = (JSONArray) obJSOn.get("genders");
        ArrayList<String> arrayListGenders = new ArrayList<>();
        for (int i = 0; i < gendresArray.length(); i++)
        {
            arrayListGenders.add(gendresArray.get(i).toString());
        }

        JSONArray labelsArray = (JSONArray) obJSOn.get("labels");
        ArrayList<String> arrayListLabels = new ArrayList<>();
        for (int i = 0; i < labelsArray.length(); i++)
        {
            arrayListLabels.add(labelsArray.get(i).toString());
        }

        JSONArray lenguagesArray = (JSONArray) obJSOn.get("languages");
        ArrayList<String> arrayListLenguages = new ArrayList<>();
        for (int i = 0; i < lenguagesArray.length(); i++)
        {
            arrayListLenguages.add(lenguagesArray.get(i).toString());
        }

        JSONArray platafomrsArray = (JSONArray) obJSOn.get("platafomrs");
        ArrayList<String> arrayListPlatafomrs = new ArrayList<>();
        for (int i = 0; i < platafomrsArray.length(); i++)
        {
            arrayListPlatafomrs.add(platafomrsArray.get(i).toString());
        }

        JSONObject releaseDateObject = (JSONObject) obJSOn.get("releaseDate");
        AppDate releaseDate = new AppDate(Integer.parseInt(releaseDateObject.get("day").toString()), Integer.parseInt(releaseDateObject.get("month").toString()), Integer.parseInt(releaseDateObject.get("year").toString()));


        this.genders = arrayListGenders;
        this.labels = arrayListLabels;
        this.language = arrayListLenguages;
        this.platafomrs = arrayListPlatafomrs;
        this.releaseDate = releaseDate;
        this.multiplayer = Boolean.parseBoolean(obJSOn.get("multiplayer").toString());
        this.developer = obJSOn.get("developer").toString();
        this.editor = obJSOn.get("editor").toString();
        this.sizeGB = Float.parseFloat(obJSOn.get("size").toString());
    }



    public ArrayList<String> getGenders()
    {
        return genders;
    }

    public void setGenders(ArrayList<String> genders)
    {
        this.genders = genders;
    }

    public ArrayList<String> getLabels()
    {
        return labels;
    }

    public void setLabels(ArrayList<String> labels)
    {
        this.labels = labels;
    }

    public ArrayList<String> getLanguage()
    {
        return language;
    }

    public void setLanguage(ArrayList<String> language)
    {
        this.language = language;
    }

    public ArrayList<String> getPlatafomrs()
    {
        return platafomrs;
    }

    public void setPlatafomrs(ArrayList<String> platafomrs)
    {
        this.platafomrs = platafomrs;
    }
    
    public AppDate getReleaseDate()
    {
        return releaseDate;
    }

    public Boolean getMultiplayer()
    {
        return multiplayer;
    }

    public void setMultiplayer(Boolean multiplayer)
    {
        this.multiplayer = multiplayer;
    }

    public String getDeveloper()
    {
        return developer;
    }

    public void setDeveloper(String developer)
    {
        this.developer = developer;
    }

    public String getEditor()
    {
        return editor;
    }

    public void setEditor(String editor)
    {
        this.editor = editor;
    }

    public float getSizeGB()
    {
        return sizeGB;
    }

    public void setSizeGB(float sizeGB)
    {
        this.sizeGB = sizeGB;
    }

    @Override
    public String toString()
    {
        return super.toString() +
                ", genders" + genders +
                ", labels=" + labels +
                ", language=" + language +
                ", platafomrs=" + platafomrs +
                ", releaseDate=" + releaseDate +
                ", multiplayer=" + multiplayer +
                ", developer='" + developer + '\'' +
                ", editor='" + editor + '\'' +
                ", size=" + sizeGB;
    }

    @Override
    public JSONObject toJSON()
    {
        try
        {
            JSONObject jo = super.toJSON();
            
            JSONArray joGenders = new JSONArray();
            for(String i : genders)
            {
                joGenders.put(i);
            }
            
            jo.put("genders", joGenders);
            
            JSONArray joLabels = new JSONArray();
            for(String i : labels)
            {
                joLabels.put(i);
            }
            
            jo.put("labels", joLabels);
            
            JSONArray joLanguage = new JSONArray();
            for(String i : language)
            {
                joLanguage.put(i);
            }
            
            jo.put("languages", joLanguage);
            
            JSONArray joPlatafomrs = new JSONArray();
            for(String i : platafomrs)
            {
                joPlatafomrs.put(i);
            }
            
            jo.put("platafomrs", joPlatafomrs);
            
            JSONObject joReleaseDate = new JSONObject();
            joReleaseDate.put("day", releaseDate.getDay());
            joReleaseDate.put("month", releaseDate.getMonth());
            joReleaseDate.put("year", releaseDate.getYear());

            jo.put("releaseDate", joReleaseDate);
            
            jo.put("multiplayer", multiplayer);
            jo.put("developer", developer);
            jo.put("editor", editor);
            jo.put("size", sizeGB);
            
            return jo;
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
