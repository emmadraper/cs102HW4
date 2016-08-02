import java.util.ArrayList;
//Persons of the communitymustbe organized into some data structure that provides 
//efficient operations for search (and for insert, because insert is used to fill the data structure)
public class FamTree {
	static class Node{
		public Person person;
		public Node leftChild;
		public Node rightChild;
	}
	// The data type inside the Tree class & inside the Tree Node class in the
	// notes must be changed to:
	public Node root; // first node of tree
	
	public FamTree() { // constructor-no nodes in tree yet
		root = null;
	}

	// -------------------------------------------------------------
	public void insert(Person person1) {
		Node newNode = new Node();
		newNode.person = person1;
		if (root == null) // no node in root
			root = newNode;
		else{
			Node current = root; // start at root
			Node parent;
			while (true) {
				parent = current;
				if (person1.getSSN() < current.person.getSSN()) {
					current = current.leftChild; 
					if(current == null){
						parent.leftChild = newNode;
						return;
					}
				}
				else{
					current = current.rightChild;
					if(current == null){
						parent.rightChild = newNode;
						return;
					}
				}
			} // end while
		} // end else not root
	} // end insert()
	
	public Node find(int SSN) {
		Node current = root;
		while (current.person.getSSN() != SSN) {
			if (SSN < current.person.getSSN()) {
				current = current.leftChild;
			} else {
				current = current.rightChild;
			}
			if (current == null) {
				return null;
			}
		}
		return current;
	}
	
	public ArrayList <Person> halfSibs(ArrayList <Person> hsibs, Person p, Node localRoot){
		if(localRoot != null){
			if((localRoot.person.getFather() == p.getFather()) ^ (localRoot.person.getMother() == p.getMother())){
				hsibs.add(localRoot.person);
			}
			halfSibs(hsibs, p, localRoot.leftChild);
			halfSibs(hsibs, p, localRoot.rightChild);
			
		}
		return hsibs;	
	}
	
	public ArrayList <Person> fullSibs(ArrayList <Person> fsibs, Person p, Node localRoot){
		if(localRoot != null){
			if((localRoot.person.getFather() == p.getFather()) && (localRoot.person.getMother() == p.getMother())){
				if(localRoot.person.getSSN() != p.getSSN()){
					fsibs.add(localRoot.person);
				}
			}
			fullSibs(fsibs, p, localRoot.leftChild);
			fullSibs(fsibs, p, localRoot.rightChild);
			
		}
		return fsibs;
	}
	public ArrayList <Person> children(ArrayList <Person> child, Person p, Node localRoot){
		if(localRoot != null){
			if((localRoot.person.getFather() == p.getSSN()) || (localRoot.person.getMother() == p.getSSN())){
					child.add(localRoot.person);
			}
			children(child, p, localRoot.leftChild);
			children(child, p, localRoot.rightChild);
		}
		return child;
	}
	
	public ArrayList <Person> mutualFriends(ArrayList <Person> mfriends, Person p, Node localRoot){
		if(localRoot != null){
			//traverse through the tree
			//then traverse find the friends list
			for(int y: p.getFriends()){
				for(int y1: find(y).person.getFriends()){
					if(y1 == p.getSSN()){
						mfriends.add(this.find(y).person);
					}
				}
			}
		}
		return mfriends;
	}	
	
	public ArrayList<Person> inverseFriends(ArrayList<Person> ifriends, Person p, Node localRoot) {
		if (localRoot != null) {
			// traverse through the tree
			// then traverse find the friends list
			for (int z : p.getFriends()) {
				boolean inverseFriends = true;
				for (int z1 : find(z).person.getFriends()) {
					if (z1 == p.getSSN()) {
						inverseFriends = false;
					}
				}
				if (inverseFriends = true) {
					ifriends.add(this.find(z).person);
				}
			}
		}
		return ifriends;
	}
	
	Person leader;
	int leaderFriends;
	//Methods to find the max mutual friends
	public Person maxMutualFriends(){
		leader = null;
		leaderFriends = 0;
		helper(root);
		return leader;
	}
	
	public void helper(Node n){
		if(n == null){
			return;
		}
		
		helper(n.leftChild);
		helper(n.rightChild);
		int num = mutualFriends(new ArrayList<Person>(), n.person, n).size();
		if(num > leaderFriends){
			leader = n.person;
			leaderFriends = num;
		}
	}
}