package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.service;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.x509.X509V3CertificateGenerator;

@SuppressWarnings("deprecation")
public class KeyGeneratorService {
	
	private KeyPair keyPair;
	private X509Certificate certificate;
	
	private static KeyGeneratorService INSTANCE = null;
	
	private KeyGeneratorService() {
		Security.addProvider(new BouncyCastleProvider());
		KeyPairGenerator keyPairGenerator;
		
		try {
			
			keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
			keyPairGenerator.initialize(4096, new SecureRandom());
			keyPair = keyPairGenerator.generateKeyPair();
			try {
				certificate = generateCertificate(keyPair);
			} catch (InvalidKeyException | CertificateException | OperatorCreationException | IllegalStateException
					| SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static KeyGeneratorService getInstance() {
		
		if(INSTANCE==null) {
			INSTANCE = new KeyGeneratorService();
			
		}
		else {}
		return INSTANCE;
	}
	


	public KeyPair getKeyPair() {
		return keyPair;
	}
	
	  public static X509Certificate generateCertificate(KeyPair keyPair) throws CertificateException, OperatorCreationException, NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, SignatureException
	  {
			Calendar fechaCreacion = Calendar.getInstance();
			fechaCreacion.add(Calendar.DAY_OF_MONTH, 6);
			X509V3CertificateGenerator certificate = new X509V3CertificateGenerator();
			certificate.setSerialNumber(new BigInteger(""+Math.abs(SecureRandom.getInstance("SHA1PRNG").nextLong())));
			certificate.setIssuerDN(new X500Principal("CN=Test CA Certificate"));
			certificate.setSubjectDN(new X500Principal("CN=Test CA Certificate"));
			certificate.setPublicKey(keyPair.getPublic());
			certificate.setSignatureAlgorithm("SHA1withRSA");
			certificate.setNotAfter(fechaCreacion.getTime());
			certificate.setNotBefore(Calendar.getInstance().getTime());
			return certificate.generate(keyPair.getPrivate());
			
	  }

	public X509Certificate getCertificate() {
		return certificate;
	}
}