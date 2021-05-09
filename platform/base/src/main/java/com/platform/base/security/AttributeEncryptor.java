package com.platform.base.security;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;

import org.springframework.stereotype.Component;

/**
 * @author Advanced Encryption Standard for DB field encryption and decryption
 *
 */
@Component
public class AttributeEncryptor implements AttributeConverter<String, String> {

	private static final String AES = "AES";
	/// move to properties file
	private static final String SECRET = "secret-key-12345";

	private final Key key;
	private final Cipher cipher;

	public AttributeEncryptor() throws Exception {
		key = new SecretKeySpec(SECRET.getBytes(), AES);
		cipher = Cipher.getInstance(AES);
	}

	@Override
	public String convertToDatabaseColumn(String attribute) {
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return Base64.getEncoder().encodeToString(cipher.doFinal(attribute.getBytes()));
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}