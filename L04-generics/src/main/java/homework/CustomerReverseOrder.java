package homework;


import java.util.Stack;

public class CustomerReverseOrder {

    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    private Stack<Customer> data = new Stack<>();
    public void add(Customer customer) {
        data.add(customer);
    }

    public Customer take() {
        return data.pop();
    }
}
