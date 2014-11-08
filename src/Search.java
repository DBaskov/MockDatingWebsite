import java.util.*;

public class Search {

	private UserID searchingFor;
	final static int NOT_WITHIN_CRITERIA_VAL = -5;
	
	public Search(UserID searchingFor) {
		this.searchingFor = searchingFor;
		assert(searchingFor.getUserCriteria() != null);
	}
	
	/*
	 * Sort elements based on how closely they match the search criteria and
	 * order them in descending order.
	 */
	public class SortUsers implements Comparator<UserID> {


		public int compare(UserID userA, UserID userB) {
			
			int userACompVal = compareUserAttrToCriteria(userA);
			int userBCompVal = compareUserAttrToCriteria(userB);
			return userACompVal > userBCompVal ? -1 : userACompVal == userBCompVal ? 0 : 1;
		}
		
	}
	
	/*
	 * Compares single attribute criteria to an attribute
	 * @returns 1 if attribute is within criteria, -1 other-wise
	 */
	private int compareToSingleAttrCriteria(EnumSet<?> singleAttrCriteria, Enum<?> userAttr) {
		
		int compare = 0;
		assert(singleAttrCriteria.getClass().getDeclaringClass().equals(userAttr.getClass().getDeclaringClass()));
		
		return singleAttrCriteria.contains(userAttr) ? 1 : -1;
	}
	
	private int compareUserAttrToCriteria(UserID user) {
		
		int compare = 0;
		Attributes userAttr = user.getAttributes();
		SearchCriteria searchCriteria = searchingFor.getUserCriteria();
		
		EnumSet<?> singleCriteria = searchCriteria.getEyeColorCriteria();
		compare += compareToSingleAttrCriteria(singleCriteria, userAttr.getEyeColor());
		
		singleCriteria = searchCriteria.getHairColorCriteria();
		compare += compareToSingleAttrCriteria(singleCriteria, userAttr.getHairColor());
		
		singleCriteria = searchCriteria.getEthnicityCriteria();
		compare += compareToSingleAttrCriteria(singleCriteria, userAttr.getEthnicity());
		
		singleCriteria = searchCriteria.getEducationCriteria();
		compare += compareToSingleAttrCriteria(singleCriteria, userAttr.getEducation());
		
		singleCriteria = searchCriteria.getZodiacCriteria();
		return compare += compareToSingleAttrCriteria(singleCriteria, userAttr.getZodiac());
	}
	
	private Collection<UserID> getListOfUsersMatchCriteria(Collection<UserID> list) {
		
		Iterator<UserID> it = list.iterator();
		while(it.hasNext()) {
			UserID user = it.next();
			if(!(fitsGenderCriteria(user) && fitsRangeCriteria(user)) || 
					(compareUserAttrToCriteria(user) <= NOT_WITHIN_CRITERIA_VAL)) {
				System.out.println("Removing: " + user.getName());
				it.remove();
			}
		}
		return list;
	}
	
	private boolean fitsGenderCriteria(UserID user) {
		
		Attributes.Gender searcherGender = searchingFor.getAttributes().getGender();
		Attributes.Sexuality searcherSexuality = searchingFor.getAttributes().getSexuality();
		Attributes.Gender userGender = user.getAttributes().getGender();
		Attributes.Sexuality userSexuality = user.getAttributes().getSexuality();
		
		if(searcherSexuality.equals(Attributes.Sexuality.HETEROSEXUAL)) {
		   return (!userGender.equals(searcherGender) && 
				   !userSexuality.equals(Attributes.Sexuality.HOMOSEXUAL));
		}
		else if(searcherSexuality.equals(Attributes.Sexuality.HOMOSEXUAL)) {
			return (userGender.equals(searcherGender) && 
					!userSexuality.equals(Attributes.Sexuality.HETEROSEXUAL));
		}
		else {
			if(searcherGender.equals(userGender))
				return !userSexuality.equals(Attributes.Sexuality.HETEROSEXUAL);
			else
				return true;
		}
	}
	
	
	private boolean fitsRangeCriteria(UserID user) {
		
		Attributes attr = user.getAttributes();
		int uAge = attr.getAge();
		double uHeight = attr.getHeight();
		int uWeight = attr.getWeight();
		
		SearchCriteria criteria = searchingFor.getUserCriteria();
		assert(criteria != null);
		return criteria.getAgeRange().withinRange(uAge) && 
				criteria.getHeightRange().withinRange(uHeight)
				&& criteria.getWeightRange().withinRange(uWeight);
	}
	
	private int compareBasedOnGenderSexuality(UserID userA, UserID userB, boolean reverse) {
		
		if(fitsGenderCriteria(userA) && !fitsGenderCriteria(userB))
			return reverse ? -1 : 1;
		else {
			if(fitsGenderCriteria(userB))
				return reverse ? 1 : -1;
			else
				return 0;
		}
	}
	
	protected LinkedList<UserID> findUsersBasedOnSearchCriteria() {
		
		LinkedList<UserID> list = new LinkedList<UserID>(UserManager.getRegistredUsers().values());
		//Collection<UserID> users = UserManager.getRegistredUsers().values();
		assert(!list.isEmpty());
		
		list.remove(searchingFor);
		list = (LinkedList<UserID>)getListOfUsersMatchCriteria(list);
//		Iterator<UserID> it = users.iterator();
//		
//		while(it.hasNext()) {
//			UserID user = it.next();
//			boolean add = list.add(user);
//		}
=======
		LinkedList<UserID> list = new LinkedList<UserID>();
		Collection<UserID> users = UserManager.getRegistredUsers().values();
		assert(!users.isEmpty());
		
		users.remove(searchingFor);
		users = getListOfUsersMatchCriteria(users);
		Iterator<UserID> it = users.iterator();
		
		while(it.hasNext()) {
			UserID user = it.next();
			boolean add = list.add(user);
		}
		list.sort(new SortUsers());
		return list;
	}
}
