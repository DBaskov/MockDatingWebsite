import java.util.*;

public class Driver {
	
	public enum UserOptions {
		SIGN_IN(1), CREATE_NEW_ACCOUNT(2), VIEW_LIST_OF_MEMBERS(3), QUIT(4);
		
		private int value;
		private UserOptions(int value) {
			this.value = value;
		}
		
		public static UserOptions getEnumByVal(int val) {
			
			switch(val) {
			case 1: return SIGN_IN;
			case 2: return CREATE_NEW_ACCOUNT;
			case 3: return VIEW_LIST_OF_MEMBERS;
			case 4: return QUIT;
			default: return null;
			}
		}
		
		public static void printEnumsNValues() {
			
			System.out.println();
			for(UserOptions options : UserOptions.values())
				System.out.print("(" + options.value + ")" + options + " ");
		}
	}
	
	public enum UserOptions2 {
		SEARCH(1), VIEW_ALL_MEMBERS(2), VIEW_CONTACTS(3), ADD_CONTACT(4), REMOVE_CONTACT(5), QUIT(6);
		
		private int value;
		private UserOptions2(int value) {
			this.value = value;
		}
		
	    public static UserOptions2 getEnumByVal(int val) {
			
			switch(val) {
			case 1: return SEARCH;
			case 2: return VIEW_ALL_MEMBERS;
			case 3: return VIEW_CONTACTS;
			case 4: return ADD_CONTACT;
			case 5: return REMOVE_CONTACT;
			case 6: return QUIT;
			default: return null;
			}
		}
	    
	    public static void printEnumsNValues() {
			
			System.out.println();
			for(UserOptions2 options : UserOptions2.values())
				System.out.print("(" + options.value + ")" + options + " ");
		}
	}
	
	private static UserID UserOptions1(Scanner scan) {
		
		UserID user = null;
		while(user == null) {
		  UserManager.inputString("", "", UserManager.StringOption.SELECT, true);
		  UserOptions.printEnumsNValues();
		  int input = UserManager.makeSureValInRange(scan, 1, UserOptions.values().length, "User options",
				UserManager.ValType.INTEGER, true, true).intValue();
		  
		  UserOptions selected = UserOptions.getEnumByVal(input);
		  switch(selected) {
		  	case SIGN_IN : user = UserManager.signIn(scan); break;
		  	case CREATE_NEW_ACCOUNT : user = UserManager.createNewAccount(scan); break;
		  	case VIEW_LIST_OF_MEMBERS : UserManager.PrintUserInfoList(); break;
		  	case QUIT: return null;
		  	default: System.out.println("error in UserOptions1 method");
		  }
		}
		return user;
	}
	
	private static void UserOptions2(Scanner scan, UserID user) {
		assert(user != null);
		
		int input = 0;
		while(true) {
			UserOptions2.printEnumsNValues();
			UserManager.inputString("", "", UserManager.StringOption.SELECT, true);
			
		    input = UserManager.makeSureValInRange(scan, 1, UserOptions2.values().length, "User options",
					UserManager.ValType.INTEGER, true, true).intValue();
		    
		    UserOptions2 selected = UserOptions2.getEnumByVal(input);
		    switch(selected) {
		    case SEARCH: UserManager.searchBasedOnCriteria(user, scan); break;
		    case VIEW_ALL_MEMBERS: UserManager.PrintUserInfoList(); break;
		    case VIEW_CONTACTS: user.printContacts(); break;
		    case ADD_CONTACT: UserManager.addContact(user, scan); break;
		    case REMOVE_CONTACT: UserManager.removeContact(user, scan); break;
		    case QUIT: return;
		    }
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserManager login = new UserManager();
		Scanner scan = new Scanner(System.in);
		//UserManager.createNewAccount(scan);
		/*
		Collection<UserID> collection = login.registeredUsers.values();
		Iterator<UserID> it =  collection.iterator();
		while(it.hasNext()) {
		  System.out.println(it.next());
		}
		
		UserID dbaskov = login.registeredUsers.get("DBaskov");
		UserID homer = login.registeredUsers.get("Homer");
		UserID userA = login.registeredUsers.get("adsadsads");
		assert(userA != null);
		//Scanner scan = new Scanner(System.in);
		homer.setUpSearch(scan);
		System.out.println(homer.getUserCriteria());
		
		Search search = new Search(dbaskov);
		Search search1 = new Search(homer);
		LinkedList<UserID> list = search1.findUsersBasedOnSearchCriteria();
		//System.out.println("size of list: " + list.size());
		Iterator<UserID> iter = list.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		*/
		UserID user = UserOptions1(scan);
		if(user != null)
			UserOptions2(scan, user);
		scan.close();
	}

}
