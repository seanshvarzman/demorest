package com.telusko.demorest;

import java.sql.SQLException;
import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("aliens")
public class AlienResource {

	AlienRepository repo=new AlienRepository();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Alien> getAliens() throws SQLException {
		
		return repo.getAliens();
	}
	
	
	@GET
	@Path("alien/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien getAlien(@PathParam("id") int id) throws SQLException {
		
		return repo.getAlien(id);
	}
	
	
	@POST
	@Path("alien")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Alien createAlien(Alien a1) throws SQLException{
		repo.create(a1);
		
		return a1;
	}
	
	@PUT
	@Path("alien")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Alien updateAlien(Alien a1) throws SQLException{
		if(repo.getAlien(a1.getId()).getId()==0) repo.create(a1);
		repo.update(a1);
		
		return a1;
	}
	
	@DELETE
	@Path("alien/{id}")
	public Alien deleteAlien(@PathParam("id") int id) throws SQLException {
		Alien a=repo.getAlien(id);
		if(a.getId()!=0) repo.delete(id);
		return a;
	}
	
}
