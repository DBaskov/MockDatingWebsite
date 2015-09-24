import java.sql.*;

public class SQLImporter implements UserID.Importer {

	ResultSet resultSet;
	
	SQLImporter(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	@Override
	public String provideGender() {
		// TODO Auto-generated method stub
		try {
			return resultSet.getString(UserID.ExportOrder.GENDER.getValue() + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String provideSexuality() {
		// TODO Auto-generated method stub
		try {
			return resultSet.getString(UserID.ExportOrder.SEXUALITY.getValue() + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String provideEthnicity() {
		// TODO Auto-generated method stub
		try {
			return resultSet.getString(UserID.ExportOrder.ETHNICITY.getValue() + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String provideEyeColor() {
		// TODO Auto-generated method stub
		try {
			return resultSet.getString(UserID.ExportOrder.EYE_COLOR.getValue() + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String provideHairColor() {
		// TODO Auto-generated method stub
		try {
			return resultSet.getString(UserID.ExportOrder.HAIR_COLOR.getValue() + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String provideEducation() {
		// TODO Auto-generated method stub
		try {
			return resultSet.getString(UserID.ExportOrder.EDUCATION.getValue() + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String provideZodiac() {
		// TODO Auto-generated method stub
		try {
			return resultSet.getString(UserID.ExportOrder.ZODIAC.getValue() + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int provideAge() {
		// TODO Auto-generated method stub
		
	    try {
			return resultSet.getInt(UserID.ExportOrder.AGE.getValue() + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public int provideWeight() {
		// TODO Auto-generated method stub
		try {
			return resultSet.getInt(UserID.ExportOrder.WEIGHT.getValue() + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public double provideHeight() {
		// TODO Auto-generated method stub
		try {
			return resultSet.getDouble(UserID.ExportOrder.AGE.getValue() + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public String provideUserName() {
		// TODO Auto-generated method stub
		try {
			return resultSet.getString(UserID.ExportOrder.USER_ID.getValue() + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String providePassword() {
		// TODO Auto-generated method stub
		try {
			return resultSet.getString(UserID.ExportOrder.PASSWORD.getValue() + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String provideName() {
		// TODO Auto-generated method stub
		try {
			return resultSet.getString(UserID.ExportOrder.NAME.getValue() + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
}
