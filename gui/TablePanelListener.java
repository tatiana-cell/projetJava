package gui;

import java.sql.SQLException;

public interface TablePanelListener {
  public void rowDeleted(int row);

public void recetteDeleted(int id) throws SQLException;
}
