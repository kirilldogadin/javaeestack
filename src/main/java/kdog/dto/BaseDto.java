package kdog.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * Базовая DTO. Содержит некоторые общие поля и
 * интерфейсы для валидации
 */
public abstract class BaseDto {

	public interface Existing {
	}

	public interface New {
	}

	@NotNull(groups = Existing.class)
	@Null(groups = New.class)
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
