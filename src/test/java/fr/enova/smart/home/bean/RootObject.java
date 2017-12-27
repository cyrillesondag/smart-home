package fr.enova.smart.home.bean;

public class RootObject {

    private String state;

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private int code;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private Result result;

    public Result getResult() {
        return this.result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}

