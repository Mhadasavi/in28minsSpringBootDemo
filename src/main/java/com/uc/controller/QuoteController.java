package com.uc.controller;

import com.uc.Exception.ItemNotFoundException;
import com.uc.bean.Item;
import com.uc.bean.Quote;
import com.uc.service.ExcelUpdaterService;
import com.uc.service.jpa.QuoteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/Quote")
public class QuoteController {

    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private ExcelUpdaterService excelUpdaterService;

    @PostMapping("/jpa/submitQuote")
    public Quote saveQuote(@Valid @RequestBody Quote quote) {
        if (quote != null) {
//            quote.setId(0);
            quoteRepository.save(quote);
        }
        return quote;
    }

    @GetMapping("/jpa/getQuotes")
    public List<Quote> getAllQuote() {
        List<Quote> quotes = quoteRepository.findAll();
        if (quotes == null || quotes.size() < 1) {
            throw new ItemNotFoundException("No Quote Found");
        }
        return quotes;
    }

    @GetMapping("/getQuote/{id}")
    public Quote getQuote(@PathVariable int id) {
        Quote quote = quoteRepository.findById(id).orElse(null);
        if (quote == null) {
            throw new ItemNotFoundException(String.format("Quote with id : %s not found", id));
        }
        return quote;
    }

    @GetMapping("/jpa/convertToPdf/{id}")
    public void convertToPdf(@PathVariable int id) throws IOException {
        Quote quote = getQuote(id);
        if (quote != null) {
            excelUpdaterService.updateExcelFile(quote);
        }

    }
}
