package sample;
public class Human {
    private String count;
    private String password;
    private String name;
    private String sex;
    private String money="0";
    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public String getCount() {
        return this.count;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {

    }
}
