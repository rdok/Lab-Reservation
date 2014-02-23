package me.dokollari.college.manager.mvc;

/** @author Rizart Dokollari
 * @since November, 2012
 */

public class ControllerException extends Exception {
    
    public ControllerException() {
        super("General College Application Error...");
        }
    
    public ControllerException(String errorMessage) {
        super (errorMessage);
        }
    
    }
