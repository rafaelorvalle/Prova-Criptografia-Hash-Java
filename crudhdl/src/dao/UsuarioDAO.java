package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javaDB.FabricaDeConexoes;
import modelo.Usuario;
import util.Hash;

public class UsuarioDAO {
	
	private Connection con;
	
	public UsuarioDAO() throws SQLException{
		this.con =  FabricaDeConexoes.getConnection();
	}
	
	public void adiciona(String login, String senha) throws SQLException {
		
		String sql = "INSERT INTO usuario (login, senha) VALUES (?, ?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, login);
		stmt.setString(2, Hash.gerarHash(senha));
		stmt.execute();
		stmt.close();
		con.close();
	}
	
	public Usuario getusuariobyId(int id) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rset = stmt.executeQuery();
		
		if (rset.next()) {
			String login = rset.getString("login");
			String senha = rset.getString("senha");
			return new Usuario(id,login,senha);
		}
		return null;
	}
	
	

}
