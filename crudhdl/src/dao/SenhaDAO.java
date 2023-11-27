package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.SecretKey;

import javaDB.FabricaDeConexoes;
import modelo.Senha;
import util.DesEncrypter;

public class SenhaDAO {

private Connection con;
	
	public SenhaDAO() throws SQLException{
		this.con =  FabricaDeConexoes.getConnection();
	}
	
	public void adiciona(String senha, String chave) throws Exception {
		
		SecretKey chave2 = DesEncrypter.criarChaveSecreta(chave);
		String sql = "INSERT INTO senha (chave_secreta) VALUES (?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, DesEncrypter.criptografa(senha, chave2));
		stmt.execute();
		stmt.close();
		con.close();
	}
	
	public Senha getSenhabyId(int id) throws SQLException {
	    String sql = "SELECT * FROM senha WHERE id = ?";
	    PreparedStatement stmt = con.prepareStatement(sql);
	    stmt.setInt(1, id);
	    ResultSet rset = stmt.executeQuery();

	    if (rset.next()) {
	        String chaveSecreta = rset.getString("chave_secreta");
	        return new Senha(id, chaveSecreta);
	    }

	    return null;
	}
}
