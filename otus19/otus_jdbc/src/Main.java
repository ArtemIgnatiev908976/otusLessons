import db.IDBExecutor;
import db.PostGreSqlDBExecutor;
import dob.Student;
import tables.ITable;
import tables.StudentTable;

import java.sql.ResultSet;
import java.util.List;

public class Main {

  public static void main(String... args) {
//    IDBExecutor idbExecutor = new PostGreSqlDBExecutor();
//    ResultSet resultSet = idbExecutor.execute("select * from student");

    String[] predicates = new String[]{};

    ITable studentTable = new StudentTable();
    List<Student> students = studentTable.list(predicates);
  }

}
