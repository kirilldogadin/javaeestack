package kdog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kdog.dto.BaseDto;
import kdog.dto.item.ItemDtoFull;
import kdog.service.ItemService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api
@Path("/item")
@Stateless
public class ItemController {

	@EJB
	ItemService itemService;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns subresource something")
	public Response getAll() {
		return Response.ok(itemService.getAllAsDto()).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@Valid @ConvertGroup(from = Default.class,to = BaseDto.New.class) ItemDtoFull itemDtoFull) {//@Valid - не поддерживает валидация групп
		return Response.ok(itemService.createFromDto(itemDtoFull)).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id){
		itemService.deleteById(id);
		return Response.ok().build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns subresource something")
	public Response getById(@PathParam("id") Long id){
		return Response.ok(itemService.getById(id)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@Valid @ConvertGroup(from = Default.class,to = BaseDto.Existing.class)ItemDtoFull itemDtoFull){
		return Response.ok(itemService.updateDto(itemDtoFull)).build();
	}

}
