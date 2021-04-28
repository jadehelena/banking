package com.jadehelena.banking.exception

class PersonHasActiveAccountException extends Exception{

    String getMessage(){
        return "This person has an active account"
    }
}
