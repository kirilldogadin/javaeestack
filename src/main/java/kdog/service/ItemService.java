package kdog.service;

import kdog.dto.item.ItemDtoFull;
import kdog.entity.Item;
import kdog.mapper.ItemMapper;
import kdog.repository.ItemRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ItemService {

	@EJB
	ItemRepository itemRepository;

	@EJB
	ItemMapper itemMapper;

	public List<Item> getAll(){
		return itemRepository.findAll();
	}

	public List<ItemDtoFull> getAllAsDto(){
		return itemMapper.toDtoFullList(getAll());
	}

	public ItemDtoFull createItem(Item item){
		return itemMapper.toDto(itemRepository.save(item));
	}

	public ItemDtoFull createFromDto(ItemDtoFull itemDtoFull){
		return createItem(itemMapper.toEntity(itemDtoFull));
	}

	public Item getItem(Long id){
		return itemRepository.find(id);
	}

	public Item updateItem(Item item){
		return itemRepository.update(item);
	}

	public ItemDtoFull updateDto(ItemDtoFull itemDtoFull){
		return itemMapper.toDto(updateItem(
				itemMapper.toEntity(itemDtoFull)));
	}

	public void deleteById(Long item){
		itemRepository.deleteById(item);
	}

	public ItemDtoFull getById(Long id){
		return itemMapper.toDto(itemRepository.find(id));
	}
}
