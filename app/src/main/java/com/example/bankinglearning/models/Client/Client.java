package com.example.bankinglearning.models.Client;

import java.util.Date;

import androidx.annotation.NonNull;

public class Client {

  private String name;
  private String cnp;
  private Date birthDate;

  public Client(String name, String cnp, Date birthDate) {
    this.name = name;
    this.cnp = cnp;
    this.birthDate = birthDate;
  }

  public String getName() {
    return name;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public String getCnp() {
    return cnp;
  }


  @NonNull
  @Override
  public String toString() {
    return "name: " + name + " " + birthDate + " " + cnp;
  }
}
