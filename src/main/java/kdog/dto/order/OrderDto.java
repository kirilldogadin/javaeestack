package kdog.dto.order;

import kdog.dto.BaseDto;
import kdog.dto.itemLine.ItemLineDto;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * отдает с Id item
 */
public class OrderDto extends BaseDto {

	private List<ItemLineDto> itemLineDtoList;

	@NotNull(groups = New.class)
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ItemLineDto> getItemLineDtoList() {
		return itemLineDtoList;
	}

	public void setItemLineDtoList(List<ItemLineDto> itemLineDtoList) {
		this.itemLineDtoList = itemLineDtoList;
	}

}
