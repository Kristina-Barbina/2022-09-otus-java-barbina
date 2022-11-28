package homework;


import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private TreeMap<Customer, String> map = new TreeMap<>(new CustomerScoreComparator());
    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        var entry = map.firstEntry();
        if (entry == null)
            return null;
        return new Entity(entry);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var entry = map.higherEntry(customer);
        if (entry == null)
            return null;
        return new Entity(entry);
    }

    public void add(Customer customer, String data) {
        this.map.put(customer, data);
    }

    public final class Entity implements Map.Entry<Customer, String>{

        Customer key;
        String value;

        private Entity(Map.Entry<Customer, String> entry){
            if (entry == null)
                return;

            key = entry.getKey();
            value = entry.getValue();
        }

        @Override
        public Customer getKey() {
            return Customer.Clone(key);
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String setValue(String value) {
            this.value = value;
            return this.value;
        }
    }
}
