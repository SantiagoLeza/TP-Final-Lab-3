package App.FilesHandler;

import App.Exceptions.FileErrorException;
import App.Products.App;
import App.Products.Game;
import App.Products.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import App.ColeccionGenerica;


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
                if(jo.get("game") != null)
                {
                    JSONObject game = jo.getJSONObject("game");
                    tree.addProduct(Integer.parseInt(game.get("id").toString()), new Game(game));
                }
                else if(jo.get("app") != null)
                {
                    JSONObject app = jo.getJSONObject("app");
                    tree.addProduct(Integer.parseInt(app.get("id").toString()), new App(app));
                }
            }

            return tree;
        }


    public static void addProductToFile(String path, Product product)
    {
        if(searchInFile(path, product))
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

    public static boolean searchInFile(String path, Product product)
    {
        try{
            JSONFiles jf = new JSONFiles(path);
            JSONArray ja = jf.readJSON();

            for (int i = 0; i < ja.length(); i++)
            {
                JSONObject productJSON = ja.getJSONObject(i);

                if(productJSON.get("game") != null) {
                    JSONObject game = productJSON.getJSONObject("game");
                    if (product.getId() == Integer.parseInt(game.get("id").toString())) {
                        return true;
                    }
                }
                else if(productJSON.get("app") != null) {
                    JSONObject app = productJSON.getJSONObject("app");
                    if (product.getId() == Integer.parseInt(app.get("id").toString())) {
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
}
