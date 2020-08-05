package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size=0;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		String strLower = word.toLowerCase();
		char[] strCharArray = strLower.toCharArray();
		TrieNode curr = root;
		TrieNode next = null;
		boolean indicator = false;
		for (Character c: strCharArray) {
			next = curr.insert(c);
			
			if (next == null && !(curr.getChild(c)).endsWord()) {
				indicator = true;
				next = curr.getChild(c);
				curr = next;
			}
			else if (next == null) {
				indicator = false;
				next = curr.getChild(c);
				curr = next;
			}
			else {
				indicator = true;
				curr = next;
			}
		}
		if (indicator == true && !curr.endsWord()) {
			size += 1;
			curr.setEndsWord(true);
		}
	    return indicator;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		String strLower = s.toLowerCase();
		char[] strCharArray = strLower.toCharArray();
		TrieNode curr = root;
		TrieNode next = null;
		int i=0;
		while (i < strCharArray.length) {
			next = curr.getChild(strCharArray[i]);
			if (next == null) return false;
			else curr = next;
			i++;
		}
		if (curr.endsWord()) {
			return true;
		}
		return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 String strLower = prefix.toLowerCase();
    	 char[] strCharArray = strLower.toCharArray();
 		 TrieNode curr = root;
 		 TrieNode next = null;
 		 TrieNode child;
 		 int i=0;
 		 if (strCharArray.length>0) {
 		 	while (i < strCharArray.length) {
 		 		next = curr.getChild(strCharArray[i]);
 				if (next == null) return new LinkedList<String>();
 				else curr = next;
 				i++;
 		 		/*next = curr.getChild(strCharArray[i]);
 		 		curr = next;
 		 		i++;*/
 		 	}
 		 	//if (curr == null)  return new LinkedList<String>();
 		 }
 		 
 		/* if (curr.getChild(strCharArray[i]) == null) {
 			 return new LinkedList<String>();
 		 } */
 		 Queue<TrieNode> q = new LinkedList<TrieNode>();
 		 q.add(curr);
 		 List<String> completions = new LinkedList<String>();
 		 while (!q.isEmpty() && completions.size() < numCompletions) {
 			 curr = q.remove();
 			 if (curr.endsWord()) {
 				 completions.add(curr.getText());
 			 }
 			 Set<Character> validChars = curr.getValidNextCharacters();
 			 for (Character c: validChars) {
 				 child = curr.getChild(c);
 				 q.add(child);
 			
 			 }
 		 }
 		 return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}