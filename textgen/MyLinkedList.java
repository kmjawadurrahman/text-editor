package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		//LLNode<E> n = new LLNode<E>(element);
		if (element==null) {
			throw new NullPointerException("List does not accept null element.");
		}
		
		add(size(), element );
		
		/*n.prev = tail.prev;
		n.prev.next = n;
		n.next = tail;
		n.next.prev = n;
		size += 1; */
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		}
		
		LLNode<E> getNode = head; 
		for (int i = 0; i <= index; i++) {
			getNode = getNode.next;
		}
		return getNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		}
		
		if (element==null) {
			throw new NullPointerException("List does not accept null element.");
		}
		
		LLNode<E> n = head;
		for (int i = 0; i < index; i++) {
			n = n.next;
		}
		//LLNode<E> m = n.next;
		LLNode<E> newNode = new LLNode<E>(element);
		newNode.next = n.next;
		newNode.prev = newNode.next.prev;
		newNode.next.prev = newNode;
		newNode.prev.next = newNode;
		size += 1;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		}
		
		LLNode<E> n = head;
		for (int i = 0; i < index; i++) {
			n = n.next;
		}
		LLNode<E> node = n.next;
		n.next = n.next.next;
		n.next.prev = n;
		size -= 1;
		
		return node.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		}
		
		if (element==null) {
			throw new NullPointerException("List does not accept null element.");
		}
		
		LLNode<E> n = head;
		for (int i = 0; i < index; i++) {
			n = n.next;
		}
		//LLNode<E> m = n.next;
		LLNode<E> newNode = new LLNode<E>(element);
		E prevData = n.next.data;
		newNode.next = n.next.next;
		newNode.prev = newNode.next.prev.prev;
		newNode.next.prev = newNode;
		newNode.prev.next = newNode;
		
		return prevData;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
