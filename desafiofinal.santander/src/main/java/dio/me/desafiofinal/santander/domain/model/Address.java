package dio.me.desafiofinal.santander.domain.model;

import jakarta.persistence.*;

@Entity( name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Barber getBarber() {
        return barber;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private Barber barber;
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    private String street;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
