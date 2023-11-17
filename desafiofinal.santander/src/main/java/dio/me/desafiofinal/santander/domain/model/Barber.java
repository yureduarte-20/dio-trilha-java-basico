package dio.me.desafiofinal.santander.domain.model;

import dio.me.desafiofinal.santander.domain.dto.BarberWithAppointments;
import jakarta.persistence.*;

import java.util.List;

@NamedNativeQuery(
        name="Barber.findBarberWithAppointmentsCount",
        query = "SELECT barber.id AS id, barber.email AS email, barber.phone AS phone, barber.name AS name, " +
                " COUNT(app.id) AS appointments " +
                "FROM BARBERS AS barber " +
                "LEFT JOIN APPOINTMENTS as app ON app.barber_id = barber.id " +
                " GROUP BY barber.id",
        resultSetMapping ="Mapping.BarberWithAppointments")
@SqlResultSetMapping(name="Mapping.BarberWithAppointments",
        classes = @ConstructorResult(targetClass = BarberWithAppointments.class,
        columns = {
                @ColumnResult(name = "id"),
                @ColumnResult(name = "name"),
                @ColumnResult(name = "phone"),
                @ColumnResult(name = "email"),
                @ColumnResult(name = "appointments")}))
@Entity(name = "barbers")
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column()
    private String phone;
    @Column( nullable = false, unique = true )
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Address> addresses;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public List<Appointment> getAppointments() {
        return appointments;
    }
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return String.format("{ name: %s, phone: %s, email: %s,  appointments: %s}",
                getName(), getPhone(), getEmail(), getAppointments());
    }
}
