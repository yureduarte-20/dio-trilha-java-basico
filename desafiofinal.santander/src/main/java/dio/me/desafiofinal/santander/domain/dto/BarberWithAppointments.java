package dio.me.desafiofinal.santander.domain.dto;

import dio.me.desafiofinal.santander.domain.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

public class BarberWithAppointments {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private long appointments;

    public BarberWithAppointments(Long id, String name, String phone, String email, long appointments) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.appointments = appointments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public long getAppointments() {
        return appointments;
    }

    public void setAppointments(long appointments) {
        this.appointments = appointments;
    }
}
