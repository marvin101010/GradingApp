package at.ac.univie.gradingapp.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import at.ac.univie.gradingapp.MainActivity;

/**
 * Created by Thomas on 01.12.2015.
 * Klasse für Leistungen
 */
@Table(name = "Achievement")
public class Achievement {
    private static final String TAG = "Achievement";
    @Column(name = "name")
    private String name;
    @Column(name = "weighting")
    private double weighting;
    @Column(name = "value") //Punkte/Note
    private int value;
    @Column(name = "type")
    private AchievementType type; // schularbeit, mitarbeit (=hausübung, referat, mitarbeit, usw - siehe Enum AchivementType)
    @Column(name = "maxValue")
    private int maxValue; //maximale Punkteanzahl

    public Achievement () { super(); }

    public Achievement (String name, float weighting, int value, AchievementType type, int maxValue) {
        this.name = name;
        this.weighting = weighting;
        this.value = value;
        this.type = type;
        this.maxValue = maxValue;
    };

    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }

    public void setWeighting(double weighting) { this.weighting = weighting; }
    public double getWeighting() { return this.weighting; }

    public void setType(AchievementType type) { this.type = type; }
    public AchievementType getType() { return this.type; }

    public void setValue(int value) { this.value = value; }
    public int getValue() { return this.value; }

    public void setMaxValue(int value) { this.maxValue = maxValue; }
    public int getMaxValue() { return this.maxValue; }
}
