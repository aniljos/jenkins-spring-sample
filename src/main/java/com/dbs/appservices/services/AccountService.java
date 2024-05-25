package com.dbs.appservices.services;

import com.dbs.appservices.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;


    public void deposit(Long id, double amt) throws Exception {


        try {
            int rowsAffected = repository.deposit(id, amt);
            System.out.println("AccountService.deposit " + id);
        } catch (Exception e) {
            throw new Exception("deposit failed");
        }

    }

    public void withdraw(Long id, double amt) throws Exception {

        try {
            double balance = repository.getBalanceById(id);
            System.out.println("AccountService.withdraw" + balance);
            if(balance > amt) {
                int rowsAffected = repository.withdraw(id, amt);
                System.out.println("AccountService.withdraw " + id);

            }
        } catch (Exception e) {
            throw new Exception("withdraw failed");
        }

    }

    public void transfer(Long fromId, Long toId, double amt) throws Exception {

        try {
            deposit(toId, amt);
            withdraw(fromId, amt);
        } catch (Exception e) {

            e.printStackTrace();;
            throw new Exception("Transfer failed");
        }

    }
}
