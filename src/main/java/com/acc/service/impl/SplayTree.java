package com.acc.service.impl;

import java.util.List;

public class SplayTree<K extends Comparable<K>, V> {
	private Node<K, V> root;

	public void insert(K key, V value) {
		if (root == null) {
			root = new Node<>(key, value);
			return;
		}
		root = splay(root, key);
		int cmp = key.compareTo(root.key);
		if (cmp < 0) {
			Node<K, V> newNode = new Node<>(key, value);
			newNode.left = root.left;
			newNode.right = root;
			root.left = null;
			root = newNode;
		} else if (cmp > 0) {
			Node<K, V> newNode = new Node<>(key, value);
			newNode.right = root.right;
			newNode.left = root;
			root.right = null;
			root = newNode;
		} else {
			root.value = value;
		}
	}

	public void remove(K key) {
		if (root == null) {
			return;
		}
		root = splay(root, key);
		if (root.key.compareTo(key) != 0) {
			return;
		}
		if (root.left == null) {
			root = root.right;
		} else {
			Node<K, V> newRoot = root.right;
			root = root.left;
			splay(root, key);
			root.right = newRoot;
		}
	}

	public V find(K key) {
		if (root == null) {
			return null;
		}
		root = splay(root, key);
		if (root.key.compareTo(key) != 0) {
			return null;
		}
		return root.value;
	}

	public List<V> getValuesInOrder() {
		List<V> values = new ArrayList<>();
		inOrderTraversal(root, values);
		return values;
	}

	private Node<K, V> splay(Node<K, V> node, K key) { if (node == null) { return null; } int cmp1 = key.compareTo(node.key); if (cmp1 < 0) { if (node.left == null) { return node; } int cmp2 = key.compareTo(node.left.key); if (cmp2 < 0) { node.left.left = splay(node.left.left, key); node = rotateRight(node); } else if (cmp2 > 0) { node.left.right = splay(node.left.right, key); if (node.left.right != null) { node.left = rotateLeft(node.left); } } return (node.left == null) ? node : rotateRight(node); } else if (cmp1 > 0) { if (node.right == null) { return node; } int cmp2 = key.compareTo(node.right.key); if (cmp2 < 0) { node.right.left = splay(node.right.left, key); if (node.right.left != null) { node.right = rotateRight(node.right); } } else if (cmp2 > 0) { node.right.right = splay(node.right.right, key); node = rotateLeft(node); } return (node.right == null) ? node : rotateLeft(node); } else { return node; } }