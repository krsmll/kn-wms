package com.example.knwms.repositories;

import com.example.knwms.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByNameContainingIgnoreCase(String name);

    @Query(value = "SELECT * FROM user_group WHERE is_active = true", nativeQuery = true)
    List<Group> getActiveGroups();

    @Query(value = "SELECT * FROM user_group WHERE is_active = false", nativeQuery = true)
    List<Group> getInactiveGroups();
}
