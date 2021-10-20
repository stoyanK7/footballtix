package bg.stoyank.footballtix.order;

import bg.stoyank.footballtix.footballmatch.FootballMatch;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private FootballMatch footballMatch;
    private String fullName;
    private String email;
    private String mobilePhone;
    private String address;
    private String city;
    private String country;
    private String postcode;
}
