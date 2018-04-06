package kdog.mapper;

/**
 * Для маппинга между слоями приложениями
 * для более объемной задачи можно добавить ещё DTO для входа и выхода
 * @param <D> DTO
 * @param <E> Entity
 */
public interface Mapper<D,E> {
	D toDto(E entity);
	E toEntity(D dto);
}
