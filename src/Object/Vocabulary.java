package Object;

import java.time.LocalDate;

public class Vocabulary {
    int index;
    String word;
    String type;
    int timeLearn;
    String dayLearn;
    String sector;

    public Vocabulary(String word, String type) {
        this.word = word;
        this.type = type;
        this.timeLearn = 0;
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        int day = currentDate.getDayOfMonth();
        dayLearn =month+"/"+day+"/"+year ;
    }

    public int getIndex() {
        return index;
    }

    public String getWord() {
        return word;
    }

    public String getType() {
        return type;
    }

    public int getTimeLearn() {
        return timeLearn;
    }

    public String getDayLearn() {
        return dayLearn;
    }

    public String getSector() {
        return sector;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTimeLearn(int timeLearn) {
        this.timeLearn = timeLearn;
    }

    public void setDayLearn(String dayLearn) {
        this.dayLearn = dayLearn;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
