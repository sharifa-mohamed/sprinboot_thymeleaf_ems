package com.sm.ems.student.repository;

import com.sm.ems.chart.model.ChartData;
import com.sm.ems.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findByFirstName(String firstName);

    public List<Student> findStudentByFirstNameContainingIgnoreCase(String firstNameContaining);

    @Modifying
    @Query(value = " truncate table tbl_student",
            nativeQuery = true)
    public void truncateStudentTable();

    public List<Student> findStudentByGuardianNameNotNull();

    public List<Student> findStudentsByFirstNameAndLastName(String firstName, String lastName);

    //jpql
    @Query(value = "select s from Student s where s.guardian.email=?1")
    public List<Student> getStudentsByGuardianEmailId(String email);

    //native named param
    @Query(value = "select * from tbl_student s where s.address = :adrs",
            nativeQuery = true)
    public List<Student> getStudentsByAddress(@Param("adrs") String address);


    @Modifying
    @Query(value = "SET SQL_SAFE_UPDATES = 0", nativeQuery = true)
    public void setSafeUpdateFalse();

    @Modifying
    @Query(value = "SET SQL_SAFE_UPDATES = 1", nativeQuery = true)
    public void setSafeUpdateTrue();

    @Modifying(flushAutomatically = true, clearAutomatically = true)//, flushAutomatically = true)
    @Query(value = "update Student s set s.emailId=:email where s.firstName =:firstName and s.id>0"
    )
    public int updateStudentEmailByFirstName(@Param("firstName") String firstName, @Param("email") String email);

    @Query(value = "select s from Student s where s.firstName like %:firstName% and s.id>0")
    public List<Student> getStudentByFirstName(@Param("firstName") String firstName);


    Student findStudentByFirstNameOrLastNameContaining(String firstName, String lastName);

    Student findStudentByEmailId(String emailId);

    @Query(value = "select  new com.sm.ems.chart.model.ChartData(DATE_FORMAT(s.regDate,'%Y'), count(s)) from Student s group by DATE_FORMAT(s.regDate,'%Y') ")
    List<ChartData> getStudentCountGroupedByYearChart();
}