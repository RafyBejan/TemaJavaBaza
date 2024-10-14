package emanuel.oop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {

	public static void main(String[] args) throws SQLException{
		   Properties connectionProps= new Properties();
		   connectionProps.put("user", "root");
		   connectionProps.put("password", "");
		   
		   Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tema1", connectionProps);
		   
		   //insert masina
		   String sqlInsertMasina="Insert into masina (marca,model,pret) Values(?,?,?)";
           PreparedStatement psInsertMasina=conn.prepareStatement(sqlInsertMasina);
           psInsertMasina.setString(1, "BMW");
           psInsertMasina.setString(2, "X5");
           psInsertMasina.setDouble(3, 50000);
           psInsertMasina.executeUpdate();
           System.out.println("Insert masina done");
           
           //Read masina
           Statement stmtMasina=conn.createStatement();
           ResultSet rsMasina=stmtMasina.executeQuery("Select * from masina");
           while(rsMasina.next()) {
        	   int id=rsMasina.getInt("id");
        	   String marca=rsMasina.getString("marca");
        	   String model=rsMasina.getString("model");
        	   double pret=rsMasina.getDouble("pret");
        	   System.out.println(id+"|"+marca+"|"+model+"|"+pret);
           }
           //Update masina
           String updateMasina="Update masina set pret=? where model=?";
           PreparedStatement psUpdateMasina=conn.prepareStatement(updateMasina);
           psUpdateMasina.setDouble(1, 60000);
           psUpdateMasina.setString(2, "X5");
           int rowsUpdatedMasina=psUpdateMasina.executeUpdate();
           System.out.println("Update masina done");
           // Delete masina
           
           String deleteMasina="Delete from masina where model=?";
           PreparedStatement psDeleteMasina=conn.prepareStatement(deleteMasina);
           psDeleteMasina.setString(1, "X5");
           int rowsDeletedMasina=psDeleteMasina.executeUpdate();
           System.out.println("Delete masina done");
           
        // Insert (Create) - carte
           String sqlInsertCarte = "INSERT INTO carte (titlu, autor, categorie) VALUES (?, ?, ?)";
           PreparedStatement psInsertCarte = conn.prepareStatement(sqlInsertCarte);
           psInsertCarte.setString(1, "Micul Prinț");
           psInsertCarte.setString(2, "Antoine de Saint-Exupéry");
           psInsertCarte.setString(3, "Fictiune");
           psInsertCarte.executeUpdate();
           System.out.println("Carte inserata!");

           // Read (Citire) - carte
           Statement stmtCarte = conn.createStatement();
           ResultSet rsCarte = stmtCarte.executeQuery("SELECT * FROM carte");
           while (rsCarte.next()) {
               int id = rsCarte.getInt("id");
               String titlu = rsCarte.getString("titlu");
               String autor = rsCarte.getString("autor");
               String categorie = rsCarte.getString("categorie");
               System.out.println(id + " | " + titlu + " | " + autor + " | " + categorie);
           }

           // Update (Actualizare) - carte
           String updateCarte = "UPDATE carte SET categorie = ? WHERE titlu = ?";
           PreparedStatement psUpdateCarte = conn.prepareStatement(updateCarte);
           psUpdateCarte.setString(1, "Clasic");
           psUpdateCarte.setString(2, "Micul Prinț");
           int rowsUpdatedCarte = psUpdateCarte.executeUpdate();
           System.out.println("Rânduri actualizate carte: " + rowsUpdatedCarte);

           // Delete (Ștergere) - carte
           String deleteCarte = "DELETE FROM carte WHERE titlu = ?";
           PreparedStatement psDeleteCarte = conn.prepareStatement(deleteCarte);
           psDeleteCarte.setString(1, "Micul Prinț");
           int rowsDeletedCarte = psDeleteCarte.executeUpdate();
           System.out.println("Rânduri șterse carte: " + rowsDeletedCarte);

           // Închidem conexiunea
           conn.close();
       
	}
           
           
	

}
