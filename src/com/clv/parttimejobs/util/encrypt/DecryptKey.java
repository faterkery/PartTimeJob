package com.clv.parttimejobs.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apaches.commons.codec.binary.Base64;


public class DecryptKey {

	private final static String UTF8 ="utf-8";
	/**
	 * AES����
	 * 
	 * @param sSrc
	 * @param sKey
	 * @return
	 * @throws Exception
	 */
	public String decrypt(String sSrc, String sKey) {
		try {
			if (sKey == null) {
				System.out.print("KeyΪ��null");
				return null;
			}
			if (sKey.length() != 16) {
				System.out.print("Key���Ȳ���16λ");
				return null;
			}
			byte[] raw = sKey.getBytes(UTF8);

			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] encrypted1 = new Base64().decode(sSrc);
			byte[] original = cipher.doFinal(encrypted1);
			return new String(original, UTF8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 
	 * @param enMessage
	 * @param phone
	 * @param securityKey
	 * @return
	 */
	public String builderMessage(String phone, String securityKey) {
		StringBuilder sb = new StringBuilder(securityKey);
		sb.delete(0, 16);

		String str = sb.toString();
		char[] ch = str.toCharArray();
		int sum = 0;
		for (char c : ch) {
			sum += (int) c;
		}
		sb.append(Integer.toString(sum % 9));

		long num = Long.parseLong(phone);
		sb.append(Integer.toString((int) sum(num) % 7));

		int count = 0;
		while (num > 10) {
			count += num % 10 % 2 == 0 ? 1 : 0;
			num /= 10;
		}
		sb.append(Integer.toString(count));
		return sb.toString();
	}

	/**
	 * 
	 * 
	 * @param enMessage
	 * @return
	 */
	public String decrypMessage(String message, String phone, String securityKey) {
		System.out.println("message=====" +message);
		System.out.println("phone======" +phone);
		System.out.println("securityKey===" +securityKey);
		String decrypt = decrypt(message, builderMessage(phone.trim(), securityKey.trim()));
		if (decrypt != null) {
			return decrypt;
		}
		return "fail";
	}

	private static long sum(long num){
		return num<10?num:num%10+sum(num/10);
	}
}
