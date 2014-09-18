import java.util.HashMap;
import java.util.Map;


public class Attributes {
   
	final static int NUM_ENUM_FIELDS = 7;
	final static int NUM_INT_FIELDS = 2;
	final static int NUM_DOUBLE_FIELDS = 1;
	
	public enum Gender {
		FEMALE(1), MALE(2);
		private int value;
		private Gender(int val) {
			this.value = val;
		}
		
		public int getValue() {
			return value;
		}
		
		public static void printEnumsNValues() {
			System.out.println();
			for(Gender gender: Gender.values())
				System.out.print("(" + gender.value + ")" + gender + " ");
		}
		
		public static Gender getEnum(int val) {
			if(val == 1)
				return Gender.FEMALE;
			else if( val == 2)
				return Gender.MALE;
			else
				return null;
		}
		
	}
	
	final static int NUM_ATTRIBUTES = 10;
	private Gender gender;
	private int age;
	private Sexuality sexuality;
	private double height;
	private int weight;
	private Ethnicity ethnicity;
	private EyeColor eye_color;
	private HairColor hair_color;
	private Education education;
	private Zodiac zodiac;
	
	public enum AttributeEnum {
		GENDER, SEXUALITY, ETHNICITY, EYE_COLOR, HAIR_COLOR, EDUCATION, ZODIAC;
	}
	
	public enum Sexuality {
		HETEROSEXUAL(1), HOMOSEXUAL(2), BISEXUAL(3);
		private int value;
		private Sexuality(int val) {
			this.value = val;
		}
		
		public static void printEnumsNValues() {
			
			System.out.println();
			for(Sexuality sexuality : Sexuality.values())
				System.out.print("(" + sexuality.value + ")" + sexuality + " ");
		}
		
		public int getValue() {
			return this.value;
		}
		
		public static Sexuality getEnum(int val) {
			
			switch(val) {
			case 1: return HETEROSEXUAL; 
			case 2: return HOMOSEXUAL; 
			case 3: return BISEXUAL; 
			default: return null;
			}
		}
		
	}
	
	public enum Ethnicity {
		AFRICAN_AMERICAN(1), CAUCASIAN(2), ASIAN(3), HISPANIC(4), NATIVE_AMERICAN(5);
		private int value;
		private Ethnicity(int val) {
			this.value = val;
		}
		
        public static void printEnumsNValues() {
			
			System.out.println();
			for(Ethnicity ethnicity : Ethnicity.values())
				System.out.print("(" + ethnicity.value + ")" + ethnicity + " ");
		}
		
		public int getValue() {
			return this.value;
		}
		
		public static Ethnicity getEnum(int val) {
			switch(val) {
			case 1: return AFRICAN_AMERICAN;
			case 2: return CAUCASIAN;
			case 3: return ASIAN;
			case 4: return HISPANIC;
			case 5: return NATIVE_AMERICAN;
			default: return null;
			}
		}
		
	}
	
	public enum EyeColor {
		GREEN(1), BROWN(2), BLUE(3), HAZEL(4);
		private int value;
		private EyeColor(int val) {
			this.value = val;
		}
		
        public static void printEnumsNValues() {
			
			System.out.println();
			for(EyeColor eyeColor : EyeColor.values())
				System.out.print("(" + eyeColor.value + ")" + eyeColor + " ");
		}
		
		public int getValue() {
			return this.value;
		}
		
	    public static EyeColor getEnum(int val) {
	    	switch(val) {
	    	case 1: return GREEN;
	    	case 2: return BROWN;
	    	case 3: return BLUE;
	    	case 4: return HAZEL;
	        default: return null;
	    	}
	    }
		
	}
	
	public enum HairColor {
		BROWN(1), BLACK(2), BLOND(3), RED(4);
		private int value;
		private HairColor(int val) {
			this.value = val;
		}
		
        public static void printEnumsNValues() {
			
			System.out.println();
			for(HairColor hairColor : HairColor.values())
				System.out.print("(" + hairColor.value + ")" + hairColor + " ");
		}
		
		public int getValue() {
			return this.value;
		}
		
		public static HairColor getEnum(int val) {
			
			switch(val) {
			case 1: return BROWN;
			case 2: return BLACK;
			case 3: return BLOND;
			case 4: return RED;
			default: return null;
			}
		}
		
	}
	
	public enum Education {
		HIGH_SCHOOL(1), SOME_COLLEGE(2), COLLEGE_GRADUATE(3), DOCTOR(4);
		private int value;
		private Education(int val) {
			this.value = val;
		}
		
        public static void printEnumsNValues() {
			
			System.out.println();
			for(Education education : Education.values())
				System.out.print("(" + education.value + ")" + education + " ");
		}
		
		public int getValue() {
			return this.value;
		}
		
		public static Education getEnum(int val) {
			
			switch(val) {
			case 1: return HIGH_SCHOOL;
			case 2: return SOME_COLLEGE;
			case 3: return COLLEGE_GRADUATE;
			case 4: return DOCTOR;
			default: return null;
			}
		}
		
	}
	
	public enum Zodiac {
		ARIES(1), TAURUS(2), GEMINI(3), CANCER(4), LEO(5), VIRGO(6), LIBRA(7), SCORPIO(8), 
		SAGITTARIUS(9), CAPRICORN(10), AQUARIUS(11), PISCES(12);
		private int value;
		private static final Map<Integer, Zodiac> valEnumMap = new HashMap<Integer, Zodiac>();
		static {
			for(Zodiac zodiac: Zodiac.values())
				valEnumMap.put(zodiac.value, zodiac);
		}
		
		private Zodiac(int val) {
			this.value = val;
		}
		
        public static void printEnumsNValues() {
			
			System.out.println();
			for(Zodiac zodiac : Zodiac.values())
				System.out.print("(" + zodiac.value + ")" + zodiac + " ");
		}
		
		public int getValue() {
			return this.value;
		}
		
		public static Zodiac getEnum(int val) {
			return valEnumMap.get(val);
		}
		
	}
	
	protected static Enum<?>[] getEnumAttrValues(AttributeEnum attribute) {
		
		switch(attribute) {
		case GENDER: return Gender.values();
		case SEXUALITY: return Sexuality.values();
		case ETHNICITY: return Ethnicity.values();
		case EYE_COLOR: return EyeColor.values();
		case HAIR_COLOR: return HairColor.values();
		case EDUCATION: return Education.values();
		case ZODIAC: return Zodiac.values();
		default: return null;
		}
	}
	
	protected static Enum<?> getEnumAttrByVal(int val, AttributeEnum attribute) {
		
		switch(attribute) {
		case GENDER: return Gender.getEnum(val);
		case SEXUALITY: return Sexuality.getEnum(val);
		case ETHNICITY: return Ethnicity.getEnum(val);
		case EYE_COLOR: return EyeColor.getEnum(val);
		case HAIR_COLOR: return HairColor.getEnum(val);
		case EDUCATION: return Education.getEnum(val);
		case ZODIAC: return Zodiac.getEnum(val);
		default: return null;
		}
	}
	
	protected static void printEnumNValuesOfEnumAttribute(AttributeEnum attribute) {
		
		switch(attribute) {
		case GENDER: Gender.printEnumsNValues(); break;
		case SEXUALITY: Sexuality.printEnumsNValues(); break;
		case ETHNICITY: Ethnicity.printEnumsNValues(); break;
		case EYE_COLOR: EyeColor.printEnumsNValues(); break;
		case HAIR_COLOR: HairColor.printEnumsNValues(); break;
		case EDUCATION: Education.printEnumsNValues(); break;
		case ZODIAC: Zodiac.printEnumsNValues(); break;
		default: System.out.println("No such attribute"); 
		}
	}
	
	protected static Class<? extends Enum> returnClassOfEnumAttr(AttributeEnum attribute) {
		switch(attribute) {
		case GENDER: return Gender.class;
		case SEXUALITY: return Sexuality.class;
		case ETHNICITY: return Ethnicity.class;
		case EYE_COLOR: return EyeColor.class;
		case HAIR_COLOR: return HairColor.class;
		case EDUCATION: return Education.class;
		case ZODIAC: return Zodiac.class;
		default: return null;
		}
	}
	
	/*
	 * Method compares enumAttrB to EnumAttrA
	 * @return returns -1 if arguments don't have same values or
	 * 1 if they do.
	 */
	public int compareEnumAttr(Enum<?> enumAttrA, Enum<?> enumAttrB) {
		
		assert(enumAttrA.getClass().equals(enumAttrB.getClass())); //arguments must have same class
		return enumAttrA.equals(enumAttrB) ? 1 : -1;
	}
	
	public Attributes(Gender gender, Sexuality sexuality, EyeColor eyeColor, 
			HairColor hairColor, Ethnicity ethnicity, Education education, Zodiac zodiac, int age, int weight, double height) {
		
		this.gender = gender;
		this.sexuality = sexuality;
		this.ethnicity = ethnicity;
		this.eye_color = eyeColor;
		this.hair_color = hairColor;
		this.education = education;
		this.zodiac = zodiac;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}
	
	public Attributes(String[] strFields, int age, int weight, double height) {
		
		assert(strFields.length == NUM_ENUM_FIELDS);
		
		int i = 0;
		gender = Gender.valueOf(strFields[i]);
		sexuality = Sexuality.valueOf(strFields[++i]);
		ethnicity = Ethnicity.valueOf(strFields[++i]);
		eye_color = EyeColor.valueOf(strFields[++i]);
		hair_color = HairColor.valueOf(strFields[++i]);
		education = Education.valueOf(strFields[++i]);
		zodiac = Zodiac.valueOf(strFields[++i]);
		
		this.age = age;
		this.height = height;
		this.weight = weight;
	}
	
	
	
	public Gender getGender() {
		
		return this.gender;
	}
	
	public void setGender(Gender gender) {
		
		this.gender = gender;
	}
	
	public int getAge() {
		
		return this.age;
	}
	
	public void setAge(int age) {
		
		this.age = age;
	}
	
	public Sexuality getSexuality() {
		
		return this.sexuality;
	}
	
	public void setSexuality(Sexuality sexuality) {
		
		this.sexuality = sexuality;
	}
	
	public double getHeight() {
		
		return this.height;
	}
	
	public void setHeight(double height) {
		
		this.height = height;
	}
	
	public int getWeight() {
		
		return this.weight;
	}
	
	public void setWeight(int weight) {
		
		this.weight = weight;
	}
	
	public Ethnicity getEthnicity() {
		
		return this.ethnicity;
	}
	
	public void setEthnicity(Ethnicity ethnicity) {
		
		this.ethnicity = ethnicity;
	}
	
	public EyeColor getEyeColor() {
		
		return this.eye_color;
	}
	
	public void setEyeColor(EyeColor eyeColor) {
		
		this.eye_color = eyeColor;
	}
	
	public HairColor getHairColor() {
		
		return this.hair_color;
	}
	
	public void setHairColor(HairColor hairColor) {
		
		this.hair_color = hairColor;
	}
	
	public Education getEducation() {
		
		return this.education;
	}
	
	public void setEducation(Education education) {
		
		this.education = education;
	}
	
	public Zodiac getZodiac() {
		
		return this.zodiac;
	}
	
	public void setZodiac(Zodiac zodiac) {
		
		this.zodiac = zodiac;
	}
	
	public String toString() {
		
		return "Gender: " + gender + "   Age: " + age + "   Sexuality: " + sexuality + "   Height: "
				+ height + "   Weight: " + weight + "   Ethnicity: " + ethnicity + "   Eye Color: "
				+ eye_color + "  Hair Color:  " + hair_color + "   Education: " + education + "   Zodiac: " + zodiac;
	}
	
}
