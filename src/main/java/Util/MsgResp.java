package Util;

import java.io.Serializable;

public class MsgResp implements Serializable {

    private Status status;
    private double value;

    public MsgResp(Status status, double value) {
        this.status = status;
        this.value = value;
    }

    public MsgResp(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
