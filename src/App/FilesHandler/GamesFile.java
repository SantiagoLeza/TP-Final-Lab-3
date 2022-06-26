package App.FilesHandler;

import App.Exceptions.FileErrorException;
import App.Products.App;
import App.Products.Game;
import App.Products.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import App.ColeccionGenerica;
import App.Review;


import java.util.TreeMap;

public class GamesFile
{

    public static ColeccionGenerica <Integer, Product> fileToTree2(String path) throws FileErrorException, JSONException
    {
        ColeccionGenerica<Integer, Product> tree = new ColeccionGenerica<>();

        JSONFiles jf = new JSONFiles(path);
        JSONArray ja = jf.readJSON();

        for (int i = 0; i < ja.length(); i++)
        {
            JSONObject jo = ja.getJSONObject(i);
            if(jo.has("game"))
            {
                JSONObject game = jo.getJSONObject("game");
                tree.addProduct(Integer.parseInt(game.get("id").toString()), new Game(game));
            }
            else if(jo.has("app"))
            {
                JSONObject app = jo.getJSONObject("app");
                tree.addProduct(Integer.parseInt(app.get("id").toString()), new App(app));
            }
        }

        return tree;
    }


    public static void addProductToFile(String path, Product product)
    {
        if(searchInFile(path, product.getId()))
        {
            throw new IllegalArgumentException("Product already in file");
        }

        try
        {
            JSONFiles jf = new JSONFiles(path);
            JSONArray ja = jf.readJSON();

            JSONObject jo = new JSONObject();
            if(product instanceof Game)
            {
                jo.put("game", (product).toJSON());
            }
            else if(product instanceof App)
            {
                jo.put("app", (product).toJSON());
            }

            ja.put(jo);

            jf.writeJSON(ja);

        } catch (FileErrorException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static boolean searchInFile(String path, int productId)
    {
        try{
            JSONFiles jf = new JSONFiles(path);
            JSONArray ja = jf.readJSON();

            for (int i = 0; i < ja.length(); i++)
            {
                JSONObject productJSON = ja.getJSONObject(i);

                if(productJSON.has("game")) {
                    JSONObject game = productJSON.getJSONObject("game");
                    if (productId == Integer.parseInt(game.get("id").toString())) {
                        return true;
                    }
                }
                else if(productJSON.has("app")) {
                    JSONObject app = productJSON.getJSONObject("app");
                    if (productId == Integer.parseInt(app.get("id").toString())) {
                        return true;
                    }
                }
            }

        } catch (FileErrorException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }



    public static void deleteById(int id)
    {
        try{
            JSONFiles jf = new JSONFiles("./src/App/FilesHandler/products.json");
            JSONArray ja = jf.readJSON();

            for (int i = 0; i < ja.length(); i++)
            {
                JSONObject productJSON = ja.getJSONObject(i);

                if(productJSON.has("game")) {
                    JSONObject game = productJSON.getJSONObject("game");
                    if (id == Integer.parseInt(game.get("id").toString())) {
                        ja.remove(i);
                        jf.writeJSON(ja);
                        return;
                    }
                }
                else if(productJSON.has("app")) {
                    JSONObject app = productJSON.getJSONObject("app");
                    if (id == Integer.parseInt(app.get("id").toString())) {
                        ja.remove(i);
                        jf.writeJSON(ja);
                        return;
                    }
                }
            }

        } catch (FileErrorException | JSONException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addReview(int id, Review review )
    {

        try{
            JSONFiles jf = new JSONFiles("./src/App/FilesHandler/products.json");
            JSONArray ja = jf.readJSON();

            JSONObject productJSON;

            for (int i = 0; i < ja.length(); i++)
            {
                productJSON = (JSONObject) ja.get(i);

                if(productJSON.has("game"))
                {
                    JSONObject game = productJSON.getJSONObject("game");
                    if ( id  == Integer.parseInt(game.get("id").toString()))
                    {
                        JSONArray jArray = game.getJSONArray("reviews");
                        jArray.put(review.toJSON());
                        game.put("reviews", jArray);
                        jf.writeJSON(ja);
                    }
                }
                else if(productJSON.has("app"))
                {
                    JSONObject app = productJSON.getJSONObject("app");
                    if (id == Integer.parseInt(app.get("id").toString()))
                    {
                        JSONArray jArray = app.getJSONArray("reviews");
                        jArray.put(review.toJSON());
                        app.put("reviews", jArray);
                        jf.writeJSON(ja);
                    }
                }
            }

        } catch (ClassCastException | FileErrorException | JSONException e)
        {
            e.printStackTrace();

        }
    }

    public static void modifyProduct( Product product )
        {

            try{
                JSONFiles jf = new JSONFiles("./src/App/FilesHandler/products.json");
                JSONArray ja = jf.readJSON();

                JSONObject productJSON;

                for (int i = 0; i < ja.length(); i++)
                {
                    productJSON = (JSONObject) ja.get(i);

                    if(productJSON.has("game"))
                    {
                        JSONObject game = productJSON.getJSONObject("game");
                        if ( product.getId()  == Integer.parseInt(game.get("id").toString()))
                        {
                            game = product.toJSON();
                            JSONObject toadd = new JSONObject();
                            toadd.put("game", game);
                            ja.put(i, toadd);
                            jf.writeJSON(ja);
                        }
                    }
                    else if(productJSON.has("app"))
                    {
                        JSONObject app = productJSON.getJSONObject("app");
                        if ( product.getId() == Integer.parseInt(app.get("id").toString()))
                        {
                            app = product.toJSON();
                            JSONObject toadd = new JSONObject();
                            toadd.put("app", app);
                            ja.put(i, toadd);
                            jf.writeJSON(ja);
                        }
                    }
                }

            } catch (ClassCastException e)
            {
                e.printStackTrace();

            }
             catch (FileErrorException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


}
