package beumbrella.repository;

import beumbrella.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartItem,Long> {
    Iterable<CartItem> findByUserId(Long id);

    @Query(value = "select *\n" +
            "from cart_item join user_table ut on cart_item.user_id = ut.id\n" +
            "where cart_item.user_id = :id ;", nativeQuery = true)
    Iterable<CartItem> findAllCartByUserId(@Param("id") Long id);

    @Query(value = "select * from cart_item join products p on cart_item.product_id = p.id\n" +
            "            where shop_id = :id and status = 2 or status = 1 or status = 3 group by bill_id", nativeQuery = true)
    Iterable<CartItem> findAllCartByProductAndUserId(@Param("id") Long id);

    @Query(value="select * from cart_item join products p on cart_item.product_id = p.id\n" +
            "where bill_id like :billId", nativeQuery = true)
    Iterable<CartItem> findDetailCart(@Param("billId") String billId);

    @Query(value = "select * from cart_item c join products p on c.product_id = p.id\n" +
            "join user_table ut on p.user_id = ut.id\n" +
            "where p.user_id = :shopId and c.user_id = :customerId and c.status = 0 ", nativeQuery = true)
    List<CartItem> findAllCartByShopIdAndCustomerId(@Param("shopId") Long shopId, @Param("customerId") Long customerId);

    @Query(value = "select * from cart_item c where c.user_id = :userId and status = 0", nativeQuery = true)
    Iterable<CartItem> findBillStatusEqualsZero(@Param("userId") Long userId);

    @Query(value = "select * from cart_item c where c.user_id = :userId and status = 1", nativeQuery = true)
    Iterable<CartItem> findBillStatusEqualsOne(@Param("userId") Long userId);

    @Query(value = "select * from cart_item c where c.user_id = :userId and status = 2", nativeQuery = true)
    Iterable<CartItem> findBillStatusEqualsTwo(@Param("userId") Long userId);

    @Query(value = "select * from cart_item c where c.user_id = :userId and status = 3", nativeQuery = true)
    Iterable<CartItem> findBillStatusEqualsThree(@Param("userId") Long userId);

    @Query(value = "select * from cart_item c where c.user_id = :userId group by c.bill_id", nativeQuery = true)
    Iterable<CartItem> findAllCartByCustomerId(@Param("userId") Long userId);
}
