package com.luxoft.logeek.repository;

import com.luxoft.logeek.entity.Pupil;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface PupilRepository extends BaseJpaRepository<Pupil, Long> {

  @Query("select p from Pupil p where p.age >= :age")
  Set<Pupil> findPupilsOlderThan(@Param("age") int age);

  @Query("select p from Pupil p where p.age >= :age " +
    "order by p.age")
  Set<Pupil> findPupilsOlderThanOrderedByAge(@Param("age") int age);

  @Query("select p from Pupil p where p.age >= :age " +
    "order by p.age")
  HashSet<Pupil> findPupilsOlderThanSortedByAge(@Param("age") int age);

  @Query("select distinct p.name from Pupil p")
  List<String> findAllNames();

  @Query("select p.name from Pupil p")
  Set<String> findAllNamesAsSet();

  @Query("select distinct p from Pupil p")
  List<String> findAllDistinct();

  @Query("select p from Pupil p")
  Set<String> findAllAsSet();
}
