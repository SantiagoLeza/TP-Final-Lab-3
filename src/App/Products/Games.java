package App.Products;

import App.AppDate;
import App.Review;

import java.util.ArrayList;

public class Games extends Product
{

    private ESRBClassification ESRB;
    private ArrayList <String> genders;
    private ArrayList <String> labels;
    private ArrayList <String> language;
    private ArrayList <String> platafomrs;
    private ArrayList <Review> reviews;
    private final AppDate releaseDate;
    private Boolean multiplayer;
    private String developer;
    private String editor;
    private float rating;

    /**
     * Use this constructor to create a new game.
     */
    public Games(String name, float price, ESRBClassification ESRB, AppDate releaseDate, Boolean multiplayer, String developer, String editor)
    {
        super(name, price);
        this.ESRB = ESRB;
        this.genders = new ArrayList<String>();
        this.labels = new ArrayList<String>();
        this.language = new ArrayList<String>();
        this.platafomrs = new ArrayList<String>();
        this.reviews = new ArrayList<Review>();
        this.releaseDate = releaseDate;
        this.multiplayer = multiplayer;
        this.developer = developer;
        this.editor = editor;
        rating = 0;
    }

    /**
     * Use this constructor when importing a game from a file.
     */
    public Games(String name, float price, ESRBClassification ESRB, AppDate releaseDate, Boolean multiplayer, String developer, String editor, float rating)
    {
        super(name, price);
        this.ESRB = ESRB;
        genders = new ArrayList<String>();
        this.labels = new ArrayList<String>();
        this.language = new ArrayList<String>();
        this.platafomrs = new ArrayList<String>();
        this.reviews = new ArrayList<Review>();
        this.releaseDate = releaseDate;
        this.multiplayer = multiplayer;
        this.developer = developer;
        this.editor = editor;
        this.rating = rating;
    }

    public ESRBClassification getESRB()
    {
        return ESRB;
    }

    public void setESRB(ESRBClassification ESRB)
    {
        this.ESRB = ESRB;
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

    public ArrayList<Review> getReviews()
    {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews)
    {
        this.reviews = reviews;
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

    public float getRating()
    {
        return rating;
    }

    public void setRating(float rating)
    {
        this.rating = rating;
    }


}
