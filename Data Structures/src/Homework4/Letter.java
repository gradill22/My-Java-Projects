package Homework4;

/*
Provide a class for authoring a sample letter. In the constructor, supply the names of the sender and the recipient:
    public Letter(String from, String to)
Supply a method
    public void addLine(String line)
to add a line of text to the body of the letter
Supply a method
    public String getText()
that returns the entire text of the letter
The text has the form:
Dear *recipient name*:
*blank line*
*first line of the body*
*second line of the body*
...
*last line of the body*
*blank line*
Sincerely,
*blank line*
*sender name*
*/

public class Letter {
    private String to, from;
    private String[] lines;

    public Letter(String from, String to) {
        this.from = from;
        this.to = to;
        this.lines = new String[0];  // no lines in the letter at first
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void addLine(String line) {
        String[] newLines = new String[lines.length + 1];  // create new list to have all current lines plus one new one
        for(int i = 0; i < lines.length; i++) {
            newLines[i] = lines[i];  // add all the current lines to the letter
        }
        newLines[newLines.length - 1] = line;  // add the new line at the end
        lines = newLines;  // set the current lines to include the new one
    }

    public String getText() {
        String letter = "Dear " + to + ":\n\n";  // initialize the letter with its start
        for(String line: lines) {
            letter += line + "\n";  // add all the lines to the letter
        }
        letter += "\nSincerely,\n\n" + from;  // finish the letter with its end
        return letter;
    }
}