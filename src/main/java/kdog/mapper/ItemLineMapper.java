package kdog.mapper;

import kdog.dto.itemLine.ItemLineDto;
import kdog.dto.itemLine.ItemLineDtoFull;
import kdog.entity.ItemLine;
import kdog.repository.ItemRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class ItemLineMapper implements Mapper<ItemLineDto, ItemLine> {

	@EJB
	ItemMapper itemMapper;

	/**
	 * @param itemLineDtoList список заказов
	 * @return Map id Item -> count of Item
	 */

	private static Map<Long, ItemLineDto> itemId2ItemLineDto(List<ItemLineDto> itemLineDtoList) {
		Map<Long, ItemLineDto> map = new HashMap<>();
		for (ItemLineDto lineDto : itemLineDtoList) {
			map.put(lineDto.getItemId(), lineDto);
		}
		return map;
	}
	private static Map<Long, Long> itemId2LineIdFromFull(List<ItemLineDtoFull> itemLineDtoList) {
		Map<Long, Long> map = new HashMap<>();
		for (ItemLineDtoFull lineDto : itemLineDtoList) {
			map.put(lineDto.getItemDtoFull().getId(), lineDto.getId());
		}
		return map;
	}
	private static Map<Long, Integer> itemId2CountMapFromFull(List<ItemLineDtoFull> itemLineDtoList) {
		Map<Long, Integer> map = new HashMap<>();
		for (ItemLineDtoFull lineDto : itemLineDtoList) {
			map.put(lineDto.getItemDtoFull().getId(), lineDto.getCount());
		}
		return map;
	}

	@EJB
	ItemRepository itemRepository;

	@Override
	public ItemLineDto toDto(ItemLine entity) {
		return null;
	}

	@Override
	public ItemLine toEntity(ItemLineDto dto) {
		return null;
	}

	public List<ItemLine> toEntityList(List<ItemLineDto> dtoList) {
		return itemRepository.findByIdIn(dtoList
				.stream()
				.map(ItemLineDto::getItemId)
				.collect(Collectors.toList()))
				.stream()
				.map(item -> {
					ItemLine line = new ItemLine();
					line.setItem(item);
					line.setCount(
							itemId2ItemLineDto(dtoList)
									.getOrDefault(item.getId(),null).getCount());
					line.setId(itemId2ItemLineDto(dtoList)
							.getOrDefault(item.getId(),null).getId());
					line.preUpdateThis();
					return line;
				})
				.collect(Collectors.toList());

	}
	public List<ItemLine> toEntityListFromDtoFull(List<ItemLineDtoFull> dtoList) {
		return dtoList
				.stream()
				.map(ItemLineDtoFull::getItemDtoFull)
				.map(itemMapper::toEntity)
				.map(item -> {
					ItemLine line = new ItemLine();
					line.setItem(item);
					line.setCount(
							itemId2CountMapFromFull(dtoList).get(item.getId()));
					line.setId(itemId2LineIdFromFull(dtoList).getOrDefault(item.getId(),null));
					line.preUpdateThis();
					return line;
				})
				.collect(Collectors.toList());

	}

	public List<ItemLineDtoFull> toFullDtoList(List<ItemLine> entityList) {
		return entityList.stream()
				.map(itemLine -> {
					ItemLineDtoFull itemLineDtoFull = new ItemLineDtoFull();
					itemLineDtoFull.setItemDtoFull(
							itemMapper.toDto(itemLine.getItem()));
					itemLineDtoFull.setCount(itemLine.getCount());
					itemLineDtoFull.setId(itemLine.getId());
					itemLineDtoFull.setActualPrice(itemLine.getActualPrice());
					itemLineDtoFull.setCost(itemLine.getCost());
					return itemLineDtoFull;
				})
				.collect(Collectors.toList());
	}
}
