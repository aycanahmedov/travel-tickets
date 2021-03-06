package bg.tuvarna.traveltickets.repository.base;

/**
 * This interface provides generified CRUD methods to reduce repetitive code.
 *
 * @param <E>  the entity type.
 * @param <ID> the entity id type.
 */
public interface GenericCrudRepository<E, ID> {

    E findById(ID id);

    E save(E entity);

    void delete(E entity);

    void flush();

}
