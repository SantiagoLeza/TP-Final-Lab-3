package App;

import org.json.JSONException;
import org.json.JSONObject;

public class Review
{
    private String user;
    private String text;
    private int rating;

    public Review(String user, String text, int rating)
    {
        this.user = user;
        this.text = text;
        this.rating = rating;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }
    
    public JSONObject toJSON()
    {
        JSONObject json = new JSONObject();
        try
        {
            json.put("user", user);
            json.put("text", text);
            json.put("rating", rating);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return json;
    }
}
