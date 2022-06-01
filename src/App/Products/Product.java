package App.Products;

import java.util.UUID;

public class Product {

    private static int lastID =  0;
    private final int id;
    private String name;
    private float price;

    public Product (String name, float price)
    {

        id = lastID++;
        this.name = name;
        this.price = price;

    }

    public static int getLastID()
    {
        return lastID;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public float getPrice()
    {
        return price;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }
}
