package com.jadehelena.banking.service

import com.jadehelena.banking.model.Account
import com.jadehelena.banking.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException
import java.security.SecureRandom

@Service
class AccountService {

    @Autowired
    AccountRepository accountRepository

    Account findById(long id) {
        accountRepository.findById(id).orElse(null)
    }

    Account findByIdOrError(long id) {
        accountRepository.findById(id).orElseThrow({
            new EntityNotFoundException()
        })
    }

    def save(Account account){
        validatesIfHolderHasAccount(account)

        account.setNumber(generateAccountNumber())
        account.setAgency(101)
        account.setBalance(BigDecimal.ZERO)
        accountRepository.save(account)
    }

    def updateBalance(BigDecimal new_balance, long id) {
        Account persistedAccount = findByIdOrError(id)

        persistedAccount.setBalance(persistedAccount.balance + new_balance)

        accountRepository.save(persistedAccount)
    }

    def deleteById(long id) {
        Account account = findByIdOrError(id)
        accountRepository.delete(account)
        account
    }

    private int generateAccountNumber() {
        try {
            SecureRandom secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG", "SUN")
            int randomNumber = secureRandomGenerator.nextInt(9999999 - 00000001) + 00000001

            return randomNumber
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    private def validatesIfHolderHasAccount(account){
        def persistedAccount = accountRepository.findByHolderId(account?.holder.getId())
        if(persistedAccount){
            throw new RuntimeException("Holder already has an account")
        }
    }

}