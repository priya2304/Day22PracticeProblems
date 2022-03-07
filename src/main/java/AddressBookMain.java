import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AddressBookMain {
    private List<AddressBookMain> contact = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;


    /*default constructor to print welcome message*/

    public AddressBookMain() {
        System.out.println("Welcome to address book program");
    }

    public AddressBookMain(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }



    /*method to check if contact is already present in addressbook*/

    public Boolean duplicateCheck(String name) {
        for (int j = 0; j < contact.size(); j++) {
            AddressBookMain object = contact.get(j);
            if (object.firstName.equals(name)) {
                System.out.println("Contact already exists!!Please enter a different contact name");
                return true;
            }
        }
        return false;
    }

    /*
     * method to search a particular contact based on city or state
     */
    public void search(String place) {
        for (int j = 0; j < contact.size(); j++) {
            AddressBookMain object = contact.get(j);
            if (object.city.equals(place) || object.state.equals(place)) {
                System.out.println(object.firstName + " " + object.lastName);
            }
        }

    }

    /*
     * method to view a particular contact based on state
     */
    public void viewPersonByState() {
        Map<String, List<String>> stateMap = new HashMap<>();
        for (int j = 0; j < contact.size(); j++) {
            AddressBookMain object = contact.get(j);
            if (stateMap.containsKey(object.state)) {
                List<String> temp = stateMap.get(object.state);
                temp.add(object.firstName);
                stateMap.put(object.state, temp);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(object.firstName);
                stateMap.put(object.state, temp);
            }
        }
        for (Map.Entry m : stateMap.entrySet()) {

            System.out.println(m.getKey() + " : " + m.getValue());
            System.out.println("There are " + ((List<String>) m.getValue()).size() + " persons in state " + m.getKey());
        }
    }

    /*
     * method to view a particular contact based on city
     */
    @SuppressWarnings("unchecked")
    public void viewPersonByCity() {
        Map<String, List<String>> cityMap = new HashMap<>();
        for (int j = 0; j < contact.size(); j++) {
            AddressBookMain object = contact.get(j);
            if (cityMap.containsKey(object.city)) {
                List<String> temp = cityMap.get(object.city);
                temp.add(object.firstName);
                cityMap.put(object.city, temp);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(object.firstName);
                cityMap.put(object.city, temp);
            }
        }
        for (Map.Entry m : cityMap.entrySet()) {

            System.out.println(m.getKey() + " : " + m.getValue());
            System.out.println("There are " + ((List<String>) m.getValue()).size() + " persons in city " + m.getKey());

        }
    }
    /*
     * method to sort the list based on name or city or state or zip
     */

    public void sortPersonByNameCityStateZip(int option) {

        Map<String, List<AddressBookMain>> map = new HashMap<>();
        if (option == 1) {
            for (int j = 0; j < contact.size(); j++) {
                AddressBookMain object = contact.get(j);
                if (map.containsKey(object.firstName)) {
                    List<AddressBookMain> temp = map.get(object.firstName);
                    temp.add(object);
                    map.put(object.firstName, temp);
                } else {
                    List<AddressBookMain> temp = new ArrayList<>();
                    temp.add(object);
                    map.put(object.firstName, temp);
                }
            }
        } else if (option == 2) {
            for (int j = 0; j < contact.size(); j++) {
                AddressBookMain object = contact.get(j);
                if (map.containsKey(object.city)) {
                    List<AddressBookMain> temp = map.get(object.city);
                    temp.add(object);
                    map.put(object.city, temp);
                } else {
                    List<AddressBookMain> temp = new ArrayList<>();
                    temp.add(object);
                    map.put(object.city, temp);
                }
            }
        } else if (option == 3) {
            for (int j = 0; j < contact.size(); j++) {
                AddressBookMain object = contact.get(j);
                if (map.containsKey(object.state)) {
                    List<AddressBookMain> temp = map.get(object.state);
                    temp.add(object);
                    map.put(object.state, temp);
                } else {
                    List<AddressBookMain> temp = new ArrayList<>();
                    temp.add(object);
                    map.put(object.state, temp);
                }
            }
        } else if (option == 4) {
            for (int j = 0; j < contact.size(); j++) {
                AddressBookMain object = contact.get(j);
                if (map.containsKey(object.zip)) {
                    List<AddressBookMain> temp = map.get(object.zip);
                    temp.add(object);
                    map.put(object.zip, temp);
                } else {
                    List<AddressBookMain> temp = new ArrayList<>();
                    temp.add(object);
                    map.put(object.zip, temp);
                }
            }
        } else {
            System.out.println("choose correct option");
        }
        Map<String, List<AddressBookMain>> sortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        for (Map.Entry<String, List<AddressBookMain>> entry : sortedMap.entrySet()) {
            for (AddressBookMain a : entry.getValue()) {
                System.out.println("First Name:" + a.firstName);
                System.out.println("Last Name:" + a.lastName);
                System.out.println("Address:" + a.address);
                System.out.println("City:" + a.city);
                System.out.println("State:" + a.state);
                System.out.println("Zip:" + a.zip);
                System.out.println("Phone number:" + a.phoneNumber);
                System.out.println("E-mail:" + a.email);
                System.out.println("--------------------------------------------");
            }
        }
    }
}
