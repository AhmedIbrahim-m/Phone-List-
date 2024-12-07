package com.mycompany.testdata;

public class Testdata {
    public static void main(String[] args) {
        MyHashTable hash = new MyHashTable();
        hash.insertContact("Alice", "12345");
        hash.insertContact("Bob", "67890");
        hash.insertContact("Charlie", "54321");

        hash.displayContacts();
        hash.updateContact("Charlie", "01120");
        hash.displayContacts();
    }
}


class LinkedList {
    private Node head;

    static class Node {
        String name;
        String phoneNumber;
        Node next;

        Node(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.next = null;
        }
    }

  
    public void add(String name, String phoneNumber) {
        Node newNode = new Node(name, phoneNumber);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    
    
    public boolean remove(String name) {
        if (head == null) return false;

        if (head.name.equals(name)) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.name.equals(name)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

   
    
    public boolean isEmpty() {
        return head == null;
    }


    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print("[" + current.name + ": " + current.phoneNumber + "] -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    
    public String search(String name) {
        Node Curr = head ;
         while(Curr != null ){
             if(Curr.name.equals(name))  return Curr.phoneNumber ; 
              Curr =Curr.next; 
              
         }
          return null ;  
        
    } 
}

class MyHashTable {
    private int SIZE = 1009;  
    private LinkedList[] table;

    public MyHashTable() {
        table = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            table[i] = new LinkedList(); 
        }
    }

  
    public static long calc_hash(String key, int SIZE) {
        int i, l = key.length();
        long hash = 0;
        for (i = 0; i < l; i++) {
            hash += Character.getNumericValue(key.charAt(i));
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        if (hash > 0) return hash % SIZE;
        else return -hash % SIZE;
    }


    public void insertContact(String name, String phoneNumber) {
        int index = (int) calc_hash(name, SIZE);  
        LinkedList chain = table[index];  
        chain.add(name, phoneNumber);  
        System.out.println("Contact added: " + name + " - " + phoneNumber);
    }

  
    public String searchContact(String name) {
        int index = (int) calc_hash(name, SIZE);  
        LinkedList chain = table[index];  
        return chain.search(name);  
    }

    
    public void deleteContact(String name) {
        int index = (int) calc_hash(name, SIZE);  
        LinkedList chain = table[index]; 
        boolean removed = chain.remove(name);  
        if (removed) {
            System.out.println("Contact deleted: " + name);
        } else {
            System.out.println("Contact not found: " + name);
        }
    }


    public void updateContact(String name, String newPhoneNumber) {
        int index = (int) calc_hash(name, SIZE);  
        LinkedList chain = table[index]; 
        String currentPhoneNumber = chain.search(name);
        if (currentPhoneNumber != null) {
            // Remove old contact and add the updated one
            chain.remove(name);
            chain.add(name, newPhoneNumber);
            System.out.println("Contact updated: " + name + " - " + newPhoneNumber);
        } else {
            System.out.println("Contact not found: " + name);
        }
    }

   
    public void displayContacts() {
        for (int i = 0; i < SIZE; i++) {
            LinkedList chain = table[i];  
            if (!chain.isEmpty()) {
                System.out.print("Bucket " + i + ": ");
                chain.display(); 
            }
        }
    }
}



