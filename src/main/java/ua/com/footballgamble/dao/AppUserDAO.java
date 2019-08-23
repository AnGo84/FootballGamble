package ua.com.footballgamble.dao;

import org.springframework.stereotype.Repository;
import ua.com.footballgamble.model.user.User;

@Repository
public class AppUserDAO {

	public User findUserAccount(String userName) {
        /*try {
            String sql = "Select e from " + AppUser.class.getName() + " e " //
                    + " Where e.userName = :userName ";
            // System.out.println(sql);
            Query query = entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userName", userName);

            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }*/
		return null;
	}

}
