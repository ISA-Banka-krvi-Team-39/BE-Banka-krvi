package app.user.model;

import app.person.model.Person;
import app.user.dto.CreateAdminUserDTO;
import app.user.dto.CreateUserDTO;
import app.user.dto.UpdateUserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "userTable")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name="email", unique=true, nullable=false)
    private String email;
    @Column(name="password", unique=false, nullable=false)
    private String password;

    @Column(name="activation_code", unique=false, nullable=false)
    private String activationCode;
    @OneToOne
    @JoinColumn(name = "personId")
    private Person person;
    @Column(name = "enabled")
    private boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"))
    private List<Role> roles;
    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;

    public User() {
    }
    public User(CreateUserDTO userDTO,Person person) {
        this.activationCode = RandomStringUtils.randomAlphanumeric(32);
        this.person = new Person();
        this.userId = 0;
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.person = person;
        this.enabled = false;
        this.lastPasswordResetDate = new Timestamp(new Date().getTime());
    }

    public User(CreateAdminUserDTO userDTO, Person person) {
        this.activationCode = RandomStringUtils.randomAlphanumeric(32);
        this.person = new Person();
        this.userId = 0;
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.person = person;
        this.enabled = false;
        this.lastPasswordResetDate = new Timestamp(new Date().getTime());
    }

    public User(int userId, String email, String password, Person person) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.person = person;
    }
    public void updateUser(UpdateUserDTO updateUserDTO){
        this.password = updateUserDTO.getPassword();
        this.getPerson().updatePerson(updateUserDTO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(person, user.person);
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getActivationCode() { return activationCode; }

    public void setActivationCode(String activationCode) { this.activationCode = activationCode; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getUserId() {
        return userId;
    }
    public List<Role> getRoles() {
        return roles;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void Disable() {
        enabled = false;
    }
    public void Enable() {
        enabled = true;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
    public Person getPerson() {
        return person;
    }
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
}
