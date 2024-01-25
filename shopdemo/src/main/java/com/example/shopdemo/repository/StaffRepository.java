package com.example.shopdemo.repository;

import com.example.shopdemo.model.Staff;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StaffRepository extends JpaRepository<Staff, UUID> {

    @Override
    <S extends Staff> S saveAndFlush(S entity);

    @Override
    <S extends Staff> List<S> findAll(Example<S> example);

    @Override
    Optional<Staff> findById(UUID uuid);

    @Override
    boolean existsById(UUID uuid);

    @Override
    long count();

    @Override
    void deleteById(UUID uuid);

    @Override
    List<Staff> findAll(Sort sort);

    @Override
    Page<Staff> findAll(Pageable pageable);

    @Query("select nv from Staff nv where nv.userName = :user_name")
    Staff getByUserName(@Param("user_name") String user_name);

}
