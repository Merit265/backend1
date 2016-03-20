package com.services;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;

import com.models.DBConnection;
import com.models.UserModel;

@Path("/")
public class Services {

	/*
	 * @GET
	 * 
	 * @Path("/signup")
	 * 
	 * @Produces(MediaType.TEXT_HTML) public Response signUp(){ return
	 * Response.ok(new Viewable("/Signup.jsp")).build(); }
	 */

	@POST
	@Path("/signup")
	@Produces(MediaType.TEXT_PLAIN)
	public String signUp(@FormParam("name") String name,
			@FormParam("email") String email, @FormParam("pass") String pass) {
		UserModel user = UserModel.addNewUser(name, email, pass);
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("email") String email,
			@FormParam("pass") String pass) {
		UserModel user = UserModel.login(email, pass);
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}
	
	@POST
	@Path("/Follow_user")
	@Produces(MediaType.TEXT_PLAIN)
	public String Follow_user(@FormParam("following_email") String following_email,
			@FormParam("follower_email") String follower_email) {
		
		boolean user = UserModel.follow_user(follower_email, following_email);
		System.out.print("sddddddddd");
		JSONObject json = new JSONObject();
		json.put("status", user);
	
		return json.toJSONString();
	}
	@POST
	@Path("/Unfollow_user")
	@Produces(MediaType.TEXT_PLAIN)
	public String unfollow_user(@FormParam("email") String email) {
		boolean user = UserModel.unflollow_user( email);
		JSONObject json = new JSONObject();
		json.put("status", user);
		return json.toJSONString();
	}
	
	@POST
	@Path("/Get_user_last_position")
	@Produces(MediaType.TEXT_PLAIN)
	public String Get_user_last_position(@FormParam("email") String email)
	 {
		UserModel user = UserModel.Get_user_last_position(email);
		JSONObject json = new JSONObject();
		//json.put("id", user.getId());
		json.put("name", user.getName());
		//json.put("email", user.getEmail());
		//json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}
	
	
	@POST
	@Path("/updatePosition")
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePosition(@FormParam("id") String id,
			@FormParam("lat") String lat, @FormParam("long") String lon) {
		Boolean status = UserModel.updateUserPosition(Integer.parseInt(id), Double.parseDouble(lat), Double.parseDouble(lon));
		JSONObject json = new JSONObject();
		json.put("status", status ? 1 : 0);
		return json.toJSONString();
	}
	@POST
	@Path("/get_following_list")
	@Produces(MediaType.TEXT_PLAIN)
	public String get_following_list(@FormParam("email") String email) {
		System.out.println("ttttttttttttttt");
		ArrayList<String> user = UserModel.Get_following( email);
		System.out.println(user);
	
		JSONObject json = new JSONObject();
		for (int i=0;i<user.size();i++)
		json.put("email"+i, user.get(i));
		return json.toJSONString();
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		return "Hello after editing";
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}
}
