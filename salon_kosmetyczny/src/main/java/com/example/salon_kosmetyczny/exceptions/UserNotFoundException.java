package com.example.salon_kosmetyczny.exceptions;

public class UserNotFoundException extends GeneralNotFoundException {
    public UserNotFoundException(){
        super("Nie znaleziono dentysty o takich parametrach");
    }
}
