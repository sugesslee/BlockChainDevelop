package com.nwnu.blockchain.p2p.rest;

import javax.annotation.Resource;
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
import java.util.List;

/**
 * rest service
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/09/05     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/9/5 10:16 AM
 * @since 1.0.0
 */
@Resource
@Path("rest")
@Consumes
public interface RestService {
	@POST
	@Path(value = "/hello/{code}/{name}")
	public String add(@PathParam("code") int code,
					  @PathParam("name") String name);

	@GET
	@Path(value = "/hello/{code}")
	public String query(@PathParam("code") int code);

	@GET
	@Path(value = "/hello2/{code}/{name}")
	public String query(@PathParam("code") int code, String name);

	@PUT
	@Path(value = "/hello/{code}/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("code") int code,
						   @PathParam("name") String name);

	@DELETE
	@Path(value = "/hello/{code}")
	public String delete(@PathParam("code") int code);

	@POST
	@Path(value = "/object")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ExampleObj object(ExampleObj code);

	@POST
	@Path(value = "/objects")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<ExampleObj> objects(List<ExampleObj> code);

	@GET
	@Path(value = "/get/{code}")
	@Produces(MediaType.TEXT_PLAIN)
	public String get(@PathParam("code") String code);

	@POST
	@Path(value = "/post/{code}")
	@Produces(MediaType.TEXT_PLAIN)
	public String post(@PathParam("code") String code, String body);
}
