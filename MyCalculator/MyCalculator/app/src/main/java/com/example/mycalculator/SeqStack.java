package com.example.mycalculator;

public final class SeqStack<T> implements Stack<T> {
    private SeqList<T> list;

    public SeqStack(int length){
        this.list = new SeqList<T>(length);
    }

    public SeqStack(){
        this(64);
    }
    public SeqStack(T[] values){
        this.list = new SeqList(values);
    }

    public boolean isEmpty(){
        return this.list.isEmpty();
    }

    public void push(T x){
        this.list.insert(list.size(),x);
    }

    public T peek(){
        return this.list.get(list.size()-1);
    }

    public T pop(){
        return list.remove(list.size()-1);
    }

    public String toString(){
        return list.toString();
    }

    public T get(int i){
        return list.get(i);
    }
}
