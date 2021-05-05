package Clase12;

import org.testng.annotations.Test;

public class grupos {

    @Test (priority = 3, groups = "successTests")
    public void primerTest(){
        System.out.println("primer test exitoso");
    }

    @Test (priority = 2, groups = "failed tests")
    public void segundoTest(){
        System.out.println("primer test fallido");
    }

    @Test (priority = 1, groups = "failed tests")
    public void tercerTest(){
        System.out.println("segundo test fallido");
    }
}
