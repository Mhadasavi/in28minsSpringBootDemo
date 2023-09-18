package com.uc.service.jpa;

import com.uc.bean.Item;
import com.uc.bean.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {
}
