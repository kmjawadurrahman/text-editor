/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		assertEquals("Remove: check 'prev' pointer is correct ", list1.head, list1.head.next.prev);
		
		int b = list1.remove(1);
		assertEquals("Remove: check a is correct ", 42, b);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 1, list1.size());
		
		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		try {
			list1.remove(list1.size() + 1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		list1.add(15);
		assertEquals("AddEnd: check size is correct ", 4, list1.size());
		assertEquals("AddEnd: check if element added to end of list ", (Integer)15, list1.get(list1.size() - 1));
		assertEquals("AddEnd: check 'next' pointer is correct ", list1.tail, list1.tail.prev.next);
	
		try {
			list1.add(null);
			fail("Null input not accepted by the set method for this list.");
		}
		catch (NullPointerException e) {
		
		}

	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		list1.add(15);
		assertEquals("Size: check size is correct ", 4, list1.size());
		assertEquals("Size: check size is correct ", 0, emptyList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		list1.add(0,14);
		list1.add(4,16);
		
		assertEquals("AddAtIndex: check size is correct ", 5, list1.size());
		assertEquals("AddAtIndex: check if element added to end of list ", (Integer)16, list1.get(list1.size()-1));
		assertEquals("AddAtIndex: check 'next' pointer is correct ", list1.tail, list1.tail.prev.next);
		assertEquals("AddAtIndex: check if element added to start of list ", (Integer)14, list1.get(0));
		assertEquals("AddAtIndex: check 'prev' pointer is correct ", list1.head, list1.head.next.prev);
		
		try {
			list1.add(-1,5);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		try {
			list1.add(list1.size() + 1, 30);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		try {
			list1.add(1,null);
			fail("Null input not accepted by the set method for this list.");
		}
		catch (NullPointerException e) {
		
		}
		
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		try {
			list1.set(-1,5);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		try {
			list1.set(list1.size(), 30);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
	    
		try {
			int b = list1.set(1, null);
			fail("Null input not accepted by the set method for this list.");
		}
		catch (NullPointerException e) {
		
		}
		
		int c = list1.set(1,15);
		assertEquals("Set method_Size: check size is correct ", 3, list1.size());
		assertEquals("Set: check if element is set correctly ", (Integer)15, list1.get(1));
		
		int a = list1.set(0, 3);
		assertEquals("Set: check a is correct ", 65, a);
		
		assertEquals("Set: check 'prev' pointer is correct ", list1.head, list1.head.next.prev);
		
	}
	
	
	// TODO: Optionally add more test methods.
	
}
