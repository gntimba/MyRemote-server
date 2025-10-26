package com.crfstech.MyRemote.Exception;

public class UserErrors extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserErrors() {
        super();
    }

    public UserErrors(String message) {
        super(message);
    }

}
