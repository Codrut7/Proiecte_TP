package BusinessLogicLayer;

import ModelLayer.Person;

import java.io.IOException;

public class PersonInteractions {
    PersonRepresentation personRepresentation;

    public PersonInteractions() throws IOException, ClassNotFoundException {
        personRepresentation = new PersonRepresentation();
    }

    public void makeFriend(Person x,Person y) throws IOException {
        personRepresentation.adaugareMuchie(x,y);
    }

}
