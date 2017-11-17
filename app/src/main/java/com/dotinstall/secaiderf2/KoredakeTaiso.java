package com.dotinstall.secaiderf2;

/**
 * Created by gosho on 2017/11/10.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KoredakeTaiso {

    @SerializedName("exercise_times")
    @Expose
    private Integer exerciseTimes;
    @SerializedName("exercise_date")
    @Expose
    private String exerciseDate;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("user")
    @Expose
    private String user;

    public Integer getExerciseTimes() {
        return exerciseTimes;
    }

    public void setExerciseTimes(Integer exerciseTimes) {
        this.exerciseTimes = exerciseTimes;
    }

    public String getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(String exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
