package com.example.booktree.domain.role.repository;


import com.example.booktree.enums.RoleType;
import com.example.booktree.domain.role.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByRole(RoleType roleType);
}
