import java.sql.*;import java.util.*;
class RegistrationDao {
 private Connection connect()throws SQLException{return DriverManager.getConnection(System.getenv().getOrDefault("DB_URL","jdbc:mysql://localhost:3306/campus_events"),System.getenv("DB_USER"),System.getenv("DB_PASSWORD"));}
 void insert(String id,String name,String event)throws SQLException{try(Connection c=connect();PreparedStatement p=c.prepareStatement("INSERT INTO registrations(student_id,student_name,event_name) VALUES(?,?,?)")){p.setString(1,id);p.setString(2,name);p.setString(3,event);p.executeUpdate();}}
 List<Object[]> page(String event,int limit,int offset)throws SQLException{var rows=new ArrayList<Object[]>();String sql="SELECT id,student_id,student_name,event_name,registered_at FROM registrations"+(event==null?"":" WHERE event_name=?")+" ORDER BY id LIMIT ? OFFSET ?";try(Connection c=connect();PreparedStatement p=c.prepareStatement(sql)){int i=1;if(event!=null)p.setString(i++,event);p.setInt(i++,limit);p.setInt(i,offset);try(ResultSet r=p.executeQuery()){while(r.next())rows.add(new Object[]{r.getLong(1),r.getString(2),r.getString(3),r.getString(4),r.getTimestamp(5)});}}return rows;}
}
