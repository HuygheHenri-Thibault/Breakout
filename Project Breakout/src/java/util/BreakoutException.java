/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Henri
 */
public class BreakoutException extends RuntimeException {
    public BreakoutException() {
    }
    public BreakoutException(Throwable ex) {
        super(ex);
    }
    public BreakoutException(String message, Throwable ex) {
        super(message, ex);
    }
}
