package kdog.service;

import kdog.dto.order.OrderDto;
import kdog.dto.order.OrderDtoFull;
import kdog.entity.Order;
import kdog.mapper.OrderMapper;
import kdog.repository.OrderRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class OrderService {

	@EJB
	OrderRepository orderRepository;

	@EJB
	OrderMapper orderMapper;

	public OrderDtoFull createOrder(Order order){
		return orderMapper.toDtoFull(orderRepository.save(order));
	}

	public OrderDtoFull createFromDto(OrderDto orderDto){
		return createOrder(
				orderMapper.toEntity(orderDto));

	}

	public Order getOrderById(Long id){
		return orderRepository.find(id);
	}

	public List<Order> getAllAsEntity(){
		return orderRepository.findAll();
	}

	public List<OrderDtoFull> getAllAsDtoFull(){
		return getAllAsEntity().stream()
				.map(orderMapper::toDtoFull)
				.collect(Collectors.toList());
	}

	public Order update(Order order){
		return orderRepository.update(order);
	}

	public Order updateAndSetActive(Order order, boolean active){
		order.setActive(active);
		return orderRepository.update(order);
	}

	public Order updateAndSetActiveTrue(Order order){
		return updateAndSetActive(order,true);
	}

	public OrderDtoFull updateDto(OrderDtoFull orderDtoFull){
		return  orderMapper.toDtoFull(update(
				orderMapper.toEntity(orderDtoFull)));
	}
	public OrderDtoFull updateDto(OrderDto orderDto){
		return  orderMapper.toDtoFull(updateAndSetActiveTrue(
				orderMapper.toEntity(orderDto)));
	}

	public void deleteById(Long item){
		orderRepository.deleteById(item);
	}

	public OrderDtoFull getById(Long id){
		return orderMapper.toDtoFull(orderRepository.find(id));
	}

}
