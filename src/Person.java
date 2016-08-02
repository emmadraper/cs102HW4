import java.util.ArrayList;

public class Person {
	// data pieces provided in the paragraph
	public String FName;
	public String LName;
	public int SSN;
	public int Father;
	public int Mother;
//	public Person leftChild; 
//	public Person rightChild;
	public ArrayList<Integer> friends;

	public Person(){
	}
	public Person(String FName, String LName, int SSN, int Father, int Mother, ArrayList<Integer> friends){
		this.FName = FName;
		this.LName = LName;
		this.SSN = SSN;
		this.Father = Father;
		this.Mother = Mother;
		this.friends = friends;
	}

	// the accessors
	public int getSSN() {
		return SSN;
	}

	public String getFName() {
		return FName;
	}

	public String getLName() {
		return LName;
	}

	public int getMother() {
		return Mother;
	}

	public int getFather() {
		return Father;
	}

	public ArrayList<Integer> getFriends() {
		return friends;
	}
}// end class Person
