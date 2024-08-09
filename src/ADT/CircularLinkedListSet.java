/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ADT;

/**
 *
 * @author USER
 * @param <T>
 */
public class CircularLinkedListSet<T> implements ListSetInterface<T> {

    private Node firstNode;
    private Node lastNode;
    private int numberOfEntries;

    public CircularLinkedListSet() {
        clear();
    }

    @Override
    public final void clear() {
        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);

        if (isEmpty()) {
            firstNode = newNode;
            lastNode = newNode;
            newNode.next = firstNode;
        } else {
            Node currentNode = firstNode;
            while (currentNode.next != firstNode) {
                currentNode = currentNode.next;
            }
            lastNode = newNode;
            currentNode.next = lastNode;
            newNode.next = firstNode;
        }

//        System.out.println("first = " + firstNode.data);
//        System.out.println("last = " + lastNode.data);
//        System.out.println("last.next = " + lastNode.next.data);
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = false;

        if (newPosition >= 1 && newPosition <= numberOfEntries + 1) {
            Node newNode = new Node(newEntry);

            if (isEmpty() || newPosition == 1) {
                firstNode = newNode;
                lastNode = newNode;
                newNode.next = firstNode;
            } else {
                Node currentNode = firstNode;
                for (int i = 1; i < newPosition - 1; i++) {
                    currentNode = currentNode.next;
                }
                if (currentNode.next == firstNode) {
                    lastNode = newNode;
                }
                newNode.next = currentNode.next;
                currentNode.next = newNode;
            }
            numberOfEntries++;
            isSuccessful = true;
        }
//        System.out.println("first = " + firstNode.data);
//        System.out.println("last = " + lastNode.data);
//        System.out.println("last.next = " + lastNode.next.data);
        return isSuccessful;
    }

    @Override
    public T remove(int givenPosition) {
        T result = null;
        if ((givenPosition >= 1 && givenPosition <= numberOfEntries)) {
            if (givenPosition == 1) {
                result = firstNode.data;
                firstNode = firstNode.next;
                lastNode.next = firstNode;
            } else {
                Node currentNode = firstNode;
                for (int i = 1; i < givenPosition - 1; i++) {
                    currentNode = currentNode.next;
                }
                result = currentNode.next.data;
                if (currentNode.next == lastNode) {
                    lastNode = currentNode;
                }
                currentNode.next = currentNode.next.next;
            }
            numberOfEntries--;
        }
//        System.out.println("first = " + firstNode.data);
//        System.out.println("last = " + lastNode.data);
//        System.out.println("last.next = " + lastNode.next.data);
        return result;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = false;
        if ((givenPosition >= 1 && givenPosition <= numberOfEntries)) {
            Node currentNode = firstNode;
            for (int i = 0; i < givenPosition - 1; i++) {
                currentNode = currentNode.next;
            }
            currentNode.data = newEntry;
            isSuccessful = true;
        }
//        System.out.println("first = " + firstNode.data);
//        System.out.println("last = " + lastNode.data);
//        System.out.println("last.next = " + lastNode.next.data);
        return isSuccessful;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;
        if(givenPosition >= 1 && givenPosition <= numberOfEntries){
            Node currentNode = firstNode;
            for(int i = 1; i < givenPosition; i++){
                currentNode = currentNode.next;
            }
            result = currentNode.data;
        }
        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        do {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        }while (!found && currentNode != firstNode);
        return found;
    }
    
    @Override
    public int getNumberOfEntries(){
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        boolean result;
        result = numberOfEntries == 0;
        return result;
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

    private class Node {

        private T data;
        private Node  next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
