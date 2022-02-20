package com.emelyan.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id;

    private String name;
    private String surname;
    private String patronymic;

    private String email;
    private String phone;

    @Column(name="birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "second_phone")
    private String secondPhone;

    @Column(name = "fact_address")
    private String factAddress;

    @Convert(converter = GenderConverter.class)
    public Gender gender;

    @Override
    public String toString(){return name+" "+surname+" "+patronymic;}

    @Converter
    static class GenderConverter implements AttributeConverter<Gender, Character> {
        public Character convertToDatabaseColumn(Gender value) {
            if (value == null) {
                return null;
            }
            return value.getCode();
        }

        public Gender convertToEntityAttribute(Character value) {
            if (value == null) {
                return null;
            }
            return Gender.fromCode(value);
        }
    }
}
