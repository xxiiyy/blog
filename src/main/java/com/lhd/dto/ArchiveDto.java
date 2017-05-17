package com.lhd.dto;

/**
 * Created by lhd on 2017/5/17.
 */
public class ArchiveDto {

    private int aYear;

    private int aMonth;

    private int aCount;

    public int getaYear() {
        return aYear;
    }

    public void setaYear(int aYear) {
        this.aYear = aYear;
    }

    public int getaMonth() {
        return aMonth;
    }

    public void setaMonth(int aMonth) {
        this.aMonth = aMonth;
    }

    public int getaCount() {
        return aCount;
    }

    public void setaCount(int aCount) {
        this.aCount = aCount;
    }

    @Override
    public String toString() {
        return "ArchiveDto{" +
                "aYear=" + aYear +
                ", aMonth=" + aMonth +
                ", aCount=" + aCount +
                '}';
    }
}
