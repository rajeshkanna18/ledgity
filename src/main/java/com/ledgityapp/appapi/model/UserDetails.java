package com.ledgityapp.appapi.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="public.userdetails")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonBinaryType.class)
})
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="username", length=15, nullable=false, unique=true)
    private String username;

    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private String usertype;

    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private int score=0;
    private String address;
    private String gender;
    private String orias;
    private String association;
    private String image;
    private String cityOfBirth;
    private String countryOfBirth;
    private String nationality;
    private String birthOfDate;
    private String companyName;
    private String capital;
    private String companyAddress;
    private String professionalInsurance;
    private String referenceContact;
    private String companyCreationDate;
    private String website;
    private String siren;

    private String kyc1Status;
    private String kyc1Id;

    @Column(name = "isKyc1Passed", columnDefinition = "boolean default false NOT NULL")
    private boolean isKyc1Passed;
    @Column(name = "isActive", columnDefinition = "boolean default true NOT NULL")
    private boolean isActive;

    @Column(name="bankToken", length=1024)
    private String bankToken;
    private int budgetId=0;

    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private String kycAnswers;

}
