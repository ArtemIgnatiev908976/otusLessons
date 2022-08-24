package tables;

import db.IDBExecutor;
import db.PostGreSqlDBExecutor;
import dob.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentTable extends TableAbs {

  private IDBExecutor idbExecutor = new PostGreSqlDBExecutor(new ArrayList<>(){{
    add("fio");
    add("sex");
    add("id_group");
  }});

  private final String tableName = "student";

  @Override
  public List list(String[] predicates) {
    String listStudentsQuery = String.format("select * from %s", tableName);

    List<Student> students = new ArrayList<>();

    List<Map<String, String>> result = idbExecutor.execute(listStudentsQuery);

    for(Map<String, String> res: result) {
      students.add(
          new Student(
              res.get("fio"),
              res.get("sex"),
              Integer.parseInt(res.get("id_group"))
          )
      );
    }

    return students;

  }
}
