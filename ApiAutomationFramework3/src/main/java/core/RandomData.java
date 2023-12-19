package core;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

public class RandomData {
	
	
	public String getRandomEmail() {
		FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
		
		String email = fakeValuesService.bothify("????##@gmail.com");
		System.out.println(email);
		return email;
		
	}
	
	
	
	public String getRandomPhoneNo() {
		//we can use regexify() for regular expression - implement regex and generate random phone number
		
		//FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
		//String email = fakeValuesService.regexify(getRandomEmail());
		
		//OR
		
		Faker faker = new Faker(new Locale("us")); // Use the US locale
		String phoneNumber = faker.phoneNumber().cellPhone();
		
		System.out.println(phoneNumber);
        return phoneNumber;
		
	}
	
	
	public String getRandomAddress() {
		
		Faker faker=new Faker();
		String address = faker.address().fullAddress();
		System.out.println(address);
		return address;
		
		
	}
	
	

}
