package model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.dto.Customer;

public interface CustomerMapper {

	public void insertCustomer(Customer customer);
	
	public Customer selectCustomerWithId(String userId);
	
	public Customer selectCustomer(@Param("userId")String userId, @Param("password")String password);
	
	public List<Customer> selectCustomerList();
	
	public Customer selectCustomerWithEmail(String email);
	
	public void updateStateByEmail(@Param("email")String email ,@Param("emailCode")String emailCode);
	
	public Customer selectCustomerByEmailAndEmailState(@Param("email")String email,@Param("emailState")String emailState);

	public Customer selectCustomerByPhone(String phone);

	public void updateCustomer(Customer cust);

	public void deleteCustomerById(int id);

	public int countCustomer();
	
	public List<Customer> selectCustomerListWithLimit (@Param("firstRow")int firstRow, @Param("customerCountPerPage")int customerCountPerPage);
	
}
