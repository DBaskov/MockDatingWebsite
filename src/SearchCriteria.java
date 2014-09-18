import java.util.*;


public class SearchCriteria {

	//Note that EnumSet in not synchronized
	private EnumSet<Attributes.EyeColor> eyeColorCriteria;
	private EnumSet<Attributes.HairColor> hairColorCriteria;
	private EnumSet<Attributes.Ethnicity> ethnicityCriteria;
	private EnumSet<Attributes.Education> educationCriteria;
	private EnumSet<Attributes.Zodiac> zodiacCriteria;
	
	private Range age;
	private Range height;
	private Range weight;
	
	public enum RangeType {
		AGE(0, 200), WEIGHT(0, 2000), HEIGHT (0, 15);
		
		private int min;
		private int max;
		
		private RangeType(int min, int max) {
			this.min = min;
			this.max = max;
		}
		
		public int getLowerBound() {
			return min;
		}
		
		public int getUpperBound() {
			return max;
		}
	}
	
	public enum CriteriaAttribute {
		ETHNICITY(1), EYE_COLOR(2), HAIR_COLOR(3), EDUCATION(4), ZODIAC(5);
		
		private int value;
		public static final int lowerBound = 1;
		public static final int upperBound = 5;
		
		private CriteriaAttribute(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
		
		public static CriteriaAttribute getEnum(int val) {
			
			switch(val) {
			case 1: return ETHNICITY;
			case 2: return EYE_COLOR;
			case 3: return HAIR_COLOR;
			case 4: return EDUCATION;
			case 5: return ZODIAC;
			default: return null;
			}
		}
		
		public static void printEnumsNValues() {
			System.out.println();
			for(CriteriaAttribute attributes : CriteriaAttribute.values())
				System.out.print("(" + attributes.value + ")" + attributes + " ");
		}
	}
	
	public class Range {
		
		private Number lowerbound;
		private Number upperbound;
		private RangeType rangeType;
		
		Range(Number lower, Number upper, RangeType rangeType) {
			
			this.lowerbound = lower;
			this.upperbound = upper;
			this.rangeType = rangeType;
		}
		
		public Number getLowerBound() {
			return lowerbound;
		}
		
		public Number getUpperBound() {
			return upperbound;
		}
		
		public boolean withinRange(Number value) {
			
			return value.intValue() >= lowerbound.intValue() && value.intValue() <= upperbound.intValue();
		}
		
		public String toString() {
			
			return "Range Type: " + rangeType + "    lowerbound : " + lowerbound + "   upperbound: "
					+ upperbound;
		}
	}
	
	
	public SearchCriteria(Scanner scan) {
		setUpSearch(scan);
	}
	
    public void setUpSearch(Scanner scan) {
		
		System.out.println("Entering the search.");
		int upperBound;
		int lowerBound;
		
		Number[] ageNumbers = UserManager.makeSureValInRangeRange(scan, RangeType.AGE.getLowerBound(), 
				RangeType.AGE.getUpperBound(), RangeType.AGE.toString(), UserManager.ValType.INTEGER);
		//Setting up Range class of Age criteria, if ageNumbers are null then we create the object
		//with default values, other-wise with custom values
		Range ageRange = ageNumbers == null ? new Range(RangeType.AGE.getLowerBound(), 
			    RangeType.AGE.getUpperBound(), RangeType.AGE) : 
			    	new Range(ageNumbers[0], ageNumbers[1], RangeType.AGE);
		System.out.println("The age range is : " + ageRange);
		
		RangeType hRange = RangeType.HEIGHT;
		lowerBound = hRange.getLowerBound();
		upperBound = hRange.getUpperBound();
		Number[] heightNumbers = UserManager.makeSureValInRangeRange(scan, lowerBound, 
				upperBound, hRange.toString(), UserManager.ValType.DOUBLE);
		Range heightRange = heightNumbers == null ? new Range(lowerBound, upperBound, hRange)
		                             : new Range(heightNumbers[0], heightNumbers[1], hRange);
		System.out.println("The height range is : " + heightRange);
		
		RangeType wRange = RangeType.WEIGHT;
		lowerBound = wRange.getLowerBound();
		upperBound = wRange.getUpperBound();
		Number[] weightNumbers = UserManager.makeSureValInRangeRange(scan, lowerBound, 
				upperBound, wRange.toString(), UserManager.ValType.INTEGER);
		Range weightRange = weightNumbers == null ? new Range(lowerBound, upperBound, wRange)
		                              : new Range(weightNumbers[0], weightNumbers[1], wRange);
		System.out.println("The weight range is " + weightRange);
		
		eyeColorCriteria = setUpEyeColorCriteria(scan);
		hairColorCriteria = setUpHairColorCriteria(scan);
		ethnicityCriteria = setUpEthnicityCriteria(scan);
		educationCriteria = setUpEducationCriteria(scan);
		zodiacCriteria = setUpZodiacCriteria(scan);
		
		age = ageRange;
		height = heightRange;
		weight = weightRange;
	}
    
    
    public static EnumSet<Attributes.EyeColor> setUpEyeColorCriteria(Scanner scan) {
    	
    	Attributes.EyeColor.printEnumsNValues();
    	LinkedList<Integer> list = UserManager.makeSureValInRange2(scan, 1, Attributes.EyeColor.values().length, 
    			Attributes.EyeColor.class.getSimpleName() + " criteria");
    	EnumSet<Attributes.EyeColor> eSet = EnumSet.noneOf(Attributes.EyeColor.class);
    	
    	if(list == null) {
    		eSet = EnumSet.allOf(Attributes.EyeColor.class);
    	}
    	else {
    		Iterator<Integer> it = list.iterator();
    		while(it.hasNext()) {
    			eSet.add(Attributes.EyeColor.getEnum(it.next()));
    		}
    	}
    	
    	return eSet;
    }
    
   public static EnumSet<Attributes.HairColor> setUpHairColorCriteria(Scanner scan) {
    	
	    Attributes.HairColor.printEnumsNValues();
    	LinkedList<Integer> list = UserManager.makeSureValInRange2(scan, 1, Attributes.HairColor.values().length, 
    			Attributes.HairColor.class.getSimpleName() + " criteria");
    	EnumSet<Attributes.HairColor> eSet = EnumSet.noneOf(Attributes.HairColor.class);
    	
    	if(list == null) {
    		eSet = EnumSet.allOf(Attributes.HairColor.class);
    	}
    	else {
    		Iterator<Integer> it = list.iterator();
    		while(it.hasNext()) {
    			eSet.add(Attributes.HairColor.getEnum(it.next()));
    		}
    	}
    	
    	return eSet;
    }
   
   public static EnumSet<Attributes.Ethnicity> setUpEthnicityCriteria(Scanner scan) {
   	
	Attributes.Ethnicity.printEnumsNValues();
   	LinkedList<Integer> list = UserManager.makeSureValInRange2(scan, 1, Attributes.Ethnicity.values().length, 
   			Attributes.Ethnicity.class.getSimpleName() + " criteria");
   	EnumSet<Attributes.Ethnicity> eSet = EnumSet.noneOf((Attributes.Ethnicity.class));
   	
   	if(list == null) {
   		eSet = EnumSet.allOf(Attributes.Ethnicity.class);
   	}
   	else {
   		Iterator<Integer> it = list.iterator();
   		while(it.hasNext()) {
   			eSet.add(Attributes.Ethnicity.getEnum(it.next()));
   		}
   	}
   	
   	return eSet;
   }
   
   public static EnumSet<Attributes.Education> setUpEducationCriteria(Scanner scan) {
	   	
	   Attributes.Education.printEnumsNValues();
	   	LinkedList<Integer> list = UserManager.makeSureValInRange2(scan, 1, Attributes.Education.values().length, 
	   			Attributes.Education.class.getSimpleName() + " criteria");
	   	EnumSet<Attributes.Education> eSet = EnumSet.noneOf(Attributes.Education.class);
	   	
	   	if(list == null) {
	   		eSet = EnumSet.allOf(Attributes.Education.class);
	   	}
	   	else {
	   		Iterator<Integer> it = list.iterator();
	   		while(it.hasNext()) {
	   			eSet.add(Attributes.Education.getEnum(it.next()));
	   		}
	   	}
	   	
	   	return eSet;
	  }
   
   public static EnumSet<Attributes.Zodiac> setUpZodiacCriteria(Scanner scan) {
	   	
	   Attributes.Zodiac.printEnumsNValues();
	   	LinkedList<Integer> list = UserManager.makeSureValInRange2(scan, 1, Attributes.Zodiac.values().length, 
	   			Attributes.Zodiac.class.getSimpleName() + " criteria");
	   	EnumSet<Attributes.Zodiac> eSet = EnumSet.noneOf(Attributes.Zodiac.class);
	   	
	   	if(list == null) {
	   		eSet = EnumSet.allOf(Attributes.Zodiac.class);
	   	}
	   	else {
	   		Iterator<Integer> it = list.iterator();
	   		while(it.hasNext()) {
	   			eSet.add(Attributes.Zodiac.getEnum(it.next()));
	   		}
	   	}
	   	
	   	return eSet;
    }
   
    protected EnumSet<Attributes.EyeColor> getEyeColorCriteria() {
    	return eyeColorCriteria;
    }
    
    protected EnumSet<Attributes.HairColor> getHairColorCriteria() {
    	return hairColorCriteria;
    }
    
    protected EnumSet<Attributes.Ethnicity> getEthnicityCriteria() {
    	return ethnicityCriteria;
    }
    
    protected EnumSet<Attributes.Education> getEducationCriteria() {
    	return educationCriteria;
    }
    
    protected EnumSet<Attributes.Zodiac> getZodiacCriteria() {
    	return zodiacCriteria;
    }
    
    protected Range getAgeRange() {
    	return age;
    }
    
    protected Range getHeightRange() {
    	return height;
    }
    
    protected Range getWeightRange() {
    	return weight;
    }
    
    public enum StringEnum {
    	ALREADY_SELECTED, SELECT_CRITERIA, SELECTED_FIRST, DEFAULT;
    }
    
    public static String prioritySetUpString(StringEnum type, String attribute) {
    	
    	switch(type) {
    	case ALREADY_SELECTED: return "You already selected this value for " + attribute
    			+ " attribute. Try again";
    	case SELECT_CRITERIA: return "Select attribute values that correspond with the keyboard "
    			+ "for the " + attribute + "  search criteria or press anything else to leave it as default";
    	case SELECTED_FIRST: return "Select next values for the " + attribute + " criteria or press anything"
    			+ "else to finish selecting for this type of criteria.";
    	case DEFAULT: return "You had selected to leave this criteria in default condition";
    	default: return "error";
    	}
    }
    
    public String toString() {
       
       String str = "Age Range: " + age.toString();
       str += "\nHeight Range: " + height.toString();
       str += "\nWeight Range: " + weight.toString();
       
       str += "\n\nEye Color Criteria: " + eyeColorCriteria.toString();
       str += "\nHair Color Criteria: " + hairColorCriteria.toString();
       str += "\nEthnicity Criteria: " + ethnicityCriteria.toString();
       str += "\nEducation Criteria: " + educationCriteria.toString();
       str += "\nZodiac Criteria: " + zodiacCriteria.toString();
       return str;
    }
}
