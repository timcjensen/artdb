package db;

public class Exhibitions {
    private int Exhibition_id;
    private String Exhibition_title;
    private String Exhibition_description;
    private String Begin;
    private String End;
    private String Display_date;
    private int Department_id;

    public Exhibitions(int exhibition_id, String exhibition_title, String exhibition_description, String begin, String end, String display_date, int department_id) {
        Exhibition_id = exhibition_id;
        Exhibition_title = exhibition_title;
        Exhibition_description = exhibition_description;
        Begin = begin;
        End = end;
        Display_date = display_date;
        Department_id = department_id;
    }

    public Exhibitions() {
    }

    public int getExhibition_id() {
        return Exhibition_id;
    }

    public void setExhibition_id(int exhibition_id) {
        Exhibition_id = exhibition_id;
    }

    public String getExhibition_title() {
        return Exhibition_title;
    }

    public void setExhibition_title(String exhibition_title) {
        Exhibition_title = exhibition_title;
    }

    public String getExhibition_description() {
        return Exhibition_description;
    }

    public void setExhibition_description(String exhibition_description) {
        Exhibition_description = exhibition_description;
    }

    public String getBegin() {
        return Begin;
    }

    public void setBegin(String begin) {
        Begin = begin;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End = end;
    }

    public String getDisplay_date() {
        return Display_date;
    }

    public void setDisplay_date(String display_date) {
        Display_date = display_date;
    }

    public int getDepartment_id() {
        return Department_id;
    }

    public void setDepartment_id(int department_id) {
        Department_id = department_id;
    }
}
