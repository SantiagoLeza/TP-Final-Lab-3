package App.FilesHandler;

import App.Exceptions.AlreadyInListException;
import App.Exceptions.FileErrorException;
import App.Products.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class UserGamesFile
{
    private final static String filePath = "./src/App/FilesHandler/usersGames.json";

    public static void addUser(UUID id) throws FileErrorException, JSONException
    {
        JSONFiles jf = new JSONFiles(filePath);
        JSONArray ja = jf.readJSON();

        JSONObject user = new JSONObject();
        user.put("UUID", id.toString());
        user.put("wishlist", new JSONArray());
        user.put("library", new JSONArray());
        user.put("cart", new JSONArray());

        ja.put(user);

        jf.writeJSON(ja);
    }
    
    public static boolean searchInWishList(UUID id, Product product) throws FileErrorException, JSONException
    {
        ArrayList<Integer> wishList = getWishList(id);

        if(wishList != null)
        {
            for (Integer i : wishList)
            {
                if(i == product.getId())
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean searchInLibrary(UUID id, Product product) throws FileErrorException, JSONException
    {
        ArrayList<Integer> library = getLibrary(id);

        if(library != null)
        {
            for (Integer i : library)
            {
                if(i == product.getId())
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean searchInCart(UUID id, Product product) throws FileErrorException, JSONException
    {
        ArrayList<Integer> cart = getCart(id);

        if(cart != null)
        {
            for (Integer i : cart)
            {
                if(i == product.getId())
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static void addProductWishList(UUID id, Product product) throws AlreadyInListException
    {
        StringBuilder sb = new StringBuilder();
        

        try
        {
            if(searchInWishList(id, product))
            {
                throw new AlreadyInListException("Product already in wishlist");
            }

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();

            while(line != null)
            {
                sb.append(line);
                line = br.readLine();
            }

            JSONArray wishListJSON = new JSONArray(sb.toString());

            JSONObject jo;
            for (int i = 0; i < wishListJSON.length(); i++) {
                jo = (JSONObject) wishListJSON.get(i);

                if(jo.get("UUID").toString().equals(id.toString()))
                {
                    JSONArray ja = (JSONArray) jo.get("wishlist");
                    ja.put(product.getId());

                    jo.put("wishlist", ja);

                    wishListJSON.put(i, jo);

                    JSONFiles jf = new JSONFiles(filePath);
                    jf.writeJSON(wishListJSON);
                    return;
                }
            }

        }
        catch(EOFException e)
        {}
        catch (IOException | JSONException e) {
            e.printStackTrace();
        } catch (FileErrorException e) {
            e.printStackTrace();
        }

    }

    public static void addProductLibrary(UUID id, Product product) throws AlreadyInListException
    {
        StringBuilder sb = new StringBuilder();

        try
        {
            if(searchInLibrary(id, product))
            {
                throw new AlreadyInListException("Product already in library");
            }
            
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();

            while(line != null)
            {
                sb.append(line);
                line = br.readLine();
            }

            JSONArray libraryJSON = new JSONArray(sb.toString());

            JSONObject jo;
            for (int i = 0; i < libraryJSON.length(); i++) {
                jo = (JSONObject) libraryJSON.get(i);

                if(jo.get("UUID").toString().equals(id.toString()))
                {
                    JSONArray ja = (JSONArray) jo.get("library");
                    ja.put(product.getId());

                    jo.put("library", ja);

                    libraryJSON.put(i, jo);

                    JSONFiles jf = new JSONFiles(filePath);
                    jf.writeJSON(libraryJSON);
                    return;
                }
            }

        }
        catch(EOFException e)
        {}
        catch (IOException | JSONException e) {
            e.printStackTrace();
        } catch (FileErrorException e) {
            e.printStackTrace();
        }

    }

    public static void addProductCart(UUID id, Product product) throws AlreadyInListException
    {
        StringBuilder sb = new StringBuilder();

        try
        {
            if(searchInCart(id, product))
            {
                throw new AlreadyInListException("Product already in cart");
            }
            
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();

            while(line != null)
            {
                sb.append(line);
                line = br.readLine();
            }

            JSONArray cartJSON = new JSONArray(sb.toString());

            JSONObject jo;
            for (int i = 0; i < cartJSON.length(); i++) {
                jo = (JSONObject) cartJSON.get(i);

                if(jo.get("UUID").toString().equals(id.toString()))
                {
                    JSONArray ja = (JSONArray) jo.get("cart");
                    ja.put(product.getId());

                    jo.put("cart", ja);

                    cartJSON.put(i, jo);

                    JSONFiles jf = new JSONFiles(filePath);
                    jf.writeJSON(cartJSON);
                    return;
                }
            }

        }
        catch(EOFException e)
        {}
        catch (IOException | JSONException e) {
            e.printStackTrace();
        } catch (FileErrorException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> getWishList(UUID id)
    {
        JSONFiles jf = new JSONFiles(filePath);

        try {
            JSONArray file = jf.readJSON();

            JSONObject jo;
            for (int i = 0; i < file.length(); i++) {
                jo = (JSONObject) file.get(i);

                if(jo.get("UUID").toString().equals(id.toString()))
                {
                    ArrayList<Integer> products = new ArrayList<>();

                    JSONArray productsJSON = (JSONArray) jo.get("wishlist");

                    for (int j = 0; j < productsJSON.length(); j++) {
                        products.add(Integer.parseInt(productsJSON.get(j).toString()));
                    }


                    return products;
                }
            }


        } catch (FileErrorException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Integer> getLibrary(UUID id)
    {
        JSONFiles jf = new JSONFiles(filePath);

        try {
            JSONArray file = jf.readJSON();

            JSONObject jo;
            for (int i = 0; i < file.length(); i++) {
                jo = (JSONObject) file.get(i);

                if(jo.get("UUID").toString().equals(id.toString()))
                {
                    ArrayList<Integer> products = new ArrayList<>();

                    JSONArray productsJSON = (JSONArray) jo.get("library");

                    for (int j = 0; j < productsJSON.length(); j++) {
                        products.add(Integer.parseInt(productsJSON.get(j).toString()));
                    }


                    return products;
                }
            }


        } catch (FileErrorException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Integer> getCart(UUID id)
    {
        JSONFiles jf = new JSONFiles(filePath);

        try {
            JSONArray file = jf.readJSON();

            JSONObject jo;
            for (int i = 0; i < file.length(); i++) {
                jo = (JSONObject) file.get(i);

                if(jo.get("UUID").toString().equals(id.toString()))
                {
                    ArrayList<Integer> products = new ArrayList<>();

                    JSONArray productsJSON = (JSONArray) jo.get("cart");

                    for (int j = 0; j < productsJSON.length(); j++) {
                        products.add(Integer.parseInt(productsJSON.get(j).toString()));
                    }


                    return products;
                }
            }


        } catch (FileErrorException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void removeFormCart(UUID uuid, Product product)
    {
        JSONFiles jf = new JSONFiles(filePath);
        try {
            JSONArray ja = jf.readJSON();

            JSONObject jo;
            for (int i = 0; i < ja.length(); i++) {
                jo = (JSONObject) ja.get(i);

                if(jo.get("UUID").toString().equals(uuid.toString()))
                {
                    JSONArray cart = (JSONArray) jo.get("cart");
                    int productJSON;
                    for (int j = 0; j < cart.length(); j++) {
                        productJSON = (int) cart.get(j);
                        if(productJSON == product.getId())
                        {
                            cart.remove(j);
                            jo.put("cart", cart);
                            ja.put(i, jo);
                            jf.writeJSON(ja);
                            return;
                        }
                    }

                    jf.writeJSON(ja);
                    return;
                }
            }
        } catch (FileErrorException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void removeFormWishList(UUID uuid, Product product)
    {
        JSONFiles jf = new JSONFiles(filePath);
        try {
            JSONArray ja = jf.readJSON();

            JSONObject jo;
            for (int i = 0; i < ja.length(); i++) {
                jo = (JSONObject) ja.get(i);

                if(jo.get("UUID").toString().equals(uuid.toString()))
                {
                    JSONArray cart = (JSONArray) jo.get("wishlist");
                    JSONObject productJSON;
                    for (int j = 0; j < cart.length(); j++) {
                        productJSON = (JSONObject) cart.get(j);
                        if(Integer.parseInt(productJSON.get("id").toString()) == product.getId())
                        {
                            cart.remove(j);
                            jo.put("wishlist", cart);
                            ja.put(i, jo);
                            jf.writeJSON(ja);
                            return;
                        }
                    }

                    jf.writeJSON(ja);
                    return;
                }
            }
        } catch (FileErrorException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
