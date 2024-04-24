package assignment;

import io.restassured.RestAssured;
import pojos.JsonPlaceHolderPojo;
import pojos.pet;

public class hw12 {
    //Write an automation test that will create,
    // read, update, delete a 'pet' using the "https://petstore.swagger.io/" document
//(All actions must be done on same pet (Use Pojo)


    public void ass12(){
        RestAssured.baseURI = "https://petstore.swagger.io";
        //Set the expected data --> With Pojo Class
        pet expectedData = new pet(1,"TestPet",available);
        System.out.println("expectedData = " + expectedData);


    }


}
