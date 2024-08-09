/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ADT;

import java.util.Iterator;

/**
 *
 * @author USER
 * @param <T>
 */
public class CircularLinkedQueueSet<T> implements QueueSetInterface<T> {

    private Node firstNode;
    private Node lastNode;
    private int n;

    public CircularLinkedQueueSet() {
        clear();
    }

    @Override
    public boolean enqueue(T newEntry) {
        boolean isSuccessful = false;

        if (!contains(newEntry)) {
            Node newNode = new Node(newEntry);
            if (isEmpty()) {
                firstNode = newNode;
                newNode.next = firstNode;
            } else {
                lastNode.next = newNode;
                newNode.next = firstNode;
            }
            lastNode = newNode;
            isSuccessful = true;
            n++;
        }

//        System.out.println("First Node : " + firstNode.data);
//        System.out.println("Last Node : " + lastNode.data);
//        System.out.println("Last Node next : " + lastNode.next.data);
//        System.out.println("QueueSet Size : " + n);
        return isSuccessful;
    }

    @Override
    public T dequeue() {
        T frontNode = null;

        if (!isEmpty()) {
            frontNode = firstNode.data;
            firstNode = firstNode.next;

            if (firstNode == null) {
                clear();
            } else {
                lastNode.next = firstNode;
            }
            n--;
        }
//        System.out.println("First Node : " + firstNode.data);
//        System.out.println("Last Node : " + lastNode.data);
//        System.out.println("Last Node next : " + lastNode.next.data);
//        System.out.println("QueueSet Size : " + n);
        return frontNode;
    }

    @Override
    public T peek() {
        return isEmpty() ? null : firstNode.data;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;

        if (!isEmpty()) {
            Node currentNode = firstNode;
            do {
                if (!anEntry.equals(currentNode.data)) {
                    currentNode = currentNode.next;
                } else {
                    found = true;
                }
            } while (!found && currentNode != firstNode);
        }
        return found;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return (n == 0) && (firstNode == null) && (lastNode == null);
    }

    @Override
    public final void clear() {
        firstNode = null;
        lastNode = null;
        n = 0;
    }

    @Override
    public String toString() {
        StringBuilder outputStr = new StringBuilder();
        if (!isEmpty()) {
            Node currentNode = firstNode;
            do {
                outputStr.append(currentNode.data).append("\n");
                currentNode = currentNode.next;
            } while (currentNode != firstNode);
        }
        return outputStr.toString();
    }
    
    @Override
    public Iterator<T> getIterator(){
        return new QueueSetIterator();
    }
    
    private class QueueSetIterator implements Iterator<T> {
        
        private Node currentNode;
        private int count;
        
        public QueueSetIterator(){
            currentNode = firstNode;
            count = 0;
        }
        
        @Override
        public boolean hasNext(){
            return count < n;
        }
        
        @Override
        public T next(){
            if(hasNext()){
                T result = currentNode.data;
                currentNode = currentNode.next;
                count++;
                return result;
            }else{
                return null;
            }
        }
    }

    private class Node {

        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}
