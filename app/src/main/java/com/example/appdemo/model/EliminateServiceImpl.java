package com.example.appdemo.model;

public class EliminateServiceImpl implements EliminateService {
    @Override
    public int eliminate(int[][] gridData, int x1, int y1, int x2, int y2) {
        int e1 = 0, e2 = 0;
        if(x1 == x2) {
            e1 += eliminateHorizontal(gridData, x1, y1, eliminateType.Horizontal);
            e1 += eliminateVertical(gridData, x1, y1, y1 > y2 ? eliminateType.Bottom : eliminateType.Top);
            e2 += eliminateHorizontal(gridData, x2, y2, eliminateType.Horizontal);
            e2 += eliminateVertical(gridData, x2, y2, y2 > y1 ? eliminateType.Bottom : eliminateType.Top);
        } else if (y1 == y2) {
            e1 += eliminateVertical(gridData, x1, y1, eliminateType.Vertical);
            e1 += eliminateHorizontal(gridData, x1, y1, x1 > x2 ? eliminateType.Right : eliminateType.Left);
            e2 += eliminateVertical(gridData, x2, y2, eliminateType.Vertical);
            e2 += eliminateHorizontal(gridData, x2, y2, x2 > x1 ? eliminateType.Right : eliminateType.Left);
        }
        if(e1 > 0) {
            gridData[x1][y1] = GameIconList.getIconEmpty();
            e1++;
        }
        if(e2 > 0) {
            gridData[x2][y2] = GameIconList.getIconEmpty();
            e2++;
        }
        return e1 + e2;
    }
    protected enum eliminateType {
        Horizontal, Left, Right, Vertical, Top, Bottom
    }

    protected int eliminateVertical(int[][] gridData, int x, int y, eliminateType type) {
        int yet = -1, yeb = -1;
        int yt = Math.max(0, y - 2);
        int yb = Math.min(gridData.length - 1, y + 2);
        if (type == eliminateType.Top) {
            yb = y;
        } else if (type == eliminateType.Bottom) {
            yt = y;
        }
        for(int k = yt; k <= yb; k++) {
            if(gridData[x][k] == gridData[x][y]) {
                if(yet == -1) yet = k;
                else yeb = k;
            } else if(yeb - yet >= 2) {
                break;
            } else if(yet != -1) {
                yet = -1;
                yeb = -1;
            }
        }
        if(yet != -1 && yeb != -1 && yeb - yet >= 2) {
            for (int k = yet; k <= yeb; k++) {
                if(k != y) gridData[x][k] = GameIconList.getIconEmpty();
            }
            return yeb - yet;
        }
        return 0;
    }

    protected int eliminateHorizontal(int[][]gridData, int x, int y, eliminateType type) {
        int xel = -1, xer = -1;
        int xl = Math.max(0, x - 2);
        int xr = Math.min(gridData.length - 1, x + 2);
        if (type == eliminateType.Left) {
            xr = x;
        } else if (type == eliminateType.Right) {
            xl = x;
        }
        for(int k = xl; k <= xr; k++) {
            if(gridData[k][y] == gridData[x][y]) {
                if(xel == -1) xel = k;
                else xer = k;
            } else if(xer - xel >= 2) {
                break;
            } else if(xel != -1) {
                xel = -1;
                xer = -1;
            }
        }
        if(xel != -1 && xer != -1 && xer - xel >= 2) {
            for (int k = xel; k <= xer; k++) {
                if(k != x) gridData[k][y] = GameIconList.getIconEmpty();
            }
            return xer - xel;
        }
        return 0;
    }
}
