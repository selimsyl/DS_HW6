package user;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable,Comparable{

  public enum UserRole {CUSTOMER,TRADER};

  public User(Integer userId, String password, UserRole role) {
    this.userId = userId;
    this.password = password;
    this.role = role;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserRole getRole() {
    return role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(userId, user.userId) &&
            Objects.equals(password, user.password);
  }

  @Override
  public int compareTo(Object o) {
    return this.getUserId().compareTo(((User)o).getUserId());
  }


  private Integer userId;
  private String password;
  private final UserRole role;
//  @Override
//  public int hashCode() {
//    return Objects.hash(userId, password);
//  }
}
