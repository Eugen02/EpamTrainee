/**
 * practice2
 */

package ua.nure.chekhunov.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author macbookpro
 *
 */
public class StackImpl implements Stack {

    /*
     * (non-Javadoc)
     * 
     * @see top
     */
    private Node top;

    /*
     * (non-Javadoc)
     * 
     * @see size
     */
    private int size;

    /**
     * {@link Object}.
     */
    private Object[] arr = {};

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Iterator#next()
     */
    public StackImpl() {
        top = null;
        size = 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Iterator#next()
     */
    public StackImpl(int m) {
        this.size = m;
        arr = new Object[size];
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see ua.nure.chekhunov
     */
    @Override
    public void clear() {

        arr = new Object[] {};
        size = 0;

    }

    /*
     * (non-Javadoc)
     * 
     * @see ua.nure.chekhunov
     */
    @Override
    public int size() {
        return toArray().length;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ua.nure.chekhunov
     */
    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ua.nure.chekhunov
     */
    public Object[] toArray() {
        return this.arr.clone();

    }

    /*
     * (non-Javadoc)
     * 
     * @see ua.nure.chekhunov
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < toArray().length; i++) {
            if (toArray()[i] == null) {
                sb.append("");
                continue;
            }
            sb.append("" + toArray()[i].toString() + "");
            if (i != size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();

    }

    /*
     * (non-Javadoc)
     * 
     * @see ua.nure.chekhunov
     */
    @Override
    public void push(Object element) {
        Object[] arr2 = new Object[arr.length + 1];
        System.arraycopy(arr, 0, arr2, 0, arr.length);
        arr2[arr2.length - 1] = element;
        arr = arr2;

        Node nod = new Node();
        nod.setElement(element);
        nod.setNext(top);
        top = nod;
        size++;

    }

    /*
     * (non-Javadoc)
     * 
     * @see ua.nure.chekhunov
     */
    @Override
    public Object pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Error No Such Element from next");
        }
        Object obj = top.getElement();
        top = top.getNext();
        size--;
        return obj;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ua.nure.chekhunov
     */
    @Override
    public Object top() {
        if (isEmpty()) {
            throw new NoSuchElementException("Error No Such Element from next");
        }
        return top.getElement();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ua.nure.chekhunov
     */
    public boolean isEmpty() {
        return (top == null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ua.nure.chekhunov
     */
    private class IteratorImpl implements Iterator<Object> {

        private int pointer = toArray().length;

        private boolean nextOrPrevious;
        
        /*
         * (non-Javadoc)
         * 
         * @see ua.nure.chekhunov
         */
        private boolean flag = true;

        /*
         * (non-Javadoc)
         * 
         * @see ua.nure.chekhunov
         */
        public boolean isFlag() {
            return flag;
        }

        /*
         * (non-Javadoc)
         * 
         * @see setFlag
         */
        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        /**
         * @return
         */
        public int getPointer() {

            return pointer;

        }

        public void setPointer(int pointer) {
            this.pointer = pointer;
        }

        public boolean isNextOrPrevious() {
            return nextOrPrevious;
        }

        public void setNextOrPrevious(boolean nextOrPrevious) {
            this.nextOrPrevious = nextOrPrevious;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Iterator#hasNext()
         */
        public boolean hasNext() {
            return (pointer + 1 >= toArray().length - 1);

        }

        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            flag = false;
            nextOrPrevious = false;
            return toArray()[--pointer];
        }

        @Override
        public void remove() {
            if (isFlag()) {
                throw new IllegalStateException();
            }
            int i = 0;
            if (!isNextOrPrevious()) {
                i = pointer--;

            } else {
                i = ++pointer;
            }
            Object[] arr2 = new Object[arr.length - 1];
            System.arraycopy(arr, 0, arr2, 0, i);
            System.arraycopy(arr, i + 1, arr2, i, arr2.length - i);
            arr = arr2;
            flag = true;
            ++pointer;
            if (isNextOrPrevious()) {
                --pointer;
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see ua.nure.chekhunov
     */
    private static class Node {
        
        private Object element;
        
        private Node next;

        public Node() {

        }

        public Node(Object element, Node next) {
            this.next = next;
            this.element = element;
        }

        public Object getElement() {
            return element;
        }

        public Node getNext() {
            return next;
        }

        public void setElement(Object element) {
            this.element = element;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Stack stack = new StackImpl();

        stack.push("A");
        stack.push("B");
        stack.push("C");
        System.out.println(stack);

        Iterator<Object> it = stack.iterator();

        System.out.println(it.next());
        it.remove();
        System.out.println(it.next());
        it.remove();
        System.out.println(it.next());
        it.remove();
        System.out.println(stack);

        stack.pop();
        stack.top();
        stack.clear();
        System.out.println(stack.size());
        System.out.println(stack.toString());

        System.out.println("\nShow " + stack);

    }
}
