package com.hexcode.electricitybillcalculator;


class List{

    int value;
    List next;

    List(int val,List l){
        value = val;
        next = l;
    }
}

class LinkedList{

    List node = null;
    List temp = null;
    void add(int val){
        if(node == null){
            node = new List(val,null);
        }
        else{
            temp = new List(val,null);
            temp.next = node;
            node = temp;
        }
    }

    int sum(List node){
        int val = 0;
        while(node != null){
            val += node.value;
            node = node.next;
        }
        return val;
    }
}

