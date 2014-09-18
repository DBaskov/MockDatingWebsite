import java.util.*;
import java.sql.*;
import java.io.*;

public class UserManager {

	public static Hashtable<String, UserID> registeredUsers;
	final static int HTABLE_INIT_CAP = 10; //initial capacity of hash table
	final static int HTABLE_LOAD_FACT = 7; //load factor
	final static int MIN_ENTRIES = Attributes.NUM_ATTRIBUTES + UserID.NUM_UID_ENTRIES;
	
	/*
	 * This Constructor is set up to take the file scanner and fill in the data for all the members
	 */
	
	public UserManager() {
		
		registeredUsers = new Hashtable<String, UserID>(HTABLE_INIT_CAP, HTABLE_LOAD_FACT);
		DBConnect connection = new DBConnect();
		connection.setResultSet();
		ResultSet data = connection.getData();
		
		try {
			while(data.next()) {
				String username = data.getString("Username");
				String password = data.getString("Password");
				String name = data.getString("Name");
				
				String[] enumFieldString = new String[Attributes.NUM_ENUM_FIELDS];
				int start = UserID.NUM_UID_ENTRIES + 1;
				int i = start;
				int j = 0;
				while(i < start + Attributes.NUM_ENUM_FIELDS) {
					enumFieldString[j] = data.getString(i);
					i++;
					j++;
				}
				
				int age = data.getInt("Age");
				int weight = data.getInt("Weight");
				double height = data.getDouble("Height");
				
				Attributes attributes = new Attributes(enumFieldString, age, weight, height);
				UserID user = new UserID(username, password, name, attributes);
				registeredUsers.put(username, user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection();
	}
	
	public enum Func {
		USERNAME, PASSWORD, NAME;
	}
	
	protected static Hashtable<String, UserID> getRegistredUsers() {
		return registeredUsers;
	}
	
	public static UserID signIn(Scanner scan) {
		
		boolean valid = false;
		UserID user = null;
		//while(!valid) {
			String username = checkString(Func.USERNAME, scan, false, null);
			if(username == null) { return null; }
			user = registeredUsers.get(username);
			String password = checkString(Func.PASSWORD, scan, false, user);
			if(password == null) { return null; }
			
		//}
		
		return user;
	}
	
	protected static void PrintUserInfoList() {
		Collection<UserID> collection = registeredUsers.values();
		Iterator<UserID> it =  collection.iterator();
		while(it.hasNext()) {
		  System.out.println(it.next());
		}
	}
	
	public static UserID createNewAccount(Scanner scan) {
		
		String username = checkString(Func.USERNAME, scan, true, null);
		String password = checkString(Func.PASSWORD, scan, true, null);
		String name = checkString(Func.NAME, scan, true, null);
		
		
		//Attributes
		Attributes.Gender gender = (Attributes.Gender) setUpEnumAttribute(scan, Attributes.AttributeEnum.GENDER);
		int age = makeSureValInRange(scan, 18, 200, "age", ValType.INTEGER, false, true).intValue();
		int weight = makeSureValInRange(scan, 70, 2000, "weight", ValType.INTEGER, false, true).intValue();
	    double height = makeSureValInRange(scan, 3, 10, "height", ValType.DOUBLE, false, true).doubleValue();
	    Attributes.Sexuality sexuality = (Attributes.Sexuality)setUpEnumAttribute(scan, Attributes.AttributeEnum.SEXUALITY);
	    Attributes.EyeColor eyeColor = (Attributes.EyeColor)setUpEnumAttribute(scan, Attributes.AttributeEnum.EYE_COLOR);
	    Attributes.HairColor hairColor = (Attributes.HairColor)setUpEnumAttribute(scan, Attributes.AttributeEnum.HAIR_COLOR);
	    Attributes.Ethnicity ethnicity = (Attributes.Ethnicity)setUpEnumAttribute(scan, Attributes.AttributeEnum.ETHNICITY);
	    Attributes.Education education = (Attributes.Education)setUpEnumAttribute(scan, Attributes.AttributeEnum.EDUCATION);
	    Attributes.Zodiac zodiac = (Attributes.Zodiac)setUpEnumAttribute(scan, Attributes.AttributeEnum.ZODIAC);
	    
	    //preparing the fields to be packaged and updated in database
	    String[] strFields = new String[Attributes.NUM_ENUM_FIELDS + UserID.NUM_UID_ENTRIES];
	    int i = 0;
	    strFields[i] = username;
	    strFields[++i] = password;
	    strFields[++i] = name;
	    strFields[++i] = gender.toString();
	    strFields[++i] = sexuality.toString();
	    strFields[++i] = ethnicity.toString();
	    strFields[++i] = eyeColor.toString();
	    strFields[++i] = hairColor.toString();
	    strFields[++i] = education.toString();
	    strFields[++i] = zodiac.toString();
	    DBConnect connection = new DBConnect();
	    connection.insertRow(strFields, age, weight, height);
	    //Update registered users hash table
	    Attributes attr = new Attributes(gender, sexuality, eyeColor, hairColor, 
	    		ethnicity, education, zodiac, age, weight, height);
	    UserID user = new UserID(username, password, name, attr);
	    registeredUsers.put(username, user);
	    return user;
	}
	
	public static Enum<?> setUpEnumAttribute(Scanner scan, Attributes.AttributeEnum attribute) {
		int num;
		Attributes.printEnumNValuesOfEnumAttribute(attribute);
		num = makeSureValInRange(scan, 1, Attributes.getEnumAttrValues(attribute).length, 
				attribute.toString(), ValType.INTEGER, true, true).intValue();
		
		Enum<?> attr = Attributes.getEnumAttrByVal(num, attribute);
		System.out.println(inputString(attr.getClass().getSimpleName(), attr.toString(), StringOption.SELECTED, true));
		return attr;
	}
	
	public enum StringOption {
		SELECT, INCORRECT, SELECTED;
	}
	
	
	
	public static String inputString(String enumName, String enumVal, 
			StringOption option, boolean enumeration) {
		
		if(enumeration) {
		  if(option == StringOption.SELECT)
			return "\nPlease select the proper option for your " + enumName + 
					" using correspoding value on keyboard: ";
		  else if(option == StringOption.INCORRECT)
			return "\nThe value you had entered for " + enumName + " does not exist";
		  else
			return "\nYou had selected: " + enumVal + " for your " + enumName + " attribute";
		}
		else {
			if(option == StringOption.SELECT)
				return "\nPlease enter a proper value for your " + enumName;
			else if(option == StringOption.INCORRECT)
				return "\nThe value you had enter is invalid";
			else
				return "error attributeSettingString";
		}
	}
	
	/*
	 * checks range for the value that corresponds to enums, all enums must be 1 to x where
	 * x is the numbers of values in enums
	 */
	
	public enum ValType {
		INTEGER, DOUBLE;
	}
	
	/*
	 *  Making sure that the user input is in range
	 *  @param1 Scanner object for input
	 *  @param2 lowerbound integer
	 *  @param3 upperbound integer
	 *  @param4 String that specifies for which attribute/field input is for
	 *  printing purposes
	 *  @param5 ValType enum that specifies whether the primitive value is
	 *  an int or double
	 *  @param6 boolean that specifies whether the input is an Enum value
	 *  @param7 boolean that specifies whether to return the value if out of
	 *  range (false) or not (true)
	 */
	public static Number makeSureValInRange(Scanner input, int lowerbound, int upperbound, 
			String inputFor, ValType valType, boolean enumeration, boolean withinRange) {
		
		boolean valid = !withinRange;
		Number val = 0;
		
		while(!valid) {

			System.out.println(inputString(inputFor, null, StringOption.SELECT, enumeration));
			
			val = valType == ValType.INTEGER ? input.nextInt() : input.nextDouble();
			
		    if(val.intValue() < lowerbound || val.intValue() > upperbound) {
			  System.out.println(inputString(inputFor, null, StringOption.INCORRECT, enumeration));
		    }
		    else {
		    	valid = true;
		    }
		}
		return val;
	}
	
	/*
	 * Similar to makeSureValInRange function except it is meant to return
	 * more than one values of valid user input via LinkedList and it will
	 * not return unless all of the input is valid
	 * @param1 scanner object
	 * @param2 lowerbound int
	 * @param3 upperbound int
	 * @param4 String that represents the entity we are entering the values for
	 */
	public static LinkedList<Integer> makeSureValInRange2(Scanner scan, int lowerbound, int upperbound, 
			String inputFor) {
		assert(lowerbound <= upperbound);
		LinkedList<Integer> list = new LinkedList<Integer>();
		System.out.println(SearchCriteria.prioritySetUpString(SearchCriteria.StringEnum.SELECT_CRITERIA, 
				inputFor)); //prints the options to the user
		int value = scan.nextInt();
		if(valInRange(lowerbound, upperbound, value)) {
		  list.add(value); //If the first inputted is in range then we add more values
		  
		  boolean done = false;
		  int i = lowerbound;
		  while(!done && i < upperbound) { //keep looking for input until user enters a duplicate value
			                               //or the LinkedList of input is full
			  value = scan.nextInt();
			  if(!list.contains(value) && valInRange(lowerbound, upperbound, value))
				  list.add(value);
			  else
				  done = true;
			  i++;
		  }
		  return list;
		}
		else { //If the first value intered is not valid, then we return a null, which means that
			//we use default values (all values within range)
			System.out.println(SearchCriteria.prioritySetUpString(SearchCriteria.StringEnum.DEFAULT, 
					inputFor));
			return null;
		}
	}
	
	/*
	 * method used to input values for search criteria ranges (Age, Weight, Height)
	 * User can enter -1 to input default values (lowerbound, upperbound) or the function
	 * won't terminate until proper input is entered for the range
	 * @param5 Enum specifies type of val we are entering input for; either an integer or double
	 */
	public static Number[] makeSureValInRangeRange(Scanner scan, int lowerbound, int upperbound,
			String className, ValType type) {
		
		System.out.println("Press and enter any value on keyboard to enter values for " + className 
				+ " range or enter -1 to use default values for the range");
		Number[] range = new Number[2];
		
		if(scan.nextInt() == -1) {
			System.out.println("You had selected default values");
			range[0] = lowerbound;
			range[1] = upperbound;
			return range;
		}
		else {
			range[0] = makeSureValInRange(scan, lowerbound, upperbound, "lowerbound", type, false, true);
			range[1] = makeSureValInRange(scan, lowerbound, upperbound, "upperbound", type, false, true);
			return range;
		}
	}
	
	public static boolean valInRange(int lowerbound, int upperbound, int val) {
		assert(lowerbound <= upperbound);
		return val >= lowerbound && val <= upperbound;
	}
	
	
	private static String checkString(Func function, Scanner input, boolean newAccount, UserID uid) {
		
		String str = "";
		boolean valid = false;
		while(!valid) {
			if(function == Func.USERNAME) {
				
				if(newAccount) {System.out.println("Please enter your new username: ");}
				else { System.out.println("Please enter your user name"); }
				str = input.next();
				valid = checkUserName(str, newAccount);
			}
			else if(function == Func.PASSWORD) {
				System.out.println("Please enter your password: ");
				str = input.next();
				valid = checkPassword(str, newAccount, uid);
				
			}
			else {
				System.out.println("Please enter your first name: ");
				str = input.next();
				valid = str.length() > 2;
			}
			
			if(!valid && !newAccount)
				return null;
		}
		
		return !valid ? null : str;
	}
	
	/*
	 * UserID uid should not be null
	 */
	private static boolean checkPassword(String password, boolean newPassword, UserID uid) {
		
		if(password == null || password.length() < 8) {
			System.out.println("Password must be length of 8 or more.");
			return false;
		}
		
		assert(uid != null);
		if(!newPassword && !uid.getPassword().equals(password)) {
			
		    System.out.println("Password does not match with the username");
			return false;
		}
		return true;
	}
	
	private static boolean checkUserName(String username, boolean newUserName) {
		
		if(username == null || (username.length() == 1 && username.charAt(0) == ' ')) {
			System.out.println("Please enter a proper username.");
			return false;
		}
		
		if(newUserName) {
		   if(registeredUsers.containsKey(username)) {
			 System.out.println("User Name already exists, please enter a different one");
			 return false;
		   }
		   else 
			   return true;
		}
		else {
			boolean exist = registeredUsers.containsKey(username);
			if(!exist) {System.out.println("Username doesn't exist"); }
			return exist; 
		}
	
	}
	
	protected static void searchBasedOnCriteria(UserID user, Scanner scan) {
		
		assert(user != null && scan != null);
		
		SearchCriteria criteria = user.getUserCriteria();
		if(criteria!= null) { //
			System.out.println("Current search criteria:\n" + criteria);
			System.out.println("Press 1 if you would like to set up new search criteria or "
					+ "anything else if you want to use current one");
			String input = scan.next();
			if(input.length() == 1 && input.charAt(0) == '1')
				user.setUpSearch(scan);
		}
		else {
			user.setUpSearch(scan);
		}
		
		Search search = new Search(user);
		printUserList(search.findUsersBasedOnSearchCriteria());
	}
	
	protected static void printUserList(Collection<UserID> users) {
		
		Iterator<UserID> it = users.iterator();
		
		while(it.hasNext()) {
			UserID user = it.next();
			System.out.println("Username: " + user.getUserID() + "Name: " + user.getName()
					+ " " + user.getAttributes());
		}
		
		if(users.isEmpty())
			System.out.println("No results");
	}
	
	
	protected static void addContact(UserID user, Scanner scan) {
		assert(user != null);
		System.out.println("Please enter username of the user you want to add: ");
		String username = scan.next();
		UserID userAboutToAdd = registeredUsers.get(username);
		if(userAboutToAdd != null) {
			user.addContact(username);
			System.out.println("Contact added.");
		}
		else 
			System.out.println("Contact cannot be added, check make sure username is correct");
		
	}
	
	protected static void removeContact(UserID user, Scanner scan) {
		System.out.println("Please enter username of the user you want to remove: ");
		String username = scan.next();
		
		if(user.removeContact(username)) {
			System.out.println("Remove was successful.");
		}
		else {
			System.out.println("Cannot remove contact, check make sure username is correct");
		}
	}
}
