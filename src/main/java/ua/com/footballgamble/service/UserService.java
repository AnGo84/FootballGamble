package ua.com.footballgamble.service;

import java.util.List;

import ua.com.footballgamble.model.user.User;

public interface UserService {

	User findById(long id);

	User findByLogin(String login);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(long id);

	List<User> findAllUsers();

	// void deleteAllUsers();

	boolean isUserExist(User user);

}
