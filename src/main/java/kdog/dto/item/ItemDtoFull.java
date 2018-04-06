package kdog.dto.item;

import kdog.dto.BaseDto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * UPdate этого dto подразумевает заполнения всех полей\
 * при использовании Hibernate Юзать @DynamicUpdate
 */
public class ItemDtoFull extends BaseDto {

	@NotNull(groups = {New.class,Existing.class})
	private String name;

	@NotNull(groups = {New.class,Existing.class})
	private BigDecimal price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
