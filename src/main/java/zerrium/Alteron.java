package zerrium;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.advancement.Advancement;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Alteron extends JavaPlugin {
    private JSONObject lang;

    private static ArrayList<AlteronCategory> categories;

    @Override
    public void onEnable() {
        System.out.println(ChatColor.YELLOW+"[Alteron] v0.1 by zerrium");
        try {
            lang = (JSONObject) new JSONParser().parse(new FileReader(new File (getDataFolder(), "lang.json")));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        //Objects.requireNonNull(this.getCommand("zstats")).setExecutor(new ZstatsUpdater());
        //Objects.requireNonNull(getCommand("zstats")).setTabCompleter(this);

        //Bukkit.getPluginManager().disablePlugin(this);

        getServer().getScheduler().scheduleSyncDelayedTask(this, this::getAllAdvancementList); //Wait for all plugins to load
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.YELLOW+"[Alteron] Disabling plugin...");
    }

    private void getAllAdvancementList(){
        BukkitRunnable r = new BukkitRunnable() {
            @Override
            public void run() {
                System.out.println(ChatColor.YELLOW+"[Alteron] Getting all advancement lists...");
                categories = new ArrayList<>();

                ArrayList<String> category = new ArrayList<>();

                loopingAllAdvancement(category, "AddCategory");
                for(String s:category){
                    categories.add(new AlteronCategory(s, new ArrayList<>()));
                }
                loopingAllAdvancement(category, "AddAdvancement");

                printAllAdvacementLists();
                System.out.println(ChatColor.YELLOW+"[Alteron] Done.");
            }
        };
        r.runTaskAsynchronously(this);
    }

    private void loopingAllAdvancement(ArrayList<String> category, String stage){
        for (Iterator<Advancement> it = Bukkit.advancementIterator(); it.hasNext();) {
            Advancement a = it.next();
            String key = a.getKey().toString();
            if(key.contains(":recipes")) continue;
            if(key.endsWith("/root")){
                if(stage.equals("AddCategory")) category.add(key.replace("/root", ""));
                continue;
            }else if(stage.equals("AddCategory")) continue;
            for (String s : category) {
                if (key.contains(s)) {
                    categories.get(categories.indexOf(new AlteronCategory(s))).getAdvancements().add(new AlteronAdvancement(key.replace(s+"/", ""), new ArrayList<>(a.getCriteria())));
                }
            }
        }
    }

    private void printAllAdvacementLists(){
        for(AlteronCategory ac:categories){
            System.out.println("Category: " + ac.getCategory());
            System.out.println("Advancements:" + "\n");
            for(AlteronAdvancement aa:ac.getAdvancements()){
                String temp = lang.get(ac.getCategory()+"."+aa.getAdvancement()+".title").toString();
                System.out.println("   " + (temp==null ? aa.getAdvancement() : temp));
                System.out.println("   Criteria:");
                for(String s:aa.getCriteria()){
                    System.out.println("      " + s);
                }
                System.out.println();
            }
            System.out.println("\n");
        }
    }
}
