package com.company;

import java.time.LocalDate;

public class WildAnimalFactorySingleton
{
    private static WildAnimalFactorySingleton obj = new WildAnimalFactorySingleton();

    private WildAnimalFactorySingleton()
    {
    }

    public static WildAnimalFactorySingleton getWildAnimalFactorySingleton()
    {
        return obj;
    }

    public static ScandinavianWildAnimal newMaleBear(LocalDate arrivalDate, String name, LocalDate dateOfBirth, String address)
    {
        return new MaleIndividual("Bjørn","Ursus arctos", "Ursidae", arrivalDate, name, dateOfBirth, true, address);
    }

    public static ScandinavianWildAnimal newFemaleWolf(LocalDate arrivalDate, String name, LocalDate dateOfBirth, String address, int noLitters)
    {
        return new FemaleIndividual("Ulv", "Canis lupus", "Canidae", arrivalDate, name, dateOfBirth, true,  address, noLitters);
    }

    public static ScandinavianWildAnimal newMaleWolf(LocalDate arrivalDate, String name, LocalDate dateOfBirth, String address)
    {
        return new MaleIndividual("Ulv", "Canis lupus", "Canidae", arrivalDate, name, dateOfBirth, true, address);
    }
}
