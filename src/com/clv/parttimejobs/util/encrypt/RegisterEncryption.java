package com.clv.parttimejobs.util.encrypt;

import java.io.PrintStream;
import java.math.BigInteger;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apaches.commons.codec.binary.Base64;

public class RegisterEncryption {
	public String Encrypt_phone(String phone, String password, String sKey)
			throws Exception {
		// 对密码进行MD5加密
		String passwordMD5 = getMD5(password);
		System.out.println(passwordMD5);

		// 加密进行中
		byte[] raw = sKey.getBytes("UTF-8");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encryptedPhone = cipher.doFinal(phone.getBytes("UTF-8"));
		byte[] encryptedPassword = cipher
				.doFinal(passwordMD5.getBytes("UTF-8"));

		// 获得加密后的结果
		String enPhone = new Base64().encodeToString(encryptedPhone);
		String enPassword = new Base64().encodeToString(encryptedPassword);

		return enPhone;
	}

	public String Encrypt_password(String phone, String password, String sKey)
			throws Exception {
		// 对密码进行MD5加密
		String passwordMD5 = getMD5(password);
		System.out.println(passwordMD5);

		// 加密进行中
		byte[] raw = sKey.getBytes("UTF-8");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encryptedPhone = cipher.doFinal(phone.getBytes("UTF-8"));
		byte[] encryptedPassword = cipher
				.doFinal(passwordMD5.getBytes("UTF-8"));

		// 获得加密后的结果
		String enPhone = new Base64().encodeToString(encryptedPhone);
		String enPassword = new Base64().encodeToString(encryptedPassword);

		return enPassword;
	}

	public String getMD5(String str) {
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			System.out.println("MD5加密出错");
		}
		return null;
	}
}
