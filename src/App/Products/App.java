package App.Products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class App extends Product
{
    private String function;


    // Para crear una nueva app
    public App(String name, float price, ESRBClassification ESRB, String function)
    {
        super(name, price, ESRB);
        this.function= function;
    }

    // Para pasar de JSONObject a objeto
    public App( JSONObject bjson ) throws JSONException
    {

       super(
               new JSONObject("{" + "id:" + bjson.get("id").toString() + "}"),
               new JSONObject("{" + "name:" + bjson.get("name").toString() + "}"),
               new JSONObject("{" + "price:" + bjson.get("price").toString() + "}"),
               (JSONArray) bjson.get("reviews"),
               new JSONObject("{" + "ESRB:" + bjson.get("ESRB").toString() + "}"),
               new JSONObject("{" + "income:" + bjson.get("income").toString() + "}")
        );

        this.function = bjson.get("function").toString();
    }

    public String getFunction ()
    {
        return function;
    }

    public void setFunction(String function)
    {
        this.function = function;
    }

    @Override
    public String toString ()
    {
        return super.toString() + "\n Function: " +function;
    }

    @Override
    public JSONObject toJSON()
    {
        JSONObject json = super.toJSON();
        try
        {
            json.put("function", function);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return json;
    }
}
