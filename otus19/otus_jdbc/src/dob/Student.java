package dob;

public class Student {
  public Student(String fio, String sex, int idGroup) {
    this.fio = fio;
    this.sex = sex;
    this.idGroup = idGroup;
  }

  private String fio;
  private String sex;
  private int idGroup;

  public String getFio() {
    return fio;
  }

  public String getSex() {
    return sex;
  }

  public int getIdGroup() {
    return idGroup;
  }
}
