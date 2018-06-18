package net.azurewebsites.luiscontrerasdev.edadcalculator;
import java.util.Calendar;

public class Calculo {
    public Calendar calendar;
    public int userYear;
    public int userDay;
    public int userMoth;
    public int userAge;

    public Calculo(){
        calendar = Calendar.getInstance();
        userYear = 1999;
        userDay = 3;
        userMoth = 5;
    }

    public int getUserYear() {
        return userYear;
    }

    public void setUserYear(int userYear) {
        this.userYear = userYear;
    }

    public int getUserDay() {
        return userDay;
    }

    public void setUserDay(int userDay) {
        this.userDay = userDay;
    }

    public int getUserMoth() {
        return userMoth;
    }

    public void setUserMoth(int userMoth) {
        this.userMoth = userMoth;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public int Calculo(){

        int actYear = calendar.get(Calendar.YEAR);
        int actDay = calendar.get(Calendar.DAY_OF_MONTH);
        int actMoth = calendar.get(Calendar.MONTH) + 1;

        if((userMoth < actMoth) || (userMoth == actMoth && userDay <= actDay))  {

            userAge = (actYear - userYear);
            return userAge;

        }else{

            userAge = (actYear - userYear - 1);
            return userAge;
        }
    }
}
