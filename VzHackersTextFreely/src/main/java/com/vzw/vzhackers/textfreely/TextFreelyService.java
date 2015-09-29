package com.vzw.vzhackers.textfreely;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vzw.vzhackers.textfreely.dto.ProcessTextRequest;
import com.vzw.vzhackers.textfreely.dto.ProcessTextResponse;

@Path("TextFreely")
public class TextFreelyService {

	@POST
	@Path("processText")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public ProcessTextResponse processTest(ProcessTextRequest r) {

		ProcessTextResponse resp = new ProcessTextResponse();
		resp.setOperation("BAL");
		resp.setResponseMessage("balance response");

		return resp;

	}

	@GET
	@Path("/InchToFeet/{i}")

	@Produces(MediaType.TEXT_XML)
	public String convertInchToFeet(@PathParam("i") int i) {

		int inch = i;
		double feet = 0;
		feet = (double) inch / 12;

		return "<InchToFeetService>" + "<Inch>" + inch + "</Inch>" + "<Feet>" + feet + "</Feet>"
				+ "</InchToFeetService>";
	}

}
