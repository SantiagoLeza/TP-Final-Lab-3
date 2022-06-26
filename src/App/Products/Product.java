package App.Products;

import App.Review;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

public class Product {

    private static int lastID =  -1;
    private int id;
    private String name;
    private float price;
    private ArrayList<Review> reviews;
    private float rating;
    private int sells;
    private ESRBClassification ESRB;
    private float income;
    private boolean aviable;
    /**
     * Use this constructor to create a new product.
     */
    public Product (String name, float price, ESRBClassification ESRB)
    {

        id = ++lastID;
        this.name = name;
        this.price = price;
        this.reviews = new ArrayList<Review>();
        this.rating = 0;
        this.ESRB = ESRB;
        this.income = 0;
        this.aviable = true;
    }

    /**
     * Use this constructor when importing a product from a file.
     */
    public Product (int id, String name, float price, ArrayList<Review> reviews, ESRBClassification ESRB, boolean aviable)
    {

        this.id = id;
        this.name = name;
        this.price = price;
        this.reviews = reviews;
        this.ESRB = ESRB;
        this.aviable = aviable;

        lastID = Math.max(lastID, id);

        float i = 0;
        for (Review review : reviews)
        {
            i += review.getRating();
        }

        this.rating = i / reviews.size();

    }

    public Product( JSONObject id, JSONObject name, JSONObject price, JSONArray rewiews, JSONObject esrb, JSONObject income, JSONObject aviable )
    {
        try
        {
            ArrayList <Review> reviewCollection= new ArrayList();

            for (int i=0; i< rewiews.length() ; i++)
            {
                JSONObject reviewObject= (JSONObject) rewiews.get(i);
                reviewCollection.add(new Review(reviewObject.get("user").toString(), reviewObject.get("text").toString(), Integer.parseInt(reviewObject.get("rating").toString())));
            }

            this.id = Integer.parseInt(id.get("id").toString());
            this.name = name.get("name").toString();
            this.price = Float.parseFloat(price.get("price").toString());
            this.reviews = reviewCollection;
            this.ESRB = ESRBClassification.valueOf(esrb.get("ESRB").toString());
            this.income= Float.parseFloat(income.get("income").toString());
            this.aviable = Boolean.parseBoolean(aviable.get("aviable").toString());

            lastID = Math.max(lastID, Integer.parseInt(id.get("id").toString()));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void addReview(Review review)
    {
        reviews.add(review);
    }

    public static int getLastID()
    {
        return lastID;
    }

    public int getId()
    {
        return id;
    }

    public ArrayList<Review> getReviews()
    {
        return reviews;
    }

    public String getName()
    {
        return name;
    }

    public float getPrice()
    {
        return price;
    }

    public boolean isAviable()
    {
        return aviable;
    }

    public void setAviable(boolean aviable)
    {
        this.aviable = aviable;
    }

    public float getRating()
    {
        float f = 0;

        for (Review r : reviews)
        {
            f += r.getRating();
        }

        if(f == 0) return 0;

        return f / reviews.size();
    }

    public void setESRB(ESRBClassification ESRB)
    {
        this.ESRB = ESRB;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ESRBClassification getESRB()
    {
        return ESRB;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public int getSells()
    {
        return sells;
    }

    public void addSell()
    {
        this.sells++;
        this.income += price;
    }

    public static JSONArray arrayToJSONObject(ArrayList array)
    {
        JSONArray jsonList = new JSONArray();

        if (!array.isEmpty())
        {
          for (int i = 0; i < array.size(); i++)
            {
                jsonList.put(array.get(i));
            }
        }

        return jsonList;

    }

    @Override
    public String toString()
    {
        return  "\nid=" + id +
                ",\nis aviable=" + aviable +
                ",\n name='" + name + '\'' +
                ",\n price=" + price +
                ",\n reviews=" + reviews +
                ",\n rating=" + rating +
                ",\n sells=" + sells +
                ",\n ESRB=" + ESRB;
    }
    
    public JSONObject toJSON()
    {
        try
        {
            JSONObject json = new JSONObject();
            json.put("id", id);
            json.put("name", name);
            json.put("price", price);

            JSONArray jsonReviews = new JSONArray();
            for (Review review : reviews)
            {
                jsonReviews.put(review.toJSON());
            }
            json.put("reviews", jsonReviews);

            json.put("rating", rating);
            json.put("sells", sells);
            json.put("ESRB", ESRB);
            json.put("income", income);
            json.put("aviable", aviable);
            return json;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
