import java.util.*;

public interface IOInterface {

	Hashtable<String, UserID> getAllUsers();
	void addNewUser(UserID user);
}
