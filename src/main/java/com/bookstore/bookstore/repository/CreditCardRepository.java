package com.bookstore.bookstore.repository;

import com.bookstore.bookstore.CreditCard;
import com.bookstore.bookstore.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    List<CreditCard> findByUser(User user);
}
