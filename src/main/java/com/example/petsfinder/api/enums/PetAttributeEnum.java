package com.example.petsfinder.api.enums;

public enum PetAttributeEnum{
    PET_TYPE,
    COLOR,
    YEARS,
    NONE;

    public static PetAttributeEnum valueOfString(String s){
        if (s.equals("")){
            return NONE;
        } else {
            try {
                return valueOf(s);
            } catch (Exception e){
                return NONE;
            }
        }
    }
}
