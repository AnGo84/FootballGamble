package ua.com.footballgamble.dao;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public class AppRoleDAO {


	public List<String> getRoleNames(Long userId) {
		/*String sql = "Select ur.appRole.roleName from " + AppUserRole.class.getName() + " ur " //
				+ " where ur.appUser.userId = :userId ";

		Query query = this.entityManager.createQuery(sql, String.class);
		query.setParameter("userId", userId);
		return query.getResultList();*/
		return null;
	}

}
