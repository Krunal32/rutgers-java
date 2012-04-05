package structures;

import java.util.*;

/**
 * This class implements an HTML DOM Tree. Each node of the tree is a TagNode, with fields for
 * tag/text, first child and sibling.
 * 
 */
public class Tree {
	
	/**
	 * Root node
	 */
	TagNode root=null;
	
	/**
	 * Scanner used to read input HTML file when building the tree
	 */
	Scanner sc;
	
	/**
	 * Tokenizer used to parse the HTML file into proper elements
	 */
	StringTokenizer tk;
	
	/**
	 * Initializes this tree object with scanner for input HTML file
	 * 
	 * @param sc Scanner for input HTML file
	 */
	public Tree(Scanner sc) {
		this.sc = sc;
		root = null;
		
		String doc = "";
		while(this.sc.hasNext())
			doc = doc + " " + this.sc.next();
		
		this.tk = new StringTokenizer(doc, "<>/ ", true); // the true statement WILL have tokenizer return the token.
	}
	
	/**
	 * Builds the DOM tree from input HTML file
	 */
	public void build() {
		this.root = this.Build();
	}
	
	private TagNode Build()
	{
		TagNode head = null, ptr = null;
		while (this.tk.hasMoreTokens())
		{
			TagNode new_tag;
			String word = this.tk.nextToken().trim();
			
			if (word.equals("") ||
					word.equals("<") ||
					word.equals(">")) continue;
			
			if (word.equals("html") || word.equals("body") || word.equals("p") || 
				word.equals("em") || word.equals("b") || word.equals("table") || 
				word.equals("tr") || word.equals("td") || 
				word.equals("ol") || word.equals("ul") || word.equals("li"))
			{
				new_tag = new TagNode(word, this.Build(), null);
			}
			else if (word.equals("/"))
			{
				this.tk.nextToken(); // skip next token
                break;
			}
			else
			{
				new_tag = new TagNode(word, null, null);
			}
			
			if (ptr == null)
			{
				head = new_tag;
				ptr = new_tag;
			}
			else
			{
				ptr.sibling = new_tag;
				ptr = new_tag;
			}
		}
		
		return head;
	}

	
	
	/**
	 * Replaces all occurrences of an old tag in the DOM tree with a new tag
	 * 
	 * @param oldTag Old tag
	 * @param newTag Replacement tag
	 */
	private void ReplaceTag(String oldTag, String newTag, TagNode node)
	{
		if (node.firstChild != null)
			this.ReplaceTag(oldTag, newTag, node.firstChild);
		
		if (node.sibling != null)
			this.ReplaceTag(oldTag, newTag, node.sibling);
		
		if (node.tag.equalsIgnoreCase(oldTag))
			node.tag = newTag;
	}
	
	public void replaceTag(String oldTag, String newTag) {
		// we only allow specific replace operations to occur
		if ((oldTag.equalsIgnoreCase("b") && newTag.equalsIgnoreCase("em"))
				|| (oldTag.equalsIgnoreCase("ol") && newTag.equalsIgnoreCase("ul"))
				|| (oldTag.equalsIgnoreCase("em") && newTag.equalsIgnoreCase("b"))
				|| (oldTag.equalsIgnoreCase("ul") && newTag.equalsIgnoreCase("ol"))
			)
		{
			this.ReplaceTag(oldTag, newTag, this.root);
		}
	}
	
	/**
	 * Boldfaces every column of the given row of the table in the DOM tree. The boldface (b)
	 * tag appears directly under the td tag of every column of this row.
	 * 
	 * @param row Row to bold, first row is numbered 1 (not 0).
	 */
	public void boldRow(int row) 
	{
		this.BoldRow(row, this.root);
	}
	
	private void BoldRow(int row, TagNode node)
	{
		if (node.firstChild != null)
			this.BoldRow(row, node.firstChild);
		
		if (node.sibling != null)
			this.BoldRow(row, node.sibling);
		
		if (node.tag.equals("table"))
			this._BoldRow(row, node.sibling);
	}
	
	private void _BoldRow(int row, TagNode node)
	{
		TagNode prev_ptr = null;
		TagNode ptr = node;
		int count = 0;
		while(ptr != null)
		{
			count++;
			if (row == count)
			{
				TagNode new_tag = new TagNode("b", ptr, ptr.sibling);
				if (prev_ptr != null)
					prev_ptr.sibling = new_tag;
				
				ptr.sibling = null;
			}
			
			prev_ptr = ptr;
			ptr = ptr.sibling;
		}
	}
	
	/**
	 * Remove all occurrences of a tag from the DOM tree. If the tag is p, em, or b, all occurrences of the tag
	 * are removed. If the tag is ol or ul, then All occurrences of such a tag are removed from the tree, and, 
	 * in addition, all the li tags immediately under the removed tag are converted to p tags. 
	 * 
	 * @param tag Tag to be removed, can be p, em, b, ol, or ul
	 */
	public void removeTag(String tag) {
		if (tag.equals("p") || tag.equals("em") || tag.equals("b") || tag.equals("ol") || tag.equals("ul"))
			this.root = this.RemoveTag(tag, this.root);
	}
	
	private TagNode RemoveTag(String tag, TagNode node)
	{
		if (node == null)
			return null;
		
		node.sibling = RemoveTag(tag, node.sibling);
		node.firstChild = RemoveTag(tag, node.firstChild);
		
		if (node.tag.equals(tag))
		{
			if (node.firstChild == null)
				return node.sibling;
			else if (node.firstChild != null || node.sibling != null)
			{
				TagNode ptr = node.firstChild;
				while (ptr.sibling != null)
					ptr = ptr.sibling;
				
				ptr.sibling = node.sibling;
				
				return node.firstChild;
			}
			
			if (node.tag.equals("ol") || node.tag.equals("ul"))
			{
				this._RemoveTag(node.sibling);
			}
		}
		
		return node;
	}
	
	private void _RemoveTag(TagNode node)
	{
		TagNode ptr = node;
		while(ptr != null)
		{
			if (ptr.tag.equals("li"))
				ptr.tag = "p";
			
			ptr = ptr.sibling;
		}
	}
	
	/**
	 * Adds a tag around all occurrences of a word in the DOM tree.
	 * 
	 * @param word Word around which tag is to be added
	 * @param tag Tag to be added
	 */
	public void addTag(String word, String tag) {
		if (tag.equals("em") || tag.equals("b"))
		{
			this.root = this.AddTag(word, tag, this.root);
		}
	}
	
	private TagNode AddTag(String word, String tag, TagNode node)
	{
		if (node.sibling != null)
			node.sibling = this.AddTag(word, tag, node.sibling);
		
		if (node.firstChild != null)
			node.firstChild = this.AddTag(word, tag, node.firstChild);
		
		if (node.tag.contains(word))
		{
			TagNode newTag = new TagNode(tag, node, node.sibling);
			node.sibling = null;
			return newTag;
		}
		
		return node;
	}
	
	/**
	 * Gets the HTML represented by this DOM tree. The returned string includes
	 * new lines, so that when it is printed, it will be identical to the
	 * input file from which the DOM tree was built.
	 * 
	 * @return HTML string, including new lines. 
	 */
	public String getHTML() {
		StringBuilder sb = new StringBuilder();
		getHTML(root, sb);
		return sb.toString();
	}
	
	private void getHTML(TagNode root, StringBuilder sb) {
		for (TagNode ptr=root; ptr != null;ptr=ptr.sibling) {
			if (ptr.firstChild == null) {
				sb.append(ptr.tag);
				sb.append("\n");
			} else {
				sb.append("<");
				sb.append(ptr.tag);
				sb.append(">\n");
				getHTML(ptr.firstChild, sb);
				sb.append("</");
				sb.append(ptr.tag);
				sb.append(">\n");	
			}
		}
	}
	
}
