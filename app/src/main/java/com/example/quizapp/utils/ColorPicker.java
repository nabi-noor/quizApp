package com.example.quizapp.utils;

import java.util.ArrayList;
import java.util.List;

public class ColorPicker {
    List<String> colors = new ArrayList<>();
    int colorIndex;

    public ColorPicker(){
        colors.add("#3EB9DF");
        colors.add("#3685BC");
        colors.add("#D36280");
        colors.add("#E44F55");
        colors.add("#FA8056");
        colors.add("#818BCA");
        colors.add("#6D659F");
        colors.add("#51BAB3");
        colors.add("#4FB66C");
        colors.add("#E3AD17");
        colors.add("#627991");
        colors.add("#EF8EAD");
        colors.add("#B5BFC6");
        colorIndex = 0;
    }

    public String colorPicker(){
        colorIndex = (colorIndex + 1) % colors.size();
        return colors.get(colorIndex);
    }
}
