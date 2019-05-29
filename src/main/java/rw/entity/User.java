package rw.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Chebotar_do on 22.05.2019.
 */

@Entity
@Table(name = "USERS")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", nullable = false)
    private long id;
    @Column(name = "USER_NAME", nullable = false)
    private String name;
    @Column(name = "USER_SURNAME", nullable = false)
    private String surname;
    @Column(name = "USER_PATRONYMIC", nullable = false)
    private String patronymic;
    @Column(name = "USER_PS", nullable = false)
    private String passportSeries;
    @Column(name = "USER_PN", nullable = false)
    private String passportNumber;
    @Column(name = "USER_PID", nullable = false)
    private LocalDate passportIssueDate;
    @Column(name = "USER_PIBY", nullable = false)
    private String passportIssueBy;
    @Column(name = "USER_EMAIL", nullable = false)
    private String email;
    @Column(name = "USER_LOGIN", nullable = false)
    private String login;
    @Column(name = "USER_PASSWORD", nullable = false)
    private String password;
    @Column(name = "USER_ISACTIVE", nullable = false)
    private boolean active;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID", nullable = false))
    @Enumerated(value = EnumType.STRING)
    private Set<Role> roles;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ticket> tickets;


    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(LocalDate passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    public String getPassportIssueBy() {
        return passportIssueBy;
    }

    public void setPassportIssueBy(String passportIssueBy) {
        this.passportIssueBy = passportIssueBy;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return null;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return isActive();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (patronymic != null ? !patronymic.equals(user.patronymic) : user.patronymic != null) return false;
        if (passportSeries != null ? !passportSeries.equals(user.passportSeries) : user.passportSeries != null)
            return false;
        if (passportNumber != null ? !passportNumber.equals(user.passportNumber) : user.passportNumber != null)
            return false;
        if (passportIssueDate != null ? !passportIssueDate.equals(user.passportIssueDate) : user.passportIssueDate != null)
            return false;
        if (passportIssueBy != null ? !passportIssueBy.equals(user.passportIssueBy) : user.passportIssueBy != null)
            return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return !(roles != null ? !roles.equals(user.roles) : user.roles != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        return result;
    }

}
