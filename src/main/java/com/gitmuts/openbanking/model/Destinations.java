package com.gitmuts.openbanking.model;

import lombok.Data;

@Data
public class Destinations {

    private String ReferenceNumber;
    private String AccountNumber;
    private String BankCode;
    private String BranchCode;
    private String Amount;
    private String TransactionCurrency;
    private String Narration;

}
