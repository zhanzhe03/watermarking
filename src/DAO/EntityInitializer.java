
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ADT.*;
import Entity.*;
import java.util.Iterator;

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
    private SortedListSetInterface<SelectedItem> distributedItem = new SortedDoublyLinkedListSet<>();
    private SortedListSetInterface<Request> request = new SortedDoublyLinkedListSet<>();

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

    public SortedListSetInterface<SelectedItem> getDistributedItem() {
        return distributedItem;
    }

    public void entityEnitialize() {

        Donor donor1 = new Donor("DR001", "Public Organisation", "Chew Zhan Zhe", "Chew Zhan Zhe", "012-6730810", "zhanzhe@gmail.com", "Jalan SS 5/2, Ss 5, 47301 Petaling Jaya, Selangor", new Date(10, 2, 2022), "Prospect");
        Donor donor2 = new Donor("DR002", "Private Organisation","Tee Yi Hang", "Tee Yi Hang", "012-5837395", "yihang@gmail.com", "5, Lorong Masria 6, Taman Bunga Raya, 53000 Kuala Lumpur", new Date(16, 3, 2022), "Inactive");
        Donor donor3 = new Donor("DR003", "Government Organisation", "Sustainable Development and Welfare", "Lim Jun Hong", "012-9123389", "junhong@gmail.com", "Jalan 4d/6, Taman Setapak Indah, 53300 Kuala Lumpur", new Date(21, 5, 2022), "Active");
        
        donors.add(new Donor("DR004", "Individual", "Zhang Wei", "-", "012-6754321", "zhangwei@gmail.com", "Jalan SS 7/3, SS 7, 47301 Petaling Jaya, Selangor", new Date(18, 6, 2022), "Prospect"));
        donors.add(new Donor("DR005", "Private Organisation", "ABC Pvt Ltd", "John Lee", "012-6837281", "johnlee@abc.com", "20, Jalan Tun Razak, 50400 Kuala Lumpur", new Date(25, 6, 2022), "Active"));
        donors.add(new Donor("DR006", "Public Organisation", "XYZ Foundation", "Wong Mei", "012-6472819", "wongmei@xyz.org", "15, Jalan Bukit Bintang, 55100 Kuala Lumpur", new Date(5, 7, 2022), "Inactive"));
        donors.add(new Donor("DR007", "Government Organisation", "Ministry of Health", "Tan Ah Kow", "012-7351827", "tanahkow@moh.gov.my", "Jalan Cempaka, 50490 Kuala Lumpur", new Date(11, 7, 2022), "Active"));
        donors.add(new Donor("DR008", "Individual", "Lee Ai Ling", "-", "012-7428910", "ailing_lee@gmail.com", "12, Jalan Ampang, 50450 Kuala Lumpur", new Date(20, 7, 2022), "Banned"));
        donors.add(new Donor("DR009", "Private Organisation", "MNO Corporation", "Ong Siew Mun", "012-8129837", "ongsm@mno.com", "18, Jalan Raja Chulan, 50200 Kuala Lumpur", new Date(28, 7, 2022), "Active"));
        donors.add(new Donor("DR010", "Public Organisation", "DEF Trust", "Lim Swee Ling", "012-8734590", "limsweeling@deftrust.org", "Jalan Tun HS Lee, 50000 Kuala Lumpur", new Date(3, 8, 2022), "Prospect"));
        donors.add(new Donor("DR011", "Government Organisation", "Department of Education", "Chong Kar Ling", "012-8491023", "karling.chong@education.gov.my", "Jalan Bukit Jalil, 57000 Kuala Lumpur", new Date(10, 8, 2022), "Inactive"));
        donors.add(new Donor("DR012", "Individual", "Chen Wei", "-", "012-7821049", "chenwei@gmail.com", "Jalan Damansara, 60000 Kuala Lumpur", new Date(15, 8, 2022), "Active"));
        donors.add(new Donor("DR013", "Private Organisation", "GHI Sdn Bhd", "Cheong Siew Fong", "012-6745120", "cheongsf@ghi.com", "Jalan Sultan Ismail, 50250 Kuala Lumpur", new Date(21, 8, 2022), "Banned"));
        donors.add(new Donor("DR014", "Public Organisation", "UVW Charities", "Yap Wei Jian", "012-6845127", "yapweijian@uvw.org", "Jalan Raja Abdullah, 50300 Kuala Lumpur", new Date(29, 8, 2022), "Active"));
        donors.add(new Donor("DR015", "Government Organisation", "National Welfare Department", "Low Kah Meng", "012-7891034", "kahmeng@welfare.gov.my", "Jalan Kuching, 51200 Kuala Lumpur", new Date(5, 9, 2022), "Inactive"));
        donors.add(new Donor("DR016", "Individual", "Ng Ai Lin", "-", "012-6712384", "ngailin@gmail.com", "Jalan Loke Yew, 55200 Kuala Lumpur", new Date(10, 9, 2022), "Prospect"));
        donors.add(new Donor("DR017", "Private Organisation", "JKL Holdings", "Khoo Kai Ling", "012-8749123", "kailing@jkl.com", "Jalan Tun Sambanthan, 50470 Kuala Lumpur", new Date(15, 9, 2022), "Active"));
        donors.add(new Donor("DR018", "Public Organisation", "MNZ Association", "Chia Wei Liang", "012-8941235", "chiaweiliang@mnz.org", "Jalan Raja Laut, 50350 Kuala Lumpur", new Date(22, 9, 2022), "Inactive"));
        donors.add(new Donor("DR019", "Government Organisation", "Ministry of Finance", "Tan Cheng Wee", "012-8120456", "chengwee@mof.gov.my", "Jalan Duta, 50480 Kuala Lumpur", new Date(28, 9, 2022), "Active"));
        donors.add(new Donor("DR020", "Individual", "Soh Wei Ling", "-", "012-6758342", "weiling.soh@gmail.com", "Jalan Pudu, 55100 Kuala Lumpur", new Date(5, 10, 2022), "Banned"));
        donors.add(new Donor("DR021", "Public Organisation", "Tan Mei Ling", "Tan Mei Ling", "012-4567890", "meiling.tan@gmail.com", "11, Jalan Taman Megah, 47301 Petaling Jaya, Selangor", new Date(5, 8, 2022), "Active"));
        donors.add(new Donor("DR022", "Private Organisation", "Khoo Wen Jian", "Khoo Wen Jian", "016-2345678", "wenjian.khoo@gmail.com", "22, Lorong Bukit Anggerik, 56100 Kuala Lumpur", new Date(12, 9, 2022), "Inactive"));
        donors.add(new Donor("DR023", "Government Organisation", "Education Support Fund", "Low Zhi Hao", "013-9876543", "zhihao.low@gov.my", "5, Persiaran Perdana, 62100 Putrajaya", new Date(18, 7, 2022), "Banned"));
        donors.add(new Donor("DR024", "Individual", "Siti Nur Aisyah", "-", "017-8765432", "aisyah.siti@gmail.com", "16, Jalan SS 2/45, 47300 Petaling Jaya, Selangor", new Date(28, 6, 2022), "Active"));
        donors.add(new Donor("DR025", "Public Organisation", "Green Earth Foundation", "Lai Wai Mun", "014-2345678", "waimun.lai@gmail.com", "3, Jalan Damansara, 60000 Kuala Lumpur", new Date(22, 8, 2022), "Prospect"));
        donors.add(new Donor("DR026", "Private Organisation", "Tech Innovators", "Yap Wei Jie", "019-3456789", "weijie.yap@techinnovators.com", "10, Jalan Teknologi, 47810 Petaling Jaya, Selangor", new Date(3, 10, 2022), "Inactive"));
        donors.add(new Donor("DR027", "Government Organisation", "Healthcare Development Fund", "Ng Wei Keat", "012-5436789", "weikeat.ng@gov.my", "7, Jalan Bukit Bintang, 55100 Kuala Lumpur", new Date(1, 9, 2022), "Active"));
        donors.add(new Donor("DR028", "Individual", "Lee Zhi Xuan", "-", "018-2345678", "zhixuan.lee@gmail.com", "12, Jalan Tun Razak, 50400 Kuala Lumpur", new Date(7, 7, 2022), "Banned"));
        donors.add(new Donor("DR029", "Public Organisation", "Wildlife Conservation Society", "Chong Wai Leng", "011-2345678", "waileng.chong@gmail.com", "4, Jalan Ampang, 50450 Kuala Lumpur", new Date(25, 8, 2022), "Active"));
        donors.add(new Donor("DR030", "Private Organisation", "Youth Empowerment Hub", "Chan Mei Yen", "013-7654321", "meiyen.chan@gmail.com", "9, Jalan Bangsar, 59000 Kuala Lumpur", new Date(15, 9, 2022), "Prospect"));


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
        Item item19 = new Item("I019", "Monetary", "Cash", 10000);

        Donation donation1 = new Donation("D001", new Date(22, 7, 2024), "Distributing", donor1);
        Donation donation2 = new Donation("D002", new Date(28, 7, 2024), "Processing", donor2);
        Donation donation3 = new Donation("D003", new Date(3, 8, 2024), "Fully Distributed", donor3);
        Donation donation4 = new Donation("D004", new Date(19, 8, 2024), "Distributing", donor1);
        Donation donation5 = new Donation("D005", new Date(30, 8, 2024), "Pending", donor2);
        Donation donation6 = new Donation("D006", new Date(1, 9, 2024), "Pending", donor1);

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
        Distribution distribution28 = new Distribution("DIST028", new Date(13, 8, 2024));
        Distribution distribution29 = new Distribution("DIST029", new Date(13, 8, 2024));
        Distribution distribution30 = new Distribution("DIST030", new Date(13, 8, 2024));
        Distribution distribution31 = new Distribution("DIST031", new Date(13, 8, 2024));
        Distribution distribution32 = new Distribution("DIST032", new Date(15, 8, 2024));
        Distribution distribution33 = new Distribution("DIST033", new Date(15, 8, 2024));
        Distribution distribution34 = new Distribution("DIST034", new Date(21, 8, 2024));
        Distribution distribution35 = new Distribution("DIST035", new Date(21, 8, 2024));
        Distribution distribution36 = new Distribution("DIST036", new Date(22, 8, 2024));

        Distribution distribution38 = new Distribution("DIST038", new Date(23, 8, 2024));
        Distribution distribution39 = new Distribution("DIST039", new Date(24, 8, 2024));

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
        Distribution distribution7 = new Distribution("DIST007", new Date(1, 8, 2024), doneeList1);
        Distribution distribution17 = new Distribution("DIST017", new Date(11, 8, 2024), doneeList2);
        Distribution distribution21 = new Distribution("DIST027", new Date(12, 8, 2024), doneeList3);
        Distribution distribution37 = new Distribution("DIST037", new Date(23, 8, 2024), doneeList4);

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
        distribution9.addDonee(donee10);
        distribution9.addSelectedItem(new SelectedItem("I013", 2));
        distribution10.addDonee(donee11);
        distribution10.addSelectedItem(new SelectedItem("I012", 5));
        distribution11.addDonee(donee12);
        distribution11.addSelectedItem(new SelectedItem("I013", 3));
        distribution12.addDonee(donee13);
        distribution12.addSelectedItem(new SelectedItem("I014", 10));
        distribution13.addDonee(donee14);
        distribution13.addSelectedItem(new SelectedItem("I014", 4));
        distribution14.addDonee(donee18);
        distribution14.addSelectedItem(new SelectedItem("I015", 2));
        distribution15.addDonee(donee1);
        distribution15.addSelectedItem(new SelectedItem("I013", 2));
        distribution16.addDonee(donee19);
        distribution16.addSelectedItem(new SelectedItem("I016", 3));
        distribution18.addDonee(donee20);
        distribution18.addSelectedItem(new SelectedItem("I017", 1));
        distribution19.addDonee(donee13);
        distribution19.addSelectedItem(new SelectedItem("I011", 1));
        distribution20.addDonee(donee11);
        distribution20.addSelectedItem(new SelectedItem("I014", 1));

        distribution7.addSelectedItem(selectedItem6);
        distribution17.addSelectedItem(new SelectedItem("I019", 1000.00));
        distribution17.addSelectedItem(new SelectedItem("I017", 1));
        distribution17.addSelectedItem(new SelectedItem("I002", 5));
        distribution21.addSelectedItem(selectedItem6);
        distribution21.addSelectedItem(new SelectedItem("I007", 500.00));
        distribution37.addSelectedItem(new SelectedItem("I010", 100.00));

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
        items.add(item19);

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
    }
}
