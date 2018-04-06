package kdog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.math.BigDecimal;

@Entity
public class Item extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private BigDecimal price; //подходов к решению проблемы хранения денег несколько, этот Тип лишь один

	@Column(name = "active")
	private Boolean active;

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

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

	@PrePersist
	public void prePersistInit(){
		active=true;
	}
}
