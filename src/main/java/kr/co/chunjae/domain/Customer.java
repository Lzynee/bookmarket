package kr.co.chunjae.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@SuppressWarnings("serial")
public class Customer implements Serializable {

    private String customerId;  // 고객 ID
    private String name;  // 고객 이름
    private Address address;  // 고객 주소 객체
    private String phone;  // 고객 전화번호

    public Customer() {
        this.address = new Address();
    }

    public Customer(String customerId, String name) {
        this();
        this.customerId = customerId;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Customer other = (Customer) obj;

        if (customerId == null) {

            if (other.customerId != null)
                return false;

        } else if (!customerId.equals(other.customerId)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        return result;
    }
}
