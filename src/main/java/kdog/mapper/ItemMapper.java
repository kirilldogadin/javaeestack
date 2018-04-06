package kdog.mapper;

import kdog.dto.item.ItemDtoFull;
import kdog.entity.Item;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class ItemMapper implements Mapper<ItemDtoFull, Item> {

	@Override
	public ItemDtoFull toDto(Item entity) {
		ItemDtoFull itemDtoFull = new ItemDtoFull();
		itemDtoFull.setPrice(entity.getPrice());
		itemDtoFull.setName(entity.getName());
		itemDtoFull.setId(entity.getId());
		return itemDtoFull;
	}

	@Override
	public Item toEntity(ItemDtoFull dto) {
		Item item = new Item();
		item.setId(dto.getId());
		item.setName(dto.getName());
		item.setPrice(dto.getPrice());
		return item;
	}

	public List<ItemDtoFull> toDtoFullList(List<Item> itemList) {
		return itemList.stream()
				.map(item -> {
					ItemDtoFull itemDto = new ItemDtoFull();
					itemDto.setId(item.getId());
					itemDto.setName(item.getName());
					itemDto.setPrice(item.getPrice());
					return itemDto;
				})
				.collect(Collectors.toList());
	}
}
