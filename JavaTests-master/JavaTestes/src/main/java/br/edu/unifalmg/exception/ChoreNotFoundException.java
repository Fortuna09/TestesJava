package br.edu.unifalmg.exception;

public class ChoreNotFoundException extends RuntimeException{
    public ChoreNotFoundException(String message){
        super (message);
    }
}
