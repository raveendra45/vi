package com.aiims.inspectionplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiims.inspectionplan.entity.NeutralParameter;

public interface NeutralParameterRepository extends JpaRepository<NeutralParameter,Integer> {

}