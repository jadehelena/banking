package com.jadehelena.banking.controller.exception

class PersonHasActiveAccountException extends Exception{

    String getMessage(){
        return "This person has an active account"
    }
}
