package in.aman.services;

import java.util.List;

import in.aman.bindings.UnlockAccForm;
import in.aman.bindings.UserAccForm;

public interface AccountService {

	public boolean createUserAccount(UserAccForm accForm);

	public List<UserAccForm> fetchUserAccounts();

	public UserAccForm getUserAccById(Integer accId);

	public String changeAccStatus(Integer accId, String status);

	public String unlockUserAccount(UnlockAccForm unlockAccForm);

}
