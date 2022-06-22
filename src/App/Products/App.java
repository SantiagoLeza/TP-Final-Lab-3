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
            (JSONObject) bjson.get("id"),
            (JSONObject) bjson.get("name"),
            (JSONObject) bjson.get("price"),
            (JSONArray) bjson.get("reviews"),
            (JSONObject) bjson.get("ESRB"),
            (JSONObject) bjson.get("income")
        );

        this.function = bjson.get("function").toString();

    }

    public String getFunction ()
    {
        return function;
    }

    @Override
    public String toString ()
    {
        return super.toString() + "\n Function: " +function;
    }


}
