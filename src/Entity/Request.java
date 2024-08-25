/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Entity;

import ADT.SortedListSetInterface;
import java.util.Objects;
import java.io.Serializable;
import ADT.*;
import java.util.Iterator;
import Entity.Date;
/**
 *
 * @author TANJIANQUAN
 */
public class Request implements Comparable<Request> {
    private String requestItems;
    private Date requestDate;
    
    
    //getter setter
    public String getRequestItems() {
        return requestItems;
    }

    public void setRequestItems(String requestItems) {
        this.requestItems = requestItems;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
    
    //Construstor
    public Request(Date requestDate, String requestItems) {
        this.requestDate = requestDate;
        this.requestItems = requestItems;
    }
    
    public Request(){
        
    }
    
    @Override
    public int compareTo(Request other) {
        int dateComparison = this.requestDate.compareTo(other.requestDate);
        if (dateComparison == 0) {
            return this.requestItems.compareTo(other.requestItems);
        }
        return dateComparison;
    }
    
    @Override
    public String toString() {
        return String.format("%-20s %-20s", requestDate,requestItems);
    }
    
    

   
    
}
