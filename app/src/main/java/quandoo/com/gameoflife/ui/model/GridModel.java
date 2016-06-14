package quandoo.com.gameoflife.ui.model;

import android.graphics.Color;

import quandoo.com.gameoflife.ui.viewconfigs.GridConfig;

/**
 * Created by malikumarbhutta on 6/13/16.
 */
public class GridModel implements GridConfig {

    int width;
    int height;
    int color;
    private int cellCount;
    private int rowCellCount;
    private int columnCellCount;
    private int defaultCellColor;
    private int cellWidth;
    private int cellHeight;
    private int defaultLineColor;
    private int newBornCellColor;


    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getBackgroundColor() {
        return color;
    }

    @Override
    public void setBackgroundColor(String color) {
        this.color = Color.parseColor(color);
    }

    @Override
    public void setBackgroundColor(int color) {
        this.color = color;
    }

    @Override
    public int getCellCount() {
        return cellCount;
    }

    @Override
    public void setCellCount(int cellCount) {
        this.cellCount = cellCount;

    }

    @Override
    public int getRowCellCount() {
        return rowCellCount;
    }

    @Override
    public void setRowCellCount(int rowCellCount) {
        this.rowCellCount = rowCellCount;
    }

    @Override
    public int getColumnCellCount() {
        return columnCellCount;
    }

    @Override
    public void setColumnCellCount(int columnCellCount) {
        this.columnCellCount = columnCellCount;
    }

    @Override
    public int getCellHeight() {
        return cellHeight;
    }

    @Override
    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    @Override
    public int getCellWidth() {
        return cellWidth;
    }

    @Override
    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    @Override
    public int getDefaultCellColor() {
        return defaultCellColor;
    }

    @Override
    public void setDefaultCellColor(String cellColor) {
        this.defaultCellColor = Color.parseColor(cellColor);
    }

    @Override
    public int getDefaultLineColor() {
        return defaultLineColor;
    }

    @Override
    public void setDefaultLineColor(String lineColor) {
        this.defaultLineColor = Color.parseColor(lineColor);
    }

    @Override
    public int getNewBornCellColor() {
        return newBornCellColor;
    }

    @Override
    public void setNewBornCellColor(String newBornCellColor) {
        this.newBornCellColor = Color.parseColor(newBornCellColor);
    }
}
