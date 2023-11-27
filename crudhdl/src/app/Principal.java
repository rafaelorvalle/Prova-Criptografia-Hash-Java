package app;

import dao.ExameHDLDAO;
import dao.SenhaDAO;
import dao.UsuarioDAO;

public class Principal {
	public static void main(String[] args) {
		try {
			//substituir valores de login e senhaUsuario 
			//se quiser mais uma tupla na tabela usuario
			String login = "rafael";
			System.out.print("login: " + login);
			String senhaUsuario = "93845dhjff12";
			System.out.print("\nsenha: " + senhaUsuario);
			UsuarioDAO dao = new UsuarioDAO();
			dao.adiciona(login, senhaUsuario);
			System.out.println("\nGravação do usuario e senha feita no banco de dados!");

			String senhaCriptografia = "kajshdgf";
			System.out.print("Senha para criptografar a tabela Exame: " + senhaCriptografia);
			SenhaDAO daoSenha = new SenhaDAO();
			daoSenha.adiciona(senhaCriptografia, senhaUsuario);
			System.out.println("\nGravação da senha para criptografar feita no banco de dados!");
			
			//susbtituir primeiro, segundo, terceiro e quinto valores
			//se quiser novas tuplas na tabela exame_hdl
			ExameHDLDAO daoExame = new ExameHDLDAO();
			daoExame.adiciona("23 mg/dL", 3, 4, senhaCriptografia, 8);
			System.out.println("\nGravação do exame de HDL feita no banco de dados!");
			daoExame.adiciona("35 mg/dL", 1, 5, senhaCriptografia, 6);
			System.out.println("\nGravação do exame de HDL feita no banco de dados!");
			daoExame.adiciona("62 mg/dL", 2, 3, senhaCriptografia, 12);
			System.out.println("\nGravação do exame de HDL feita no banco de dados!");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
