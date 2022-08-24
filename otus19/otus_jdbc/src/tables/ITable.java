package tables;

import java.sql.SQLException;
import java.util.List;

public interface ITable {
  List list(String[] predicates);
}
