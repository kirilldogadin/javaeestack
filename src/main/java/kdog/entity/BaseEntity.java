package kdog.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@DynamicUpdate
public abstract class BaseEntity {

	public interface Existing {
	}

	public interface New {
	}

	@Id
	@GeneratedValue
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	@PrePersist
	public void prePersistInit(){
		setCreatedAt(new Date());
		setUpdateAt(new Date());

	}

	@PreUpdate
	public void preUpdateInit(){
		setUpdateAt(new Date());
	}
}
