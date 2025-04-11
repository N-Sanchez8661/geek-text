package com.example.shopping_cart_api.repository; // Corrected package name

import com.example.shopping_cart_api.model.CreditCard; // Corrected import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    List<CreditCard> findByUser_UserId(Long userId);
}
