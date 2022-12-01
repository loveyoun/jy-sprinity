package sprinity.jysprinity.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity //이 class가 database랑 연결되는 걸 알려줌. 자동으로 table 생성해줌. JPA library때문에 가능
public class User {

    //PK지정
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, length=20)
    private String userId;

    private String password;
    private String name;
    private String email;
    
    //아마 id getter setter은 없어도 될 듯
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {  //메소드 네임이랑 html의 name부분이 같아야지, html -> user.setUserId()
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void update(User newUser) {
        //???newUser.getUserId()를 안했는데, 필드값을 가져올 수 있네????
        this.userId=newUser.userId;
        this.password=newUser.password;
        this.name=newUser.name;
        this.email=newUser.email;
    }

}
