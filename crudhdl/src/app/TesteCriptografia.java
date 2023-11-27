package app;

import java.util.List;

import dao.ExameHDLDAO;
import dao.SenhaDAO;
import dao.UsuarioDAO;
import modelo.ExameHDL;
import modelo.Senha;
import modelo.Usuario;
import util.DesEncrypter;

public class TesteCriptografia {
    public static void main(String[] args) {
		try {			
			int id = 1;
			String senhaUsuario = "93845dhjff12";
			
			UsuarioDAO daoUsuario = new UsuarioDAO();
			Usuario usuario = daoUsuario.getusuariobyId(id);
			System.out.println("Hash da senha do Usuario (banco de dados): " + usuario.senha());
			
			ExameHDLDAO daoExame = new ExameHDLDAO();
			SenhaDAO daoSenha = new SenhaDAO();			
			
			Senha senha = daoSenha.getSenhabyId(id);
			System.out.println("Chave criptografada da tabela senha (banco de dados): " + senha.chaveSecreta());
			String senhaCriptografia = DesEncrypter.decriptografa(
					senha.chaveSecreta(), 
					DesEncrypter.criarChaveSecreta(senhaUsuario));
			System.out.println("Chave descriptografada: " + senhaCriptografia);			
			
			List<ExameHDL> exames = daoExame.getLista();
			
			for (ExameHDL exame : exames) {
				System.out.println("-----------------------------------------------------------");
				System.out.println("Resultado do exame de HDL do paciente: " + exame.paciente() + " solicitado "
						+ "pelo médico: " + exame.medico());
				System.out.println("Resultado do exame criptografado (banco de dados): " + exame.valorHDL());
				String nomeDoExame = DesEncrypter.decriptografa(
						exame.valorHDL(), 
						//substituir parâmetro senhaCriptografia se quiser testar
						//se dados sigilosos serão mostrados com chave secreta incorreta
						DesEncrypter.criarChaveSecreta(senhaCriptografia));
				System.out.println("Resultado do exame descriptografado: " + nomeDoExame);
			}
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

    }
}

