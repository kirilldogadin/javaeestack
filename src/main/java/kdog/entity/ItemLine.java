package kdog.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ItemLine extends BaseEntity {

	@ManyToOne
	private Order order;

	@OneToOne
	@JoinColumn(nullable = false)
	private Item item;
	private Integer count;
	private BigDecimal actualPrice;
	private BigDecimal cost;

	public BigDecimal getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(BigDecimal actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@PreUpdate
	public void preUpdateThis(){
		setActualPrice(item.getPrice());
		setCost(getActualPrice().multiply(BigDecimal.valueOf(count)));
	}

	@PrePersist
	public void prePersistThis(){
		setActualPrice(item.getPrice());
		preUpdateThis();
	}
}
