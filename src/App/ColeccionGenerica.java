// Package not
package App;

import App.Products.Product;

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ColeccionGenerica <K, V> implements Collection<V>
{

     private TreeMap<K, V> treemap;


     public ColeccionGenerica()
     {
          treemap = new TreeMap<>();
     }


    public void addProduct (K key, V value)
    {
        treemap.put(key, value);
    }

    public ArrayList<V> treeToArray ()
    {
        ArrayList <V> array= new ArrayList<>();
        for (Map.Entry<K, V> p : treemap.entrySet())
        {
            array.add(p.getValue());
        }

        return array;
    }

    @Override
    public int size()
    {
       return treemap.size();
    }

    @Override
    public boolean isEmpty()
    {
        return treemap.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return treemap.containsValue(o);
    }

    @Override
    public Iterator<V> iterator()
    {
        return treemap.values().iterator();
    }

    @Override
    public Object[] toArray()
    {
        Object[] array = new Object[treemap.size()];
        int i = 0;
        for (Map.Entry<K, V> p : treemap.entrySet())
        {
            array[i] = p.getValue();
            i++;
        }
        return array;
    }

    @Deprecated
    public <T> T[] toArray(T[] a)
    {
        return null;
    }

    /**
     * Use addProduct instead
     */
    @Deprecated
    public boolean add(V v)
    {
        return false;
    }

    @Override
    public boolean remove(Object o)
    {
        return treemap.values().remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return treemap.values().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends V> c)
    {
        for (V v : c)
        {
            addProduct((K) v.getClass().getName(), v);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return treemap.values().removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return treemap.values().retainAll(c);
    }

    @Override
    public void clear()
    {
        treemap.clear();
    }

    public boolean containsKey (K key)
    {
        return treemap.containsKey(key);
    }

    public boolean containsValue (V value)
    {
         return treemap.containsValue(value);
    }

    public V getThroughId (int id)
    {
        for (Map.Entry<K, V> p : treemap.entrySet())
        {
            if (p.getKey().equals(id))
            {
                return p.getValue();
            }
        }
        return null;
    }

    public V getThroughName (String gameName)
    {

        for (Map.Entry<K, V> p : treemap.entrySet())
        {
            if (p.getValue() instanceof Product)
            {
                Product product = (Product) p.getValue();
                
                if (product.getName().equals(gameName))
                {
                    V value = (V) product;
                    return value;
                }
            }

        }
        return null;
    }
    
    public void removeThroughID (int id)
    {
       
        for (Map.Entry<K, V> p : treemap.entrySet())
        {
            if (p.getValue() instanceof Product product)
            {
                if (product.getId() == id)
                {
                    treemap.remove(p.getKey());
                }
            }

        }
   }

    public V erase (K key)
    {
        return treemap.remove(key);
    }

    public boolean eraseABoolean ( K key )
    {
        V value = erase(key);
        
        return treemap.remove(key, value);
    }

    public TreeMap<K, V> getTreemap()
    {
        return treemap;
    }
}
