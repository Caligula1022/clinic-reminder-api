package com.pitt.reminder.api.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pitt.reminder.api.model.TAuth;

public interface AuthRepo extends JpaRepository<TAuth, Integer>{

	TAuth findByUsername(String username);
	
}
