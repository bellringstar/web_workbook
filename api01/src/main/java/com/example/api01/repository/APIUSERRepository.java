package com.example.api01.repository;

import com.example.api01.domain.APIUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APIUSERRepository extends JpaRepository<APIUser, String> {

}
