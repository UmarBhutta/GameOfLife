package quandoo.com.gameoflife.ui.viewconfigs;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public interface GridConfig {
    int getWidth();

    void setWidth(int width);

    int getHeight();

    void setHeight(int height);

    int getBackgroundColor();

    void setBackgroundColor(String color);

    void setBackgroundColor(int color);

    int getCellCount();

    void setCellCount(int cellCount);

    int getRowCellCount();

    void setRowCellCount(int rowCellCount);

    int getColumnCellCount();

    void setColumnCellCount(int columnCellCount);

    int getCellHeight();

    void setCellHeight(int cellHeight);

    int getCellWidth();

    void setCellWidth(int cellWidth);

    int getDefaultCellColor();

    void setDefaultCellColor(String cellColor);

    int getDefaultLineColor();

    void setDefaultLineColor(String lineColor);

    int getNewBornCellColor();

    void setNewBornCellColor(String newBornCellColor);
}
