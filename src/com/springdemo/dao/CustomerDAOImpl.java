package com.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to inject the session factory
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	 
	// @Transactional
	// remove @Transactional here while using Service layer
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		// create a query.........sort by last name
		
		Query<Customer> theQuery=currentSession.createQuery("from Customer order by lastName", Customer.class);
				
				
		// execute query and get result list
		
		List<Customer> customers=theQuery.getResultList();

		//return the results
		return customers;
	}



	@Override
	public void saveCustomer(Customer theCustomer) {
		//get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//save the customer
		currentSession.saveOrUpdate(theCustomer);
		//We are using same method saveCustomer to add and update customer data
		//saveOrUpdate - if(primary key/id) empty then INSERT new customer else UPDATE existing customer
	}



	@Override
	public Customer getCustomer(int theId) {
		
		// get the current hibernate session
		
		Session currentSession=sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		
		Customer theCustomer=currentSession.get(Customer.class, theId);
				
		return theCustomer;
	}



	@Override
	public void deleteCustomer(int theId) {
		
		//get current hibernate session
		 Session currentSession=sessionFactory.getCurrentSession();
				
		//delete object with primary key
		 Query theQuery=currentSession.createQuery("delete from Customer where id=:customerId");
		 theQuery.setParameter("customerId",theId);
		 theQuery.executeUpdate();
	}



	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		  // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        //
        // only search by name/email if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName or email ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName or lower(email) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
        
	}

}
