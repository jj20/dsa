package data_structures.linkedlist;

/**
 * Created by jessica on 4/8/2016.
 */

import java.util.*;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int length;

    static class Node<T> {
        private T data;
        private Node<T> next;

        Node(T data) {
            this.data = data;
            next = null;
        }

        boolean hasNext() {
            return next != null;
        }

        void setData(T data) {
            this.data = data;
        }

        T getData() {
            return data;
        }

        void setNext(Node<T> next) {
            this.next = next;
        }

        Node<T> getNext() {
            return next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public LinkedList() {
        head = tail = null;
        length = 0;
    }

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<String>();
        System.out.println("list is " + (linkedList.isEmpty() ? "" : "not ") + "empty.");
        linkedList.append("firstNode append");
        linkedList.append("secondNode append");
        linkedList.prepend("thirdNode prepend");
        linkedList.printList();

        linkedList.insertAt("fourthNode insertAt 0", 0);
        linkedList.insertAt("fifthNode insertAt 1", 1);
        linkedList.insertAt("sixthNode insertAt 5", 5);
        linkedList.printList();

        System.out.println("list is " + (linkedList.isEmpty() ? "" : "not ") + "empty.");

        System.out.println("data at index 0: " + linkedList.getAt(0));
        System.out.println("data at index 3: " + linkedList.getAt(3));
        System.out.println("data at index 5: " + linkedList.getAt(5));

        linkedList.printList();
        linkedList.insertAtMiddle("1st mid");
        linkedList.insertAtMiddle("2nd mid");
        linkedList.insertAtMiddle("3rd mid");
        linkedList.printList();

        linkedList.insertBefore("fourthNode insertAt 0", "*insertBefore: fourthNode insertAt 0*");
        linkedList.insertAfter("sixthNode insertAt 5", "*insertAfter: sixthNode insertAt 5*");
        linkedList.insertBefore("thirdNode prepend", "*insertBefore: thirdNode prepend*");
        linkedList.insertAfter("firstNode append", "*insertAfter: firstNode append*");
        linkedList.printList();
    }

    public int getLength() {
        Node<T> curr = head;
        int length = 0;

        while (curr != null) {
            length++;
            curr = curr.getNext();
        }
        return length;
    }

    public void printList() {
        Node<T> curr = head;

        while (curr != null) {
            System.out.print(curr + " - ");
            curr = curr.getNext();
        }
        System.out.println();
    }

    public void prepend(T data) {
        if (data == null) return;

        Node<T> n = new Node<T>(data);
        if (head == null) {
            head = tail = n;
            length++;
            return;
        }

        n.setNext(head);
        head = n;
        length++;
    }

    public void append(T data) {
        if (data == null) return;

        Node<T> n = new Node<T>(data);
        if (head == null) {
            head = tail = n;
            length++;
            return;
        }

        Node<T> curr = head;
        while (curr.getNext() != null) {
            curr = curr.getNext();
        }
        curr.setNext(n);
        tail = n;
        length++;
    }

    public void insertAt(T data, int index) {
        if (data == null) return;

        // index out of bounds
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            prepend(data);
        } else if (index == length) {
            append(data);
        } else {
            Node<T> newNode = new Node<T>(data);
            Node<T> prev = head;
            int currIndex = 1;
            while (currIndex < index && prev.getNext() != null) {
                currIndex++;
                prev = prev.getNext();
            }

            if (currIndex != index) {
                // error: missing link in linked list
            } else {
                newNode.setNext(prev.getNext());
                prev.setNext(newNode);
                length++;
            }
        }
    }

    public T getAt(int index) {
        if (head == null) return null;
        else if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return head.getData();
        } else if (index == length - 1) {
            return tail.getData();
        } else {
            Node<T> curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            return curr.getData();
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insertAtMiddle(T data) {
        if (data == null) return;

        // empty list
        if (head == null) {
            prepend(data);
            // list of one node
        } else if (head == tail) {
            append(data);
        } else {
            int middle = length / 2;
            // uncomment if insertAtMiddle() for odd numbers is after the center..
            //if (length % 2 == 1) middle++;
            insertAt(data, middle);
        }
    }

    public void insertBefore(T dataLookFor, T data) {
        if (dataLookFor == null) return;

        // empty list or head is a match
        if (head == null || head.getData().equals(dataLookFor)) {
            prepend(data);
        } else {
            Node<T> newNode = new Node<T>(data);
            Node<T> prev = head;
            Node<T> curr = head.getNext();

            while (curr != null && !curr.getData().equals(dataLookFor)) {
                curr = curr.getNext();
            }

            if (curr.getData().equals(dataLookFor)) {
                prev.setNext(newNode);
                newNode.setNext(curr);
                length++;
            }
        }
    }

    public void insertAfter(T dataLookFor, T data) {
        if (dataLookFor == null) return;

        // empty list or tail is a match
        if (tail == null || tail.getData().equals(dataLookFor)) {
            append(data);
        } else {
            Node<T> newNode = new Node<T>(data);
            if (head.getData().equals(dataLookFor)) {
                newNode.setNext(head.getNext());
                head.setNext(newNode);
            } else {
                Node<T> curr = head.getNext();
                while (curr != null && !curr.getData().equals(dataLookFor)) {
                    curr = curr.getNext();
                }

                if (curr.getData().equals(dataLookFor)) {
                    newNode.setNext(curr.getNext());
                    curr.setNext(newNode);
                }
            }
            length++;
        }
    }

    public void deleteFirst() {
        if (head == null) return;
        else if (head == tail) {
            head = tail = null;
        } else {
            head = head.getNext();
        }
        length--;
    }

    public void deleteLast() {
        if (head == null) return;
        else if (head == tail) {
            head = tail = null;
        } else {
            Node<T> prev = head;
            Node<T> curr = head.getNext();

            while (curr.getNext() != null) {
                prev = curr;
                curr = curr.getNext();
            }

            prev.setNext(null);
            tail = prev;
        }
        length--;
    }

    public void deleteAt(int index) {
        if (head == null) return;
        else if (index == 0) {
            deleteFirst();
            return;
        } else if (index == length - 1) {
            deleteLast();
            return;
        }

        if (head == tail) {
            head = tail = null;
        } else {
            Node<T> prev = head;
            Node<T> curr = head.getNext();
            for (int i = 1; i < index; i++) {
                prev = curr;
                curr = curr.getNext();
            }

            if (curr == tail) {
                tail = prev;
            }
            prev.setNext(curr.getNext());
        }
        length--;
    }

    public void deleteBefore(T lookForData) {
        // empty list or only 1 node or head is a match (& there's no nodes before head)
        if (head == null || head == tail || head.getData().equals(lookForData)) return;
        else {
            if (head.getNext().equals(lookForData)) {
                deleteFirst();
                return;
            }
        }

        Node<T> prevPrev = head;
        Node<T> prev = prevPrev.getNext();
        Node<T> curr = prev.getNext();

        while (curr != null && !curr.getData().equals(lookForData)) {
            prevPrev = prev;
            prev = curr;
            curr = curr.getNext();
        }

        if (curr == null) return;
        else if (curr.getData().equals(lookForData)) {
            prevPrev.setNext(curr);
            prev.setNext(null);
            length--;
        }

    }

    public void deleteAfter(T lookForData) {
        // empty list or only 1 node or tail is a match
        if (head == null || head == tail || tail.getData().equals(lookForData)) return;

        Node<T> prev = head;
        Node<T> curr = head.getNext();

        while (curr.getNext() != null && !curr.getData().equals(lookForData)) {
            prev = curr;
            curr = curr.getNext();
        }

        if (curr.getData().equals(lookForData)) {
            if (curr.getNext() == tail) {
                curr.setNext(null);
                tail = curr;
            } else {
                curr.setNext(curr.getNext().getNext());
            }
            length--;
        }
    }

    public void deleteAtMiddle() {
        if (head == null) return;
        else if (head == tail) {
            deleteFirst();
            return;
        }

        int middle = length / 2;
        if (length % 2 == 1) middle++;
        deleteAt(middle);
    }

    public void deleteNodeData(T data) {
        if (head == null) return;
        else if (head.getData().equals(data)) {
            deleteFirst();
            return;
        } else if (tail.getData().equals(data)) {
            deleteLast();
            return;
        }

        Node<T> prev = head;
        Node<T> curr = head.getNext();

        while (curr != null && !curr.getData().equals(data)) {
            prev = curr;
            curr = curr.getNext();
        }

        if (curr == null) return;
        else if (curr.getData().equals(data)) {
            prev.setNext(curr.getNext());
            curr.setNext(null);
            length--;
        }
    }

    public void swapNodes(T data1, T data2) {
        if (head == null || head == tail || data1.equals(data2)) {
            return;
        }

        Node<T> prev1 = null;
        Node<T> curr1 = head;
        while (!curr1.equals(data1) && curr1 != null) {
            prev1 = curr1;
            curr1 = curr1.getNext();
        }

        if (!curr1.equals(data1)) return;

        Node<T> prev2 = null;
        Node<T> curr2 = head;
        while (!curr2.equals(data1) && curr2.getNext() != null) {
            prev2 = curr2;
            curr2 = curr2.getNext();
        }

        if (!curr2.equals(data2)) return;

        // if curr1 is head
        if (prev1 == null) {
            head = curr2;
        } else {
            prev1.setNext(curr2);
        }

        // if curr2 is head
        if (prev2 == null) {
            head = curr1;
        } else {
            prev2.setNext(curr1);
        }

        Node<T> tempNext = curr1.getNext();
        curr1.setNext(curr2.getNext());
        curr2.setNext(tempNext);

        if (tail == curr1) tail = curr2;
        if (tail == curr2) tail = curr1;
    }

    public void iterativeReverseList() {
        if (head == null || head == tail) return;

        Node<T> prev = null;
        Node<T> curr = head;
        Node<T> next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head = prev;
    }

    public void recursiveReverseList(Node curr) {
        if (curr == null) {
            return;
        } else if (curr.getNext() == null) {
            head = curr;
            return;
        }

        recursiveReverseList(curr.getNext());
        Node<T> next = curr.getNext();
        next.setNext(curr);
        curr.setNext(null);
    }

    public Node<T> tailRecursiveReverseList(Node prev, Node curr) {
        // empty list
        if (prev == null) return null;
            // if beginning of list, set head's next pointer to point to null
        else if (prev == head) {
            head.setNext(null);
        }

        // exit case: end of list, set head
        if (curr == null) {
            head = prev;
            return head;
        } else {
            Node<T> next = curr.getNext();
            curr.setNext(prev);
            return tailRecursiveReverseList(curr, next);
        }
    }

    public void cleanerTailRecursiveReverseList() {
        head = cleanerReverse(null, head);
    }


    private Node<T> cleanerReverse(Node prev, Node curr) {
        if (curr == null) return prev;

        Node next = curr.getNext();
        curr.setNext(prev);
        return cleanerReverse(curr, next);
    }

    public void printRecursive(Node curr) {
        if (curr == null) return;

        System.out.print(curr.getData() + " ");
        printRecursive(curr.getNext());
    }

    public void printRecursiveReverse(Node curr) {
        if (curr == null) {
            System.out.print("printRecursiveReverse ");
            return;
        }

        printRecursiveReverse(curr.getNext());
        System.out.print(curr.getData() + " ");
    }

    public void printListReverse() {
        if (head == null) return;
        Stack<Node<T>> stack = new Stack<Node<T>>();

        Node<T> curr = head;
        while (curr.getNext() != null) {
            stack.push(curr);
            curr = curr.getNext();
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop().getData() + " ");
        }
    }

    public Node<T> reverseInGroups(int size, Node smallHead) {
        if (smallHead == null) return null;

        Node<T> prev = null, curr = smallHead, next = null;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (curr == null) break;
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }

        if (next != null) {
            smallHead.setNext(reverseInGroups(size, next));
        }

        return prev;
    }

    public LinkedList<Integer> mergeTwoSortedLists(Node<Integer> head1, Node<Integer> head2) {
        LinkedList<Integer> list = new LinkedList<Integer>();

        if ((head1 == null && head2 == null) || head1 == head2) {
            list.head = head1;
            return list;
        }

        if (head1 == null) {
            list.head = head2;
            return list;
        } else if (head2 == null) {
            list.head = head1;
            return list;
        }

        Node<Integer> listHead = new Node<Integer>(0);
        Node<Integer> curr = listHead;
        while (head1 != null && head2 != null) {
            if (head1.getData() < head2.getData()) {
                curr.setNext(new Node<Integer>(head1.getData()));
                head1 = head1.getNext();
            } else {
                curr.setNext(new Node<Integer>(head2.getData()));
                head2 = head2.getNext();
            }
            curr = curr.getNext();
        }

        if (head1 == null & head2 != null) {
            curr.setNext(head2);
        } else if (head1 != null && head2 == null) {
            curr.setNext(head1);
        }

        list.head = listHead.getNext();
        return list;
    }
}

