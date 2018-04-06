package kdog.dto.itemLine;

import kdog.dto.BaseDto;

public class ItemLineDto extends BaseDto {

	private Long itemId;
	private Integer count;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
