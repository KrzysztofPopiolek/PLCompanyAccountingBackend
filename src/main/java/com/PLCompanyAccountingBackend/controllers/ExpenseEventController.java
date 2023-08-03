//package com.PLCompanyAccountingBackend.controllers;
//
//import com.PLCompanyAccountingBackend.exceptions.ResourceNotFoundException;
//import com.PLCompanyAccountingBackend.models.AnnualSummary;
//import com.PLCompanyAccountingBackend.models.ExpenseEvent;
//import com.PLCompanyAccountingBackend.models.MonthlySummary;
//import com.PLCompanyAccountingBackend.models.Summary;
//import com.PLCompanyAccountingBackend.repository.AnnualSummaryRepository;
//import com.PLCompanyAccountingBackend.repository.BusinessContractorRepository;
//import com.PLCompanyAccountingBackend.repository.ExpenseEventRepository;
//import com.PLCompanyAccountingBackend.repository.MonthlySummaryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//import java.util.List;
//import java.util.logging.Logger;
//
//
//@RestController
//@RequestMapping("/api/v_1/")
//public class ExpenseEventController {
//    @Autowired
//    private ExpenseEventRepository expenseEventRepository;
//
//    @Autowired
//    private AnnualSummaryRepository annualSummaryRepository;
//
//    @Autowired
//    private MonthlySummaryRepository monthlySummaryRepository;
//
//    @Autowired
//    private BusinessContractorRepository businessContractorRepository;
//
//    @GetMapping("/getAllExpense&Event")
//    public List<ExpenseEvent> getAllExpenseEvent() {
//        return expenseEventRepository.findAll();
//    }
//
//    @GetMapping("/getExpense&Event/{id}")
//    public ResponseEntity<ExpenseEvent> getExpenseEventById(@PathVariable Long id) {
//        ExpenseEvent expenseEvent = expenseEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Searched item not found!"));
//        return ResponseEntity.ok(expenseEvent);
//    }
//
//    @PostMapping("/addExpense&Event")
//    public ExpenseEvent addBusinessExpense(@RequestBody ExpenseEvent expenseEvent) {
//        boolean taxYearExists;
//
//        if (!businessContractorRepository.existsById(expenseEvent.getBusinessContractor().getId())) {
//            throw new ResourceNotFoundException("Contractor not found");
//        }
//
//        BigDecimal expenseRemuneration = expenseEvent.getRemuneration() == null ? new BigDecimal(0) : expenseEvent.getRemuneration();
//        BigDecimal expenseOtherExpenses = expenseEvent.getOtherExpenses() == null ? new BigDecimal(0) : expenseEvent.getOtherExpenses();
//
//        expenseEvent.setTotalExpenses(expenseRemuneration.add(expenseOtherExpenses));
//
//        taxYearExists = updateAnnualSummary(expenseEvent);
//
//        if (!taxYearExists) {
//            throw new ResourceNotFoundException("Tax year does not exist");
//        }
//
//        updateMonthlySummary(expenseEvent);
//
//        return expenseEventRepository.save(expenseEvent);
//    }
//
//    @DeleteMapping("/deleteExpense&Event/{id}")
//    public void deleteExpenseEvent(@PathVariable Long id) {
//
//        if (expenseEventRepository.existsById(id)) {
//            expenseEventRepository.deleteById(id);
//        } else {
//            throw new ResourceNotFoundException("Item not found!");
//        }
//    }
//
//    @PutMapping("/editExpense&Event/{id}")
//    ExpenseEvent editExpenseEvent(@RequestBody ExpenseEvent newExpenseEvent, @PathVariable Long id) {
//
//        return expenseEventRepository.findById(id).map(expenseEvent -> {
//            if (!businessContractorRepository.existsById(newExpenseEvent.getId())) {
//                throw new ResourceNotFoundException("Contractor not found");
//            }
//            expenseEvent.setDateEconomicEvent(newExpenseEvent.getDateEconomicEvent());
//            expenseEvent.setAccountingDocumentNumber(newExpenseEvent.getAccountingDocumentNumber());
//            expenseEvent.setDescriptionEconomicEvent(newExpenseEvent.getDescriptionEconomicEvent());
//            expenseEvent.setRemuneration(newExpenseEvent.getRemuneration());
//            expenseEvent.setOtherExpenses(newExpenseEvent.getOtherExpenses());
//            expenseEvent.setFinancialEconomicIssues(newExpenseEvent.getFinancialEconomicIssues());
//            expenseEvent.setTotalExpenses(newExpenseEvent.getTotalExpenses());
//            expenseEvent.setEventNotesComments(newExpenseEvent.getEventNotesComments());
//            expenseEvent.setBusinessContractor((newExpenseEvent.getBusinessContractor()));
//            return expenseEventRepository.save(expenseEvent);
//        }).orElseThrow(() -> new ResourceNotFoundException("Expenses and event not found!"));
//    }
//
//    private void updateMonthlySummary(ExpenseEvent expenseEvent) {
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(expenseEvent.getDateEconomicEvent());
//        int expenseEventYear = calendar.get(Calendar.YEAR);
//        int expenseEventMonth = calendar.get(Calendar.MONTH);
//
//        List<MonthlySummary> monthlySummaries = monthlySummaryRepository.findAll();
//
//        for (int i = 0; i < monthlySummaries.size(); i++) {
//            calendar.setTime(monthlySummaries.get(i).getDate());
//            int monthlySummariesYear = calendar.get(Calendar.YEAR);
//            int monthlySummariesMonth = calendar.get(Calendar.MONTH);
//            if (expenseEventYear == monthlySummariesYear && expenseEventMonth == monthlySummariesMonth) {
//                MonthlySummary newMonthlySummary = updateSummaries(expenseEvent, monthlySummaries.get(i));
//                monthlySummaryRepository.save(newMonthlySummary);
//            }
//        }
//    }
//
//    private boolean updateAnnualSummary(ExpenseEvent expenseEvent) {
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(expenseEvent.getDateEconomicEvent());
//        int expenseEventYear = calendar.get(Calendar.YEAR);
//
//        List<Summary> annualSummaries = annualSummaryRepository.findAll();
//
//        for (int i = 0; i < annualSummaries.size(); i++) {
//            calendar.setTime(annualSummaries.get(i).getDate());
//            int annualSummariesYear = calendar.get(Calendar.YEAR);
//            if (expenseEventYear == annualSummariesYear) {
//                Summary newAnnualSummary = updateSummaries(expenseEvent, annualSummaries.get(i));
//                annualSummaryRepository.save(newAnnualSummary);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private Summary updateSummaries(ExpenseEvent expenseEvent, Summary summary){
//        BigDecimal remuneration = summary.getRemuneration() == null ? new BigDecimal(0) : summary.getRemuneration();
//        BigDecimal otherExpenses = summary.getOtherExpenses() == null ? new BigDecimal(0) : summary.getOtherExpenses();
//        BigDecimal totalExpenses = summary.getTotalExpenses() == null ? new BigDecimal(0) : summary.getTotalExpenses();
//        BigDecimal financialEconomicIssues = summary.getFinancialEconomicIssues() == null ? new BigDecimal(0) : summary.getFinancialEconomicIssues();
//
//        summary.setRemuneration(remuneration.add(expenseEvent.getRemuneration()));
//        summary.setOtherExpenses(otherExpenses.add(expenseEvent.getOtherExpenses()));
//        summary.setTotalExpenses(totalExpenses.add(expenseEvent.getTotalExpenses()));
//        summary.setFinancialEconomicIssues(financialEconomicIssues.add(expenseEvent.getFinancialEconomicIssues()));
//        //monthlySummaryRepository.save(summary);
//        return summary;
//    }
//}
