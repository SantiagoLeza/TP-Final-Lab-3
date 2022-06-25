// Package not
package App;

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ColeccionGenerica <K, V> implements Collection
{

     private TreeMap<K, V> treemap =  new TreeMap <>();


     public ColeccionGenerica()
     {
          this.treemap = new TreeMap<>();
     }


    public void addProduct (K key, V value)
    {
        treemap.put(key, value);
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
         return true;
    }

    public boolean containsKey (K key)
    {
        return treemap.containsKey(key);
    }

    public boolean containsValue (V value)
    {
         return treemap.containsValue(value);
    }

    public V getThroughId (K key)
    {
        return treemap.get(key);
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

    @Override
    public boolean remove (Object o)
    {
        return false;
    }


    @Override
    public Iterator iterator()
    {
         return null;
    }

    @Override
    public Object[] toArray()
    {
       return new Object[0];
    }

    @Override
    public Object[] toArray(IntFunction generator)
    {
        return Collection.super.toArray(generator);
    }

    @Override
    public boolean add(Object o)
    {
        return false;
    }


    @Override
    public boolean addAll(Collection c)
    {
        return false;
    }

    @Override
    public boolean removeIf(Predicate filter)
    {
        return Collection.super.removeIf(filter);
    }

    @Override
    public void clear()
    {

    }

    @Override
    public Spliterator spliterator()
    {
        return Collection.super.spliterator();
    }

    @Override
    public Stream stream()
    {
        return Collection.super.stream();
    }

    @Override
    public Stream parallelStream()
    {
        return Collection.super.parallelStream();
    }

    @Override
    public boolean retainAll(Collection c)
    {
        return false;
    }

    @Override
    public boolean removeAll(Collection c)
    {
        return false;
    }

    @Override
    public boolean containsAll(Collection c)
    {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a)
    {
        return new Object[0];
    }
    
    public TreeMap<K, V> getTreemap()
    {
        return treemap;
    }
}
