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

    public int compareTo(Date otherDate) {
        if (this.year != otherDate.year) {
            return Integer.compare(this.year, otherDate.year);
        } else if (this.month != otherDate.month) {
            return Integer.compare(this.month, otherDate.month);
        } else {
            return Integer.compare(this.day, otherDate.day);
        }
    }
    
    public boolean afterDate(Date otherDate) {
        return this.compareTo(otherDate) > 0;
    }

    public boolean beforeDate(Date otherDate) {
        return this.compareTo(otherDate) < 0;
    }
    
    public boolean withinTwoDays(Date otherDate){
        int numberOfDays = 3;
        if(this.year + 1 == otherDate.year){
            if(this.month == 12 && otherDate.month == 1){
                numberOfDays = (31 - this.day) + otherDate.day;
            }
        }else if(this.year == otherDate.year){
            numberOfDays = otherDate.convertToDays() - this.convertToDays();
        }
        
        return numberOfDays <= 2;
    }

    //check within pass week
    public boolean withinPassWeek(Date otherDate) {
        int numberOfDays = 8;
        if (this.year + 1 == otherDate.year) {
            if (this.month == 12 && otherDate.month == 1) {
                numberOfDays = (31 - this.day) + otherDate.day;
            }
        } else if (this.year == otherDate.year) {
            numberOfDays = otherDate.convertToDays() - this.convertToDays();
        }

        return numberOfDays <= 7;
    }

    //check within pass month
    public boolean withinPassMonth(Date otherDate) {
        int numberOfDays = 32;
        if (this.year + 1 == otherDate.year) {
            if (this.month == 12 && otherDate.month == 1) {
                numberOfDays = (31 - this.day) + otherDate.day;
            }
        } else if (this.year == otherDate.year) {
            numberOfDays = otherDate.convertToDays() - this.convertToDays();
        }

        return numberOfDays <= 31;
    }

    //check within next 15 days
    public boolean next15Days(Date otherDate) {
        int numberOfDays = 16;
        if (this.afterDate(otherDate)) {
            if (this.year - 1 == otherDate.year) {
                if (this.month == 1 && otherDate.month == 12) {
                    numberOfDays = (31 - otherDate.day) + this.day;
                }
            } else if (this.year == otherDate.year) {
                numberOfDays = this.convertToDays() - otherDate.convertToDays();
            }
        }
        return numberOfDays <= 15;
    }

    //only for current year
    private int convertToDays() {
        int daysInMonth = 0;
        switch (this.month) {
            case 12:
                daysInMonth += 30;
            case 11:
                daysInMonth += 31;
            case 10:
                daysInMonth += 30;
            case 9:
                daysInMonth += 31;
            case 8:
                daysInMonth += 31;
            case 7:
                daysInMonth += 30;
            case 6:
                daysInMonth += 31;
            case 5:
                daysInMonth += 30;
            case 4:
                daysInMonth += 31;
            case 3:
                daysInMonth += 28;
            case 2:
                daysInMonth += 31;
            case 1:
                daysInMonth += 0;
        }
        daysInMonth += this.day;

        return (this.year % 4 == 0 && this.month > 2) ? daysInMonth + 1 : daysInMonth;
    }

    // This method calculates the difference in days between this date and another date
    public int daysBetween(Date otherDate) {
        return this.convertToDays() - otherDate.convertToDays();
    }

    //szewen
       public Date addDays(int daysToAdd) {
        int newDay = this.day;
        int newMonth = this.month;
        int newYear = this.year;
        
        while (daysToAdd > 0) {
            int daysInCurrentMonth = getDaysInMonth(newMonth, newYear);
            
            if (newDay + daysToAdd <= daysInCurrentMonth) {
                newDay += daysToAdd;
                daysToAdd = 0;  // All days have been added
            } else {
                // Move to the next month
                daysToAdd -= (daysInCurrentMonth - newDay + 1);
                newDay = 1;

                if (newMonth == 12) {
                    newMonth = 1;
                    newYear++;
                } else {
                    newMonth++;
                }
            }
        }

        return new Date(newDay, newMonth, newYear);
    }

    // Helper method to get the number of days in a month considering leap years
    private int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    // Helper method to check if a year is a leap year
    private boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 100 != 0) {
            return true;
        } else {
            return year % 400 == 0;
        }
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
