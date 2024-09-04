
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ADT.*;
import Entity.*;
import java.util.Iterator;
import Utility.CommonUse;

/**
 *
 * @author CHEWZHANZHE
 */
public class EntityInitializer {
    
    private SortedListSetInterface<Donation> donations = new SortedDoublyLinkedListSet<>();
    private SortedListSetInterface<Donation> donationsHistory = new SortedDoublyLinkedListSet<>();
    private SortedListSetInterface<Donor> donors = new SortedDoublyLinkedListSet<>();
    private SortedListSetInterface<Donee> donees = new SortedDoublyLinkedListSet<>();
    private SortedListSetInterface<Distribution> distributions = new SortedDoublyLinkedListSet<>();
    private SortedListSetInterface<Item> items = new SortedDoublyLinkedListSet<>();
    private SortedListSetInterface<Request> request = new SortedDoublyLinkedListSet<>();
    private SortedListSetInterface<SelectedItem> distributedItemList = new SortedDoublyLinkedListSet<>();
    
    public SortedListSetInterface<Request> getRequest() {
        return request;
    }
    
    public SortedListSetInterface<Donation> getDonations() {
        return donations;
    }
    
    public SortedListSetInterface<Donation> getDonationsHistory() {
        return donationsHistory;
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
    
    public SortedListSetInterface<SelectedItem> getDistributedItemList(){
        return distributedItemList;
    }
    
    public void entityEnitialize() {
        
        Donor donor1 = new Donor("DR001", "Public Organization", "Chew Zhan Zhe", "Chew Zhan Zhe", "012-6730810", "zhanzhe@gmail.com", "Jalan SS 5/2, Ss 5, 47301 Petaling Jaya, Selangor", new Date(10, 2, 2022), "Prospect");
        Donor donor2 = new Donor("DR002", "Private Organization", "Tee Yi Hang", "Tee Yi Hang", "012-5837395", "yihang@gmail.com", "5, Lorong Masria 6, Taman Bunga Raya, 53000 Kuala Lumpur", new Date(16, 3, 2022), "Inactive");
        Donor donor3 = new Donor("DR003", "Government Organization", "Sustainable Development and Welfare", "Lim Jun Hong", "012-9123389", "junhong@gmail.com", "Jalan 4d/6, Taman Setapak Indah, 53300 Kuala Lumpur", new Date(21, 5, 2022), "Active");
        
        Donor donor4 =new Donor("DR004", "Individual", "Zhang Wei", "-", "012-6754321", "zhangwei@gmail.com", "Jalan SS 7/3, SS 7, 47301 Petaling Jaya, Selangor", new Date(18, 6, 2022), "Prospect");
        Donor donor5 =new Donor("DR005", "Private Organization", "ABC Pvt Ltd", "John Lee", "012-6837281", "johnlee@abc.com", "20, Jalan Tun Razak, 50400 Kuala Lumpur", new Date(25, 6, 2022), "Active");
        Donor donor6 =new Donor("DR006", "Public Organization", "XYZ Foundation", "Wong Mei", "012-6472819", "wongmei@xyz.org", "15, Jalan Bukit Bintang, 55100 Kuala Lumpur", new Date(5, 7, 2022), "Inactive");
        Donor donor7 =new Donor("DR007", "Government Organization", "Ministry of Health", "Tan Ah Kow", "012-7351827", "tanahkow@moh.gov.my", "Jalan Cempaka, 50490 Kuala Lumpur", new Date(11, 7, 2022), "Active");
        Donor donor8 =new Donor("DR008", "Individual", "Lee Ai Ling", "-", "012-7428910", "ailing_lee@gmail.com", "12, Jalan Ampang, 50450 Kuala Lumpur", new Date(20, 7, 2022), "Banned");
        Donor donor9 =new Donor("DR009", "Private Organization", "MNO Corporation", "Ong Siew Mun", "012-8129837", "ongsm@mno.com", "18, Jalan Raja Chulan, 50200 Kuala Lumpur", new Date(28, 7, 2022), "Active");
        Donor donor10 =new Donor("DR010", "Public Organization", "DEF Trust", "Lim Swee Ling", "012-8734590", "limsweeling@deftrust.org", "Jalan Tun HS Lee, 50000 Kuala Lumpur", new Date(3, 8, 2022), "Prospect");
        Donor donor11 =new Donor("DR011", "Government Organization", "Department of Education", "Chong Kar Ling", "012-8491023", "karling.chong@education.gov.my", "Jalan Bukit Jalil, 57000 Kuala Lumpur", new Date(10, 8, 2022), "Inactive");
        Donor donor12 =new Donor("DR012", "Individual", "Chen Wei", "-", "012-7821049", "chenwei@gmail.com", "Jalan Damansara, 60000 Kuala Lumpur", new Date(15, 8, 2022), "Active");
        Donor donor13 =new Donor("DR013", "Private Organization", "GHI Sdn Bhd", "Cheong Siew Fong", "012-6745120", "cheongsf@ghi.com", "Jalan Sultan Ismail, 50250 Kuala Lumpur", new Date(21, 8, 2022), "Banned");
        Donor donor14 =new Donor("DR014", "Public Organization", "UVW Charities", "Yap Wei Jian", "012-6845127", "yapweijian@uvw.org", "Jalan Raja Abdullah, 50300 Kuala Lumpur", new Date(29, 8, 2022), "Active");
        Donor donor15 =new Donor("DR015", "Government Organization", "National Welfare Department", "Low Kah Meng", "012-7891034", "kahmeng@welfare.gov.my", "Jalan Kuching, 51200 Kuala Lumpur", new Date(5, 9, 2022), "Inactive");
        Donor donor16 =new Donor("DR016", "Individual", "Ng Ai Lin", "-", "012-6712384", "ngailin@gmail.com", "Jalan Loke Yew, 55200 Kuala Lumpur", new Date(10, 9, 2022), "Prospect");
        Donor donor17 =new Donor("DR017", "Private Organization", "JKL Holdings", "Khoo Kai Ling", "012-8749123", "kailing@jkl.com", "Jalan Tun Sambanthan, 50470 Kuala Lumpur", new Date(15, 9, 2022), "Active");
        Donor donor18 =new Donor("DR018", "Public Organization", "MNZ Association", "Chia Wei Liang", "012-8941235", "chiaweiliang@mnz.org", "Jalan Raja Laut, 50350 Kuala Lumpur", new Date(22, 9, 2022), "Inactive");
        Donor donor19 =new Donor("DR019", "Government Organization", "Ministry of Finance", "Tan Cheng Wee", "012-8120456", "chengwee@mof.gov.my", "Jalan Duta, 50480 Kuala Lumpur", new Date(28, 9, 2022), "Active");
        Donor donor20 =new Donor("DR020", "Individual", "Soh Wei Ling", "-", "012-6758342", "weiling.soh@gmail.com", "Jalan Pudu, 55100 Kuala Lumpur", new Date(5, 10, 2022), "Banned");
        Donor donor21 =new Donor("DR021", "Public Organization", "Tan Mei Ling", "Tan Mei Ling", "012-4567890", "meiling.tan@gmail.com", "11, Jalan Taman Megah, 47301 Petaling Jaya, Selangor", new Date(5, 8, 2022), "Active");
        Donor donor22 =new Donor("DR022", "Private Organization", "Khoo Wen Jian", "Khoo Wen Jian", "016-2345678", "wenjian.khoo@gmail.com", "22, Lorong Bukit Anggerik, 56100 Kuala Lumpur", new Date(12, 9, 2022), "Inactive");
        Donor donor23 =new Donor("DR023", "Government Organization", "Education Support Fund", "Low Zhi Hao", "013-9876543", "zhihao.low@gov.my", "5, Persiaran Perdana, 62100 Putrajaya", new Date(18, 7, 2022), "Banned");
        Donor donor24 =new Donor("DR024", "Individual", "Siti Nur Aisyah", "-", "017-8765432", "aisyah.siti@gmail.com", "16, Jalan SS 2/45, 47300 Petaling Jaya, Selangor", new Date(28, 6, 2022), "Active");
        Donor donor25 =new Donor("DR025", "Public Organization", "Green Earth Foundation", "Lai Wai Mun", "014-2345678", "waimun.lai@gmail.com", "3, Jalan Damansara, 60000 Kuala Lumpur", new Date(22, 8, 2022), "Prospect");
        Donor donor26 =new Donor("DR026", "Private Organization", "Tech Innovators", "Yap Wei Jie", "019-3456789", "weijie.yap@techinnovators.com", "10, Jalan Teknologi, 47810 Petaling Jaya, Selangor", new Date(3, 10, 2022), "Inactive");
        Donor donor27 =new Donor("DR027", "Government Organization", "Healthcare Development Fund", "Ng Wei Keat", "012-5436789", "weikeat.ng@gov.my", "7, Jalan Bukit Bintang, 55100 Kuala Lumpur", new Date(1, 9, 2022), "Active");
        Donor donor28 =new Donor("DR028", "Individual", "Lee Zhi Xuan", "-", "018-2345678", "zhixuan.lee@gmail.com", "12, Jalan Tun Razak, 50400 Kuala Lumpur", new Date(7, 7, 2022), "Banned");
        Donor donor29 =new Donor("DR029", "Public Organization", "Wildlife Conservation Society", "Chong Wai Leng", "011-2345678", "waileng.chong@gmail.com", "4, Jalan Ampang, 50450 Kuala Lumpur", new Date(25, 8, 2022), "Active");
        Donor donor30 =new Donor("DR030", "Private Organization", "Youth Empowerment Hub", "Chan Mei Yen", "013-7654321", "meiyen.chan@gmail.com", "9, Jalan Bangsar, 59000 Kuala Lumpur", new Date(15, 9, 2022), "Prospect");


        Donee donee1 = new Donee("DE001", "INDIVIDUAL", "TAN JIAN QUAN", "jianquan@gmail.com", "0125030510", "No80 Taman Gembira", "Location A", new Date(10, 2, 2022));
        Donee donee2 = new Donee("DE002", "ORGANISATION", "TARUMT FOOD BANK", "tarumtoffice@gmail.com", "0125558888", "JALAN TUNKU ABDUL RAHMAN", "Location B", new Date(2, 3, 2022));
        Donee donee3 = new Donee("DE003", "FAMILY", "LIM FAMILY", "qiern@gmail.com", "0125030512", "No82 Taman Gilang", "Location C", new Date(14, 3, 2022));
        Donee donee4 = new Donee("DE004", "INDIVIDUAL", "KIKI GUO", "kiki@gmail.com", "012503251", "No20 Taman Gembira", "Location A", new Date(10, 5, 2022));
        Donee donee5 = new Donee("DE005", "INDIVIDUAL", "DARREN LIM", "darren@gmail.com", "0123030520", "No30 Taman Gembira", "Location A", new Date(2, 4, 2021));
        Donee donee6 = new Donee("DE006", "INDIVIDUAL", "GUO JING LIM", "jinglim@gmail.com", "0133030520", "No11 Taman Kuching", "Location A", new Date(2, 5, 2021));
        Donee donee7 = new Donee("DE007", "FAMILY", "QUAN FAMILY", "quan@gmail.com", "0143030520", "No2 Jalan Kuching", "Location A", new Date(2, 6, 2021));
        Donee donee8 = new Donee("DE008", "FAMILY", "JIAN FAMILY", "jian@gmail.com", "0155030510", "No32 Sungai Besar Jalan Tepi", "Location A", new Date(8, 2, 2022));
        Donee donee9 = new Donee("DE009", "ORGANISATION", "SUNWAY FOOD BANK", "sunway@gmail.com", "0125558888", "PJ Jalan Sunway", "Location B", new Date(3, 3, 2022));
        Donee donee10 = new Donee("DE010", "FAMILY", "KIKI FAMILY", "kiki@gmail.com", "0125130512", "No81 Bagan Pasir", "Location C", new Date(14, 9, 2022));
        Donee donee11 = new Donee("DE011", "INDIVIDUAL", "QIAN ER", "qianer@gmail.com", "012523251", "No11 Bagan Pasir", "Location A", new Date(10, 11, 2022));
        Donee donee12 = new Donee("DE012", "INDIVIDUAL", "DEBBIE", "debbie@gmail.com", "0163030520", "No30 Bagan Pasir", "Location A", new Date(2, 12, 2021));
        Donee donee13 = new Donee("DE013", "ORGANISATION", "TAYLOR FOOD BANK", "taylor@gmail.com", "0123032520", "KL Jalan Taylor", "Location A", new Date(13, 4, 2021));
        Donee donee14 = new Donee("DE014", "ORGANISATION", "SEGI FOOD BANK", "segi@gmail.com", "0123012520", "BLOCK A PV16 09-12", "Location A", new Date(8, 4, 2021));
        Donee donee15 = new Donee("DE015", "INDIVIDUAL", "BAODING", "baoding@gmail.com", "0125030534", "PV18 BLOCK B 0813", "Location A", new Date(11, 5, 2022));
        Donee donee16 = new Donee("DE016", "ORGANISATION", "VTAR FOOD BANK", "vtar@gmail.com", "0125458888", "JALAN TUNKU ABDUL RAHMAN", "Location B", new Date(2, 6, 2022));
        Donee donee17 = new Donee("DE017", "FAMILY", "ABU FAMILY", "abu@gmail.com", "0125030345", "No89 Taman Gila", "Location C", new Date(11, 2, 2020));
        Donee donee18 = new Donee("DE018", "INDIVIDUAL", "ANG XIAO", "ang@gmail.com", "017523251", "No20 Taman Gembira", "Location A", new Date(10, 5, 2021));
        Donee donee19 = new Donee("DE019", "ORGANISATION", "CITC FOOD BANK", "citc@gmail.com", "0143232520", "JALAN TUNKU ABDUL RAHMAN", "Location A", new Date(3, 11, 2021));
        Donee donee20 = new Donee("DE020", "ORGANISATION", "UTAR FOOD BANK", "utar@gmail.com", "0163240520", "SUNGAI BULOH UTAR", "Location A", new Date(1, 1, 2021));
        
        Request request1 = new Request(new Date(10, 2, 2023), "Food and Beverage");
        Request request2 = new Request(new Date(11, 3, 2022), "Clothing and Apparel");
        Request request3 = new Request(new Date(12, 3, 2021), "Household Items");
        Request request4 = new Request(new Date(15, 4, 2024), "Food and Beverage");
        Request request5 = new Request(new Date(20, 7, 2024), "Medical");
        Request request6 = new Request(new Date(8, 5, 2023), "Food and Beverage");
        Request request7 = new Request(new Date(5, 5, 2022), "Monetary");
        Request request8 = new Request(new Date(2, 6, 2021), "Educational Materials");
        Request request9 = new Request(new Date(5, 1, 2022), "Electronic");
        Request request10 = new Request(new Date(2, 1, 2021), "Monetary");
        
        Item item1 = new Item("I001", "Monetary", "Cash", 2000);
        Item item2 = new Item("I002", "Household Items", "Bed", 5, 120);
        Item item3 = new Item("I003", "Electronic", "Accessories", 24, 20);
        Item item4 = new Item("I004", "Food and Beverage", "Water Bottle", 60, 1.20, new Date(10, 8, 2024));
        Item item5 = new Item("I005", "Clothing and Apparel", "Clothes", 40, 30);
        Item item6 = new Item("I006", "Clothing and Apparel", "Gloves", 20, 15);
        Item item7 = new Item("I007", "Monetary", "Online Transfers", 750);
        Item item8 = new Item("I008", "Electronic", "Laptop", 4, 2500);
        Item item9 = new Item("I009", "Educational Materials", "Stationary", 50, 2.40);
        Item item10 = new Item("I010", "Monetary", "QR Code Payment", 120);
        Item item11 = new Item("I011", "Medical", "Wheelchair", 10, 180);
        Item item12 = new Item("I012", "Clothing and Apparel", "Shoes", 0, 50);
        Item item13 = new Item("I013", "Food and Beverage", "White Rice", 30, 1.00, new Date(30, 9, 2024));
        Item item14 = new Item("I014", "Food and Beverage", "Packaged Snacks", 4, 9, new Date(12, 10, 2024));
        Item item15 = new Item("I015", "Household Items", "Table", 10, 30);
        Item item16 = new Item("I016", "Educational Materials", "Textbook", 10, 5);
        Item item17 = new Item("I017", "Electronic", "Mobile Phone", 7, 800);
        Item item18 = new Item("I018", "Medical", "First and Kit", 5, 50);
        Item item19 = new Item("I019", "Monetary", "Cash", 10000);
        Item item20 = new Item("I020", "Monetary", "QR Code Payment", 750);
        Item item21 = new Item("I021", "Household Items", "Chair", 30, 15);
        Item item22 = new Item("I022", "Electronic", "Laptop", 5, 1500);
        Item item23 = new Item("I023", "Clothing and Apparel", "Clothes", 50, 50);
        Item item24 = new Item("I024", "Food and Beverage", "Water Bottle", 200, 1.20, new Date(5, 1, 2025));
        Item item25 = new Item("I025", "Medical", "Wheelchair", 8, 160);
        Item item26 = new Item("I026", "Clothing and Apparel", "Clothes", 30, 80);
        Item item27 = new Item("I027", "Food and Beverage", "Nasi Lemak", 100, 3, new Date(3, 9, 2024));
        Item item28 = new Item("I028", "Monetary", "Online Transfers", 2000);
        Item item29 = new Item("I029", "Educational Materials", "Stationary", 20, 2.40);
        Item item30 = new Item("I030", "Household Items", "Table", 20, 30);
        Item item31 = new Item("I031", "Household Items", "Bed", 6, 250);
        Item item32 = new Item("I032", "Medical", "First and Kit", 30, 60);
        Item item33 = new Item("I033", "Monetary", "Online Transfers", 5000);
        Item item34 = new Item("I034", "Monetary", "Cash", 1500);
        Item item35 = new Item("I035", "Clothing and Apparel", "Gloves", 30, 23);
        Item item36 = new Item("I036", "Medical", "First and Kit", 15, 80);
        Item item37 = new Item("I037", "Food and Beverage", "Water Bottle", 20, 1.20, new Date(10, 8, 2024));
        Item item38 = new Item("I038", "Electronic", "Ipad", 20, 2000);
        Item item39 = new Item("I039", "Household Items", "Chair", 40, 12);
        Item item40 = new Item("I040", "Educational Materials", "Textbook", 50, 2.60);
        Item item41 = new Item("I041", "Educational Materials", "Storybook", 20, 25);
        Item item42 = new Item("I042", "Household Items", "Cabinet", 4, 170);
        Item item43 = new Item("I043", "Electronic", "Mobile Phone", 3, 4000);
        Item item44 = new Item("I044", "Medical", "Wheelchair", 13, 200);
        Item item45 = new Item("I045", "Educational Materials", "Textbook", 30, 1.50);
        Item item46 = new Item("I046", "Medical", "Wheelchair", 30, 300);
        Item item47 = new Item("I047", "Electronic", "Ipad", 6, 1300);
        Item item48 = new Item("I048", "Monetary", "Cash", 800);
        Item item49 = new Item("I049", "Food and Beverage", "Packaged Snacks", 20, 5, new Date(28, 8, 2024));
        Item item50 = new Item("I050", "Electronic", "Accessories", 30, 100);
        Item item51 = new Item("I051", "Food and Beverage", "Water Bottle", 50, 1.20, new Date(7, 1, 2025));
        Item item52 = new Item("I052", "Monetary", "Online Transfers", 3000);
        Item item53 = new Item("I053", "Household Items", "Table", 20, 20);
        Item item54 = new Item("I054", "Electronic", "Laptop", 10, 900);
        Item item55 = new Item("I055", "Monetary", "Online Transfers", 2000);
        Item item56 = new Item("I056", "Educational Materials", "Stationary", 20, 4);
        Item item57 = new Item("I057", "Food and Beverage", "Water Bottle", 30, 1.40, new Date(30, 11, 2024));
        Item item58 = new Item("I058", "Clothing and Apparel", "Clothes", 20, 40);
        Item item59 = new Item("I059", "Clothing and Apparel", "Shoes", 25, 120);
        Item item60 = new Item("I060", "Monetary", "QR Code Payment", 7500);
        Item item61 = new Item("I061", "Medical", "First and Kit", 4, 40);
        Item item62 = new Item("I062", "Clothing and Apparel", "Shoes", 100, 35);
        Item item63 = new Item("I063", "Monetary", "Cash", 2000);
        Item item64 = new Item("I064", "Food and Beverage", "Water Bottle", 70, 1.20, new Date(10, 9, 2024));
        Item item65 = new Item("I065", "Food and Beverage", "Nasi Lemak", 20, 2.20, new Date(5, 10, 2024));
        Item item66 = new Item("I066", "Electronic", "Mobile Phone", 5, 700);
        
        Donation donation1 = new Donation("D001", new Date(17, 4, 2024), "Processing", donor1);
        Donation donation2 = new Donation("D002", new Date(20, 4, 2024), "Processing", donor2);
        Donation donation3 = new Donation("D003", new Date(4, 5, 2024), "Fully Distributed", donor3);
        Donation donation4 = new Donation("D004", new Date(4, 5, 2024), "Processing", donor5);
        Donation donation5 = new Donation("D005", new Date(10, 6, 2024), "Processing", donor7);
        Donation donation6 = new Donation("D006", new Date(17, 6, 2024), "Processing", donor7);
        Donation donation7 = new Donation("D007", new Date(24, 6, 2024), "Processing", donor8);
        Donation donation8 = new Donation("D008", new Date(24, 6, 2024), "Processing", donor10);
        Donation donation9 = new Donation("D009", new Date(30, 6, 2024), "Processing", donor12);
        Donation donation10 = new Donation("D010", new Date(30, 6, 2024), "Processing", donor14);
        Donation donation11 = new Donation("D011", new Date(6, 7, 2024), "Processing", donor14);
        Donation donation12 = new Donation("D012", new Date(23, 7, 2024), "Processing", donor15);
        Donation donation13 = new Donation("D013", new Date(29, 7, 2024), "Processing", donor15);
        Donation donation14 = new Donation("D014", new Date(3, 8, 2024), "Processing", donor17);
        Donation donation15 = new Donation("D015", new Date(10, 8, 2024), "Processing", donor17);
        Donation donation16 = new Donation("D016", new Date(11, 8, 2024), "Processing", donor18);
        Donation donation17 = new Donation("D017", new Date(13, 8, 2024), "Processing", donor20);
        Donation donation18 = new Donation("D018", new Date(13, 8, 2024), "Processing", donor20);
        Donation donation19 = new Donation("D019", new Date(17, 8, 2024), "Processing", donor1);
        Donation donation20 = new Donation("D020", new Date(30, 8, 2024), "Processing", donor1);
        Donation donation21 = new Donation("D021", new Date(31, 8, 2024), "Pending", donor21);
        Donation donation22 = new Donation("D022", new Date(3, 9, 2024), "Pending", donor30);
        Donation donation23 = new Donation("D023", new Date(4, 9, 2024), "Pending", donor25);
        Donation donation24 = new Donation("D024", new Date(4, 9, 2024), "Pending", donor26);
        Donation donation25 = new Donation("D025", new Date(4, 9, 2024), "Pending", donor27);
        
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
        donation6.assignItems(item19);
        donation7.assignItems(item20);
        donation7.assignItems(item21);
        donation8.assignItems(item22);
        donation8.assignItems(item23);
        donation8.assignItems(item24);
        donation9.assignItems(item25);
        donation9.assignItems(item26);
        donation9.assignItems(item27);
        donation9.assignItems(item28);
        donation9.assignItems(item29);
        donation10.assignItems(item30);
        donation10.assignItems(item31);
        donation10.assignItems(item32);
        donation11.assignItems(item33);
        donation12.assignItems(item34);
        donation12.assignItems(item35);
        donation12.assignItems(item36);
        donation12.assignItems(item37);
        donation13.assignItems(item38);
        donation13.assignItems(item39);
        donation13.assignItems(item40);
        donation14.assignItems(item41);
        donation14.assignItems(item42);
        donation14.assignItems(item43);
        donation15.assignItems(item44);
        donation15.assignItems(item45);
        donation15.assignItems(item46);
        donation16.assignItems(item47);
        donation16.assignItems(item48);
        donation17.assignItems(item49);
        donation17.assignItems(item50);
        donation18.assignItems(item51);
        donation19.assignItems(item52);
        donation20.assignItems(item53);
        donation20.assignItems(item54);
        donation21.assignItems(item55);
        donation21.assignItems(item56);
        donation22.assignItems(item57);
        donation23.assignItems(item58);
        donation23.assignItems(item59);
        donation23.assignItems(item60);
        donation23.assignItems(item61);
        donation24.assignItems(item62);
        donation24.assignItems(item63);
        donation25.assignItems(item64);
        donation25.assignItems(item65);
        donation25.assignItems(item66);
        
        Distribution distribution1 = new Distribution("DIST001", new Date(1, 1, 2023));
        Distribution distribution2 = new Distribution("DIST002", new Date(3, 1, 2023));
        Distribution distribution3 = new Distribution("DIST003", new Date(12, 2, 2023));
        Distribution distribution4 = new Distribution("DIST004", new Date(25, 3, 2023));
        Distribution distribution5 = new Distribution("DIST005", new Date(1, 4, 2023));
        Distribution distribution6 = new Distribution("DIST006", new Date(1, 9, 2023));
        Distribution distribution8 = new Distribution("DIST008", new Date(1, 11, 2023));
        Distribution distribution9 = new Distribution("DIST009", new Date(18, 12, 2023));
        Distribution distribution10 = new Distribution("DIST010", new Date(11, 1, 2024));
        Distribution distribution11 = new Distribution("DIST011", new Date(25, 2, 2024));
        Distribution distribution12 = new Distribution("DIST012", new Date(12, 3, 2024));
        Distribution distribution13 = new Distribution("DIST013", new Date(11, 4, 2024));
        Distribution distribution14 = new Distribution("DIST014", new Date(17, 5, 2024));
        Distribution distribution15 = new Distribution("DIST015", new Date(17, 5, 2024));
        Distribution distribution16 = new Distribution("DIST016", new Date(25, 6, 2024));
        Distribution distribution18 = new Distribution("DIST018", new Date(1, 7, 2024));
        Distribution distribution19 = new Distribution("DIST019", new Date(1, 7, 2024));
        Distribution distribution20 = new Distribution("DIST020", new Date(21, 7, 2024));
        
        Distribution distribution22 = new Distribution("DIST022", new Date(25, 7, 2024));
        Distribution distribution23 = new Distribution("DIST023", new Date(13, 8, 2024));
        Distribution distribution24 = new Distribution("DIST024", new Date(1, 8, 2024));
        Distribution distribution25 = new Distribution("DIST025", new Date(11, 8, 2024));
        Distribution distribution26 = new Distribution("DIST026", new Date(12, 8, 2024));
        Distribution distribution27 = new Distribution("DIST027", new Date(12, 8, 2024));
        
        Distribution distribution28 = new Distribution("DIST028", new Date(13, 8, 2024));
        Distribution distribution29 = new Distribution("DIST029", new Date(1, 8, 2024));
        
        SortedDoublyLinkedListSet<Donee> doneeList1 = new SortedDoublyLinkedListSet<>();
        doneeList1.add(donee1);
        doneeList1.add(donee4);
        SortedDoublyLinkedListSet<Donee> doneeList2 = new SortedDoublyLinkedListSet<>();
        doneeList2.add(donee10);
        doneeList2.add(donee17);
        SortedDoublyLinkedListSet<Donee> doneeList3 = new SortedDoublyLinkedListSet<>();
        doneeList3.add(donee18);
        doneeList3.add(donee20);
        SortedDoublyLinkedListSet<Donee> doneeList4 = new SortedDoublyLinkedListSet<>();
        doneeList4.add(donee9);
        doneeList4.add(donee16);
        Distribution distribution7 = new Distribution("DIST007", new Date(21, 9, 2023), doneeList1);
        Distribution distribution17 = new Distribution("DIST017", new Date(30, 6, 2024), doneeList2);
        Distribution distribution21 = new Distribution("DIST021", new Date(22, 7, 2024), doneeList3);
        Distribution distribution30 = new Distribution("DIST030", new Date(2, 9, 2024), doneeList4);

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
        
        distribution8.addDonee(donee9);
        distribution8.addSelectedItem(new SelectedItem("I012", 1));
        distribution8.addSelectedItem(new SelectedItem("I034", 150.00));
        distribution8.addSelectedItem(new SelectedItem("I017", 8));
        
        distribution9.addDonee(donee10);
        distribution9.addSelectedItem(new SelectedItem("I013", 2));
        distribution9.addSelectedItem(new SelectedItem("I022", 1));
        distribution9.addSelectedItem(new SelectedItem("I023", 2));
        
        distribution10.addDonee(donee11);
        distribution10.addSelectedItem(new SelectedItem("I014", 5));
        distribution10.addSelectedItem(new SelectedItem("I016", 10));
        distribution10.addSelectedItem(new SelectedItem("I023", 3));
        
        distribution11.addDonee(donee12);
        distribution11.addSelectedItem(new SelectedItem("I016", 1));
        distribution11.addSelectedItem(new SelectedItem("I027", 2));
        
        distribution12.addDonee(donee13);
        distribution12.addSelectedItem(new SelectedItem("I016", 10));
        distribution12.addSelectedItem(new SelectedItem("I023", 2));
        distribution13.addDonee(donee14);
        distribution13.addSelectedItem(new SelectedItem("I017", 4));
        distribution13.addSelectedItem(new SelectedItem("I048", 400.00));
        
        distribution14.addDonee(donee18);
        distribution14.addSelectedItem(new SelectedItem("I018", 2));
        
        distribution15.addDonee(donee1);
        distribution15.addSelectedItem(new SelectedItem("I019", 200.00));
        distribution15.addSelectedItem(new SelectedItem("I022", 5));
        
        distribution16.addDonee(donee19);
        distribution16.addSelectedItem(new SelectedItem("I020", 350.00));
        distribution18.addDonee(donee20);
        distribution18.addSelectedItem(new SelectedItem("I021", 3));
        distribution18.addSelectedItem(new SelectedItem("I034", 3000.00));
        
        distribution19.addDonee(donee13);
        distribution19.addSelectedItem(new SelectedItem("I022", 2));
        distribution20.addDonee(donee11);
        distribution20.addSelectedItem(new SelectedItem("I023", 10));
        distribution20.addSelectedItem(new SelectedItem("I033", 100.00));
        
        distribution21.addDonee(donee12);
        distribution21.addSelectedItem(new SelectedItem("I024", 8));
        distribution21.addSelectedItem(new SelectedItem("I033", 80.00));
        distribution21.addSelectedItem(new SelectedItem("I035", 10));
        distribution21.addSelectedItem(new SelectedItem("I040", 10));
        
        distribution22.addDonee(donee13);
        distribution22.addSelectedItem(new SelectedItem("I025", 10));
        distribution22.addSelectedItem(new SelectedItem("I026", 2));
        
        distribution23.addDonee(donee14);
        distribution23.addSelectedItem(new SelectedItem("I026", 3));
        distribution24.addDonee(donee9);
        distribution24.addSelectedItem(new SelectedItem("I027", 2));
        distribution25.addDonee(donee8);
        distribution25.addSelectedItem(new SelectedItem("I028", 50.00));
        
        distribution26.addDonee(donee7);
        distribution26.addSelectedItem(new SelectedItem("I029", 50));
        distribution27.addDonee(donee6);
        distribution27.addSelectedItem(new SelectedItem("I030", 50));
        distribution28.addDonee(donee10);
        distribution28.addSelectedItem(new SelectedItem("I031", 20));
        distribution29.addDonee(donee11);
        distribution29.addSelectedItem(new SelectedItem("I033", 300.00));
        
        distribution7.addSelectedItem(selectedItem6);
        distribution17.addSelectedItem(new SelectedItem("I017", 1));
        distribution17.addSelectedItem(new SelectedItem("I019", 1000.00));
        
        distribution21.addSelectedItem(selectedItem6);
        distribution21.addSelectedItem(new SelectedItem("I007", 500.00));
        distribution30.addSelectedItem(new SelectedItem("I010", 100.00));
        
        distributions.add(distribution1);
        distributions.add(distribution2);
        distributions.add(distribution3);
        distributions.add(distribution4);
        distributions.add(distribution5);
        distributions.add(distribution6);
        distributions.add(distribution7);
        distributions.add(distribution8);
        distributions.add(distribution9);
        distributions.add(distribution10);
        distributions.add(distribution11);
        distributions.add(distribution12);
        distributions.add(distribution13);
        distributions.add(distribution14);
        distributions.add(distribution15);
        distributions.add(distribution16);
        distributions.add(distribution17);
        distributions.add(distribution18);
        distributions.add(distribution19);
        distributions.add(distribution20);
        distributions.add(distribution21);
        distributions.add(distribution22);
        distributions.add(distribution23);
        distributions.add(distribution24);
        distributions.add(distribution25);
        distributions.add(distribution26);
        distributions.add(distribution27);
        distributions.add(distribution28);
        distributions.add(distribution29);
        distributions.add(distribution30);
        
        donors.add(donor1);
        donors.add(donor2);
        donors.add(donor3);
        donors.add(donor4);
        donors.add(donor5);
        donors.add(donor6);
        donors.add(donor7);
        donors.add(donor8);
        donors.add(donor9);
        donors.add(donor10);
        donors.add(donor11);
        donors.add(donor12);
        donors.add(donor13);
        donors.add(donor14);
        donors.add(donor15);
        donors.add(donor16);
        donors.add(donor17);
        donors.add(donor18);
        donors.add(donor19);
        donors.add(donor20);
        donors.add(donor21);
        donors.add(donor22);
        donors.add(donor23);
        donors.add(donor24);
        donors.add(donor25);
        donors.add(donor26);
        donors.add(donor27);
        donors.add(donor28);
        donors.add(donor29);
        donors.add(donor30);
        
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
        items.add(item19);
        items.add(item20);
        items.add(item21);
        items.add(item22);
        items.add(item23);
        items.add(item24);
        items.add(item25);
        items.add(item26);
        items.add(item27);
        items.add(item28);
        items.add(item29);
        items.add(item30);
        items.add(item31);
        items.add(item32);
        items.add(item33);
        items.add(item34);
        items.add(item35);
        items.add(item36);
        items.add(item37);
        items.add(item38);
        items.add(item39);
        items.add(item40);
        items.add(item41);
        items.add(item42);
        items.add(item43);
        items.add(item44);
        items.add(item45);
        items.add(item46);
        items.add(item47);
        items.add(item48);
        items.add(item49);
        items.add(item50);
        items.add(item51);
        items.add(item52);
        items.add(item53);
        items.add(item54);
        items.add(item55);
        items.add(item56);
        items.add(item57);
        items.add(item58);
        items.add(item59);
        items.add(item60);
        items.add(item61);
        items.add(item62);
        items.add(item63);
        items.add(item64);
        items.add(item65);
        items.add(item66);
        
        donations.add(donation1);
        donations.add(donation2);
        donations.add(donation3);
        donations.add(donation4);
        donations.add(donation5);
        donations.add(donation6);
        donations.add(donation7);
        donations.add(donation8);
        donations.add(donation9);
        donations.add(donation10);
        donations.add(donation11);
        donations.add(donation12);
        donations.add(donation13);
        donations.add(donation14);
        donations.add(donation15);
        donations.add(donation16);
        donations.add(donation17);
        donations.add(donation18);
        donations.add(donation19);
        donations.add(donation20);
        donations.add(donation21);
        donations.add(donation22);
        donations.add(donation23);
        donations.add(donation24);
        donations.add(donation25);
        
        donee1.addRequest(request1);
        donee1.addRequest(request2);
        donee2.addRequest(request2);
        donee2.addRequest(request4);
        donee2.addRequest(request7);
        donee3.addRequest(request3);
        donee4.addRequest(request4);
        donee5.addRequest(request5);
        donee6.addRequest(request1);
        donee7.addRequest(request2);
        donee7.addRequest(request9);
        donee8.addRequest(request2);
        donee9.addRequest(request4);
        donee10.addRequest(request7);
        donee11.addRequest(request3);
        donee12.addRequest(request4);
        donee12.addRequest(request5);
        donee12.addRequest(request1);
        donee13.addRequest(request2);
        donee15.addRequest(request2);
        donee15.addRequest(request4);
        donee16.addRequest(request7);
        donee16.addRequest(request3);
        donee17.addRequest(request4);
        donee18.addRequest(request5);
        donee19.addRequest(request9);
        donee20.addRequest(request10);
        donee19.addRequest(request8);
        donee20.addRequest(request6);
        
        donees.add(donee1);
        donees.add(donee2);
        donees.add(donee3);
        donees.add(donee4);
        donees.add(donee5);
        donees.add(donee6);
        donees.add(donee7);
        donees.add(donee8);
        donees.add(donee9);
        donees.add(donee10);
        donees.add(donee11);
        donees.add(donee12);
        donees.add(donee13);
        donees.add(donee14);
        donees.add(donee15);
        donees.add(donee16);
        donees.add(donee17);
        donees.add(donee18);
        donees.add(donee19);
        donees.add(donee20);

//        donor1.addDonationToList(donation1.clone());
//        donor2.addDonationToList(donation2.clone());
//        donor3.addDonationToList(donation3.clone());
//        donor2.addDonationToList(donation4.clone());
//        donor3.addDonationToList(donation5.clone());
//        donor2.addDonationToList(donation6.clone());
        Iterator<Donation> donationIterator = donations.getIterator();
        while (donationIterator.hasNext()) {
            Donation donation = donationIterator.next();
            Donation clonedDonation = donation.clone();
            donationsHistory.add(clonedDonation);
            
            Donor donor = clonedDonation.getDonor(); // Get the donor associated with the donation

            // Find the corresponding donor in the donors list and set their DonationList
            Iterator<Donor> donorIterator = donors.getIterator();
            while (donorIterator.hasNext()) {
                Donor d = donorIterator.next();
                if (d.equals(donor)) {
                    d.addDonationToList(clonedDonation); // Add the cloned donation to the donor's donation list
                    break;
                }
            }
        }
        
        Iterator<Distribution> i1 = distributions.getIterator();
        do {
            Distribution distribution = i1.next();
            Iterator<SelectedItem> i2 = distribution.getDistributedItemList().getIterator();
            distributedItemList.merge(distribution.getDistributedItemList());
            do {
                SelectedItem selectedItem = i2.next();
                Item item = CommonUse.findItem(selectedItem.getItemId(), items);
                Donation donation = CommonUse.findDonationByItem(item, donations);
                donation.setStatus("Distributing");
            } while (i2.hasNext());
            
        } while (i1.hasNext());
    }
}
