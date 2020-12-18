package com.example.salon_kosmetyczny.exceptions;

public class PracownikNotFoundException extends GeneralNotFoundException {

    public PracownikNotFoundException() {
        super("Nie znaleziono pacjenta o podanych parametrach");
    }
}
