package kr.co.chunjae.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 도서 주문 처리 정보를 담는 도메인 객체
 * */

@SuppressWarnings("serial")
@Getter
@Setter
public class Address implements Serializable {

    // 필드 선언
    private String detailName;  // 상세 주소
    private String addressName;  // 주소
    private String country;  // 국가명
    private String zipCode;  // 우편번호

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Address other = (Address) obj;

        if (addressName == null) {

            if (other.addressName != null)
                return false;

        } else if (!addressName.equals(other.addressName)) {
            return false;
        }

        if (country == null) {

            if (other.country != null)
                return false;

        } else if (!country.equals(other.country)) {
            return false;
        }

        if (detailName == null) {

            if (other.detailName != null)
                return false;

        } else if (!detailName.equals(other.detailName)) {
            return false;
        }

        if (zipCode == null) {

            if (other.zipCode != null)
                return false;

        } else if (!zipCode.equals(other.zipCode)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + ((addressName == null) ? 0 : addressName.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((detailName == null) ? 0 : detailName.hashCode());
        result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());

        return result;
    }
}
