package App.Products;

import java.util.ArrayList;

public class Games extends Product
{

    private String ESRB;
    private ArrayList <String> genders;


    public Games(String name, float price , String ESRB, ArrayList<String> genders)
    {
        super(name, price);
        this.ESRB = ESRB;
        this.genders = genders;
    }
}
