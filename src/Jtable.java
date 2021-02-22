
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class Jtable extends JFrame {
    public Jtable() {
        super();
        try {
	   Connection conn = connexion.getConnexion();
           Statement stmNbEtu = (Statement) conn.createStatement();
           ResultSet resNbEtu = stmNbEtu.executeQuery("SELECT count(idEtu) FROM etudiant");
           int nbEtu=0;
           while (resNbEtu.next()){
               nbEtu = resNbEtu.getInt("count(idEtu)");
           }
           
           Statement stmInfo = (Statement) conn.createStatement();
           String reqInfo = "SELECT * FROM Etudiant";
	   ResultSet resInfo = stmInfo.executeQuery(reqInfo);
           
           String entetes[] = {"Nom", "Prénom", "dateNaiss","lieuNaiss", "sexe", "nationalite", "rue", "cp", "ville", "telephone", "mail", "niveau", "Filière", "Bac"};
           String donnees[][] = new String[nbEtu][14];
           
           int i=0;
	   while (resInfo.next()) {
                donnees[i][0]= resInfo.getString("nom");
                donnees[i][1]= resInfo.getString("prenom");
                donnees[i][2]= resInfo.getString("dateNaiss");;
                donnees[i][3]= resInfo.getString("lieuNaiss");
                donnees[i][4]= resInfo.getString("sexe");
                donnees[i][5]= resInfo.getString("nationalite");
                donnees[i][6]= resInfo.getString("rue");
                donnees[i][7]= resInfo.getString("cp");
                donnees[i][8]= resInfo.getString("ville");
                donnees[i][9]= resInfo.getString("telephone");
                donnees[i][10]= resInfo.getString("mail");
                donnees[i][11]= resInfo.getString("niveau");
                
                Statement stmfiliere = (Statement) conn.createStatement();
                ResultSet resfiliere = stmfiliere.executeQuery("SELECT * FROM filiere WHERE idFil="+resInfo.getString("idFil"));
                while (resfiliere.next()){
                    donnees[i][12 ]= resfiliere.getString("nom");
                }
                
                Statement stmbac = (Statement) conn.createStatement();
                ResultSet resbac = stmbac.executeQuery("SELECT * FROM Bac WHERE idBac="+resInfo.getString("idBac"));
                while (resbac.next()){
                    donnees[i][13]= resbac.getString("libelle");
                }
            }
           
           DefaultTableModel model = new DefaultTableModel(donnees, entetes);
           JTable table = new JTable(model);
           table.setShowGrid(true);
           table.setShowVerticalLines(true);
           TableColumn col = null;
            for (int j = 0; j < 13; j++) {
                col = table.getColumnModel().getColumn(j);
                col.setPreferredWidth(150);
            }
           JScrollPane pane = new JScrollPane(table);
           JFrame f = new JFrame("Information des étudiants");
           JPanel panel = new JPanel();
           panel.setLayout(new GridLayout(1,1));
           panel.add(pane);
           f.add(panel);
           f.setSize(1200, 250);
           f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           f.setVisible(true);
           f.setLocationRelativeTo(null);
	}
	catch (SQLException e) {
            System.out.println("Erreur lors du chargement "+e.getMessage()) ;
	}
    }
}
