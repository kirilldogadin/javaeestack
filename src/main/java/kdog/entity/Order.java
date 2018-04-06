package kdog.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ee_order")
public class Order extends BaseEntity{

	@OneToMany(cascade = {
			CascadeType.PERSIST,CascadeType.MERGE},
			orphanRemoval = true)
	@JoinColumn
	@NotNull(groups = {Existing.class, New.class})
	List<ItemLine> itemLineList;

	@Column(name = "active")
	@Null(groups = {Existing.class, New.class})
	private Boolean active;

	@Column(name = "email")
	private String email;

	@Column(name = "orderCost")
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

	public Boolean getActive() {
		return active;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<ItemLine> getItemLineList() {
		return itemLineList;
	}

	public void setItemLineList(List<ItemLine> itemLineList) {
		this.itemLineList = itemLineList;
	}

	@PrePersist
	public void prePersistInitThis(){
		active=true;
		preUpdateThis();
	}

	@PreUpdate
	public void preUpdateThis(){
		setOrderCost(BigDecimal.ZERO);
		for (ItemLine itemLine: getItemLineList()){
			if (Objects.nonNull(itemLine.getCost()))
			setOrderCost(orderCost.add(itemLine.getCost()));
		}
	}
}
