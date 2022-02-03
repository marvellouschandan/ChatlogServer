package com.ofbusiness.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofbusiness.models.Chatlog;
import com.ofbusiness.models.User;

@Repository
public interface ChatlogRepository extends JpaRepository<Chatlog, Long> {
	public List<Chatlog> findAllByUser(User user, Pageable pageSortByField);
}
