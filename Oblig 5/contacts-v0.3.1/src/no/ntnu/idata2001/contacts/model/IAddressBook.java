package no.ntnu.idata2001.contacts.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public interface IAddressBook extends Serializable, Iterable<ContactDetails> {
    void addContact(ContactDetails contact);

    void yeetusDeletus(String phoneNumber);

    Collection<ContactDetails> getAllContacts();

    @Override
    Iterator<ContactDetails> iterator();

    void close();
}
