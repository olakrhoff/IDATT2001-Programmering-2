package com.company;

import java.time.LocalDate;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class WildAnimalFactoryClient
{
    //Looger av Factory designmønster, grunnet factory metoder, se JavaDoc i Logger.
    private static final Logger log = Logger.getLogger(WildAnimalFactoryClient.class.getName());

    public static void main(String[] args)
    {
        //Factory design pattern
        WildAnimalFactory wildAnimalFactory = new WildAnimalFactory();
        ScandinavianWildAnimal ulla = wildAnimalFactory.newFemaleWolf(LocalDate.of(2015,4,29), "Ulla", LocalDate.of(2015,2,26), "Innhegning 2, Skandinaviske rovdyr", 2);
        System.out.println(ulla.printInfo() + "\nAge: " + ulla.getAge());
        log.log(Level.INFO, "kake");

        //Singleton implementation
        ScandinavianWildAnimal per = WildAnimalFactorySingleton.newMaleBear(LocalDate.of(2005,5,5), "Per", LocalDate.of(2000,5,5), "Innhegning 3, Skandinaviske rovdyr");
        System.out.println(per.printInfo() + "\nAge: " + per.getAge());
    }
}
