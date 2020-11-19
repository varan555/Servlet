package ru.appline.Logic;

/**
 * Created by JUVA on 18.11.2020.
 */
public class Math {
    private double a;
    private double b;
    private double result;

    public Math(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }



    public void plus () {
        result = a +b;
    }
    public void minus () {
        result = a-b;
    }
    public void multiplication () {
        result = a*b;
    }
    public void delay () {
        result = a/b;
    }
}
