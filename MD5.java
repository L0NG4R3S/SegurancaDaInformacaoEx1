import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {	
		String usuarioDigitado = "";
		String senhaDigitada = "";
		String usuarioAutenticacao = "";
		String senhaAutenticacao = "";
		
		Scanner lerUsuario = new Scanner(System.in);
		  
			
			System.out.printf("\n###### CADASTRE-SE ######\n");
			
			System.out.printf("\n\nInforme seu usuario (4 caracteres): \n");
			usuarioDigitado = lerUsuario.nextLine();
			
			if(usuarioDigitado.length() != 4) {
				do {
					System.out.printf("\nO usuario deve conter 4 caracteres, tente novamente: \n");
					usuarioDigitado = lerUsuario.nextLine();
				}while(usuarioDigitado.length() != 4);
			}
				  
			System.out.printf("Informe sua senha (4 caracteres): \n");
			senhaDigitada = lerUsuario.nextLine();
			
			if(senhaDigitada.length() != 4) {
				do {
					System.out.printf("\nA senha deve conter 4 caracteres, tente novamente: \n");
					senhaDigitada = lerUsuario.nextLine();
				}while(senhaDigitada.length() != 4);
			}
			
			System.out.printf("\n###### EFETUE LOGIN ######\n");
			
			System.out.printf("\n\nInforme seu usuario: \n");
			usuarioAutenticacao = lerUsuario.nextLine();
					  
			System.out.printf("Informe sua senha: \n");
			senhaAutenticacao = lerUsuario.nextLine();
			
			lerUsuario.close();
			
			MessageDigest m = null;
			try {
				m = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
		    m.update(senhaDigitada.getBytes(),0,senhaDigitada.length());
		    String hash = new BigInteger(1,m.digest()).toString(16);
		    
		    m.update(senhaAutenticacao.getBytes(),0,senhaAutenticacao.length());
		    String hash2 = new BigInteger(1,m.digest()).toString(16);
		    
		    System.out.printf("MD5: %s\n", hash);
		    System.out.printf("MD5 2: %s", hash2);
			
			FileWriter arq = new FileWriter("/Users/procob/Downloads/Criptografia/autenticacao.txt");
			PrintWriter gravarArq = new PrintWriter(arq);
			
			gravarArq.printf("%s\n", usuarioDigitado);
			gravarArq.printf("%s", hash);
			
			arq.close();
			
			if(usuarioDigitado.equals(usuarioAutenticacao) && hash.equals(hash2)) {
				System.out.print("\n\nCREDENCIAIS CORRETAS");
			}else {
				System.out.print("\n\nCREDENCIAIS INCORRETAS");
			}
		   
	}
}