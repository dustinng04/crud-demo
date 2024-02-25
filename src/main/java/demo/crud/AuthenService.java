package demo.crud;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class AuthenService {
	public static HashMap<String, String> userCredentials = new HashMap<>();
	
	static {
		userCredentials.put("in28minutes", "dummy");
		userCredentials.put("user1", "pass1");
		userCredentials.put("user2", "pass2");
	}
	public boolean authenticate(String username, String password) {
		 String storedPassword = userCredentials.get(username);
        return storedPassword != null && storedPassword.equals(password);			
	}
}
