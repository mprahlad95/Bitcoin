
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;


public class VerifySignature {
	private List<byte[]> list;

	@SuppressWarnings("unchecked")
	// The constructor of VerifyMessage class retrieves the byte arrays from the
	// File
	// and prints the message only if the signature is verified.
	public VerifySignature(String signature, String keyFile, Transaction t) throws Exception {
		try {
			signature = new String(Base64.getDecoder().decode(signature));
			
			String[] parts = signature.split("\\*");
			byte[] decodedSignature = Base64.getDecoder().decode(parts[1]);
			
			if(verifySignature(parts[0].getBytes(), decodedSignature, keyFile)) {
				System.out.println("OK\n");
				t.isVerified = 1;
			}
			else 
				System.out.println("Bad\n");
			
			
		} catch (Exception e ) {
			System.err.println("Error in verifying signature");
		}
	}

	// Method for signature verification that initializes with the Public Key,
	// updates the data to be verified and then verifies them using the signature
	private boolean verifySignature(byte[] data, byte[] signature, String keyFile) throws Exception {
		Signature sign = Signature.getInstance("SHA256WithRSA");
		sign.initVerify(getPublic(keyFile));
		sign.update(data);
		return sign.verify(signature);
	}

	// Method to retrieve the Public Key from a file
	public PublicKey getPublic(String filename) throws Exception {
		byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
		byte[] decodedBytes = Base64.getDecoder().decode(keyBytes);

		X509EncodedKeySpec spec = new X509EncodedKeySpec(decodedBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA"); 
		return kf.generatePublic(spec);
	}
}
