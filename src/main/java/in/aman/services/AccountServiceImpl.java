package in.aman.services;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.aman.bindings.UnlockAccForm;
import in.aman.bindings.UserAccForm;
import in.aman.constants.AppConstants;
import in.aman.entities.UserEntity;
import in.aman.repositories.UserRepo;
import in.aman.utils.EmailUtils;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public boolean createUserAccount(UserAccForm accForm) {

		UserEntity entity = new UserEntity();

		BeanUtils.copyProperties(accForm, entity);

		entity.setPwd(generateRandomPwd());

		entity.setAccStatus("LOCKED");

		entity.setActiveSw("Y");

		userRepo.save(entity);

		String subject = "User Registration";
		String body = readEmailBody("REG_EMAIL_BODY.txt", entity);
		return emailUtils.sendEmail(subject, body, accForm.getEmail());
	}

	@Override
	public List<UserAccForm> fetchUserAccounts() {

		List<UserEntity> userEntities = userRepo.findAll();

		List<UserAccForm> users = new ArrayList<>();

		for (UserEntity userEntity : userEntities) {

			UserAccForm user = new UserAccForm();
			BeanUtils.copyProperties(userEntity, user);
			users.add(user);

		}

		return users;
	}

	@Override
	public UserAccForm getUserAccById(Integer accId) {

		Optional<UserEntity> optional = userRepo.findById(accId);

		if (optional.isPresent()) {

			UserEntity userEntity = optional.get();

			UserAccForm user = new UserAccForm();

			BeanUtils.copyProperties(userEntity, user);

			return user;

		}
		return null;

	}

	@Override
	public String changeAccStatus(Integer userId, String status) {

		Integer cnt = userRepo.updateAccStatus(userId, status);

		if (cnt > 0) {
			return "Status Changed";
		}

		return "Failed to Changed";
	}

	@Override
	public String unlockUserAccount(UnlockAccForm unlockAccForm) {

		UserEntity entity = userRepo.findByEmail(unlockAccForm.getEmail());

		entity.setPwd(unlockAccForm.getNewPwd());
		entity.setAccStatus("UNLOCKED");

		return "Account Unlocked";
	}

	private static String generateRandomPwd() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String pwd = RandomStringUtils.random(6, characters);

		return pwd;
	}

	private String readEmailBody(String filename, UserEntity user) {
		StringBuilder sb = new StringBuilder();
		try (Stream<String> lines = Files.lines(Paths.get(filename))) {
			lines.forEach(line -> {
				line = line.replace(AppConstants.FNAME, user.getFullName());
				line = line.replace(AppConstants.PWD, user.getPwd());
				line = line.replace(AppConstants.EMAIL, user.getEmail());
				sb.append(line);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
