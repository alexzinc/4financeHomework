package user;

/**
 * Created by aleksandrs on 09/09/2017.
 */
public enum TreatmentType {

    MR("Mr"), MRS("Mrs"), MISS("Miss"), MS("Ms");

    private String title;

    TreatmentType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}

