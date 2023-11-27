package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javaDB.FabricaDeConexoes;
import modelo.ExameHDL;
import util.Hash;

public class ValorPadraoDAO {
	
private Connection con;
	
	public ValorPadraoDAO() throws SQLException{
		this.con =  FabricaDeConexoes.getConnection();
	}
	
	public void adiciona( double limiteInferior, double limiteSuperior, 
			String unidade, String valorReferencia) throws SQLException {
		
		String sql = "INSERT INTO valor_padrao (limite_inferior,limite_superior,"
				+ "unidade, valor_referencia) VALUES (?, ?, ?, ?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setDouble(1,limiteInferior);
		stmt.setDouble(2,limiteSuperior);
		stmt.setString(3, unidade);
		stmt.setString(4, valorReferencia);
		stmt.execute();
		stmt.close();
		con.close();
	}
}
