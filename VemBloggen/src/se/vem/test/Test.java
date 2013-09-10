package se.vem.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import se.vem.data.Blogs;
import se.vem.data.Posts;
import se.vem.data.User;
import se.vem.databas.BlogsMapper;
import se.vem.databas.PostsMapper;
import se.vem.databas.UserMapper;

public class Test {

	public static void main(String[] args) {
		getBlog(73);
		addUser();
		createBlog();
		createPost();
		getBlog(5);
		listAllBlogs();
		//updateBlog();
		//removeBlogs();
		addPost("andra posten"," detta var en blog.");

		
	}
	

	
	private static void addPost(String titel, String text){
		PostsMapper postsMapper = PostsMapper.getInstance();
		Posts p = new Posts();
		p.setTitle(titel);
		p.setText(text);
		postsMapper.createPost(p);
	}
	
	private static void removeBlogs(){
		BlogsMapper blogMapper = BlogsMapper.getInstance();
		
		long blog_id =  69; // det objekt vi ska radera.
		int res = blogMapper.removeBlog(blog_id);
		
		if( res == 1) {
			System.out.println("blog raderades");
		}else if (res == 2){
			System.out.println("inget raderades.");
		}else if(res == 3){
			System.out.println("hittade inget objekt att radera.");
		}
	}
	
	private static void updateBlog(){
		BlogsMapper blogMapper = BlogsMapper.getInstance();
		String title = "de twoow";
		long blogId = 71;
		if(blogMapper.editBlog(blogId, title) != null){
			System.out.println("objekt uppdaterat");
		}else{
			System.out.println("hittade inget objekt");
		}
	}

	private static void addUser() {
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

	private static void createBlog(){
		BlogsMapper blogMapper = BlogsMapper.getInstance();
		Blogs blog = new Blogs();
		blog.setOwner_id(2);
		blog.setTitle("Alpine");
		blogMapper.createBlog(blog);
	}
	
	//hämtar ett blog obj med blog_id samt skriver ut titeln.
	private static void getBlog(long i) {
		BlogsMapper blogMapper = BlogsMapper.getInstance();
		Blogs b = blogMapper.getBlog(i);

		if(b != null){
			System.out.println(b.getTitle());
		}else{
			System.out.println("hittade inget blog objekt");
		}
		
	}
	
	// lista alla bloggar
	private static void listAllBlogs(){
		BlogsMapper blogMapper = BlogsMapper.getInstance();
			List<Blogs> blogs = blogMapper.listAllBlogs(); //vi får alla obj i db.
			int i = 1;
			for (Blogs listBlogs : blogs){
				System.out.print(listBlogs.getBlog_id() + " "); //skriver ut blog_id.
				System.out.println(listBlogs.getTitle()); //Skriver ut titeln på bloggen.
			}
	}
	//Test for Posts
	private static void createPost(){
		PostsMapper postsMapper = PostsMapper.getInstance();
		Posts post = new Posts();
		post.setTitle("Zlatan");
		post.setText("Zlatan e bra.");
		Date date = new Date();
		post.setDate(new Timestamp(date.getTime()));
		postsMapper.createPost(post);
	}

}
