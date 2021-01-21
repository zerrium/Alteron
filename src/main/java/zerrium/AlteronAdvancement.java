package zerrium;

import java.util.ArrayList;
import java.util.Date;

public class AlteronAdvancement {
    private final String advancement;
    private final ArrayList<String> criteria;
    private Date awarded_date;

    public AlteronAdvancement(String advancement, ArrayList<String> criteria){
        this.advancement = advancement;
        this.criteria = criteria;
    }

    public AlteronAdvancement(String advancement, ArrayList<String> criteria, Date awarded_date){
        this.advancement = advancement;
        this.criteria = criteria;
        this.awarded_date = awarded_date;
    }

    public String getAdvancement() {
        return advancement;
    }

    public ArrayList<String> getCriteria() {
        return criteria;
    }

    public Date getAwarded_date() {
        return awarded_date;
    }
}
