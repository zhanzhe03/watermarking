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
public class SortedDoublyLinkedListSet<T extends Comparable<T>> implements SortedListSetInterface<T> {

    private Node firstNode;
    private Node lastNode;
    private int numberOfEntries;

    public SortedDoublyLinkedListSet() {
        clear();
    }

    @Override
    public void reSort() {
        SortedListSetInterface<T> tempList = new SortedDoublyLinkedListSet<>();
        tempList.merge(this);
        this.clear();
        this.merge(tempList);
    }

    @Override
    public boolean merge(SortedListSetInterface<T> otherListSet) {
        Iterator<T> iterator = otherListSet.getIterator();
        do {
            T anEntry = iterator.next();
            if(!this.contains(anEntry)){
                add(anEntry);
            }
        } while (iterator.hasNext());
        return true;
    }
    
    @Override
    public boolean intersect(SortedListSetInterface<T> otherListSet) {
        Iterator<T> iterator = this.getIterator();
        SortedListSetInterface<T> toRemove = new SortedDoublyLinkedListSet<>();

      
        while (iterator.hasNext()) {
            T anEntry = iterator.next();

            if (!otherListSet.contains(anEntry)) {
                toRemove.add(anEntry);
            }
        }

        iterator = toRemove.getIterator();
        while (iterator.hasNext()) {
            this.remove(iterator.next());
        }

        return (!this.isEmpty());
    }

    @Override
    public boolean relativeComplement(SortedListSetInterface<T> otherListSet) {
        Iterator<T> iterator = otherListSet.getIterator();
        do {
            T anEntry = iterator.next();
            if (contains(anEntry)) {
                remove(anEntry);
            }
        } while (iterator.hasNext());
        return true;
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);

        if (isEmpty()) {
            firstNode = lastNode = newNode;
        } else {
            Node currentNode = firstNode;
            while (currentNode != null && newEntry.compareTo(currentNode.data) > 0) {
                currentNode = currentNode.next;
            }
            if (currentNode == firstNode) {
                newNode.next = firstNode;
                firstNode.prev = newNode;
                firstNode = newNode;
            } else if (currentNode == null) {
                lastNode.next = newNode;
                newNode.prev = lastNode;
                lastNode = newNode;
            } else {
                Node previousNode = currentNode.prev;
                previousNode.next = newNode;
                newNode.prev = previousNode;
                newNode.next = currentNode;
                currentNode.prev = newNode;
            }
        }
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean remove(T anEntry) {
        if (!isEmpty()) {
            Node currentNode = firstNode;
            while (currentNode != null && currentNode.data.compareTo(anEntry) < 0) {
                currentNode = currentNode.next;
            }
            if (currentNode != null && currentNode.data.equals(anEntry)) {
                if (currentNode == firstNode) {
                    firstNode = firstNode.next;
                    if (firstNode == null) {
                        lastNode = null;
                    } else {
                        firstNode.prev = null;
                    }
                } else if (currentNode == lastNode) {
                    lastNode = lastNode.prev;
                    lastNode.next = null;
                } else {
                    Node beforeNode = currentNode.prev;
                    Node afterNode = currentNode.next;
                    beforeNode.next = afterNode;
                    afterNode.prev = beforeNode;
                }
                numberOfEntries--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && currentNode != null) {
            if (anEntry.compareTo(currentNode.data) == 0) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        }
        return found;
    }

    @Override
    public T getLastEntries() {
        return lastNode.data;
    }

    @Override
    public T getFirstEntry() {
        return firstNode.data;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return (numberOfEntries == 0) && (firstNode == null) && (lastNode == null);
    }

    @Override
    public final void clear() {
        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;
    }

    @Override
    public String toString() {
        StringBuilder outputStr = new StringBuilder();
        Node currentNode1 = firstNode;
        while (currentNode1 != null) {
            outputStr.append(currentNode1.data).append("\n");
            currentNode1 = currentNode1.next;
        }
        return outputStr.toString();
    }

    @Override
    public Iterator<T> getIterator() {
        return new SortedDoublyLinkedListSetIterator();
    }

    private class SortedDoublyLinkedListSetIterator implements Iterator<T> {

        private Node currentNode;

        private SortedDoublyLinkedListSetIterator() {
            currentNode = firstNode;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            T result = null;
            if (hasNext()) {
                result = currentNode.data;
                currentNode = currentNode.next;
            }
            return result;
        }
    }

    private class Node {

        private T data;
        private Node next;
        private Node prev;

        private Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
}
