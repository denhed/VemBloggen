package se.vem.test;

import java.util.List;

import se.vem.data.Blogs;
import se.vem.data.User;
import se.vem.databas.BlogsMapper;
import se.vem.databas.UserMapper;

public class Test {

	public static void main(String[] args) {
		addUser();
		createBlog();
		getBlog(5);
		listAllBlogs();

	}



	private static void addUser() {
		UserMapper userMapper = UserMapper.getInstance();
		User user = new User();
		user.setUsername("Robin");
		user.setPassword("hemligt");
		userMapper.register(user);
		
		List<User> users = userMapper.listUsers();
		int i = 1;
		for (User listUser : users){
			System.out.print(i++ + " ");
			System.out.println(listUser);
		}
	}
	// om adduser får id 10 så får bloggen id 11??
	// nästa adduser får id 12 och bloggen 13.
	private static void createBlog(){
		BlogsMapper blogMapper = BlogsMapper.getInstance();
		Blogs blog = new Blogs();
		blog.setOwner_id(2);
		blog.setTitle("Alpine");
		blogMapper.createBlog(blog);
	}
	//hämtar ett blog obj med blog_id samt skriver ut titeln.
	private static void getBlog(int i) {
		BlogsMapper blogMapper = BlogsMapper.getInstance();
		Blogs b = blogMapper.getBlog(i);
		System.out.println(b.getTitle());
		
	}
	
	// lista alla bloggar
	private static void listAllBlogs(){
		BlogsMapper blogMapper = BlogsMapper.getInstance();
			List<Blogs> blogs = blogMapper.listAllBlogs(); //vi får alla obj i db.
			int i = 1;
			for (Blogs listBlogs : blogs){
				System.out.print(i++ + " ");
				System.out.println(listBlogs.getTitle()); //Skriver ut titeln på bloggen.
			}
	}

}