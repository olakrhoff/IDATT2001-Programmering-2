package no.ntnu.idata2001.contacts.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.Collection;
import java.util.Iterator;

public class AddressBookDBHandler implements IAddressBook
{

    private EntityManagerFactory _emf;

    public AddressBookDBHandler(EntityManagerFactory newEmf)
    {
        this._emf = newEmf;
    }

    @Override
    public void addContact(ContactDetails contact)
    {
        EntityManager em  = this.getEM();
        try
        {
            em.getTransaction().begin();
            em.persist(contact);
            em.getTransaction().commit(); //store in database
        }
        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
        }
        finally
        {
            this.closeEM(em);
        }
    }

    @Override
    public void yeetusDeletus(String phoneNumber)
    {
        EntityManager em = this.getEM();
        try
        {
            ContactDetails contactDetails = findContactWPhone(phoneNumber);
            System.out.println(contactDetails);
            em.getTransaction().begin();
            if (!em.contains(contactDetails))
            {
                contactDetails = em.merge(contactDetails);
            }
            em.remove(contactDetails);
            em.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            em.getTransaction().rollback();
            this.closeEM(em);
        }
        finally
        {
            System.out.println("\nYeetus deletus!!!!!!\n");
            this.closeEM(em);
        }
    }

    public ContactDetails findContactWPhone(String phonenumber)
    {
        EntityManager em = this.getEM();
        ContactDetails contactDetails = null;

        try
        {
            contactDetails = em.find(ContactDetails.class, phonenumber);
        }
        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
        }
        finally
        {
            this.closeEM(em);
        }
        return contactDetails;
    }

    public void editContact(ContactDetails newContact)
    {
        EntityManager em = getEM();
        ContactDetails contact = null;

        try
        {
            em.getTransaction().begin();
            contact = findContactWPhone(newContact.getPhone());
            if (newContact.getName() != null && newContact.getAddress() != null)
            {
                contact.setName(newContact.getName());
                contact.setAddress(newContact.getAddress());
                em.merge(contact);
                em.getTransaction().commit();
                closeEM(em);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
            em.getTransaction().rollback();
            closeEM(em);
        }
        finally
        {
            closeEM(em);
        }
    }

    @Override
    public Collection<ContactDetails> getAllContacts()
    {
        Collection<ContactDetails> contacts = null;
        EntityManager em = this.getEM();
        try
        {
            Query q = em.createQuery("SELECT OBJECT(o) FROM ContactDetails o");
            contacts = q.getResultList();
        }
        catch (Exception e)
        {
            System.out.println("Kake" + e.getStackTrace());
        }
        finally
        {
            this.closeEM(em);
            return contacts;
        }
    }

    @Override
    public Iterator<ContactDetails> iterator()
    {
        Collection<ContactDetails> contacts = null;
        EntityManager em = this.getEM();
        try
        {
            Query q = em.createQuery("SELECT OBJECT(o) FROM ContactDetails o");
            contacts = q.getResultList();
        }
        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
        }
        finally
        {
            this.closeEM(em);
            return contacts.iterator();
        }
    }

    @Override
    public void close()
    {
        this._emf.close();
    }

    private EntityManager getEM()
    {
        return this._emf.createEntityManager();
    }

    private void closeEM(EntityManager em)
    {
        if (em != null && em.isOpen())
        {
            em.close();
        }
    }
}
