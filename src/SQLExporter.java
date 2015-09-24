import java.sql.*;

public class SQLExporter implements UserID.Exporter {
	
	String[] firstTenColumns;
	int column11;
	int column12;
	double column13;
	
	public void writeToResultSet(ResultSet endOfSet) throws SQLException {
		
		int i = 0;
		while(i < firstTenColumns.length) {
			endOfSet.updateString(i + 1, firstTenColumns[i]);
			++i;
		}
		endOfSet.updateInt("Age", column11);
		endOfSet.updateInt("Weight", column12);
		endOfSet.updateDouble("Height", column13);
	}
	
	public void addGender(String gender) {
		// TODO Auto-generated method stub
		firstTenColumns[UserID.ExportOrder.GENDER.getValue()] = gender;
	}

	public void addSexuality(String sexuality) {
		// TODO Auto-generated method stub
		firstTenColumns[UserID.ExportOrder.SEXUALITY.getValue()] = sexuality;
	}

	@Override
	public void addEthnicity(String ethnicity) {
		// TODO Auto-generated method stub
		firstTenColumns[UserID.ExportOrder.ETHNICITY.getValue()] = ethnicity;
	}

	@Override
	public void addEyeColor(String eyeColor) {
		// TODO Auto-generated method stub
		firstTenColumns[UserID.ExportOrder.EYE_COLOR.getValue()] = eyeColor;
	}

	@Override
	public void addHairColor(String hairColor) {
		// TODO Auto-generated method stub
		firstTenColumns[UserID.ExportOrder.HAIR_COLOR.getValue()] = hairColor;
	}

	@Override
	public void addEducation(String education) {
		// TODO Auto-generated method stub
		firstTenColumns[UserID.ExportOrder.EDUCATION.getValue()] = education;
	}

	@Override
	public void addZodiac(String zodiac) {
		// TODO Auto-generated method stub
		firstTenColumns[UserID.ExportOrder.ZODIAC.getValue()] = zodiac;
	}

	@Override
	public void addAge(int age) {
		// TODO Auto-generated method stub
		column11 = age;
	}

	@Override
	public void addWeight(int weight) {
		// TODO Auto-generated method stub
		column12 = weight;
	}

	@Override
	public void addHeight(double height) {
		// TODO Auto-generated method stub
		column13 = height;
	}

	@Override
	public void addUserID(String userID) {
		// TODO Auto-generated method stub
		firstTenColumns[UserID.ExportOrder.USER_ID.getValue()] = userID;
	}

	@Override
	public void addPassword(String password) {
		// TODO Auto-generated method stub
		firstTenColumns[UserID.ExportOrder.PASSWORD.getValue()] = password;
	}

	@Override
	public void addName(String name) {
		// TODO Auto-generated method stub
		firstTenColumns[UserID.ExportOrder.NAME.getValue()] = name;
	}
}
