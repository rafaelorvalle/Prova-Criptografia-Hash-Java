package util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DesEncrypter {
	public static String criptografa(String data, SecretKey chave) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, chave);

		byte[] dadosCriptografados = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

		return Base64.getEncoder().encodeToString(dadosCriptografados);
	}

	public static String decriptografa(String dataCriptografada, SecretKey chave) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, chave);
		byte[] decodedData = Base64.getDecoder().decode(dataCriptografada);

		byte[] decryptedBytes = cipher.doFinal(decodedData);
		return new String(decryptedBytes, StandardCharsets.UTF_8);
	}

	public static SecretKey criarChaveSecreta(String chave) {
		byte[] chaveBytes = chave.getBytes(StandardCharsets.UTF_8);
		byte[] chaveBytes8 = new byte[8];
		System.arraycopy(chaveBytes, 0, chaveBytes8, 0, Math.min(chaveBytes.length, 8));

		return new SecretKeySpec(chaveBytes8, "DES");
	}
}
