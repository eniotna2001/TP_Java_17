import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class connexion {
    
     public static Connection getConnexion(){
        try {
		Class.forName("com.mysql.jdbc.Driver") ;
            }
             catch (ClassNotFoundException e){
		System.out.println("Erreur lors du chargement "+e.getMessage()) ;
            }            
        Connection connexion = null;
        try{
            String url = "jdbc:mysql://localhost:3306/bddgraph";
            Properties props = new Properties();
            props.setProperty("user","root");
            props.setProperty("password","");
            connexion = DriverManager.getConnection(url,props) ;
        }
        catch (SQLException e) {
            System.out.println("Erreur lors du chargement "+e.getMessage()) ;
	}
        return connexion;
    }
}