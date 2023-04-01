package com.dsa.linkedlist;

public class MyLinkedList {
    //types of linkedlist
    //singly *doing this one*
    //doubly
    //circular linked list
    //data members
    int data;
    MyLinkedList nextNode = null;
    //constructors
    public MyLinkedList(){

    }
    public MyLinkedList(int data, MyLinkedList nextNode){
        this.data = data;
        this.nextNode = nextNode;
    }
    //getter and setter
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    //methods for functionalities
    public MyLinkedList getHeadNode() {
        return this;
    }

    public  boolean addNextNode(MyLinkedList nextNode){
        if(nextNode==null){
            this.nextNode = nextNode;
        }
        else {
            boolean lastNodeChk = false;
            MyLinkedList temp = nextNode;
            while(!lastNodeChk){
                if(temp.nextNode==null){
                    //we are at the last node
                    this.nextNode = nextNode;
                    lastNodeChk = true;
                }
                else{
                    //need to find the next node
                    temp = temp.nextNode;
                }
            }
        }
        return true;
    }

    public boolean removeNextNode(){
        if(nextNode==null){
            //there is no next node
            return false;
        }
        else{
            nextNode = null;
            //java garbage collector will automatically reclaim the free memory used by the object that is no longer referenced
        }
        return true;
    }

    public boolean removeSpecificNode(int data){
        //will remove the first encounter of the specific data
            //checking is the head is node to be removed
        boolean loopchk = true;
        boolean nodeRemoved = false;
        MyLinkedList temp = this;
            if(this.data==data){
                // we'll copy the contents of the last node here
                // or if you want you can copy the contents of following node and henceforth
                // which will ensure the order of nodes added in the first place both take O(n)
                // as you iterating from first to last node
                while (loopchk){
                    if(temp.nextNode==null){
                        // we are at last node no need to copy the contents
                        loopchk = false;
                    }
                    else{
                        //copy the contents of the next node
                        temp.data = temp.nextNode.data;
                        temp.nextNode = temp.nextNode.nextNode;
                        temp = nextNode;
                    }

                }
                nodeRemoved = true;
            }
            // node to be removed is not the head
        else {
                loopchk = true;
                temp = this;
                while (loopchk) {
                    if(temp.nextNode==null){
                        //we have reached the limit
                        loopchk = false;
                    }
                    else{
                        if(temp.nextNode.data==data){
                            //this is the data to be removed
                            temp.nextNode = temp.nextNode.nextNode;
                            nodeRemoved = true;
                        }
                        else{
                            //move onto next node
                            temp = temp.nextNode;
                        }
                    }
                }
            }
        //there was no data existing in the node hence false will be returned
        return nodeRemoved;
    }

    public boolean insertNodeAtPoint (int point, MyLinkedList nodeToBeInserted){
        //it will insert node after the node at 'point' position
        boolean isnodeInserted = false;
        int count = 1; //need to find the point so starting from zero
        boolean loopchk = true;
        MyLinkedList temp1  = this; //starting from head
        MyLinkedList temp2 = temp1.nextNode; // will store the next node after temp1
        while (loopchk){
            if(count == point){
                //we have reached the point need to add the node

            }
        }
        return isnodeInserted;
    }
}
