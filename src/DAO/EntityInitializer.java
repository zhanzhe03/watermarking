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
    private SortedListSetInterface<Donee> donees = new SortedDoublyLinkedListSet<>();
    private SortedListSetInterface<Distribution> distributions = new SortedDoublyLinkedListSet<>();
    
        
    public SortedListSetInterface<Donation> getDonations() {
        return donations;
    }
    
    public SortedListSetInterface<Donor> getDonors() {
        return donors;
    }
    
    public SortedListSetInterface<Donee> getDonees() {
        return donees;
    }
    
    public SortedListSetInterface<Distribution> getDistributions() {
        return distributions;
    }
    
    public void entityEnitialize() {
        Donor donor1 = new Donor("DR001","Chew Zhan Zhe","Chew Zhan Zhe","012-6730810","zhanzhe@gmail.com","Jalan SS 5/2, Ss 5, 47301 Petaling Jaya, Selangor","public", "prospect");
        Donor donor2 = new Donor("DR002","Tee Yi Hang","Tee Yi Hang","012-5837395", "yihang@gmail.com","5, Lorong Masria 6, Taman Bunga Raya, 53000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "private", "inactive");
        Donor donor3 = new Donor("DR003","Department of Sustainable Development and Welfare","Lim Jun Hong","012-9123389","junhong@gmail.com","Jalan 4d/6, Taman Setapak Indah, 53300 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur", "government","active");
        
        Donee donee1 = new Donee("DE001","INDIVIDUAL","TAN JIAN QUAN","jianquan@gmail.com","0125030510","No80 Taman Gembira", "Location A");
        Donee donee2 = new Donee("DE002","ORGANIZATION","TARUMT FOOD BANK","tarumtoffice@gmail.com","0125558888","JALAN TUNKU ABDUL RAHMAN", "Location B");
        Donee donee3 = new Donee("DE003","FAMILY","LIM FAMILY","qiern@gmail.com","0125030512","No82 Taman Gilang", "Location C");
        Donee donee4 = new Donee("DE004","INDIVIDUAL","KIKI GUO","kiki@gmail.com","012503251","No20 Taman Gembira", "Location A");
        Donee donee5 = new Donee("DE005","INDIVIDUAL","DARREN LIM","darren@gmail.com","0123030520","No30 Taman Gembira", "Location A");
        
        
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
        
  

        Distribution distribution = new Distribution("Dist001", new Date(1,1,1));

     

        // Create a selected item
        SelectedItem selectedItem1 = new SelectedItem("I001", 50);
        SelectedItem selectedItem2 = new SelectedItem("I002",22.22);

        // Add selected item to the distribution
        distribution.addSelectedItem(selectedItem1);
        distribution.addSelectedItem(selectedItem2);
        
        distributions.add(distribution);
        System.out.println(distributions);


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
        
        donees.add(donee1);
        donees.add(donee2);
        donees.add(donee3);
        donees.add(donee4);
        donees.add(donee5);
    }
}
