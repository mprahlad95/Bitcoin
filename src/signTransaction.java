import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

import java.util.Base64;


public class signTransaction {
	private List<byte[]> list;
	public String signedData;
	
	// The constructor of Message class builds the list that will be written to the
	// file.
	// The list consists of the message and the signature.
	public signTransaction(String data, String keyFile) throws InvalidKeyException, Exception {
		list = new ArrayList<byte[]>();
		list.add(data.getBytes());
		list.add(sign(data, keyFile));
		
		StringBuilder signedData = new StringBuilder();
		signedData.append(new String(list.get(0)));
		signedData.append("*");
		signedData.append(new String(Base64.getEncoder().encode(list.get(1))));
		
		this.signedData = new String(Base64.getEncoder().encode(signedData.toString().getBytes()));
	}
	

	// The method that signs the data using the private key that is stored in
	// keyFile path
	public byte[] sign(String data, String keyFile) throws InvalidKeyException, Exception {
		Signature rsa = Signature.getInstance("SHA256withRSA");
		rsa.initSign(getPrivate(keyFile));
		rsa.update(data.getBytes());
		return rsa.sign();
		
	}

	// Method to retrieve the Private Key from a file
	public PrivateKey getPrivate(String filename) throws Exception {
		System.out.println(filename);
		byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
		byte[] decodedBytes = Base64.getDecoder().decode(new String(keyBytes));
				
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decodedBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}
}
