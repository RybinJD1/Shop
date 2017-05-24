package com.dao.exceptions;

public class DaoException extends Exception {

    private Exception e;
    private String key;

    public DaoException(String key, Exception e) {
        this.key = key;
        this.e = e;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
