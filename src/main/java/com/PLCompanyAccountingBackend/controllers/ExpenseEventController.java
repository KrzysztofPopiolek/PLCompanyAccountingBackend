package com.PLCompanyAccountingBackend.controllers;

import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
import com.PLCompanyAccountingBackend.models.ExpenseEvent;
import com.PLCompanyAccountingBackend.repository.ExpenseEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v_1/")
public class ExpenseEventController {
    @Autowired
    private ExpenseEventRepository expenseEventRepository;

    @GetMapping("/getAllExpense&Event")
    public List<ExpenseEvent> getAllExpenseEvent() {
        return expenseEventRepository.findAll();
    }

    @GetMapping("/getExpense&Event/{id}")
    public ResponseEntity<ExpenseEvent> getExpenseEventById(@PathVariable Long id) {
        ExpenseEvent expenseEvent = expenseEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
        return ResponseEntity.ok(expenseEvent);
    }

    @PostMapping("/addExpense&Event")
    public ExpenseEvent addBusinessExpense(@RequestBody ExpenseEvent expenseEvent) {
        return expenseEventRepository.save(expenseEvent);
    }

    @DeleteMapping("/deleteExpense&Event/{id}")
    public void deleteExpenseEvent(@PathVariable Long id) {

        if (expenseEventRepository.existsById(id)) {
            expenseEventRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Item not found!");
        }
    }

    @PutMapping("/editExpense&Event/{id}")
    ExpenseEvent editExpenseEvent(@RequestBody ExpenseEvent newExpenseEvent, @PathVariable Long id) {
        return expenseEventRepository.findById(id).map(
                expenseEvent -> {
                    expenseEvent.setDateEconomicEvent(newExpenseEvent.getDateEconomicEvent());
                    expenseEvent.setAccountingDocumentNumber(newExpenseEvent.getAccountingDocumentNumber());
                    expenseEvent.setDescriptionEconomicEvent(newExpenseEvent.getDescriptionEconomicEvent());
                    expenseEvent.setRemuneration(newExpenseEvent.getRemuneration());
                    expenseEvent.setOtherExpenses(newExpenseEvent.getOtherExpenses());
                    expenseEvent.setFinancialEconomicIssues(newExpenseEvent.getFinancialEconomicIssues());
                    expenseEvent.setTotalExpenses(newExpenseEvent.getTotalExpenses());
                    expenseEvent.setEventNotesComments(newExpenseEvent.getEventNotesComments());
                    return expenseEventRepository.save(expenseEvent);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Expenses and event not found!"));
    }
}
