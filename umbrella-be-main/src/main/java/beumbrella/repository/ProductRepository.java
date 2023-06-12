package beumbrella.repository;

import beumbrella.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from products p join user_table ut on p.user_id = ut.id\n" +
            "where p.user_id != :id order by p.id desc limit 4", nativeQuery = true)
    Iterable<Product> findNewProduct(@Param("id") Long id);

    @Query(value = "select * from products join category c on products.category_id = c.id where c.id = :id", nativeQuery = true)
    Iterable<Product> findProductByCategory(@Param("id") Long id);
    @Query(value = "select * from products where address like :address", nativeQuery = true)
    Iterable<Product> findProductByAddress(String address);

    Iterable<Product> findAllByNameContaining(String name);

    @Query(value = "select * from products p join user_table ut on p.user_id = ut.id where p.user_id != :id", nativeQuery = true)
    Iterable<Product> findAllByUserIdNot(@Param("id") Long id);

//    @Query(value = "select * from products p  where(:name is null or p.name like :name ) and (:description is null or p.description like :description) and (:from is null or p.price >=:from) and(:to is null or p.price<=:to) and p.user_id != :userId", nativeQuery = true)
//    Iterable<Product> find(@Param("name") String name,
//                                      @Param("description") String description,
//                                      @Param("from") Integer from,
//                                      @Param("to") Integer to,
//                                      @Param("userId") Long userId);
    @Query(value = "select * from products p  where(:name is null or p.name like :name ) and (:address is null or p.address like :address) and (:description is null or p.description like :description) and (:from is null or p.price >=:from) and(:to is null or p.price<=:to)", nativeQuery = true)
    Iterable<Product> find(@Param("name") String name,
                           @Param("address") String address,
                           @Param("description") String description,
                           @Param("from") Integer from,
                           @Param("to") Integer to
                           );
    @Query(value = "select * from products where user_id = :id", nativeQuery = true)
    Iterable<Product> findProductByUserId(@Param("id") Long id);

//    @Query(value = "select * from products p join user_table ut on p.user_id = ut.id\n" +
//            "join fitness_db.category c on p.category_id = c.id\n" +
//            "where c.id = :categoryId and ut.id != :userId", nativeQuery = true)
//    Iterable<Product> findProductByCategoryAndUserIdNot(@Param("categoryId") Long categoryId, @Param("userId") Long userId);
    @Query(value = "select * from products p join user_table ut on p.user_id = ut.id\n" +
            "join category c on p.category_id = c.id\n" +
            "where c.id = :categoryId and ut.id != :userId", nativeQuery = true)
    Iterable<Product> findProductByCategoryAndUserIdNot(@Param("categoryId") Long categoryId, @Param("userId") Long userId);
    @Query(value = "select *\n" +
            "from products p\n" +
            "         join user_table ut on p.user_id = ut.id\n" +
            "where p.user_id = :customerId\n" +
            "  and ut.id != :userId", nativeQuery = true)
    Iterable<Product> findProductsByCustomerId(@Param("customerId") Long customerId, @Param("userId") Long userId);}


