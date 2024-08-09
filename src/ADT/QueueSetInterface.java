/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ADT;

import java.util.Iterator;

/**
 *
 * @author USER
 * @param <T>
 */
public interface QueueSetInterface<T> {

    public boolean enqueue(T newEntry);

    public T dequeue();

    public T peek();

    public boolean contains(T anEntry);

    public int size();

    public boolean isEmpty();

    public void clear();
    
     public Iterator<T> getIterator();
}
