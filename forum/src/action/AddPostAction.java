package action;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.*;
import entity.*;

@SuppressWarnings("serial")
public class AddPostAction extends ActionSupport{

	private Post post;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	public String execute() throws UnsupportedEncodingException, SQLException {
		HttpServletRequest request = ServletActionContext.getRequest();

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		ForumDao forumDao = new ForumDao();
		post.setPostTime(new Timestamp(new Date().getTime()));
		post.setUser(user);
		System.out.println("-----------"+post.getTopic());
		forumDao.savePost(post);
		//user.setUserPoints(user.getUserPoints()+3);
		forumDao.updateUser(user.getUserName(), 1);
		return SUCCESS;
	} 
	
}
