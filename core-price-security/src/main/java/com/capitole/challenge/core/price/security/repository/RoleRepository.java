package com.capitole.challenge.core.price.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capitole.challenge.core.price.security.domain.ERole;
import com.capitole.challenge.core.price.security.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
