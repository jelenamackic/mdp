package net.etfbl.api;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.rpc.ServiceException;

import UserServicesCentralApp.LineService;
import UserServicesCentralApp.LineServiceServiceLocator;
import UserServicesCentralApp.UserService;
import UserServicesCentralApp.UserServiceServiceLocator;
import UserServicesCentralApp.ZSMDPService;
import UserServicesCentralApp.ZSMDPServiceServiceLocator;
import model.Line;
import model.User;

@Path("/lines")
public class APIService {

	LineService lineService;
	UserService userService;
	ZSMDPService zsmdpService;

	public APIService() {
		try {
			LineServiceServiceLocator lineServiceServiceLocator = new LineServiceServiceLocator();
			lineService = lineServiceServiceLocator.getLineService();
			UserServiceServiceLocator userServiceServiceLocator = new UserServiceServiceLocator();
			userService = userServiceServiceLocator.getUserService();
			ZSMDPServiceServiceLocator zsmdpServiceServiceLocator = new ZSMDPServiceServiceLocator();
			zsmdpService = zsmdpServiceServiceLocator.getZSMDPService();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllLines() {
		LineServiceServiceLocator locator = new LineServiceServiceLocator();
		try {
			ArrayList<Line> lines = new ArrayList<Line>();
			for(Line line : lineService.getAllLines()) {
				lines.add(line);
			}
			return Response.status(200).entity(lines).build();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(404).entity(null).build();
	}

	@GET
	@Path("/stations/{stationID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLinesByStationId(@PathParam("stationID") String stationID) {
		try {
			return Response.status(200).entity(lineService.getLinesForOneStation(stationID)).build();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(404).entity(null).build();
		}
	}

	@GET
	@Path("/{lineID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getALine(@PathParam("lineID") String lineID) {
			try {
				String string = lineService.writeLine(lineService.getALine(lineID));
				System.out.println("NA RESTU: " + string);
				System.out.println("ZAstot");
				return Response.status(200).entity(string).build();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(404).entity(null).build();
			}
		
	}

	@PUT
	@Path("/{lineID}/setPassed/{stationID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response edit(@PathParam("stationID") String stationID, @PathParam("lineID") String lineID, String time) {
		try {
			lineService.markTheStationIsPassed(lineID, stationID, time);
			return Response.status(200).entity("Uspjesno setovano vrijeme").build();   
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).entity("Neuspjesno setovano vrijeme").build();
		}
		
	}

	
}
