import java.sql.*;

public class DBConnect {
   
	private Connection con;
	private Statement statement;
	private ResultSet result;
	
	public DBConnect() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/People", "root", "");
			statement = con.createStatement();
		}
		catch(Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
	
	public void setResultSet() {
		
		try {
			String querry = "select * from members";
			result = statement.executeQuery(querry);
		}
		catch(Exception ex) {
			
		}
	}
	
	public ResultSet getData() {
		return result;
	}
	
	public void closeConnection() {
		
		try {
			if(!statement.isClosed())
			  statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertRow(String[] strFields, int age, int weight, double height) {
		
		try {
			statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String querry = "select * from members";
			result = statement.executeQuery(querry);
			result.moveToInsertRow();
			
			int cIndex = 1;
			int i = 0;
			while(i < strFields.length) {
				result.updateString(cIndex, strFields[i]);
				cIndex++;
				i++;
			}
			result.updateInt("Age", age);
			result.updateInt("Weight", weight);
			result.updateDouble("Height", height);
			
			result.insertRow();
			result.beforeFirst();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(statement != null) {	try { statement.close(); } catch (SQLException e) {e.printStackTrace();
				}
			}
		}
	}
}
