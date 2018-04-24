package com.king.service.impl;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.king.pojo.User;

@Service("passwordHelper")
public class PasswordHelper {

	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	@Value("${password.algorithmName}")
	private String algorithmName = "md5";
	@Value("${password.hashIterations}")
	private int hashIterations = 2;

	public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
		this.randomNumberGenerator = randomNumberGenerator;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	public void encryptPassword(User user) {

		user.setSalt(randomNumberGenerator.nextBytes().toHex());

		String newPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(user
				.getCredentialsSalt()), hashIterations).toHex();

		user.setPassword(newPassword);
	}
	
	public static void main(String[] args) {
		User user = new User();
		user.setUserName("admin");
		user.setPassword("123456");
		user.setLoginName("admin");
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		PasswordHelper p = new PasswordHelper();
		String newPassword = new SimpleHash("md5", user.getPassword(), ByteSource.Util.bytes(user
				.getCredentialsSalt()), 2).toHex();
		user.setPassword(newPassword);
		System.out.println(user);

	}
}
