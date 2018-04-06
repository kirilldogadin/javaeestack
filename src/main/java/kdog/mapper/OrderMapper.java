package kdog.mapper;

import kdog.dto.order.OrderDto;
import kdog.dto.order.OrderDtoFull;
import kdog.entity.Order;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class OrderMapper implements Mapper<OrderDto, Order> {

	@EJB
	ItemLineMapper itemLineMapper;

	@Override
	public OrderDto toDto(Order entity) {
		throw new RuntimeException(
				"Don't use this inheritance mock method in OrderMapper.toDto()" );
	}

	@Override
	public Order toEntity(OrderDto dto) {
		Order order = new Order();
		order.setEmail(dto.getEmail());
		order.setId(dto.getId());
		order.setItemLineList(
				itemLineMapper.toEntityList(dto.getItemLineDtoList())
		);
		order.preUpdateThis();
		return order;
	}

	public Order toEntity(OrderDtoFull dto) {
		Order order = new Order();
		order.setEmail(dto.getEmail());
		order.setItemLineList(
				itemLineMapper.toEntityListFromDtoFull(dto.getItemLineDtoList())
		);
		order.preUpdateThis();
		return order;
	}

	public OrderDtoFull toDtoFull(Order entity) {
		OrderDtoFull orderDtoFull = new OrderDtoFull();
		orderDtoFull.setId(entity.getId());
		orderDtoFull.setItemLineDtoList(
				itemLineMapper.toFullDtoList(
						entity.getItemLineList())
		);
		orderDtoFull.setCreatedAt(entity.getCreatedAt());
		orderDtoFull.setEmail(entity.getEmail());
		orderDtoFull.setOrderCost(entity.getOrderCost());
		return orderDtoFull;
	}


}
