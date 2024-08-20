/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

public class Date {

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public boolean beforeDate(Date otherDate){
        if (this.year < otherDate.year) {
            return true;
        } else if (this.year == otherDate.year) {
            if (this.month < otherDate.month) {
                return true;
            } else if (this.month == otherDate.month) {
                return this.day < otherDate.day;
            }
        }
        return false;
    }
    
    public boolean afterDate(Date otherDate){
        if (this.year > otherDate.year) {
            return true;
        } else if (this.year == otherDate.year) {
            if (this.month > otherDate.month) {
                return true;
            } else if (this.month == otherDate.month) {
                return this.day > otherDate.day;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%02d-%02d-%04d", day, month, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Date other = (Date) obj;
        if (this.day != other.day) {
            return false;
        }
        if (this.month != other.month) {
            return false;
        }
        return this.year == other.year;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
}
