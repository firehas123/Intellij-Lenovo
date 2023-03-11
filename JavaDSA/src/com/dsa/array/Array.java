package com.dsa.array;

public class Array {
    public int arr[];
    public Array(){
        //initailzing it to default size 5
        arr = new int[5];
    }

    public Array(int i) {
        arr = new int[i];
    }

    public void display(){
        for(int i=0;i< arr.length;i++){
            System.out.println(arr[i]);
        }
    }
}
