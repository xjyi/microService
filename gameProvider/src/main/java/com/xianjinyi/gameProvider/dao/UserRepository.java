package com.xianjinyi.gameProvider.dao;

import com.xianjinyi.gameProvider.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: xianjinyi
 * @date 2019/03/12
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
