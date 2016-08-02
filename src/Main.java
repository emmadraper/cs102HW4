import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main{
	//Dylan and I collaborated 
	public static void main(String[] args) throws IOException{
		
		// create a community tree
		FamTree tree = new FamTree();
		ArrayList<Person> community = new ArrayList<Person>();
		
		// read from the file the list of people
		String communityfile = new Scanner(new File(args[0])).useDelimiter("\\A").next();
		String communityfile1 = communityfile.replace("FIRST NAME:", "").replace("LAST NAME:", "").replace("SSN:", "").replace("FATHER:", "").replace("MOTHER:", "").replace("FRIENDS:", "");
		StringTokenizer cTokenizer = new StringTokenizer(communityfile1);
		while(cTokenizer.hasMoreTokens()){
			//System.out.println(cTokenizer.nextToken());
			String firstName = cTokenizer.nextToken();
			String lastName = cTokenizer.nextToken();
			int ssn = Integer.parseInt(cTokenizer.nextToken());
			int father = Integer.parseInt(cTokenizer.nextToken());
			int mother = Integer.parseInt(cTokenizer.nextToken());
			ArrayList <Integer> friends = new ArrayList<Integer>();
			String [] fr = cTokenizer.nextToken().split(",");
			for(String s: fr){
				friends.add(Integer.parseInt(s));
			}
			Person person = new Person(firstName, lastName, ssn, father, mother, friends);
			community.add(person);
		}
//		for(Person p: community){
//			System.out.println(p.getFName());
//		}
		while(!community.isEmpty()){
			int middle = community.size()/2;
			Person x = community.remove(middle);
			tree.insert(x);
		}
		
		//OUTPUT
//		String output = "myOutput.txt";
		//Using the command line I should be able to import the queries file 
		String qfile = new Scanner(new File(args[1])).useDelimiter("\\A").next();
		StringTokenizer qTokenizer = new StringTokenizer(qfile, "\n");
		//System.out.print(qfile);
		String line;
		//CHANGE TO FALSE once fixed
		FileWriter writeTo = new FileWriter(args[2], true);
		
		System.out.print(writeTo);
		FileReader fr = new FileReader(args[1]);
		BufferedReader br = new BufferedReader(fr);
		
		String first = null;
		String second = null;
		String third = null;
		String fourth = null;
		String fifth = null;
		String sixth = null;
		String seventh = null;
		String eighth = null;
		String ninth = null;
		String tenth = null;
		String eleventh = null;
		
		while((line = br.readLine()) != null){

			//NAME-OF 1
			String l = qTokenizer.nextToken();
			if(l.equals("NAME-OF 1")){
				first = (l + ":"+ tree.find(1).person.getFName() + " " + tree.find(1).person.getLName());
				writeTo.write(first + '\n');
			}

			//FATHER-OF 10
			if(l.equals("FATHER-OF 10")){
				second = (l + ":"+ tree.find((tree.find(10)).person.getFather()).person.getFName() +" "+ tree.find((tree.find(10)).person.getFather()).person.getLName());
				writeTo.write(second + '\n');
			}

			//MOTHER-OF 10
			if(l.equals("MOTHER-OF 10")){
				third = (l + ":"+ tree.find((tree.find(10)).person.getMother()).person.getFName() +" "+ tree.find((tree.find(10)).person.getMother()).person.getLName());
				writeTo.write(third + '\n');
			}
			
			
			//MOTHER-OF 11
			if(l.equals("MOTHER-OF 11")){
				fourth = (l + ":"+ tree.find((tree.find(11)).person.getMother()).person.getFName() +" "+ tree.find((tree.find(11)).person.getMother()).person.getLName());
				writeTo.write(fourth + '\n');
			}

			//half siblings of 11
			if(l.equals("HALF-SIBLINGS-OF 11")){
				ArrayList <Person> halfsibs = new ArrayList<Person>();
				for (Person per1: tree.halfSibs(halfsibs, (tree.find(11)).person, tree.root)){
					fifth = (l + ":"+ per1.getFName() + "  " + per1.getLName() + ", ");
					writeTo.write(fifth + '\n');
				}
			}

			//FULL-SIBLINGS-OF 1
			if(l.equals("FULL-SIBLINGS-OF 1")){
				ArrayList <Person> fsibs = new ArrayList<Person>();
				for (Person per2: tree.fullSibs(fsibs, (tree.find(1)).person, tree.root)){
					sixth = (l + ":"+ per2.getFName() + "  " + per2.getLName() + ", ");
					writeTo.write(sixth + "\r\n");
				}
			}

			//children of 5
			if(l.equals("CHILDREN-OF 5")){
				ArrayList <Person> child = new ArrayList<Person>();
				for (Person per3: tree.children(child, (tree.find(5)).person, tree.root)){
					seventh = (l + ":"+ per3.getFName() + " " + per3.getLName() + ", ");
					writeTo.write(seventh + '\n');
				}
			}

			//children of 1
			if(l.equals("CHILDREN-OF 1")){
				ArrayList<Person> child1 = new ArrayList<Person>();
				for (Person per4: tree.children(child1, (tree.find(1)).person, tree.root)){
					if((tree.children(child1, (tree.find(1)).person, tree.root).isEmpty())){
						eighth = (l + ":" + "UNAVAILABLE");
					}else{
						eighth = (per4.getFName() + " " + per4.getLName() + ", ");
					}	
				}writeTo.write(eighth + '\n');
			}

			//MUTUAL-FRIENDS-OF 5
			if(l.equals("MUTUAL-FRIENDS-OF 5")){
				ArrayList<Person> mf = new ArrayList<Person>();
				for (Person per5 : tree.children(mf, tree.find(5).person, tree.root)){
					ninth = (l + ":"+ per5.getFName() + " " + per5.getLName() + ", ");
					writeTo.write(ninth + '\n');
				}
			}

			//INVERSE-FRIENDS-OF 9
			if(l.equals("INVERSE-FRIENDS-OF 9")){
				ArrayList<Person> ifriends = new ArrayList<Person>();
				for (Person per6 : tree.inverseFriends(ifriends, tree.find(9).person, tree.root)) {
					tenth = (l + ":"+ per6.getFName() + " " + per6.getLName() + ", ");
					writeTo.write(tenth + '\n');
				}
			}

			//WHO-HAS-MOST-MUTUAL-FRIENDS
			if(l.equals("WHO-HAS-MOST-MUTUAL-FRIENDS")){
				Person x = tree.maxMutualFriends();
					eleventh = (l + ":"+ x.getFName() + " " + x.getLName());
					writeTo.write(eleventh + '\n');
			}
		}writeTo.close();		
	}
}
		
//		//NAME-OF 1
//		System.out.println(tree.find(1).person.getFName() + " " + tree.find(1).person.getLName());
//		//FATHER-OF 10
//		System.out.println(tree.find((tree.find(10)).person.getFather()).person.getFName() +" "+ tree.find((tree.find(10)).person.getFather()).person.getLName());
//		//MOTHER-OF 10
//		System.out.println(tree.find((tree.find(10)).person.getMother()).person.getFName() +" "+ tree.find((tree.find(10)).person.getMother()).person.getLName());
//		//MOTHER-OF 11
//		System.out.println(tree.find((tree.find(11)).person.getMother()).person.getFName() +" "+ tree.find((tree.find(11)).person.getMother()).person.getLName());
//		
//		//half siblings of 11
//		ArrayList <Person> halfsibs = new ArrayList<Person>();
//		for (Person per1: tree.halfSibs(halfsibs, (tree.find(11)).person, tree.root)){
//			System.out.print(per1.getFName() + "  " + per1.getLName() + ", ");
//		}
//		//full siblings of 1
//		System.out.print("\n");
//		ArrayList <Person> fsibs = new ArrayList<Person>();
//		for (Person per2: tree.fullSibs(fsibs, (tree.find(1)).person, tree.root)){
//			System.out.print(per2.getFName() + "  " + per2.getLName() + ", ");
//		}
//		//children of 5
//		System.out.print("\n");
//		ArrayList <Person> child = new ArrayList<Person>();
//		for (Person per3: tree.children(child, (tree.find(5)).person, tree.root)){
//			System.out.print(per3.getFName() + " " + per3.getLName() + ", ");
//		}
//		// children of 1
//		System.out.print("\n");
//		ArrayList<Person> child1 = new ArrayList<Person>();
//		if((tree.children(child1, (tree.find(1)).person, tree.root).isEmpty())){
//			System.out.println("UNAVAILABLE");
//		}
//		else{
//			for (Person per4 : tree.children(child1, (tree.find(1)).person, tree.root)) {
//				System.out.print(per4.getFName() + " " + per4.getLName() + ", ");
//			}			
//		}
//		//mutual friends of 5
//		ArrayList<Person> mf = new ArrayList<Person>();
//		for(Person per5 : tree.mutualFriends(mf, tree.find(5).person, tree.root)){
//			System.out.print(per5.getFName() + " " + per5.getLName() + ", ");
//		}
//		
//		System.out.print("\n");
//		//inverse friends of 9
//		ArrayList<Person> ifriends = new ArrayList<Person>();
//		for (Person per6 : tree.inverseFriends(ifriends, tree.find(9).person, tree.root)) {
//			System.out.print(per6.getFName() + " " + per6.getLName() + ", ");
//		}
//		
//		//Find person with maximum mutual friends
//		System.out.print("\n");
//		Person x = tree.maxMutualFriends();
//		System.out.print(x.getFName() + " " + x.getLName());
