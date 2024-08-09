/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ADT.*;
import Entity.*;

/**
 *
 * @author USER
 */
public class EntityInitializer {

    private SortedListSetInterface<Donation> donations = new SortedDoublyLinkedListSet<>();
    private SortedListSetInterface<Donor> donors = new SortedDoublyLinkedListSet<>();
    
    public SortedListSetInterface<Donation> getDonations() {
        return donations;
    }
    
    public SortedListSetInterface<Donor> getDonors() {
        return donors;
    }
    
    public void entityEnitialize() {
        Donor donor1 = new Donor("DR001","Chew Zhan Zhe","zhanzhe@gmail.com","012-6730810");
        Donor donor2 = new Donor("DR002","Tee Yi Hang","yihang@gmail.com","012-5837395");
        Donor donor3 = new Donor("DR003","Lim Jun Hong","junhong@gmail.com","012-9123389");
        
        Item item1 = new Item("I001","Cash",200);
        Item item2 = new Item("I002","Book",3,15);
        Item item3 = new Item("I003","Clothes",5,20);
        Item item4 = new Item("I001","Water",20,1.20,new Date(28,9,2024));
        Item item5 = new Item("I001","Food",20,5, new Date(29,9,2024));
        Item item6 = new Item("I002","Clothes",10,15.5);
        Item item7 = new Item("I001","Cash",500);
        Item item8 = new Item("I002","Food",50,7, new Date(30,9,2024));
        
        Donation donation1 = new Donation("D001",new Date(1,1,1),donor1);
        Donation donation2 = new Donation("D002",new Date(2,2,2),donor2);
        Donation donation3 = new Donation("D003",new Date(3,3,3),donor3);
        Donation donation4 = new Donation("D004",new Date(4,4,4), donor2);
        
        donation1.assignItems(item1);
        donation1.assignItems(item2);
        donation1.assignItems(item3);
        donation2.assignItems(item4);
        donation3.assignItems(item5);
        donation3.assignItems(item6);
        donation4.assignItems(item7);
        donation4.assignItems(item8);
        
        donors.add(donor1);
        donors.add(donor2);
        donors.add(donor3);
        
        donations.add(donation1);
        donations.add(donation2);
        donations.add(donation3);
        donations.add(donation4);
    }
}
