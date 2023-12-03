package com.alibou.demo.address;

import com.alibou.demo.student.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue
    private Integer id;

    private String homeAddress;

    @OneToOne
    @JoinColumn(name = "std_id")
    private Student student;
}
