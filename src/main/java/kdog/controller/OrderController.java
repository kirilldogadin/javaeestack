package kdog.controller;

import io.swagger.annotations.Api;
import kdog.dto.order.OrderDto;
import kdog.entity.BaseEntity;
import kdog.service.OrderService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api
@Path("/order")
@Stateless
public class OrderController {

	@EJB
	OrderService orderService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		return Response.ok(orderService.getAllAsDtoFull()).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(OrderDto orderDto) {
		return Response.ok(orderService.createFromDto(orderDto)).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id){
		orderService.deleteById(id);
		return Response.ok().build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") Long id){
		return Response.ok(orderService.getById(id)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@Valid @ConvertGroup(from = Default.class,to = BaseEntity.Existing.class) OrderDto orderDto){
		return Response.ok(orderService.updateDto(orderDto)).build();
	}
}
