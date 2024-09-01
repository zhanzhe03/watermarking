
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
    private SortedListSetInterface<Item> items = new SortedDoublyLinkedListSet<>();
    private SortedListSetInterface<SelectedItem> distributedItem = new SortedDoublyLinkedListSet<>();
    private SortedListSetInterface<Request> request = new SortedDoublyLinkedListSet<>();

    public SortedListSetInterface<Request> getRequest() {
        return request;
    }


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

    public SortedListSetInterface<Item> getItems() {
        return items;
    }

    public SortedListSetInterface<SelectedItem> getDistributedItem() {
        return distributedItem;
    }

    public void entityEnitialize() {
        Donor donor1 = new Donor("DR001", "Chew Zhan Zhe", "Chew Zhan Zhe", "012-6730810", "zhanzhe@gmail.com", "Jalan SS 5/2, Ss 5, 47301 Petaling Jaya, Selangor", "Public", "Prospect");
        Donor donor2 = new Donor("DR002", "Tee Yi Hang", "Tee Yi Hang", "012-5837395", "yihang@gmail.com", "5, Lorong Masria 6, Taman Bunga Raya, 53000 Kuala Lumpur", "Private", "Inactive");
        Donor donor3 = new Donor("DR003", "Sustainable Development and Welfare", "Lim Jun Hong", "012-9123389", "junhong@gmail.com", "Jalan 4d/6, Taman Setapak Indah, 53300 Kuala Lumpur", "Government", "Active");

        Donee donee1 = new Donee("DE001", "INDIVIDUAL", "TAN JIAN QUAN", "jianquan@gmail.com", "0125030510", "No80 Taman Gembira", "Location A", new Date(10, 2, 2022));
        Donee donee2 = new Donee("DE002", "ORGANIZATION", "TARUMT FOOD BANK", "tarumtoffice@gmail.com", "0125558888", "JALAN TUNKU ABDUL RAHMAN", "Location B", new Date(2, 3, 2022));
        Donee donee3 = new Donee("DE003", "FAMILY", "LIM FAMILY", "qiern@gmail.com", "0125030512", "No82 Taman Gilang", "Location C", new Date(14, 3, 2022));
        Donee donee4 = new Donee("DE004", "INDIVIDUAL", "KIKI GUO", "kiki@gmail.com", "012503251", "No20 Taman Gembira", "Location A", new Date(10, 5, 2022));
        Donee donee5 = new Donee("DE005", "INDIVIDUAL", "DARREN LIM", "darren@gmail.com", "0123030520", "No30 Taman Gembira", "Location A", new Date(2, 4, 2021));
        Donee donee6 = new Donee("DE006", "INDIVIDUAL", "GUO JING LIM", "jinglim@gmail.com", "0133030520", "No11 Taman Kuching", "Location A", new Date(2, 5, 2021));
        Donee donee7 = new Donee("DE007", "FAMILY", "QUAN FAMILY", "quan@gmail.com", "0143030520", "No2 Jalan Kuching", "Location A", new Date(2, 6, 2021));
        Donee donee8 = new Donee("DE008", "FAMILY", "JIAN FAMILY", "jian@gmail.com", "0155030510", "No32 Sungai Besar Jalan Tepi", "Location A", new Date(8, 2, 2022));
        Donee donee9 = new Donee("DE009", "ORGANIZATION", "SUNWAY FOOD BANK", "sunway@gmail.com", "0125558888", "PJ Jalan Sunway", "Location B", new Date(3, 3, 2022));
        Donee donee10 = new Donee("DE0010", "FAMILY", "KIKI FAMILY", "kiki@gmail.com", "0125130512", "No81 Bagan Pasir", "Location C", new Date(14, 9, 2022));
        Donee donee11 = new Donee("DE0011", "INDIVIDUAL", "QIAN ER", "qianer@gmail.com", "012523251", "No11 Bagan Pasir", "Location A", new Date(10, 11, 2022));
        Donee donee12 = new Donee("DE0012", "INDIVIDUAL", "DEBBIE", "debbie@gmail.com", "0163030520", "No30 Bagan Pasir", "Location A", new Date(2, 12, 2021));
        Donee donee13 = new Donee("DE0013", "ORGANIZATION", "TAYLOR FOOD BANK", "taylor@gmail.com", "0123032520", "KL Jalan Taylor", "Location A", new Date(13, 4, 2021));
        Donee donee14 = new Donee("DE0014", "ORGANIZATION", "SEGI FOOD BANK", "segi@gmail.com", "0123012520", "BLOCK A PV16 09-12", "Location A", new Date(8, 4, 2021));
        Donee donee15 = new Donee("DE0015", "INDIVIDUAL", "BAODING", "baoding@gmail.com", "0125030534", "PV18 BLOCK B 0813", "Location A", new Date(11, 5, 2022));
        Donee donee16 = new Donee("DE0016", "ORGANIZATION", "VTAR FOOD BANK", "vtar@gmail.com", "0125458888", "JALAN TUNKU ABDUL RAHMAN", "Location B", new Date(2, 6, 2022));
        Donee donee17 = new Donee("DE0017", "FAMILY", "ABU FAMILY", "abu@gmail.com", "0125030345", "No89 Taman Gila", "Location C", new Date(11, 2, 2020));
        Donee donee18 = new Donee("DE0018", "INDIVIDUAL", "ANG XIAO", "ang@gmail.com", "017523251", "No20 Taman Gembira", "Location A", new Date(10, 5, 2021));
        Donee donee19 = new Donee("DE0019", "ORGANIZATION", "CITC FOOD BANK", "citc@gmail.com", "0143232520", "JALAN TUNKU ABDUL RAHMAN", "Location A", new Date(3, 11, 2021));
        Donee donee20 = new Donee("DE0020", "ORGANIZATION", "UTAR FOOD BANK", "utar@gmail.com", "0163240520", "SUNGAI BULOH UTAR", "Location A", new Date(1, 1, 2021));

        Request request1 = new Request(new Date(10, 2, 2023), "Food and Beverage");
        Request request2 = new Request(new Date(11, 3, 2022), "Clothing and Apparel");
        Request request3 = new Request(new Date(12, 3, 2021), "Household Items");
        Request request4 = new Request(new Date(15, 4, 2024), "Food and Beverage");
        Request request5 = new Request(new Date(20, 7, 2024), "Medical");
        Request request6 = new Request(new Date(8, 5, 2023), "Food and Beverage");
        Request request7 = new Request(new Date(5, 5, 2022), "Monetary");
        Request request8 = new Request(new Date(2, 6, 2021), "Educational Materials");

        Item item1 = new Item("I001", "Monetary", "Cash", 200);
        Item item2 = new Item("I002", "Household Items", "Bed", 2, 120);
        Item item3 = new Item("I003", "Electronic", "Accessories", 2, 20);
        Item item4 = new Item("I004", "Food and Beverage", "Water Bottle", 20, 1.20, new Date(10, 8, 2024));
        Item item5 = new Item("I005", "Clothing and Apparel", "Clothes", 5, 30);
        Item item6 = new Item("I006", "Clothing and Apparel", "Gloves", 20, 15);
        Item item7 = new Item("I007", "Monetary", "Online Transfers", 750);
        Item item8 = new Item("I008", "Electronic", "Laptop", 2, 1500);
        Item item9 = new Item("I009", "Educational Materials", "Stationary", 3, 2.40);
        Item item10 = new Item("I010", "Monetary", "QR Code Payment", 120);
        Item item11 = new Item("I011", "Medical", "Wheelchair", 1, 180);
        Item item12 = new Item("I012", "Clothing and Apparel", "Shoes", 10, 50);
        Item item13 = new Item("I013", "Food and Beverage", "White Rice", 30, 1.00, new Date(1, 9, 2024));
        Item item14 = new Item("I014", "Food and Beverage", "Packaged Snacks", 4, 9, new Date(12, 10, 2024));
        Item item15 = new Item("I015", "Household Items", "Table", 4, 30);
        Item item16 = new Item("I016", "Educational Materials", "Textbook", 10, 5);
        Item item17 = new Item("I017", "Electronic", "Mobile Phone", 1, 800);
        Item item18 = new Item("I018", "Medical", "First and Kit", 3, 50);

        Donation donation1 = new Donation("D001", new Date(22, 7, 2024), donor1);
        Donation donation2 = new Donation("D002", new Date(28, 7, 2024), donor2);
        Donation donation3 = new Donation("D003", new Date(3, 8, 2024), donor3);
        Donation donation4 = new Donation("D004", new Date(10, 8, 2024), donor2);
        Donation donation5 = new Donation("D005", new Date(19, 8, 2024), donor3);
        Donation donation6 = new Donation("D006", new Date(23, 8, 2024), donor2);

        donation1.assignItems(item1);
        donation1.assignItems(item2);
        donation1.assignItems(item3);
        donation1.assignItems(item4);
        donation1.assignItems(item5);
        donation2.assignItems(item6);
        donation2.assignItems(item7);
        donation3.assignItems(item8);
        donation3.assignItems(item9);
        donation3.assignItems(item10);
        donation3.assignItems(item11);
        donation4.assignItems(item12);
        donation5.assignItems(item13);
        donation5.assignItems(item14);
        donation5.assignItems(item15);
        donation6.assignItems(item16);
        donation6.assignItems(item17);
        donation6.assignItems(item18);

        Distribution distribution1 = new Distribution("DIST001", new Date(1, 1, 2023));

        Distribution distribution2 = new Distribution("DIST002", new Date(1, 8, 2024));
        Distribution distribution3 = new Distribution("DIST003", new Date(11, 8, 2024));
        Distribution distribution4 = new Distribution("DIST004", new Date(25, 8, 2024));
        Distribution distribution5 = new Distribution("DIST005", new Date(1, 9, 2024));
        Distribution distribution6 = new Distribution("DIST006", new Date(1, 9, 2024));

        // Create a selected item
        SelectedItem selectedItem1 = new SelectedItem("I001", 20.22);
        SelectedItem selectedItem2 = new SelectedItem("I002", 50);
        SelectedItem selectedItem3 = new SelectedItem("I003", 1);
        SelectedItem selectedItem4 = new SelectedItem(item4.getItemId(), 10);
        SelectedItem selectedItem5 = new SelectedItem(item5.getItemId(), 3);
        SelectedItem selectedItem6 = new SelectedItem(item6.getItemId(), 2);



        // Add selected item to the distribution
        distribution1.addDonee(donee1);
        distribution1.addSelectedItem(selectedItem1);
        distribution1.addSelectedItem(selectedItem2);

        distribution2.addDonee(donee2);
        distribution2.addSelectedItem(selectedItem3);

        distribution3.addDonee(donee3);
        distribution3.addSelectedItem(selectedItem3);

        distribution4.addDonee(donee4);
        distribution4.addSelectedItem(selectedItem4);
        distribution4.addSelectedItem(selectedItem5);
        

        distribution5.addDonee(donee1);
        distribution5.addSelectedItem(selectedItem1);

        distribution6.addDonee(donee1);
        distribution6.addSelectedItem(selectedItem6);

        distributions.add(distribution1);
       distributions.add(distribution2);
        distributions.add(distribution3);
        distributions.add(distribution4);
        distributions.add(distribution5);
        distributions.add(distribution6);

        donors.add(donor1);
        donors.add(donor2);
        donors.add(donor3);

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        items.add(item7);
        items.add(item8);
        items.add(item9);
        items.add(item10);
        items.add(item11);
        items.add(item12);
        items.add(item13);
        items.add(item14);
        items.add(item15);
        items.add(item16);
        items.add(item17);
        items.add(item18);

        donations.add(donation1);
        donations.add(donation2);
        donations.add(donation3);
        donations.add(donation4);
        donations.add(donation5);
        donations.add(donation6);

        donee1.addRequest(request1);
        donee1.addRequest(request2);
        donee2.addRequest(request2);
        donee2.addRequest(request4);
        donee2.addRequest(request7);
        donee3.addRequest(request3);
        donee4.addRequest(request4);
        donee5.addRequest(request5);

        donees.add(donee1);
        donees.add(donee2);
        donees.add(donee3);
        donees.add(donee4);
        donees.add(donee5);
    }
}

