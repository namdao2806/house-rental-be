package beumbrella.repository;




import beumbrella.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(value = "select * from user_table u where lower(u.name) like (concat('%', lower(:name), '%'));", nativeQuery = true)
    Iterable<User> findByName(@Param("name") String name);

    @Query(value = "select * from user_table ut join user_role ur on ut.id=ur.user_id where ur.role_id=1 ));", nativeQuery = true)
    Iterable<User> findAllByAdmin();

    @Query(value = "update user_table ut join user_role ub\n" +
            "set ut.status = false, ub.role_id = 3\n" +
            "where id = ?;", nativeQuery = true)
    Optional<User> lockedUser(Long id);

    @Query(value = "select * from user_table ut where ut.id != :id", nativeQuery = true)
    Iterable<User> findAllExcept(@Param("id") Long id);

    @Query(value="select * from user_table ut where ut.phone like concat('%', :phone, '%') and ut.id != :id", nativeQuery = true)
    Iterable<User> findCustomerByPhone(@Param("phone") String phone, @Param("id") Long id);
}