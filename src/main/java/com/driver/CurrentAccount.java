package com.driver;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class CurrentAccount extends BankAccount{

    String tradeLicenseId; //consists of Uppercase English characters only
    public static int minBalance = 5000;
    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name,balance,minBalance);
        if(balance<minBalance)
            throw new Exception("Insufficient Balance");
        else {
            this.tradeLicenseId = tradeLicenseId;
        }
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(validate(tradeLicenseId)) return;
        else{
            HashMap<Character,Integer> hm = new HashMap<>();
            int n = tradeLicenseId.length();
            int max = 0;
            for(int i=0;i<n;i++){
                Character c = tradeLicenseId.charAt(i);
                hm.put(c,hm.getOrDefault(c,0)+1);
                if(max<hm.get(c)) max = hm.get(c);
            }
            if(max>(n+1)/2) {
                throw new Exception("Valid License can not be generated");
            }
            else{
                Collections.shuffle(Arrays.asList(tradeLicenseId.toCharArray()));
            }
        }
    }
    public boolean validate(String a){
        int n = a.length();
        for(int i=0;i<n-1;i++) {
            if (a.charAt(i) == a.charAt(i + 1)) return false;
        }
            return true;

    }

}
