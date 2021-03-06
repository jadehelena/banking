package com.jadehelena.banking.service

import com.jadehelena.banking.exception.PersonHasActiveAccountException
import com.jadehelena.banking.model.Account
import com.jadehelena.banking.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
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
            new IllegalArgumentException("Account #${id} does not exists")
        })
    }

    def save(Account account){
        validatesIfHolderDoesntHaveAccount(account?.holder.getId())

        account.setNumber(generateAccountNumber())
        account.setAgency(101)
        account.setBalance(BigDecimal.ZERO)
        accountRepository.save(account)
    }

    def updateBalance(BigDecimal newBalance, long id) {
        Account persistedAccount = findById(id)

        persistedAccount.setBalance(persistedAccount.balance + newBalance)

        accountRepository.save(persistedAccount)
    }

    def deleteById(long id) {
        Account account = findById(id)
        accountRepository.delete(account)
        account
    }

    int generateAccountNumber() {
        SecureRandom secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG", "SUN")
        int randomNumber = secureRandomGenerator.nextInt(9999999 - 00000001) + 00000001

        return randomNumber
    }

    private def validatesIfHolderDoesntHaveAccount(Long id){
        def persistedAccount = accountRepository.findByHolderId(id)
        if(persistedAccount){
            throw new PersonHasActiveAccountException()
        }
        true
    }

}
