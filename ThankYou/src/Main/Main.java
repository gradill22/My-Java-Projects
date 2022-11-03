package Main;

public class Main {
    private static final Person[] people = new Person[16];
    public static void main(String[] args) {
        parseData();
        for(Person p : people) {
            StringBuilder str = new StringBuilder();
            if(p.isFamily()) {
                str.append("The ").append(p.getName()).append(" family");
            } else {
                str.append("Dear ").append(p.getName());
            }
            str.append(",\n\nThank you for the thoughtful graduation wishes and ");
            if(p.attendedParty()) {
                str.append("for attending my graduation party, as well as ");
            }
            if(p.getMoney() > 0 && p.getGiftCard() == null) {
                str.append("the $").append(p.getMoney());
            } else if(p.getMoney() > 0 && p.getGiftCard() != null) {
                str.append("the $").append(p.getMoney()).append(" ").append(p.getGiftCard());
            } else if(p.getMoney() == 0 && p.getGiftCard() != null) {
                str.append("the ").append(p.getGiftCard());
            }
            str.append(". I truly do appreciate it. \n\nThank you a ton,\n\nGrady Dillon");
            System.out.println(str + "\n-------------");
        }
    }

    private static void parseData() {
        people[0] = new Person("Bundy", true, true, 50);
        people[1] = new Person("Silverstein", true, true, 100);
        people[2] = new Person("Nanny and Grand-daddy", false, false, 100);
        people[3] = new Person("Lesjak", true, true, 50);
        people[4] = new Person("Todd", false, false, 100);
        people[5] = new Person("Rudy", false, false, 100);
        people[6] = new Person("Brauchli", true, true, 50);
        people[7] = new Person("Sahlgren", true, true, 0, "sunglasses");
        people[8] = new Person("Godbout", true, false, 20);
        people[9] = new Person("Nolen", true, false, 50);
        people[10] = new Person("Farmor and Farfar", false, false, 100, "Amazon gift card");
        people[11] = new Person("Derek and Amy", false, false, 50);
        people[12] = new Person("Papa Tom and Grandma Laura", false, false, 100);
        people[13] = new Person("Randall and Kathy", false, false, 50);
        people[14] = new Person("Aunt Pat and Ronnie", false, false, 0, "gift card");
        people[15] = new Person("Crystal", false, false, 50);
    }
}