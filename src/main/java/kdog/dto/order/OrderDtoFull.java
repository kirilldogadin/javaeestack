package kdog.dto.order;

import kdog.dto.BaseDto;
import kdog.dto.itemLine.ItemLineDtoFull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * отдает с Id item
 */
public class OrderDtoFull extends BaseDto {

	@Null(groups = {Existing.class, New.class})
	private Date createdAt;

	@NotNull(groups = Existing.class)
	private List<ItemLineDtoFull> itemLineDtoList;

	private String email;

	private BigDecimal orderCost;

	public BigDecimal getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(BigDecimal orderCost) {
		this.orderCost = orderCost;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ItemLineDtoFull> getItemLineDtoList() {
		return itemLineDtoList;
	}

	public void setItemLineDtoList(List<ItemLineDtoFull> itemLineDtoList) {
		this.itemLineDtoList = itemLineDtoList;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
