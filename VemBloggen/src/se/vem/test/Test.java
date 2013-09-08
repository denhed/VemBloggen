package se.vem.test;

import java.util.List;

import se.vem.data.User;
import se.vem.databas.UserMapper;

public class Test {

	public static void main(String[] args) {
		UserMapper userMapper = UserMapper.getInstance();
		User user = new User();
		user.setUsername("Robin");
		user.setPassword("hemligt");
		userMapper.register(user);
		
		List<User> users = userMapper.getAllUsers();
		int i = 1;
		for (User listUser : users){
			System.out.print(i++ + " ");
			System.out.println(listUser);
		}
		
	}

}
