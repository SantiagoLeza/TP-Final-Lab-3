package App;

public class AppDate
{
    private int day;
    private int month;
    private int year;

    public AppDate(int day, int month, int year)
    {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    public AppDate(String date)
    {
        String[] dateSplit = date.split("/");
        this.day = Integer.parseInt(dateSplit[0]);
        this.month = Integer.parseInt(dateSplit[1]);
        this.year = Integer.parseInt(dateSplit[2]);
    }

    public int getDay()
    {
        return day;
    }

    public void setDay(int day)
    {
        this.day = day;
    }

    public int getMonth()
    {
        return month;
    }

    public void setMonth(int month)
    {
        this.month = month;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public String toString()
    {
        return day + "/" + month + "/" + year;
    }
}
