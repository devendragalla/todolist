package com.acc.service.impl;

import java.util.List;

import com.acc.domain.Task;

public class CuckooHashing<K, V> {
    private int capacity;
    private int size;
    private final int MAX_ITERATIONS = 1000;
    private K[] table1Keys;
    private V[] table1Values;
    private K[] table2Keys;
    private V[] table2Values;

    public CuckooHashing(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.table1Keys = (K[]) new Object[capacity];
        this.table1Values = (V[]) new Object[capacity];
        this.table2Keys = (K[]) new Object[capacity];
        this.table2Values = (V[]) new Object[capacity];
        
    }

    public void insert(K key, V value) {
        if (size == capacity) {
            resize();
        }
        int table1Index = getIndex1(key);
        if (table1Keys[table1Index] == null) {
            table1Keys[table1Index] = key;
            table1Values[table1Index] = value;
            size++;
        } else {
            int table2Index = getIndex2(key);
            if (table2Keys[table2Index] == null) {
                table2Keys[table2Index] = key;
                table2Values[table2Index] = value;
                size++;
            } else {
                rehash(key, value, table1Index, table2Index, 0);
            }
        }
    }
    public List<Task> search(K key) {
        int table1Index = getIndex1(key);
        if (table1Keys[table1Index] != null && table1Keys[table1Index].equals(key)) {
            return (List<Task>) table1Values[table1Index];
        }
        int table2Index = getIndex2(key);
        if (table2Keys[table2Index] != null && table2Keys[table2Index].equals(key)) {
            return (List<Task>) table2Values[table2Index];
        }
        return null;
    }
    public void resize() {
        capacity *= 2;
        K[] oldTable1Keys = table1Keys;
        V[] oldTable1Values = table1Values;
        K[] oldTable2Keys = table2Keys;
        V[] oldTable2Values = table2Values;

        table1Keys = (K[]) new Object[capacity];
        table1Values = (V[]) new Object[capacity];
        table2Keys = (K[]) new Object[capacity];
        table2Values = (V[]) new Object[capacity];
        size = 0;

        for (int i = 0; i < oldTable1Keys.length; i++) {
            if (oldTable1Keys[i] != null) {
                insert(oldTable1Keys[i], oldTable1Values[i]);
            }
            if (oldTable2Keys[i] != null) {
                insert(oldTable2Keys[i], oldTable2Values[i]);
            }
        }
    }

    public void rehash(K key, V value, int table1Index, int table2Index, int iteration) {
        if (iteration > MAX_ITERATIONS) {
            resize();
            insert(key, value);
            return;
        }
        K tempKey = table2Keys[table2Index];
        V tempValue = table2Values[table2Index];
        table2Keys[table2Index] = key;
        table2Values[table2Index] = value;
        iteration++;
        int newTable1Index = getIndex1(tempKey);
        if (table1Keys[newTable1Index] == null) {
            table1Keys[newTable1Index] = tempKey;
            table1Values[newTable1Index] = tempValue;
            size++;
        } else {
            int newTable2Index = getIndex2(tempKey);
            if (table2Keys[newTable2Index] == null) {
                table2Keys[newTable2Index] = tempKey;
                table2Values[newTable2Index] = tempValue;
                size++;
            } else {
                rehash(tempKey, tempValue, newTable1Index, newTable2Index, iteration);
            }
        }
    }
    public int getIndex1(K key) {
        int hash = key.hashCode();
        return Math.abs(hash % capacity);
    }

    public int getIndex2(K key) {
        int hash = key.hashCode();
        int index = Math.abs((hash % capacity) + 1);
        return index % capacity;
    }

}

