package zerrium;

import java.util.ArrayList;

public class AlteronCategory {
    private final String category;
    private final ArrayList<AlteronAdvancement> advancements;

    public AlteronCategory(String category, ArrayList<AlteronAdvancement> advancements){
        this.category = category;
        this.advancements = advancements;
    }

    public AlteronCategory(String category){
        this.category = category;
        this.advancements = null;
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<AlteronAdvancement> getAdvancements() {
        return advancements;
    }

    /*
    public void addAdvancements(AlteronAdvancement ad) {
        assert this.advancements != null;
        this.advancements.add(ad);
    }
    */

    @Override
    public boolean equals (Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            //if(Zstats.debug) System.out.println("Comparing instance of itself");
            return true;
        }

        /* Check if o is an instance of ZPlayer or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof AlteronCategory)) {
            //if(Zstats.debug) System.out.println("Not a ZPlayer instance");
            return false;
        }

        // Compare the data members and return accordingly
        boolean result = ((AlteronCategory) o).category.equals(category) || category.equals(((AlteronCategory) o).category);
        //if(Zstats.debug) System.out.println("ZPlayer instance, equal? "+result);
        return result;
    }

    @Override
    public int hashCode() {
        return category.hashCode();
    }
}
