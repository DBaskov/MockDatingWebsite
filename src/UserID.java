import java.util.*;


public class UserID {
  
	private String userID;
	private String password;
	private String name;
	final static int NUM_UID_ENTRIES = 3;
	private Attributes attributes;
	private HashSet<String> contacts; //todo change datastructure
	private SearchCriteria userCriteria;
	
	
	public UserID(String userID, String password, String name) {
		
		this.userID = userID;
		this.password = password;
		this.name = name;
		contacts = new HashSet<String>(10, 5);
	}
	
	public UserID(String userID, String password, String name, Attributes attributes) {
		
		this.userID = userID;
		this.password = password;
		this.name = name;
		this.attributes = attributes;
		contacts = new HashSet<String>(10, 5);
	}
	
	public UserID(String userID, String password, String name, 
			Attributes attributes, HashSet<String> friends) {
		
		this.userID = userID;
		this.password = password;
		this.name = name;
		this.attributes = attributes;
		this.contacts = friends;
	}
	
	public int compareEyeColor(UserID userA, UserID userB, Attributes.EyeColor eyeColor) {
		
		Attributes.EyeColor aEyeColor = userA.getAttributes().getEyeColor();
		Attributes.EyeColor bEyeColor = userB.getAttributes().getEyeColor();
		
		if(aEyeColor.equals(eyeColor) && !bEyeColor.equals(eyeColor))
			return 1;
		else if(bEyeColor.equals(eyeColor) && !aEyeColor.equals(eyeColor))
			return -1;
		else
			return 0;
		
	}
	
	public void setUpSearch(Scanner scan) {
		
		userCriteria = new SearchCriteria(scan);
	}
	
	public SearchCriteria getUserCriteria() {
		return userCriteria;
	}
	
	public String getName() {
		
		return this.name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	protected HashSet<String> getFriendList() {
		
		return this.contacts;
	}
	
	protected void addContact(String username) {
		assert(username != null);
		contacts.add(username);
	}
	
	protected boolean removeContact(String usernameToRemove) {
		assert(usernameToRemove != null);
		
		return contacts.remove(usernameToRemove);
	}
	
	public String getUserID() {
		
		return this.userID;
	}
	
	public void setUserID(String userID) {
		
		this.userID = userID;
	}
	
	String getPassword() {
		
		return this.password;
	}
	
	protected Attributes getAttributes() {
		
		return attributes;
	}
	
	protected void printContacts() {
		
		if(contacts.isEmpty())
			System.out.println("No contacts");
		
		String username;
		Iterator<String> it = contacts.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	public String toString() {
		
		return "Name: " + name + "  Username: " + userID + "  " + attributes;
	}
	
}
