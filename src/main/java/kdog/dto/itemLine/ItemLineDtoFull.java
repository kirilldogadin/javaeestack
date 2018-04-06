package kdog.dto.itemLine;

import kdog.dto.BaseDto;
import kdog.dto.item.ItemDtoFull;

import java.math.BigDecimal;

public class ItemLineDtoFull extends BaseDto {

	private ItemDtoFull itemDtoFull;
	private Integer count;
	private BigDecimal actualPrice;
	private BigDecimal cost;

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(BigDecimal actualPrice) {
		this.actualPrice = actualPrice;
	}

	public ItemDtoFull getItemDtoFull() {
		return itemDtoFull;
	}

	public void setItemDtoFull(ItemDtoFull itemDtoFull) {
		this.itemDtoFull = itemDtoFull;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
