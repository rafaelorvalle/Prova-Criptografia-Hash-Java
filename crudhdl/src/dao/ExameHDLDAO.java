package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

import javaDB.FabricaDeConexoes;
import modelo.ExameHDL;
import modelo.Medico;
import modelo.Paciente;
import util.DesEncrypter;

public class ExameHDLDAO {
	
	private Connection con;
	
	public ExameHDLDAO() throws SQLException{
		this.con =  FabricaDeConexoes.getConnection();
	}
	
	public void adiciona(String resultado, int id_paciente, int id_medico , String chave, int jejum) throws Exception {
		
		SecretKey chave2 = DesEncrypter.criarChaveSecreta(chave);
		
		String sql = "INSERT INTO exame_hdl (resultado, "
				+ "id_paciente, id_medico, jejum) VALUES (?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, DesEncrypter.criptografa(resultado, chave2));
		stmt.setInt(2, id_paciente);
		stmt.setInt(3, id_medico);
		stmt.setInt(4, jejum);
				
		stmt.execute();
		stmt.close();
	}
	
	public List<ExameHDL> getLista() throws SQLException{
		String sql = "SELECT a.id, a.resultado,a.jejum, m.id, m.nome, m.cpf, m.especialidade,"
				+ "p.id, p.nome, p.cpf FROM exame_hdl a "
				+ "JOIN medico m ON a.id_medico = m.id "
				+ "JOIN paciente p ON a.id_paciente = p.id";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rset = stmt.executeQuery();
		
		List<ExameHDL> exames = new ArrayList<ExameHDL>();
		
		while (rset.next()) {
			Medico medico = new Medico(rset.getInt("m.id"),rset.getString("m.nome") ,
					rset.getString("m.cpf"), rset.getString("m.especialidade"));
			Paciente paciente = new Paciente(rset.getInt("p.id"),rset.getString("p.nome") ,
					rset.getString("p.cpf"));
			ExameHDL exame = new ExameHDL(rset.getInt("a.id"), rset.getString("a.resultado"), paciente, medico,rset.getInt("a.jejum"));
			exames.add(exame);
			
		}
		rset.close();
		stmt.close();
		
		return exames;
	}
	
	public ExameHDL getResultadobyId(int id) throws SQLException {
	    String sql = "SELECT * FROM exame_hdl WHERE id = ?";
	    PreparedStatement stmt = con.prepareStatement(sql);
	    stmt.setInt(1, id);
	    ResultSet rset = stmt.executeQuery();

	    if (rset.next()) {
	        int resultadoId = rset.getInt("id");
	        String valorHDL = rset.getString("resultado");
	        int idPaciente = rset.getInt("id_paciente");
	        String nomePaciente = rset.getString("nome_paciente");
	        String cpfPaciente = rset.getString("cpf_paciente");
	        Paciente paciente = new Paciente(idPaciente, nomePaciente, cpfPaciente);
	        int idMedico = rset.getInt("id_medico");
	        String nomeMedico = rset.getString("nome_medico");
	        String cpfMedico = rset.getString("cpf_medico");
	        String especialidadeMedico = rset.getString("especialidade");
	        Medico medico = new Medico(idMedico, nomeMedico, cpfMedico, especialidadeMedico);
	        int jejum = rset.getInt("jejum");

	        ExameHDL exameResult = new ExameHDL(resultadoId, valorHDL, paciente, medico, jejum);
	        return exameResult;
	    }

	    return null;
	}

}
