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
		userMapper.registrera(user);
		
		List<User> users = userMapper.hämtaAllaPersoner();
		for (User listUser : users){
			System.out.println(listUser);
		}
		
	}

}
