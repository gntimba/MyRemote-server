package com.crfstech.MyRemote.Exception;

public class GroupNotFound extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public GroupNotFound() {
        super();
    }

    public GroupNotFound(String message) {
        super(message);
    }
}

