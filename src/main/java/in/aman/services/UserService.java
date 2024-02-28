package in.aman.services;

import in.aman.bindings.DashboardCard;
import in.aman.bindings.LoginForm;
import in.aman.bindings.UserAccForm;

public interface UserService {
	
	public String login(LoginForm loginForm);
	
	public boolean recoverPwd(String email);

	public DashboardCard fetchDashboardInfo();
	
	public UserAccForm getUserByEmail(String email);
}
