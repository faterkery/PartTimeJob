package com.clv.parttimejobs.util.encrypt;

import java.math.BigInteger;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apaches.commons.codec.binary.Base64;
/**
 * 客户端生成唯一密匙且具有时效性
 * @author evanglist
 *
 */
public class MarkKey {
	/**
	 * 加密
	 * @param sSrc
	 * 			：加密内容
	 * @param sKey
	 * 			：加密钥匙
	 * @return
	 * @throws Exception
	 */
	 public static String Encrypt(String sSrc, String sKey) throws Exception {
	        if (sKey == null) {
	            System.out.print("Key为空null");
	            return null;
	        }
	        // 判断Key是否为16位
	        if (sKey.length() != 16) {
	            System.out.print("Key长度不是16位");
	            return null;
	        }
	        byte[] raw = sKey.getBytes("utf-8");
	        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

	        return new Base64().encodeToString(encrypted);///此处使用BASE64做转码功能，同时能起到2次加密的作用。
	    }
	/**
	 * 生成身份号
	 * @param phone
	 * @return
	 */
	public String builderId(String id,String security_key){
		long time = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder(security_key);
//		sb.delete(0, 11);
		sb.append(Long.valueOf(time).toString());
		sb.append(id);
		String idr = sb.toString();
		System.out.println(idr.length());
		System.out.println(idr);
		return idr;
	}
	/**
	 * 生成信息钥匙
	 * @param phone
	 * @param security_key
	 * @return
	 */
	public String builderMessageKey(String phone,String security_key){
		
		StringBuilder sb = new StringBuilder(security_key);
		sb.delete(0, 16);
		
		String str = sb.toString();
		char[] ch = str.toCharArray();
		int sum = 0;
		for(char c: ch){
			sum += (int) c;
		}
		sb.append(Integer.valueOf(sum%9).toString());
		
		long Num = Long.parseLong(phone);
		sb.append(Integer.valueOf((int)sum(Num)%7).toString());
		
		int count = 0;
		while(Num>10){
			count += Num%10%2==0?1:0;
			Num /= 10;
		}
		sb.append(Integer.valueOf(count).toString());
		String message =  sb.toString();
		return message;
	}
	/**
	 * 生成时间钥匙
	 * @param timeDelta
	 * 			:时间差
	 * @return
	 */
	public String builderTimeKey(){
		long time = System.currentTimeMillis();
		
		StringBuilder sb = new StringBuilder(getMD5(getMD5(Long.valueOf(time/100000*100000).toString())));
		
		return sb.substring(0, 16);
		
	}
	
	public static long sum(long num){
		return num<10?num:num%10+sum(num/10);
	}
	
	public static String getMD5(String str) {
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











