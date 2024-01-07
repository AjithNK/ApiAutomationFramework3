package core;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

public class RandomData {
	
	
	public String getRandomEmail() {
		
		//can use bothify()for obtaining random email ids
		FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
		String email = fakeValuesService.bothify("????##@gmail.com");
		
		System.out.println(email);
		return email;
		
	}
	
	public String getRandomEmailUsingRegEx() {
		
		//can use regexify()[for regular expressions] for obtaining random email ids
		FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
		String email = fakeValuesService.regexify(getRandomEmail());
		
		System.out.println(email);
		return email;
		
	}
	
	
	public String getRandomPhoneNo() {
		
		//can use Faker class object for obtaining random phone numbers
		Faker faker = new Faker(new Locale("us")); // Use the US locale
		String phoneNumber = faker.phoneNumber().cellPhone();
		
		System.out.println(phoneNumber);
        return phoneNumber;
		
	}
	
	//can use Faker class object for obtaining random phone numbers
	public String getRandomAddress() {
		
		Faker faker=new Faker();
		String address = faker.address().fullAddress();
		
		System.out.println(address);
		return address;
		
	}
	

}
