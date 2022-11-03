package Main;

public class Person {
    private String name, giftCard;
    private boolean isFamily, attendedParty;
    private int money;

    public Person(String name, boolean isFamily, boolean attendedParty, int money) {
        this.name = name;
        this.isFamily = isFamily;
        this.attendedParty = attendedParty;
        this.money = money;
    }

    public Person(String name, boolean isFamily, boolean attendedParty, int money, String giftCard) {
        this.name = name;
        this.isFamily = isFamily;
        this.attendedParty = attendedParty;
        this.money = money;
        this.giftCard = giftCard;
    }

    public String getName() {
        return name;
    }

    public boolean isFamily() {
        return isFamily;
    }

    public boolean attendedParty() {
        return attendedParty;
    }

    public int getMoney() {
        return money;
    }

    public String getGiftCard() {
        return giftCard;
    }
}