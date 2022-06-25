package com.crypto.repository;

import org.springframework.data.repository.CrudRepository;
import com.crypto.model.UserInfo;

public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
}