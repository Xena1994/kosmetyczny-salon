package com.example.salon_kosmetyczny.exceptions;

public class TreatmentNotFoundException extends GeneralNotFoundException {
    public TreatmentNotFoundException(){ super("Nie znaleziono zabiegu o podanych parametrach");}
}
