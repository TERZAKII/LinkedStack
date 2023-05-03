package PA;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

class StackNode<E> {
    E data;
    StackNode<E> next;
    StackNode<E> prev;
    public StackNode(E data) {
        this.data = data;
    }
}

class LinkedStack<E> {
    private StackNode<E> top;
    private int size;

    public LinkedStack(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            push(objects[i]);
        }
    }

    public boolean isEmpty() {
        // your code goes here
        return size == 0;
    }

    public int size() {
        // your code goes here
        return size;
    }

    public void push(E e) {
        // your code goes here
        StackNode<E> newNode = new StackNode<E>(e);
        newNode.next = top;
        if (top != null) {
            top.prev = newNode;
        }
        top = newNode;
        size++;
    }

    public E pop() throws EmptyStackException {
        // your code goes here
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public E peek() throws EmptyStackException {
        // your code goes here
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    @Override
    public String toString() {
        // your code goes here
        StringBuilder sb = new StringBuilder("[ ");
        StackNode<E> current = top;
        while (current != null) {
            sb.append(current.data).append(" ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> iterator() {
        return new StackIterator();
    }

    class StackIterator implements Iterator<E> {

        boolean canRemove = false;
        int previousLoc = -1;
        StackNode<E> current = top;

        @Override
        public boolean hasNext() {
            // your code goes here
            return current != null;
        }

        @Override
        public E next() {
            // your code goes here
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E data = current.data;
            current = current.next;
            return data;
        }
    }

    public ListIterator<E> listIterator() {
        // Only for C task !!!!!!!!
        return new LinkedStack.StackListIterator();
    }
    private class StackListIterator implements ListIterator<E> {
        private boolean canRemove = false;
        private int previousLoc = -1;
        private StackNode<E> current = top;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = current.data;
            previousLoc++;
            current = current.next;
            canRemove = true;
            return element;
        }

        @Override
        public boolean hasPrevious() {
            return previousLoc >= 0 && size > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (current == null) {
                current = top;
                for (int i = 0; i < previousLoc; i++) {
                    current = current.next;
                }
            } else {
                current = current.prev;
            }
            E element = current.data;
            previousLoc--;
            canRemove = true;
            return element;
        }

        @Override
        public int nextIndex() {
            return previousLoc + 1;
        }

        @Override
        public int previousIndex() {
            return previousLoc;
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException();
            }
            if (previousLoc == -1) {
                top = top.next;
            } else {
                StackNode<E> previous = top;
                for (int i = 0; i < previousLoc; i++) {
                    previous = previous.next;
                }
                previous.next = current.next;
            }
            size--;
            canRemove = false;
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }
}



public class PA12C{
    public static void main(String[] args) {
        LinkedStack<Integer> linkedStack = new LinkedStack<>(new Integer[]{1,2,3,4,5,6});
        while(linkedStack.size() > 0) {
            System.out.println(linkedStack.size() + "_linkedStack.peek(): " + linkedStack.peek());
            System.out.println(linkedStack.size() + "_linkedStack.pop(): " + linkedStack.pop());
            System.out.println(linkedStack.size() + "_After pop: " + linkedStack.toString() + "\n");
        }

        for (int i = 0; i < 6; i++) {
            linkedStack.push(i+25);
            System.out.println(i + "_After push" + "(" + (i+25) +  ")" + ": " + linkedStack.toString());
        }

        Iterator<Integer> iterator = linkedStack.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        iterator = linkedStack.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }

        System.out.println();
        System.out.println();

        System.out.println("Problem-C: ");
        ListIterator<Integer> listIterator =
                linkedStack.listIterator();
        while (listIterator.hasNext()){
            System.out.print(listIterator.nextIndex() + "_" +
                    listIterator.next() + " ");
        }
        System.out.println();
        while (listIterator.hasPrevious()){
            System.out.print(listIterator.previousIndex() + "_" +
                    listIterator.previous() + " ");
        }
        System.out.println();


    }
}
